package com.mxkapp.dao.comments;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;

import com.mxkapp.common.http.MxkHttpPost;
import com.mxkapp.vo.CommentsVO;

public class MxkCommentsService {
   
	public void addComments(String userid,String beenCommentsId,String message,String projectid,String ownerid){
	    MultipartEntity mulentity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
        // 把请求参数加入请求实体中,包含文件和字符串  
          try {
        	  StringBody useridBody = new StringBody(userid,Charset.forName("UTF-8"));  
              StringBody beenCommentsIdBody = new StringBody(beenCommentsId, Charset.forName("UTF-8"));  
              StringBody messageBody = new StringBody(message,Charset.forName("UTF-8"));  
              StringBody projectidBody = new StringBody(projectid, Charset.forName("UTF-8"));  
              StringBody owneridBody = new StringBody(ownerid, Charset.forName("UTF-8"));  
              StringBody typeBody = new StringBody("plan", Charset.forName("UTF-8"));  
              
              mulentity.addPart("userid", useridBody);  
              mulentity.addPart("beenCommentsId", beenCommentsIdBody);  
              mulentity.addPart("message", messageBody); 
              mulentity.addPart("projectid", projectidBody);
              mulentity.addPart("ownerid", owneridBody);
              mulentity.addPart("type", typeBody);
      	     
          }catch(Exception e){
        	  e.printStackTrace();
          }
       
	    MxkHttpPost.addComent("/addcomment",mulentity);
	    //CommentsVO vo = (CommentsVO) MxkHttpPost.getUniqueData("/addcomment", CommentsVO.class, formParams);
	   // MxkHttpPost.getUniqueData();
	}
	
}
