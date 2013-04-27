package com.mxkapp.view;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.mxkapp.common.application.Application;
import com.mxkapp.dao.user.MxkUserSerive;
import com.mxkapp.vo.UserVO;

public class MxkLoginViewActivity extends Activity {

	private final static int SHOW_LOGIN_ERROR_ALERT = 2;
	private final static int SHOW_LOGIN_WAIT = 3;
	
	private Button login;
	private EditText loginName, password;

	private MxkUserSerive service;
	private Context context = this;
	
	private ProgressDialog progressDialog;
	private UserVO uvo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxkloginviewactivity);
		init();
	}

	private void checkiUser() {
		uvo = Application.CURRENT_USER;
		if(uvo != null){
			service.navToMainView();
		}
	}
	
	private void init() {
		service = new MxkUserSerive();
		service.setContext(this);
		checkiUser();
		initializeComponent();
	}

    private Handler loginHandler = new Handler(){  
         
        public void handleMessage(Message msg) {  
        	Boolean success = (Boolean) msg.obj;
        	progressDialog.dismiss();
        	if(success){
        		service.navToMainView();
        	}else{
        		showDialog(SHOW_LOGIN_ERROR_ALERT);
        	}
        }; 
	};  
	
	public void initializeComponent() {
		loginName = (EditText) this.findViewById(R.id.MXKLoginViewActivityLoginName);
		password = (EditText) this.findViewById(R.id.MXKLoginViewActivityUserPsss);
		login = (Button) this.findViewById(R.id.MXKLoginViewActivityLoginButton);
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				showDialog(SHOW_LOGIN_WAIT);
				
				new Thread( new Runnable() {     
					public void run() {     
				    	boolean success = service.userLogin(loginName.getText().toString().trim(),password.getText().toString().trim());
				    	Message message = loginHandler.obtainMessage(0, success);  
				    	loginHandler.sendMessage(message);
				    }            
				}).start(); 
			}

		});

	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case SHOW_LOGIN_WAIT:
			return buildDialogForLoginWait();
		case SHOW_LOGIN_ERROR_ALERT:
			return buildDialogForLogin();
		default:
			return null;
		}

	}

	private Dialog buildDialogForLogin() {
		AlertDialog.Builder ad = new AlertDialog.Builder(context);
		ad.setMessage("密码或账号有误请重试！");//R.string.MXKLoginActivityloginUserError
		return ad.create();
	}
	
	private Dialog buildDialogForLoginWait() {
		 progressDialog = new ProgressDialog(context);  
		 progressDialog.setTitle("模型控");  
		 progressDialog.setMessage("正在登陆..");  
		 progressDialog.setIndeterminate(false);  
		 progressDialog.setCancelable(false);  
         return progressDialog;  
	}

}