package com.mxkapp.dao;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.mxkapp.view.MxkShowBigImageViewActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MxkService {
    
	public final static String imageurl = "http://192.168.1.102/image/";
	
	public Context context;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void navShowBigImage(String iamgeurl){
		Intent intent = new Intent();
		intent.putExtra("iamgeurl", iamgeurl); //´«ÖÁ
		intent.setClass(context,MxkShowBigImageViewActivity.class);
		context.startActivity(intent);
	}
	
	public Bitmap getBitmapFromUrl(String imgUrl) {
		URL url;
		Bitmap bitmap = null;
		try {
			url = new URL(imageurl+imgUrl);
			HttpURLConnection httpURLconnection =  (HttpURLConnection) url.openConnection();  
	        httpURLconnection.setRequestMethod("GET");  
	        httpURLconnection.setReadTimeout(6*1000);  
			InputStream is = httpURLconnection.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bitmap = BitmapFactory.decodeStream(bis);
			is.close();
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
}
