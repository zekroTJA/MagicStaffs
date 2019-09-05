package de.zekro.magicstaffs.util;

/**
 * Container for a Configuration entry.
 * @param <T> Value Type of the entry
 */
public class ConfigEntry<T> {

    private String key, comment;
    private T def, max, min, collected;

    /**
     * Create a new instance of ConfigEntry.
     * @param key key name of the entry
     * @param defaultValue default value
     * @param minValue minimum value
     * @param maxValue maximum value
     * @param comment Entry comment
     */
    public ConfigEntry(String key, T defaultValue, T minValue, T maxValue, String comment) {
        this.key = key;
        this.def = defaultValue;
        this.min = minValue;
        this.max = maxValue;
        this.comment = comment;
    }

    /**
     * Returns the key name.
     * @return key name
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the default value.
     * @return default value
     */
    public T getDef() {
        return def;
    }

    /**
     * Returns the minimum value.
     * @return minimum value
     */
    public T getMin() {
        return min;
    }

    /**
     * Returns the maximum value
     * @return maximum value
     */
    public T getMax() {
        return max;
    }

    /**
     * Get set comment.
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set collected value.
     * @param collected value
     */
    public void setCollected(T collected) {
        this.collected = collected;
    }

    /**
     * Returns the set collected value.
     * @return collected value
     */
    public T getCollected() {
        return collected != null ? collected : def;
    }
}
