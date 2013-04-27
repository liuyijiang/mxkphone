package com.mxkapp.common.adapter;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxkapp.view.R;

public class MXKProjectPlanAdpater extends BaseAdapter {

	private List<HashMap<String, Object>> data; // 数据
	private LayoutInflater layoutInflater;// 布局
	@SuppressWarnings("unused")
	private Context context; // 上下文

	
	public MXKProjectPlanAdpater(Context context,
			List<HashMap<String, Object>> data) {
		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(context);
	}

	// 获得记录条数
	public int getCount() {
		return data.size();
	}

	/**
	 * 获取某一位置的数据
	 */
	public Object getItem(int position) {
		return data.get(position);
	}

	/**
	 * 获取唯一标识
	 */
	public long getItemId(int position) {
		return position;
	}

	// 绘制界面
	public View getView(int position, View convertView, ViewGroup parent) {
		MXKProjectPlanAdpaterModel model = null;
		if (convertView == null) {
			// 获取组件布局
			model = new MXKProjectPlanAdpaterModel();
			convertView = layoutInflater.inflate(
					R.layout.mxkprojectplanadpaterview, null);
			model.planinfo = (TextView) convertView
					.findViewById(R.id.mxkprojectplanadaptarinfo);
			model.planmeesage = (TextView) convertView
					.findViewById(R.id.mxkprojectplanadaptarmessage);
			model.plantime = (TextView) convertView
					.findViewById(R.id.mxkprojectplanadaptartime);
			model.planimage = (ImageView) convertView
					.findViewById(R.id.mxkprojectplanadaptarimage);
			// 这里要注意，是使用的tag来存储数据的。
			convertView.setTag(model);
		} else {
			model = (MXKProjectPlanAdpaterModel) convertView.getTag();
		}
		model.planinfo.setText((String) data.get(position).get("planinfo"));
		model.planmeesage.setText((String) data.get(position).get("planmeesage"));
		model.plantime.setText((String) data.get(position).get("plantime"));
		model.planimage.setImageBitmap(getBitmapFromUrl((String) data.get(position).get("planimage")));
		model.planimage.invalidate();
		return convertView;
	}

	private Bitmap getBitmapFromUrl(String imgUrl) {
		URL url;
		Bitmap bitmap = null;
		try {
			url = new URL(imgUrl);
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
