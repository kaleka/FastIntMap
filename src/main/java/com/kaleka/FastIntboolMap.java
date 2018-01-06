package com.kaleka;

public final class FastIntboolMap {
	boolean[] data = null;

	public FastIntboolMap(int size) {
		data = new boolean[size];
	}

	public void put(int key, boolean value) {
		int dataLength=data.length;
		if (key > data.length - 1) {
            boolean dataNew[] = null;
            System.arraycopy(data, 0,
                    (dataNew = new boolean[key << 1]), 0,
                    dataLength);
            data = dataNew;
		}
		data[key] = value;
	}

	public boolean get(int key) {
		if (key > data.length - 1)
			return false;
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
			a.put(i, 1);
		for (int i = 0; i < 10000000; ++i)
			a.get(i);
		System.out.println(System.currentTimeMillis() - start);
	}	
	
	public static void testFastintbyteMap() {
		long start = System.currentTimeMillis();
		FastintbyteMap a = new FastintbyteMap(16);
		for (int i = 0; i < 10000000; ++i)
			a.put(i, (byte)1);
		for (int i = 0; i < 10000000; ++i)
			a.get(i);
		System.out.println(System.currentTimeMillis() - start);
	}	

	public static void testFastIntboolMap() {
		long start = System.currentTimeMillis();
		FastIntboolMap a = new FastIntboolMap(16);
		for (int i = 0; i < 10000000; ++i)
			a.put(i, true);
		for (int i = 0; i < 10000000; ++i)
			a.get(i);
		System.out.println(System.currentTimeMillis() - start);
	}	
	
	
	
	public static void main(String[] args) {
		//testFastIntMap();
		testFastintbyteMap();
		//testFastintintMap();
		//testFastIntboolMap();
		//FastIntboolMap a = new FastIntboolMap(16);
		//System.out.println(a.get(1));
		System.out.println(Runtime.getRuntime().totalMemory());
		 System.out.println(Runtime.getRuntime().freeMemory());
	}	

}

