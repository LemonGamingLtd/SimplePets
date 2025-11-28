/*
 * Copyright © 2025
 * BSDevelopment <https://bsdevelopment.org>
 */

package simplepets.brainsynder.api.plugin.utils;

import org.bsdevelopment.pluginutils.version.VersionCompatibility;

import java.util.function.Predicate;

/**
 * Utility for cycling through enum constants with optional compatibility checks.
 */
public final class EnumCycle {
    /**
     * Returns the next enum constant after {@code current}, wrapping around,
     * and skipping any constants that do not pass {@code validator}.
     *
     * @param current   the current enum value
     * @param values    the full enum values array (usually {@code E.values()})
     * @param validator predicate to decide if a value is allowed (e.g. version support)
     * @param fallback  returned if no compatible value is found
     */
    public static <E extends Enum<E>> E next(E current, E[] values, Predicate<E> validator, E fallback) {
        return traverse(current, 1, values, validator, fallback);
    }

    /**
     * Returns the previous enum constant before {@code current}, wrapping around,
     * and skipping any constants that do not pass {@code validator}.
     *
     * @param current   the current enum value
     * @param values    the full enum values array (usually {@code E.values()})
     * @param validator predicate to decide if a value is allowed (e.g. version support)
     * @param fallback  returned if no compatible value is found
     */
    public static <E extends Enum<E>> E previous(E current, E[] values, Predicate<E> validator, E fallback) {
        return traverse(current, -1, values, validator, fallback);
    }

    private static <E extends Enum<E>> E traverse(E current, int step, E[] values, Predicate<E> validator, E fallback) {
        int length = values.length;
        int start = current.ordinal();

        for (int offset = 1; offset <= length; offset++) {
            int index = Math.floorMod(start + step * offset, length);
            E candidate = values[index];
            // validator == null || validator.test(candidate)
            if (VersionCompatibility.getIfCompatible(candidate).isPresent()) {
                return candidate;
            }
        }
        return fallback;
    }
}
