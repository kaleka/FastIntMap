package com.kaleka;

public final class FastintintMap {
	private int[] data = null;

	public FastintintMap(int size) {
		data = new int[size];
	}

	public void put(int key, int value) {
		int dataLength=data.length;
		if (key > data.length - 1) {
            int dataNew[] = null;
            System.arraycopy(data, 0,
                    (dataNew = new int[key << 1]), 0,
                    dataLength);
            data = dataNew;
		}
		data[key] = value;
	}

	public int get(int key) {
		if (key > data.length - 1)
			return -1;
		else
			return data[key];
	}
	

	public static void testFastIntMap() {
		long start = System.currentTimeMillis();
		FastIntMap<Integer> a = new FastIntMap<Integer>(16);
		for (int i = 0; i < 10000000; ++i)
			a.put(i, i);
		for (int i = 0; i < 10000000; ++i)
			a.get(i);
		System.out.println(System.currentTimeMillis() - start);
	}
	
	public static void testFastintintMap() {
		long start = System.currentTimeMillis();
		FastintintMap a = new FastintintMap(16);
		for (int i = 0; i < 10000000; ++i)
			a.put(i, i);
		for (int i = 0; i < 10000000; ++i)
			a.get(i);
		System.out.println(System.currentTimeMillis() - start);
	}	
	
	public static void main(String[] args) {
		testFastIntMap();
		//testFastintintMap();
		 System.out.println(Runtime.getRuntime().totalMemory());
		 System.out.println(Runtime.getRuntime().freeMemory());
	}	

}
