package com.mxkapp.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	public static boolean stringIsEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	//
    public static String dateToString(Date date,String formater){
    	if(formater == null){
    		formater = "yyyy年MM月dd HH时mm分ss秒";
    	}
    	SimpleDateFormat s  = new SimpleDateFormat(formater);
    	return s.format(date);
	}
	
}
