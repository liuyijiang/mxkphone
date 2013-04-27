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

public class MXKShowNewInforAdapter extends BaseAdapter {

	private List<HashMap<String, Object>> data; // ����
	private LayoutInflater layoutInflater;// ����
	@SuppressWarnings("unused")
	private Context context; // ������

	public MXKShowNewInforAdapter(Context context,
			List<HashMap<String, Object>> data) {
		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(context);
	}

	// ��ü�¼����
	public int getCount() {
		return data.size();
	}

	/**
	 * ��ȡĳһλ�õ�����
	 */
	public Object getItem(int position) {
		return data.get(position);
	}

	/**
	 * ��ȡΨһ��ʶ
	 */
	public long getItemId(int position) {
		return position;
	}

	// ���ƽ���
	public View getView(int position, View convertView, ViewGroup parent) {
		MXKShowNewInforAdapterModel model = null;
		if (convertView == null) {
			// ��ȡ�������
			model = new MXKShowNewInforAdapterModel();
			convertView = layoutInflater.inflate(
					R.layout.mxknewinforadpaterview, null);
			model.commint = (TextView) convertView
					.findViewById(R.id.mxknewinforcommmet);
			model.share = (TextView) convertView
					.findViewById(R.id.mxknewinforshare);
			model.createtime = (TextView) convertView
					.findViewById(R.id.mxknewinfortime);
			model.username = (TextView) convertView
					.findViewById(R.id.mxknewinforusername);
			model.planinfo = (TextView) convertView
					.findViewById(R.id.mxknewinforplaninfor);
			model.userimage = (ImageView) convertView
					.findViewById(R.id.mxknewinforuseriamge);
			model.planimage = (ImageView) convertView
					.findViewById(R.id.mxknewinforplanimage);
			// ����Ҫע�⣬��ʹ�õ�tag���洢���ݵġ�
			convertView.setTag(model);
		} else {
			model = (MXKShowNewInforAdapterModel) convertView.getTag();
		}
		model.commint.setText((String) data.get(position).get("comments"));
		model.share.setText((String) data.get(position).get("share"));
		model.createtime.setText((String) data.get(position).get("createtime"));
		model.username.setText((String) data.get(position).get("username"));
		model.planinfo.setText((String) data.get(position).get("planinfo"));
		model.userimage.setImageBitmap(getBitmapFromUrl((String) data.get(
				position).get("userimage")));
		model.userimage.invalidate();
		model.planimage.setImageBitmap(getBitmapFromUrl((String) data.get(
				position).get("planimage")));
		
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
