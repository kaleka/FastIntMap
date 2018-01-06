package com.kaleka;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 
 * @author kaleka
 * 
 */
public final class FastDateUtil {

	private static ReentrantLock lock = new ReentrantLock();

	private static Calendar calendar = Calendar.getInstance();

	public final static Date parseString2Date(final String input) {
		try {
			lock.lock();
			String year = input.substring(0, 4);
			String month = input.substring(5, 7);
			String day = input.substring(8, 10);
			String hour = input.substring(11, 13);
			String minute = input.substring(14, 16);
			String second = input.substring(17, 19);
			calendar.set(Integer.parseInt(year), Integer.parseInt(month),
					Integer.parseInt(day), Integer.parseInt(hour),
					Integer.parseInt(minute), Integer.parseInt(second));
			return calendar.getTime();
		} catch (Exception e) {
			return null;
		} finally {
			lock.unlock();
		}
	}

	private final static String fillZero(final int input) {
		if (input < 10)
			return new StringBuilder(2).append('0').append(input).toString();
		else
			return Integer.toString(input);
	}

	public final static String getCurrentTime() {
		try {
			lock.lock();
			calendar.setTime(new Date());
			StringBuilder stringBuilder = new StringBuilder(8);
			stringBuilder.append(fillZero(calendar.get(calendar.HOUR_OF_DAY)));
			stringBuilder.append(':');
			stringBuilder.append(fillZero(calendar.get(calendar.MINUTE)));
			stringBuilder.append(':');
			stringBuilder.append(fillZero(calendar.get(calendar.SECOND)));
			return stringBuilder.toString();
		} catch (Exception e) {
			return null;
		} finally {
			lock.unlock();
		}
	}

	public final static String formatHMS(Date date) throws Exception {
		try {
			lock.lock();
			calendar.setTime(date);
			StringBuilder stringBuilder = new StringBuilder(8);
			stringBuilder.append(fillZero(calendar.get(calendar.HOUR_OF_DAY)));
			stringBuilder.append(':');
			stringBuilder.append(fillZero(calendar.get(calendar.MINUTE)));
			stringBuilder.append(':');
			stringBuilder.append(fillZero(calendar.get(calendar.SECOND)));
			return stringBuilder.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			lock.unlock();
		}
	}

	public final static boolean isValidFormatHMS(String input){
		try {
			if(input.length()!=8)
				return false;
			String[] splitArray=input.split(":");
			if(splitArray.length!=3)
				return false;
			for (int i = 0; i < splitArray.length; i++) {
				if(splitArray[i].length()!=2)
					return false;
			}
			int hour = Integer.parseInt(input.substring(0, 2));
			if((hour>=24)||(hour<0))
				return false;
			int minute = Integer.parseInt(input.substring(3, 5));
			if((minute>=60)||(minute<0))
				return false;
			int second = Integer.parseInt(input.substring(6, 8));
			if((second>=60)||(second<0))
				return false;
			return true;
		} catch (Exception e) {
			return false;
		} 
	}

	public final static String parseDateToString(final Date input) {
		try {
			lock.lock();
			calendar.setTime(input);
			StringBuilder stringBuilder = new StringBuilder(19);
			stringBuilder.append(calendar.get(calendar.YEAR));
			stringBuilder.append('-');
			stringBuilder.append(fillZero(calendar.get(calendar.MONTH)+1));
			stringBuilder.append('-');
			stringBuilder.append(fillZero(calendar.get(calendar.DATE)));
			stringBuilder.append(' ');
			stringBuilder.append(fillZero(calendar.get(calendar.HOUR_OF_DAY)));
			stringBuilder.append(':');
			stringBuilder.append(fillZero(calendar.get(calendar.MINUTE)));
			stringBuilder.append(':');
			stringBuilder.append(fillZero(calendar.get(calendar.SECOND)));
			return stringBuilder.toString();
		} catch (Exception e) {
			return null;
		} finally {
			lock.unlock();
		}
	}
	
	public final static String parseDateToStringYMD(final Date input) {
		try {
			lock.lock();
			calendar.setTime(input);
			StringBuilder stringBuilder = new StringBuilder(6);
			stringBuilder.append(calendar.get(calendar.YEAR));
			stringBuilder.append(fillZero(calendar.get(calendar.MONTH)+1));
			stringBuilder.append(fillZero(calendar.get(calendar.DATE)));
			return stringBuilder.toString();
		} catch (Exception e) {
			return null;
		} finally {
			lock.unlock();
		}
	}	

	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++)
			FastDateUtil.isValidFormatHMS("01:01:01");
		System.out.println("cost time:" + (System.currentTimeMillis() - start)
				+ "ms");
	}

}
