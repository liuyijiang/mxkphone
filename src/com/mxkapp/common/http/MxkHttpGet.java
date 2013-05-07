package com.mxkapp.common.http;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.mxkapp.common.util.MxkJsonUtil;
/**
 * 模型空app 
 * @author jim.liu
 *
 */
public class MxkHttpGet {
   
	private static final String ENCODE = "UTF-8";
	//private static final String HOST = "http://192.168.1.100:8080/mxkapp/";
	private static final String HOST = "http://www.waileecn.com/mxkapp/";
	public static <T> T getUniqueData(String action, Class<T> beanClass){
		HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(HOST+action);
        String result = null; 
        try {  
            HttpResponse res = client.execute(get);  
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
        return  MxkJsonUtil.jsonToObject(result.toString(), beanClass);
	}
	
	public static <T> List<T> getListData(String action, Class<T> beanClass){
		HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(HOST+action);
        String result = null; 
        try {  
            HttpResponse res = client.execute(get);  
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
        return  MxkJsonUtil.jsonToObjectList(result.toString(), beanClass);
	}
	
}
