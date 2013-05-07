package com.mxkapp.common.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mxkapp.view.R;

public class MXKProjectLogAdapter extends BaseAdapter{
  
	private List<HashMap<String, Object>> data; //数据	
    private LayoutInflater layoutInflater;// 布局
	@SuppressWarnings("unused")
	private Context context; //上下文
	
    public MXKProjectLogAdapter(Context context, List<HashMap<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    //获得记录条数
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

 	//绘制界面
 	public View getView(int position, View convertView, ViewGroup parent) {
 		MXKProjectAdapterLogModel model = null;
 		if(convertView == null){
 			// 获取组件布局
 			model = new MXKProjectAdapterLogModel();
             convertView = layoutInflater.inflate(R.layout.mxkprojectlogadpaterview, null);
             model.workName = (TextView) convertView.findViewById(R.id.mxkprojectlogadapterprojectname);
             model.workdescrible = (TextView) convertView.findViewById(R.id.mxkprojectlogadapterdesc);
             // 这里要注意，是使用的tag来存储数据的。
             convertView.setTag(model); 
 		}else{
 			model = (MXKProjectAdapterLogModel) convertView.getTag(); 
 		}
 		   model.workName.setText((String) data.get(position).get("workName"));
 		   model.workdescrible.setText((String) data.get(position).get("workDescrible"));
 		   return convertView;
 	}
    
 
	
}
