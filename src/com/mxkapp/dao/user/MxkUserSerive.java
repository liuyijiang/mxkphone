package com.mxkapp.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;

import com.mxkapp.common.application.Application;
import com.mxkapp.common.http.MxkHttpPost;
import com.mxkapp.dao.MxkService;
import com.mxkapp.view.MxkLoginViewActivity;
import com.mxkapp.view.MxkMainViewActivity;
import com.mxkapp.vo.UserVO;

public class MxkUserSerive extends MxkService {

	public boolean userLogin(String username,String password){
		boolean success = false;
		List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //创建参数队列  
	    formParams.add(new BasicNameValuePair("username", username)); 
	    formParams.add(new BasicNameValuePair("password", password));
	    UserVO userVO = (UserVO) MxkHttpPost.getUniqueData("/login", UserVO.class, formParams);
	    if(userVO != null && userVO.getId() != null){
	    	userVO.setImageBitMap(getBitmapFromUrl(userVO.getImage()));
	    	Application.CURRENT_USER = userVO;
	    	success = true;
	    }
		return success;
	}
	
	public void loginOut(){
		Application.CURRENT_PROJECT = null;
		Application.CURRENT_USER = null;
		Application.USER_PROJECT_LIST = null;
		Intent intent = new Intent();
		intent.setClass(context,MxkLoginViewActivity.class);
		context.startActivity(intent);
	}
	
	
	public void navToMainView(){
		Intent intent = new Intent();
		intent.setClass(context,MxkMainViewActivity.class);
		context.startActivity(intent);
	}
	
	
}
