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
  
	private List<HashMap<String, Object>> data; //����	
    private LayoutInflater layoutInflater;// ����
	@SuppressWarnings("unused")
	private Context context; //������
	
    public MXKProjectLogAdapter(Context context, List<HashMap<String, Object>> data) {
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
 		MXKProjectAdapterLogModel model = null;
 		if(convertView == null){
 			// ��ȡ�������
 			model = new MXKProjectAdapterLogModel();
             convertView = layoutInflater.inflate(R.layout.mxkprojectlogadpaterview, null);
             model.workName = (TextView) convertView.findViewById(R.id.mxkprojectlogadapterprojectname);
             model.workdescrible = (TextView) convertView.findViewById(R.id.mxkprojectlogadapterdesc);
             // ����Ҫע�⣬��ʹ�õ�tag���洢���ݵġ�
             convertView.setTag(model); 
 		}else{
 			model = (MXKProjectAdapterLogModel) convertView.getTag(); 
 		}
 		   model.workName.setText((String) data.get(position).get("workName"));
 		   model.workdescrible.setText((String) data.get(position).get("workDescrible"));
 		   return convertView;
 	}
    
 
	
}
