package com.kaleka;

/**
 *
 * @author kaleka
 *
 * @param <T>
 */
public final class FastIntMap<T> {
    private T data[] = null;


    public FastIntMap(int size) {
        data = (T[]) new Object[size];
    }


    public T get(int key) {
        if (key > data.length - 1)
            return null;
        else
            return data[key];
    }


    public void put(int key, T value) {
        int dataLength = data.length;
        if (key > dataLength - 1) {
            T dataNew[] = null;
            System.arraycopy(data, 0,
                    (dataNew = (T[]) new Object[key << 1]), 0,
                    dataLength);
            data = dataNew;
        }
        data[key] = value;
    }
    /**是否包含Integer key
     * @param key integer key
     * @return 是否
     */
    public boolean containsKey(int key) {
        if (key > data.length - 1)
            return false;
        else
            return data[key] != null;
    }

}