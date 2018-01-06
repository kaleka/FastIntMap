package com.kaleka;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author kaleka
 * 
 * @param <T>
 */
public final class FastTwoIntMap<T> {

	private T data[] = null;

	private int offset=0;

	public FastTwoIntMap(int size,int offset) {
		data = (T[]) new Object[size];
		this.offset=offset;
	}

	public T get(int x,int y) {
		int keyInt=offset*x+y;
		if (keyInt > data.length - 1)
			return null;
		else
			return data[keyInt];
		}


	public void put(int x,int y,T value) {
		int keyInt=offset*x+y;
	   if(keyInt > data.length - 1) {
	    	int dataLength=data.length;
			T dataNew[] = null;
			System.arraycopy(data, 0,
					(dataNew = (T[]) new Object[keyInt << 1]), 0,
					dataLength);
			data = dataNew;
		}
		data[keyInt] = value;
	}

	
    public static void testFastTwoIntMap()
    {
    	long start=System.currentTimeMillis();
    	FastTwoIntMap<Integer> a=new FastTwoIntMap<Integer>(16,100);
    	for (int i = 0; i < 20000; i++) {
			for (int j = 0; j < 50; j++) {
                   a.put(i,j, i);
			}
		}
    	for (int i = 0; i < 20000; i++) {
			for (int j = 0; j < 50; j++) {
							a.get(i,j);
			}
		}
    	System.out.println(System.currentTimeMillis()-start);
    }
    
    public static void testHashMap()
    {
    	long start=System.currentTimeMillis();
    	Map<String,Integer> a=new HashMap<String,Integer>(16);
    	for (int i = 0; i < 20000; i++) {
			for (int j = 0; j < 50; j++) {
                   a.put(i+"_"+j, i);					
				
			}
		}
    	for (int i = 0; i < 20000; i++) {
			for (int j = 0; j < 50; j++) {
							a.get(i+"_"+j);
			}
		}
    	System.out.println(System.currentTimeMillis()-start);
    }    
    
    
	

	public static void main(String[] args) {
		 //testFastTwoIntMap();
		testHashMap();		
		
		 System.out.println(Runtime.getRuntime().totalMemory());
		 System.out.println(Runtime.getRuntime().freeMemory());
	}

}
