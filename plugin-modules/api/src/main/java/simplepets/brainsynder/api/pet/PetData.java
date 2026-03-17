package simplepets.brainsynder.api.pet;

import lib.brainsynder.item.ItemBuilder;
import lib.brainsynder.json.JsonObject;
import lib.brainsynder.json.JsonValue;
import org.bsdevelopment.pluginutils.version.ServerVersion;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.debug.DebugLevel;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Defines how a single pet property is displayed and modified in-game.
 *
 * <h3>Option 1 — inline builder (no class file required)</h3>
 * <b>Two-option toggle (same action on left- and right-click):</b>
 * <pre>{@code
 * PetData.of("sitting", ISitting.class)
 *     .defaultValue(false)
 *     .item(true,  new ItemBuilder(Material.OAK_STAIRS).withName("Sitting: on"))
 *     .item(false, new ItemBuilder(Material.OAK_STAIRS).withName("Sitting: off"))
 *     .onToggle(e -> e.setSitting(!e.isSitting()))
 *     .value(e -> e.isSitting())
 *     .build();
 * }</pre>
 *
 * <b>Multi-option enum cycle (left = next, right = previous):</b>
 * <pre>{@code
 * PetData.of("cat_type", IEntityCatPet.class)
 *     .defaultValue(Cat.Type.TABBY)
 *     .items(Cat.Type.values(), t -> new ItemBuilder(Material.CAT_SPAWN_EGG).withName(t.name()))
 *     .onLeftClick(e  -> e.setCatType(PetData.cycleForward(e.getCatType(),  Cat.Type.values())))
 *     .onRightClick(e -> e.setCatType(PetData.cycleBackward(e.getCatType(), Cat.Type.values())))
 *     .value(e -> e.getCatType())
 *     .build();
 * }</pre>
 *
 * <b>Multi-option list cycle:</b>
 * <pre>{@code
 * PetData.of("size", ISizable.class)
 *     .defaultValue(1)
 *     .items(List.of(1, 2, 3, 4), v -> new ItemBuilder(Material.PLAYER_HEAD).withName("Size: " + v))
 *     .onLeftClick(e  -> e.setSize(PetData.cycleForward(e.getSize(),  List.of(1, 2, 3, 4))))
 *     .onRightClick(e -> e.setSize(PetData.cycleBackward(e.getSize(), List.of(1, 2, 3, 4))))
 *     .value(e -> e.getSize())
 *     .build();
 * }</pre>
 *
 * <h3>Option 2 — subclass (for complex or stateful behavior)</h3>
 * <pre>{@code
 * public class SizeData extends PetData<ISizable> {
 *     public SizeData() {
 *         addDefaultItem(1, size1Item);
 *         addDefaultItem(2, size2Item);
 *     }
 *
 *     @Override public String  namespace()             { return "size"; }
 *     @Override public Object  defaultValue()          { return 1; }
 *     @Override public Object  value(ISizable e)       { return e.getSize(); }
 *     @Override public void    onLeftClick(ISizable e) { e.setSize(nextSize(e)); }
 *     @Override public void    onRightClick(ISizable e){ e.setSize(prevSize(e)); }
 * }
 * }</pre>
 *
 * @param <E> The entity type this data targets
 */
public abstract class PetData<E extends IEntityPet> {
    private final Map<String, ItemBuilder> defaultItems = new LinkedHashMap<>();
    private ServerVersion minVersion = null;
    private ServerVersion maxVersion = null;

    /**
     * Returns a filtered list from {@code values}, excluding any entry for which
     * {@code exclude} returns {@code true}.
     *
     * <pre>{@code
     * List<Cat.Type> types = PetData.filterValues(Cat.Type.values(), t -> t == Cat.Type.ALL_BLACK);
     * }</pre>
     */
    public static <V> List<V> filterValues(V[] values, Predicate<V> exclude) {
        List<V> result = new ArrayList<>();
        for (V value : values) {
            if (!exclude.test(value)) result.add(value);
        }
        return result;
    }

    /**
     * Returns the value that comes <em>after</em> {@code current} in {@code values},
     * wrapping around to the first element when the end is reached.
     *
     * <pre>{@code
     * e.setCatType(PetData.cycleForward(e.getCatType(), Cat.Type.values()));
     * }</pre>
     */
    public static <V> V cycleForward(V current, V[] values) {
        if (values.length == 0) return current;
        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(values[i], current)) return values[(i + 1) % values.length];
        }
        return values[0];
    }

    /**
     * Returns the value that comes <em>after</em> {@code current} in {@code values},
     * wrapping around to the first element when the end is reached.
     */
    public static <V> V cycleForward(V current, List<V> values) {
        if (values.isEmpty()) return current;
        int i = values.indexOf(current);
        return values.get(i < 0 ? 0 : (i + 1) % values.size());
    }

    /**
     * Returns the value that comes <em>before</em> {@code current} in {@code values},
     * wrapping around to the last element when the start is reached.
     *
     * <pre>{@code
     * e.setCatType(PetData.cycleBackward(e.getCatType(), Cat.Type.values()));
     * }</pre>
     */
    public static <V> V cycleBackward(V current, V[] values) {
        if (values.length == 0) return current;
        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(values[i], current)) return values[(i - 1 + values.length) % values.length];
        }
        return values[values.length - 1];
    }

    /**
     * Returns the value that comes <em>before</em> {@code current} in {@code values},
     * wrapping around to the last element when the start is reached.
     */
    public static <V> V cycleBackward(V current, List<V> values) {
        if (values.isEmpty()) return current;
        int i = values.indexOf(current);
        return values.get(i <= 0 ? values.size() - 1 : i - 1);
    }

    /**
     * Starts a new builder for an inline {@link PetData} definition.
     *
     * @param namespace  Config-file key (e.g. {@code "sitting"})
     * @param entityType Used only for generic type inference — not stored at runtime
     */
    public static <E extends IEntityPet> Builder<E> of(String namespace, Class<E> entityType) {
        return new Builder<>(namespace);
    }

    /**
     * Starts a new builder for an inline {@link PetData} definition.
     * Use {@link #of(String, Class)} when the entity type cannot be inferred from context.
     */
    public static <E extends IEntityPet> Builder<E> of(String namespace) {
        return new Builder<>(namespace);
    }

    /**
     * The config-file key for this data entry (e.g. {@code "sitting"}, {@code "size"}).
     * Builder-created instances return the namespace passed to {@link #of(String)}.
     * Subclasses must override this method.
     */
    public abstract String namespace();

    /**
     * The value written to the config "default" key when the section is first generated.
     * Return {@code null} to omit the key entirely.
     */
    public Object defaultValue() {
        return null;
    }

    /**
     * Returns the current value of this data for the given entity.
     * The returned value is converted to a String to look up the display item.
     */
    public abstract Object value(E entity);

    /**
     * Called when the player left-clicks the data item in the GUI.
     * For two-option toggles this is the only handler needed (use {@link Builder#onToggle}).
     * For multi-option cycling this should advance to the <em>next</em> value.
     */
    public abstract void onLeftClick(E entity);

    /**
     * Called when the player right-clicks the data item in the GUI.
     * Defaults to the same behavior as {@link #onLeftClick}.
     * Override for multi-option cycling to go to the <em>previous</em> value.
     */
    public void onRightClick(E entity) {
        onLeftClick(entity);
    }

    /**
     * Returns whether this data can currently be changed for the entity.
     * Returning {@code false} hides the item from the data GUI.
     */
    public boolean isModifiable(E entity) {
        return true;
    }

    /**
     * Returns whether this data should be enabled by default when the pet config is first generated.
     * Override and return {@code false} for data that should be opt-in (e.g. cosmetic-only toggles).
     * Replaces the {@code @DisableDefault} annotation.
     */
    public boolean isEnabledByDefault() {
        return true;
    }

    /**
     * Returns whether this data is supported on the current server version.
     *
     * <p>The check order is:
     * <ol>
     *   <li>Instance version fields set via {@link Builder#minVersion}/{@link Builder#maxVersion}
     *       or {@link #setMinVersion}/{@link #setMaxVersion} in a subclass constructor.</li>
     *   <li>If neither is set, the data is always considered supported.</li>
     * </ol>
     */
    public boolean isVersionSupported() {
        if (minVersion != null)
            return ServerVersion.getVersion().isEqualOrNewer(minVersion) && (maxVersion == null || ServerVersion.getVersion().isEqualOrOlder(maxVersion));
        return true;
    }

    /**
     * Sets the minimum server version required for this data to be active.
     * Subclasses can call this from their constructor instead of using {@link SupportedVersion}.
     */
    protected void setMinVersion(ServerVersion version) {
        this.minVersion = version;
    }

    /**
     * Sets the maximum server version on which this data is active (inclusive).
     * Use {@code null} to indicate no upper bound (the default).
     */
    protected void setMaxVersion(ServerVersion version) {
        this.maxVersion = version;
    }

    /**
     * Registers a display item for the given value key.
     * {@code valueKey} is converted to a String via {@link String#valueOf}.
     */
    public <V> void addDefaultItem(V valueKey, ItemBuilder builder) {
        defaultItems.put(String.valueOf(valueKey), builder);
    }

    public Map<String, ItemBuilder> getDefaultItems() {
        return defaultItems;
    }

    /**
     * Returns whether this data is enabled in the pet's config file.
     */
    public boolean isEnabled(E entity) {
        Optional<IPetConfig> configOptional = SimplePets.getPetConfigManager().getPetConfig(entity.getPetType());
        return configOptional.map(config -> config.getRawData(namespace()).getBoolean("enabled", true)).orElse(true);
    }

    /**
     * Returns the config-driven display item for the entity's current value,
     * falling back to the registered default item when the config entry is absent.
     */
    public Optional<ItemBuilder> getItem(E entity) {
        Optional<IPetConfig> configOptional = SimplePets.getPetConfigManager().getPetConfig(entity.getPetType());
        String key = String.valueOf(value(entity));

        if (configOptional.isEmpty()) {
            if (defaultItems.containsKey(key)) return Optional.of(defaultItems.get(key));
            SimplePets.getDebugLogger().debug(DebugLevel.ERROR, getClass().getSimpleName() + " had no default item for '" + key + "'");
            return Optional.empty();
        }

        IPetConfig config = configOptional.get();
        if (defaultItems.containsKey(key)) return config.getDataItem(namespace(), key, defaultItems.get(key));
        return config.getDataItem(namespace(), key);
    }

    /**
     * Reads the configured default value for {@code type} from the pet config file.
     */
    public Optional<Object> getDefault(PetType type) {
        Optional<IPetConfig> configOptional = SimplePets.getPetConfigManager().getPetConfig(type);
        if (configOptional.isPresent()) {
            JsonObject json = configOptional.get().getRawData(namespace());
            if (json.names().contains("default")) {
                JsonValue val = json.get("default");
                if (val.isBoolean()) return Optional.of(val.asBoolean());
                if (val.isNumber()) return Optional.of(val.asInt());
                if (val.isString()) return Optional.of(val.asString());
            }
        }
        return Optional.empty();
    }

    public static final class Builder<E extends IEntityPet> {
        private final String namespace;
        private final Map<String, ItemBuilder> items = new LinkedHashMap<>();
        private Object defaultValue = null;
        private boolean enabledByDefault = true;
        private ServerVersion minVersion = null;
        private ServerVersion maxVersion = null;
        private Function<E, Object> valueFunction;
        private Consumer<E> leftClick;
        private Consumer<E> rightClick;
        private Predicate<E> modifiable = e -> true;

        private Builder(String namespace) {
            this.namespace = Objects.requireNonNull(namespace, "namespace must not be null");
        }

        /**
         * Sets the value written to the config "default" key on first generation.
         */
        public Builder<E> defaultValue(Object value) {
            this.defaultValue = value;
            return this;
        }

        /**
         * Sets whether this data is enabled by default in the generated pet config.
         * Pass {@code false} for opt-in data that should be disabled until the server admin enables it.
         */
        public Builder<E> enabledByDefault(boolean enabled) {
            this.enabledByDefault = enabled;
            return this;
        }

        /**
         * Sets the minimum server version required for this data to be registered.
         * The data will be silently skipped on older servers.
         *
         * <pre>{@code .minVersion(ServerVersion.v1_21_5) }</pre>
         */
        public Builder<E> minVersion(ServerVersion version) {
            this.minVersion = version;
            return this;
        }

        /**
         * Sets the maximum server version on which this data is registered (inclusive).
         * The data will be silently skipped on newer servers.
         * Omit this call (or pass {@code null}) to impose no upper bound.
         *
         * <pre>{@code .maxVersion(ServerVersion.v1_21_4) }</pre>
         */
        public Builder<E> maxVersion(ServerVersion version) {
            this.maxVersion = version;
            return this;
        }

        /**
         * Registers a single display item for the given value.
         * {@code valueKey} is converted to a String via {@link String#valueOf}.
         */
        public <V> Builder<E> item(V valueKey, ItemBuilder item) {
            this.items.put(String.valueOf(valueKey), item);
            return this;
        }

        /**
         * Registers a display item for every entry in {@code values} using
         * {@code itemFactory} to build each one.
         * Works directly with {@code SomeEnum.values()} arrays.
         *
         * <pre>{@code
         * .items(Cat.Type.values(), t -> new ItemBuilder(Material.CAT_SPAWN_EGG).withName(t.name()))
         * }</pre>
         */
        public <V> Builder<E> items(V[] values, Function<V, ItemBuilder> itemFactory) {
            for (V value : values) this.items.put(String.valueOf(value), itemFactory.apply(value));
            return this;
        }

        /**
         * Registers a display item for every entry in {@code values} that does <em>not</em>
         * match {@code exclude}, using {@code itemFactory} to build each one.
         *
         * <pre>{@code
         * .items(HorseArmorType.values(), a -> a == HorseArmorType.NONE, a -> new ItemBuilder(...))
         * }</pre>
         */
        public <V> Builder<E> items(V[] values, Predicate<V> exclude, Function<V, ItemBuilder> itemFactory) {
            for (V value : values) {
                if (!exclude.test(value)) this.items.put(String.valueOf(value), itemFactory.apply(value));
            }
            return this;
        }

        /**
         * Registers a display item for every entry in {@code values} using
         * {@code itemFactory} to build each one.
         *
         * <pre>{@code
         * .items(List.of(1, 2, 3, 4), v -> new ItemBuilder(Material.PLAYER_HEAD).withName("Size: " + v))
         * }</pre>
         */
        public <V> Builder<E> items(List<V> values, Function<V, ItemBuilder> itemFactory) {
            for (V value : values) this.items.put(String.valueOf(value), itemFactory.apply(value));
            return this;
        }

        /**
         * Sets the <em>same</em> handler for both left- and right-click.
         * Use this for simple two-option toggles (e.g. sitting on/off).
         */
        public Builder<E> onToggle(Consumer<E> handler) {
            this.leftClick = handler;
            this.rightClick = handler;
            return this;
        }

        /**
         * Sets the left-click handler.
         * For multi-option cycling, this should advance to the <em>next</em> value.
         * Pair with {@link #onRightClick} to cycle backwards on right-click.
         */
        public Builder<E> onLeftClick(Consumer<E> handler) {
            this.leftClick = handler;
            return this;
        }

        /**
         * Sets the right-click handler.
         * For multi-option cycling, this should go to the <em>previous</em> value.
         * If not set, defaults to the left-click handler.
         */
        public Builder<E> onRightClick(Consumer<E> handler) {
            this.rightClick = handler;
            return this;
        }

        /**
         * Provides a function that reads the current value from the entity.
         * The value is converted to a String to look up the correct display item.
         *
         * <pre>{@code .value(e -> e.isSitting()) }</pre>
         */
        public Builder<E> value(Function<E, Object> supplier) {
            this.valueFunction = supplier;
            return this;
        }

        /**
         * Provides a predicate that controls whether this data can be changed.
         * When it returns {@code false} the item is hidden from the GUI.
         */
        public Builder<E> isModifiable(Predicate<E> check) {
            this.modifiable = check;
            return this;
        }

        /**
         * Builds and returns the configured {@link PetData} instance.
         *
         * @throws NullPointerException if {@link #value} or a click handler was not provided
         */
        public PetData<E> build() {
            PetData<E> instance = new PetData<>() {
                {
                    items.forEach(this::addDefaultItem);
                }

                @Override
                public String namespace() {
                    return namespace;
                }

                @Override
                public Object defaultValue() {
                    return defaultValue;
                }

                @Override
                public boolean isEnabledByDefault() {
                    return enabledByDefault;
                }

                @Override
                public Object value(E entity) {
                    return valueFunction.apply(entity);
                }

                @Override
                public void onLeftClick(E entity) {
                    leftClick.accept(entity);
                }

                @Override
                public void onRightClick(E entity) {
                    if (rightClick != null) {
                        rightClick.accept(entity);
                        return;
                    }
                    leftClick.accept(entity);
                }

                @Override
                public boolean isModifiable(E entity) {
                    return modifiable.test(entity);
                }
            };
            instance.setMinVersion(minVersion);
            instance.setMaxVersion(maxVersion);
            return instance;
        }
    }
}
