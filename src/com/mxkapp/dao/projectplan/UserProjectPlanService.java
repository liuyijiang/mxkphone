package com.mxkapp.dao.projectplan;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import android.content.Intent;

import com.mxkapp.common.http.MxkHttpGet;
import com.mxkapp.common.http.MxkHttpPost;
import com.mxkapp.dao.MxkService;
import com.mxkapp.view.MxkAddComentViewActivity;
import com.mxkapp.vo.CommentsVO;
import com.mxkapp.vo.UserProjectPlanVO;

public class UserProjectPlanService extends MxkService {

	
	public List<HashMap<String, Object>> findUserProjectPlanComments(List<HashMap<String, Object>> apdata,String planid){
		List<CommentsVO> list = null;
	    list = MxkHttpGet.getListData("/findComments/"+planid+"/plan", CommentsVO.class);
		if (list != null && !list.isEmpty()) {
			if (apdata == null) {
				apdata = new ArrayList<HashMap<String, Object>>();
			} 
			for (CommentsVO vo : list) {
				HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
				tempHashMap.put("workDescrible", vo.getCreateTime());
				tempHashMap.put("workName",  vo.getCreateUserName()+" 评论："+vo.getMessage());
				tempHashMap.put("userimage",imageurl+vo.getUserImage());
				tempHashMap.put("username", vo.getCreateUserName());
				tempHashMap.put("planid", vo.getBeenCommentsId());
				tempHashMap.put("projectid", vo.getProjectid());
				tempHashMap.put("ownerid", vo.getOwnerid());
				apdata.add(tempHashMap);
			}
			
		}
		return apdata;
	}
	
	
	
	public List<HashMap<String, Object>> findUserProjectPlans(List<HashMap<String, Object>> apdata,String projectid,int page){
		List<UserProjectPlanVO> list = null;
	    list = MxkHttpGet.getListData("/findProjectPlanList/"+projectid+"/"+page, UserProjectPlanVO.class);
		if (list != null && !list.isEmpty()) {
			if (apdata == null) {
				apdata = new ArrayList<HashMap<String, Object>>();
			} 
			for (UserProjectPlanVO vo : list) {
				HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
				tempHashMap.put("plantime", vo.getCreateTime());
				tempHashMap.put("planinfo",  "评论"+ vo.getCommints()+" 分享"+ vo.getShare() +" 进度"+ vo.getPg() +"%");
				tempHashMap.put("planmeesage",vo.getInfo());
				tempHashMap.put("planimage",imageurl+vo.getImageUrl());
				tempHashMap.put("userProjectPlanVO", vo);
				apdata.add(tempHashMap);
			}
			
		}
		return apdata;
	}
	
	public boolean createProjectPlan(String filePath,String jd,String info,String userid,String pid,String planform,String projectName){
		try {  
		FileBody file = new FileBody(new File(filePath));  
		 // 请求实体  
        MultipartEntity mulentity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
        // 把请求参数加入请求实体中,包含文件和字符串  
        mulentity.addPart("file", file);  
        
        StringBody useridBody = new StringBody(userid,Charset.forName("UTF-8"));  
        StringBody projectidBody = new StringBody(pid, Charset.forName("UTF-8"));  
        StringBody infoBody = new StringBody(info,Charset.forName("UTF-8"));  
        StringBody pgBody = new StringBody(jd, Charset.forName("UTF-8"));  
        StringBody planformBody = new StringBody(planform, Charset.forName("UTF-8"));  
        StringBody projectNameBody = new StringBody(projectName, Charset.forName("UTF-8"));  
        
        mulentity.addPart("userid", useridBody);  
        mulentity.addPart("projectid", projectidBody);  
        mulentity.addPart("info", infoBody); 
        mulentity.addPart("pg", pgBody);
        mulentity.addPart("planform", planformBody);
        mulentity.addPart("projectName", projectNameBody);
        
        MxkHttpPost.uploadFile("/createPorjectPlan", filePath,mulentity);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
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
