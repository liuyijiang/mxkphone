package com.mxkapp.common.application;

import java.util.List;

import com.mxkapp.vo.UserProjectVO;
import com.mxkapp.vo.UserVO;

public class Application {

	// 保存当前用户
	public static UserVO CURRENT_USER = null;
    
	// 保存当前的所有工程
	public static List<UserProjectVO> USER_PROJECT_LIST = null;

	public static UserProjectVO CURRENT_PROJECT = null;
	
}
