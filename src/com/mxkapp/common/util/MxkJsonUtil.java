package com.mxkapp.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class MxkJsonUtil {
   
    private static boolean hasText(String text) {
        if (text == null) return false;
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isWhitespace(text.charAt(i))) return true;
        }
        return false;
    }
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T jsonToObject(String json,Class<T> beanClass){
		 if(!hasText(json)){
			 return null;
		 }
		 JSONObject jobj = null;
		 T obj = null;
		 try{
			 ClassLoader loader = Thread.currentThread().getContextClassLoader();
			 Class clazz = loader.loadClass(beanClass.getName());
			 jobj = new JSONObject(json);
			 Field Fields[] = clazz.getDeclaredFields();//获取所有属性
			 Constructor<T> cons = clazz.getDeclaredConstructor((Class[])null); //创建构造函数
			 obj = cons.newInstance();
			 for (Field f : Fields) { //获得所有属性
				 if (!f.getName().equals("serialVersionUID")) {
				     if( !jobj.isNull(f.getName())){
				    	 String methodName = f.getName();
						 methodName = "set" + methodName.replaceFirst(methodName.substring(0, 1), methodName.substring(0, 1).toUpperCase()); 
					     Method setMethod = clazz.getMethod(methodName, String.class);
					     setMethod.invoke(obj, jobj.getString(f.getName()));
				     }
				 }
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
         return obj;
	} 
	 
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	public static  <T> List<T> jsonToObjectList(String json,Class<T> beanClass){
		 final List<T> list = new ArrayList<T>(); 
		 if(!hasText(json)){
			 return list;
		 }
		 try {
			 JSONArray ja = new JSONArray(json);
			 for (int i=0; i< ja.length();i++) {
				 JSONObject jobj = ja.getJSONObject(i);
				 ClassLoader loader = Thread.currentThread().getContextClassLoader();
				 Class clazz = loader.loadClass(beanClass.getName());
				 Field Fields[] = clazz.getDeclaredFields();//获取所有属性
				 Constructor<T> cons = clazz.getDeclaredConstructor((Class[])null); //创建构造函数
				 T obj = cons.newInstance();
				 for (Field f : Fields) { //获得所有属性
					 if (!f.getName().equals("serialVersionUID")) {
					     if( !jobj.isNull(f.getName())){
					    	 String methodName = f.getName();
							 methodName = "set" + methodName.replaceFirst(methodName.substring(0, 1), methodName.substring(0, 1).toUpperCase()); 
						     Method setMethod = clazz.getMethod(methodName, String.class);
						     setMethod.invoke(obj, jobj.getString(f.getName()));
					     }
					 }
				 }
				 list.add(obj);
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return list;
	}
	
}
