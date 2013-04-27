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

public class MXKProjectAdapter extends BaseAdapter{
  
	private List<HashMap<String, Object>> data; //����	
    private LayoutInflater layoutInflater;// ����
	@SuppressWarnings("unused")
	private Context context; //������
	
    public MXKProjectAdapter(Context context, List<HashMap<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    //��ü�¼����
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

 	//���ƽ���
 	public View getView(int position, View convertView, ViewGroup parent) {
 		MXKProjectAdapterModel model = null;
 		if(convertView == null){
 			// ��ȡ�������
 			model = new MXKProjectAdapterModel();
             convertView = layoutInflater.inflate(R.layout.mxkprojectadpaterview, null);
             model.workName = (TextView) convertView.findViewById(R.id.showwoklistworkname);
             model.workdescrible = (TextView) convertView.findViewById(R.id.showwoklistworkdescrible);
             model.workstarttime = (TextView) convertView.findViewById(R.id.showwoklistworktime);
             model.workuseday = (TextView) convertView.findViewById(R.id.showwoklistworkuseday);
             // ����Ҫע�⣬��ʹ�õ�tag���洢���ݵġ�
             convertView.setTag(model); 
 		}else{
 			model = (MXKProjectAdapterModel) convertView.getTag(); 
 		}
 		   model.workName.setText((String) data.get(position).get("workName"));
 		   model.workdescrible.setText((String) data.get(position).get("workDescrible"));
 		   model.workstarttime.setText((String) data.get(position).get("worktime"));
 		   model.workuseday.setText( (String) data.get(position).get("workday"));
 		   return convertView;
 	}
    
 
	
}
