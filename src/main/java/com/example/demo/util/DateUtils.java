package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.exception.DateFormatException;

public class DateUtils {

	public static Date parse(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			throw new DateFormatException("Date format exception :" + dateString + ": message:" + e.getMessage());
		}
		return date;
	}

	public static String format(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String dateString = null;
		try {
			dateString = dateFormat.format(date);
		} catch (Exception e) {
			throw new DateFormatException("Date format exception :" + dateString + ": message:" + e.getMessage());
		}
		return dateString;
	}
}
