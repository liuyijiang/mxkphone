package com.mxkapp.common.http;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.mxkapp.common.util.MxkJsonUtil;


public class MxkHttpPost {
  
	private static final String ENCODE = "UTF-8";
    //private static final String HOST = "http://192.168.1.100:8080/mxkapp/";
    private static final String HOST = "http://www.waileecn.com/mxkapp/";
    
	public static <T> T getUniqueData(String action, Class<T> beanClass, List<NameValuePair> formParams){
		HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(HOST+action);
        String result = null; 
        try {  
        	post.setEntity(new UrlEncodedFormEntity(formParams, HTTP.UTF_8)); 
            HttpResponse res = client.execute(post);  
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
            	 HttpEntity entity = res.getEntity();            //获取响应实体  
                 if (null != entity) {  
                	 result = EntityUtils.toString(entity, ENCODE);  
                     //EntityUtils.consume(entity); //Consume response content  
                 }  
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return  MxkJsonUtil.jsonToObject(result, beanClass);
	}
	
	public static <T> List<T> getListData(String action, Class<T> beanClass, List<NameValuePair> formParams){
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(HOST+action);
		String result = null; 
        try {  
        	post.setEntity(new UrlEncodedFormEntity(formParams, ENCODE)); 
            HttpResponse res = client.execute(post);  
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
            	 HttpEntity entity = res.getEntity();            //获取响应实体  
                 if (null != entity) {  
                	 result = EntityUtils.toString(entity, ENCODE); 
                	 //EntityUtils.
                     //EntityUtils.consume(entity); //Consume response content  
                 }  
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return  MxkJsonUtil.jsonToObjectList(result, beanClass);
	}
	
	public static boolean uploadFile(String action,String imagefile, MultipartEntity mulentity ){
		try {  
			HttpClient httpclient= new DefaultHttpClient();
            HttpPost httppost = new HttpPost(HOST+action); 
            // 设置请求实体  
            httppost.setEntity(mulentity); 
            HttpResponse res = httpclient.execute(httppost);  
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
            	 HttpEntity entity = res.getEntity();            //获取响应实体  
                 if (null != entity) {  
                	 String result = EntityUtils.toString(entity, "UTF-8");  
                     //EntityUtils.consume(entity); //Consume response content  
                     System.out.println(result);
                 }  
            }
		}catch(Exception e){
			e.printStackTrace();
		}
       return true;
	}
	
	public static boolean addComent(String action,MultipartEntity mulentity){
		try {  
			HttpClient httpclient= new DefaultHttpClient();
            HttpPost httppost = new HttpPost(HOST+action); 
            // 设置请求实体  
            httppost.setEntity(mulentity); 
            HttpResponse res = httpclient.execute(httppost);  
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
            	 HttpEntity entity = res.getEntity();            //获取响应实体  
                 if (null != entity) {  
                	 String result = EntityUtils.toString(entity, "UTF-8");  
                     System.out.println(result);
                 }  
            }
		}catch(Exception e){
			e.printStackTrace();
		}
       return true;
	}
	
	
}
