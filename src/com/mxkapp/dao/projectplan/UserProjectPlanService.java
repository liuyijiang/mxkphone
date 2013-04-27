package com.mxkapp.dao.projectplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mxkapp.common.http.MxkHttpGet;
import com.mxkapp.dao.MxkService;
import com.mxkapp.vo.UserProjectPlanVO;

public class UserProjectPlanService extends MxkService {

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
	
	
}
