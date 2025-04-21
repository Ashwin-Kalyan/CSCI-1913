public class Map<Key, Value> {
    private Key[] keys;
    private Value[] values;
    private int count;

    /**
     * Default constructor that initializes the Map with a default size of 1.
     * @param size - the initial size of the Map. 
     */
    public Map(int size) {
        if (size <= 0) {
            size = 1;
        }

        keys = (Key[]) new Object[size];
        values = (Value[]) new Object[size];
        count = 0;
    }

    /**
     * Checks if two keys are equal. 
     * @param leftKey - the first key to be compared.
     * @param rightKey - the second key to be compared.
     * @return - true if the keys are equal, false otherwise.
     */
    private boolean isEqual(Key leftKey, Key rightKey) {
        if (leftKey == null && rightKey == null) return true;
        else if (leftKey == null || rightKey == null) return false; 
        return leftKey.equals(rightKey);
    }

    /**
     * Returns the index of an element in the Map.
     * @param key - the key to be searched.
     * @return - the index of the key in the Map, or -1 if the key does not exist.
     */
    private int getIndex(Key key) {
        for (int i = 0; i < count; i++) {
            if (isEqual(keys[i], key)) return i;
        }
        return -1; 
    }

    /**
     * Returns the value associated with the key.
     * @param key - the key to be searched.
     * @return - the value associated with the key, or null if the key does not exist.
     */
    public Value get(Key key) {
        int index = getIndex(key);
        return index == -1 ? null : values[index];
    }

    /**
     * Determines if the Map contains a key.
     * @param key - the key to be checked
     * @return - true if the key exists, false otherwise.
     */
    public boolean containsKey(Key key) {
        return getIndex(key) != -1; 
    }

    /**
     * Adds a key-value pair to the Map. If the key already exists, it updates the value/key.
     * @param key - the key to be added/updated.
     * @param value - the value to be added/updated.
     */
    public void put(Key key, Value value) {
        int index = getIndex(key);
        
        if (index != -1) {
            values[index] = value;
            return;
        }

        if (count == keys.length) {
            Key[] newKeys = (Key[]) new Object[keys.length * 2];
            Value[]newValues = (Value[]) new Object[values.length * 2];

            System.arraycopy(keys, 0, newKeys, 0, keys.length);
            System.arraycopy(values, 0, newValues, 0, values.length);

            keys = newKeys;
            values = newValues;
        }

        keys[count] = key;
        values[count] = value;
        count++;
    }

    /**
     * Returns the number of distinct keys in the Map.
     * @return - the number of distinct keys in the Map.
     */
    public int size() {
        return count;
    }
} 
