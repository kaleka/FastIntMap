package com.kaleka;

import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FastSkipListMap<T> {

	private static Lock lock = new ReentrantLock();

	private T data[] = null;

	public FastSkipListMap(int size) {
		data = (T[]) new Object[size];
	}

	public T get(int key) {
		if (key > data.length - 1)
			return null;
		else
			return data[key];
	}

	public void put(int key, T value) {
		lock.lock();
		try {
			// update object state
			int dataLength = data.length;
			if (key > dataLength - 1) {
				T dataNew[] = null;
				System.arraycopy(data, 0,
						(dataNew = (T[]) new Object[key<<1]), 0,
						dataLength);
				data = dataNew;
			}
			data[key] = value;
		} finally {
			lock.unlock();
		}
	}

	public boolean containsKey(int key) {
		if (key > data.length - 1)
			return false;
		else
			return data[key] != null;
	}

	public static void testFastSkipListMap() {
		long start = System.currentTimeMillis();
		int key=Integer.parseInt(FastDateUtil.parseDateToStringYMD(new Date()),10);
		FastSkipListMap<Double> d=new FastSkipListMap<Double>(16);
		for (int i = 0; i < 1000000; i++) {
              d.put(key, 1d);			
		}
		for (int i = 0; i < 2000000000; i++) {
           d.get(key);			
		}
		System.out.println(System.currentTimeMillis()-start);
	}

	public static void testSkipListMap() {
		long start = System.currentTimeMillis();
		ConcurrentSkipListMap<Long,Double> d=new ConcurrentSkipListMap<Long,Double>();
		long key=System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
              d.put(key, 1d);			
		}
		for (int i = 0; i < 2000000000; i++) {
            d.get(key);			
		}
		System.out.println(System.currentTimeMillis()-start);
	}
	
    public static void main(String[] args) {
		testSkipListMap();
		//testFastSkipListMap();
		 System.out.println(Runtime.getRuntime().totalMemory());
		 System.out.println(Runtime.getRuntime().freeMemory());
		
	}
	
	
	
}
