package com.mxkapp.common.application;

import java.util.List;

import com.mxkapp.vo.UserProjectVO;
import com.mxkapp.vo.UserVO;

public class Application {

	// ���浱ǰ�û�
	public static UserVO CURRENT_USER = null;
    
	// ���浱ǰ�����й���
	public static List<UserProjectVO> USER_PROJECT_LIST = null;

	public static UserProjectVO CURRENT_PROJECT = null;
	
}
