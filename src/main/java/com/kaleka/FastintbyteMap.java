package com.kaleka;

public final class FastintbyteMap {
	byte[] data = null;

	public FastintbyteMap(int size) {
		data = new byte[size];
	}

	public void put(int key, byte value) {
		int dataLength=data.length;
		if (key > data.length - 1) {
            byte dataNew[] = null;
            System.arraycopy(data, 0,
                    (dataNew = new byte[key << 1]), 0,
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
	
	public static void main(String[] args) {
		//testFastIntMap();
		testFastintbyteMap();
		//testFastintintMap();
		 System.out.println(Runtime.getRuntime().totalMemory());
		 System.out.println(Runtime.getRuntime().freeMemory());
	}	

}
