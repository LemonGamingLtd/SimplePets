package simplepets.brainsynder.addon;

import org.bsdevelopment.pluginutils.files.YamlFile;

import java.io.File;
import java.util.List;

public abstract class AddonConfig extends YamlFile {
    public AddonConfig(File folder, String fileName) {
        super(folder, fileName);
    }

    public void addDefault(String key, Object value) {
        super.addDefault(key, value);
    }

    public void addDefault(String key, Object value, String comment) {
        super.addDefault(key, value, comment);
    }

    public void set(String tag, Object data) {
        super.set(tag, data);
    }

    public void set(String tag, Object data, boolean save) {
        super.set(tag, data, save);
    }

    public void remove(String key) {
        super.remove(key);
    }

    public boolean contains(String tag) {
        return super.contains(tag);
    }

    public String getString(String tag) {
        return super.getString(tag);
    }

    public String getString(String tag, String fallback) {
        return super.getString(tag, fallback);
    }

    public List<String> getStringList(String tag) {
        return super.getStringList(tag);
    }

    public int getInt(String tag) {
        return super.getInt(tag);
    }

    public int getInt(String tag, int fallback) {
        return super.getInt(tag, fallback);
    }

    public double getDouble(String tag) {
        return super.getDouble(tag);
    }

    public double getDouble(String tag, double fallback) {
        return super.getDouble(tag, fallback);
    }

    public boolean getBoolean(String tag) {
        return super.getBoolean(tag);
    }

    public boolean getBoolean(String tag, boolean fallback) {
        return super.getBoolean(tag, fallback);
    }

    public void save() {
        super.save();
    }
}
