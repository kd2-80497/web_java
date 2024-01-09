package com.sunbeam.utils;

import java.text.SimpleDateFormat;

/**
 * @author Ritu And Ujjwal
 * prn no ritu = 230940820086
 * prn no ujjwal = 230940820111
 *
 */

//this converts date in desired format
public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static String toString(java.util.Date date) {
		return sdf.format(date);
	}
	public static java.util.Date parse(String dateStr) {
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//converting sql date to util date 
	public static java.util.Date sqlToUtilDate(java.sql.Date sDate) {
		return new java.util.Date(sDate.getTime());
	}
	
	//converting util date to sql date
	public static java.sql.Date utilToSqlDate(java.util.Date uDate) {
		return new java.sql.Date(uDate.getTime());
	}
}
