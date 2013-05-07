package com.mxkapp.dao.vister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;

import com.mxkapp.common.http.MxkHttpGet;
import com.mxkapp.common.util.StringUtil;
import com.mxkapp.dao.MxkService;
import com.mxkapp.view.MxkAddComentViewActivity;
import com.mxkapp.view.MxkVisitrSeePlanViewActivity;
import com.mxkapp.vo.UserProjectPlanVO;

public class MxkVistirService extends MxkService {
  
	
	public List<HashMap<String, Object>> findNewInforPlans(List<HashMap<String, Object>> apdata,String userid,int page){
		List<UserProjectPlanVO> list = null;
	    list = MxkHttpGet.getListData("/findUserNewInforShow/"+userid+"/"+page, UserProjectPlanVO.class);
		if (list != null && !list.isEmpty()) {
			if (apdata == null) {
				apdata = new ArrayList<HashMap<String, Object>>();
			} 
			for (UserProjectPlanVO vo : list) {
				HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
				tempHashMap.put("comments", "����"+vo.getCommints());
				tempHashMap.put("share", "����"+vo.getShare());
				if (StringUtil.stringIsEmpty(vo.getPlanfrom())) {
					tempHashMap.put("createtime", "������ ģ��Fan web");
				}else {
					tempHashMap.put("createtime", vo.getPlanfrom());
				}
				if (vo.getType().equals("share")) { // �Ƿ���
					tempHashMap.put("username", vo.getUsername());
					tempHashMap.put("planinfo", "����"+ vo.getOwnername() + " �ڹ��̣�"+ vo.getProjectName() + " �����Ľ���");
				} else {
					tempHashMap.put("username", vo.getOwnername());
					tempHashMap.put("planinfo", "�ڹ��̣�"+ vo.getProjectName() + " �����Ľ���");
				}
				tempHashMap.put("userimage", imageurl+vo.getUserimage());
				tempHashMap.put("planimage", imageurl+vo.getImageUrl());
				tempHashMap.put("UserProjectPlanVO", vo);
  			    apdata.add(tempHashMap);
			}
			
		}
		return apdata;
	}
	
	
	
	
	public void navToMxkVisitrSeePlanView(UserProjectPlanVO vo){
		Intent intent = new Intent();
		intent.putExtra("UserProjectPlanVO", vo); //����
		intent.setClass(context,MxkVisitrSeePlanViewActivity.class);
		context.startActivity(intent);
	}

	public void navToMxkAddComentView(String planid,String username,String projectid, String ownerid){
		Intent intent = new Intent();
		intent.putExtra("planid", planid);
		intent.putExtra("username", username);
		intent.putExtra("projectid", projectid);
		intent.putExtra("ownerid", ownerid);
		intent.setClass(context,MxkAddComentViewActivity.class);
		context.startActivity(intent);
	}
	
	
	
}
