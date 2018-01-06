package com.kaleka;

import java.util.HashMap;
import java.util.Map;

public final class FastTwoIntintMap {
	private int data[] = null;

	private int offset=0;

	public FastTwoIntintMap(int size,int offset) {
		data = new int[size];
		this.offset=offset;
	}

	public int get(int x,int y) {
		int keyInt=offset*x+y;
		if (keyInt > data.length - 1)
			return -1;
		else
			return data[keyInt];
		}


	public void put(int x,int y,int value) {
		int keyInt=offset*x+y;
	   if(keyInt > data.length - 1) {
	    	int dataLength=data.length;
			int dataNew[] = null;
			System.arraycopy(data, 0,
					(dataNew =new int[keyInt << 1]), 0,
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
    
    public static void testFastTwoIntintMap()
    {
    	long start=System.currentTimeMillis();
    	FastTwoIntintMap a=new FastTwoIntintMap(16,100);
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
		testFastTwoIntintMap();
		//testFastTwoIntMap();
		//testHashMap();		
		
		 System.out.println(Runtime.getRuntime().totalMemory());
		 System.out.println(Runtime.getRuntime().freeMemory());
	}

}
