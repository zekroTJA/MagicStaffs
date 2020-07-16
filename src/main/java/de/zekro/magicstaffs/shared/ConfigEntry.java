package de.zekro.magicstaffs.shared;

import javax.annotation.Nullable;

/**
 * Container for a Configuration entry.
 * @param <T> Value Type of the entry
 */
public class ConfigEntry<T> {

    private final String key, comment;
    private final T def, max, min;
    private T collected;

    /**
     * Creates a new instance of ConfigEntry.
     * @param key key name of the entry
     * @param defaultValue default value
     * @param comment entry comment
     */
    public ConfigEntry(String key, T defaultValue, String comment) {
        this(key, defaultValue, null, null, comment);
    }

    /**
     * Creates a new instance of ConfigEntry.
     * @param key key name of the entry
     * @param defaultValue default value
     * @param minValue minimum value
     * @param maxValue maximum value
     * @param comment entry comment
     */
    public ConfigEntry(String key, T defaultValue, @Nullable T minValue, @Nullable T maxValue, String comment) {
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
