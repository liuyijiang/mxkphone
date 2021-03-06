package com.mxkapp.dao.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;

import com.mxkapp.common.application.Application;
import com.mxkapp.common.http.MxkHttpGet;
import com.mxkapp.dao.MxkService;
import com.mxkapp.view.MxkCreateProjectPlanViewActivity;
import com.mxkapp.view.MxkLoginViewActivity;
import com.mxkapp.view.MxkMainViewActivity;
import com.mxkapp.view.MxkProjectLogViewActivity;
import com.mxkapp.view.MxkProjectPlanViewActivity;
import com.mxkapp.view.MxkProjectViewActivity;
import com.mxkapp.view.MxkShowNewInforViewActivity;
import com.mxkapp.vo.UserProjectLogVO;
import com.mxkapp.vo.UserProjectPlanVO;
import com.mxkapp.vo.UserProjectVO;

public class MxkProjectService extends MxkService {
	
	
	public List<HashMap<String, Object>> findUserProjects(String userid){
		List<HashMap<String, Object>> arrayList = null;
		List<UserProjectVO> list = null;
	    list = MxkHttpGet.getListData("/findProjectList/"+userid, UserProjectVO.class);
		if (list != null && !list.isEmpty()) {
			arrayList = new ArrayList<HashMap<String, Object>>();
			for (UserProjectVO vo : list) {
				HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
				tempHashMap.put("workName", vo.getName());
				tempHashMap.put("workDescrible",vo.getDesc());
				tempHashMap.put("worktime", "创建时间："+ vo.getStartDate());
				tempHashMap.put("workday", "总进度：" + vo.getProgress() + "%");
				tempHashMap.put("workid",vo.getId());
				tempHashMap.put("project", vo);
				arrayList.add(tempHashMap);
			}
		}
		return arrayList;
	}
	
	public List<HashMap<String, Object>> findUserProjectLogs(String userid){
		List<HashMap<String, Object>> arrayList = null;
		List<UserProjectLogVO> list = null;
	    list = MxkHttpGet.getListData("/findProjectLogList/"+userid, UserProjectLogVO.class);
		if (list != null && !list.isEmpty()) {
			arrayList = new ArrayList<HashMap<String, Object>>();
			for (UserProjectLogVO vo : list) {
				HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
				tempHashMap.put("workName", vo.getProjectName());
				tempHashMap.put("workDescrible",vo.getLoginfo());
				arrayList.add(tempHashMap);
			}
		}
		return arrayList;
	}
	
	public void loginOut(){
		Application.key.put(Application.CURRENT_PROJECT, null);
		Application.key.put(Application.CURRENT_USER, null);
		Intent intent = new Intent();
		intent.setClass(context,MxkLoginViewActivity.class);
		context.startActivity(intent);
	}
	
	
	public void navCreateProjectPlanView(){
		Intent intent = new Intent();
		intent.setClass(context,MxkCreateProjectPlanViewActivity.class);
		context.startActivity(intent);
	}
	
	public void navMxkProjectLogView(){
		Intent intent = new Intent();
		intent.setClass(context,MxkProjectLogViewActivity.class);
		context.startActivity(intent);
	}
	
	public void navMxkProjectPlanView(UserProjectPlanVO ppvo){
		Intent intent = new Intent();
		intent.putExtra("UserProjectPlanVO", ppvo); //传至
		intent.setClass(context,MxkProjectPlanViewActivity.class);
		context.startActivity(intent);
	}
	
	public void navShowNewInforView(){
		Intent intent = new Intent();
		intent.setClass(context,MxkShowNewInforViewActivity.class);
		context.startActivity(intent);
	}
	
	public void navProjectView(){
		Intent intent = new Intent();
		intent.setClass(context,MxkProjectViewActivity.class);
		context.startActivity(intent);
	}
	
	public void navToMainView(){
		Intent intent = new Intent();
		intent.setClass(context,MxkMainViewActivity.class);
		context.startActivity(intent);
	}
	
}
