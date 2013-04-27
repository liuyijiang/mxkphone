package com.mxkapp.view;

import java.util.HashMap;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mxkapp.common.adapter.MXKProjectAdapter;
import com.mxkapp.common.application.Application;
import com.mxkapp.dao.project.MxkProjectService;
import com.mxkapp.dao.user.MxkUserSerive;
import com.mxkapp.vo.UserVO;

public class MxkMainViewActivity extends Activity{

	private final static int SHOW_LOGIN_WAIT = 1;
	private final static int SHOW_LOAD_ERROR = 2;
	
	private ListView listView;
	private List<HashMap<String, Object>> adapterData;
	private Context context = this;
	
	private ImageView imageview;
	private TextView myguanzhu,username;//我的关注 
	private UserVO uvo;
	
	private TextView loginOut;
	private ProgressDialog progressDialog;
	private MxkProjectService projectService;
	
	private MxkUserSerive service;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mxkmainviewactivity);
        init();
    }
	
	private void init() {
		projectService = new MxkProjectService();
		projectService.setContext(this);
		service = new MxkUserSerive();
		service.setContext(this);
		initializeComponent();
		initShowData();
	}
	
	//异步加载数据
	 private Handler mainnHandler = new Handler(){  
         
	        public void handleMessage(Message msg) {  
	        	progressDialog.dismiss();
	        	if(uvo != null){
        			username.setText(uvo.getName());
        			imageview.setImageBitmap(uvo.getImageBitMap());
        		}
	        	if(adapterData!= null && !adapterData.isEmpty()){//显示用户名 头像
	        		//加载工程信息
		    		MXKProjectAdapter functionAdapter = new MXKProjectAdapter(context, adapterData);
		    	    listView.setAdapter(functionAdapter);	
		    		listView.setOnItemClickListener(new OnItemClickListener() {

		    			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		    					long arg3) {
		    				if(Application.USER_PROJECT_LIST != null && !Application.USER_PROJECT_LIST.isEmpty()){
		    					  Application.CURRENT_PROJECT = Application.USER_PROJECT_LIST.get(arg2);
		    		    		  projectService.navProjectView();
		    				}
		     			}

		    		});
	        	}else {
	        		showDialog(SHOW_LOAD_ERROR);
	        	}
	        }; 
		};  
		
	private void initShowData(){
		showDialog(SHOW_LOGIN_WAIT);//加载
		new Thread(new Runnable() {

			public void run() {
				uvo = Application.CURRENT_USER;
				if(uvo != null){
					adapterData = projectService.findUserProjects(uvo.getId());
				}
				Message message = new Message();  
				mainnHandler.sendMessage(message);
			} 
		}).start();
	}
	
	private void initializeComponent() {
		
		username = (TextView) this.findViewById(R.id.mxkmainviewusername);
		imageview = (ImageView) this.findViewById(R.id.mxkmainviewuserimage);
		myguanzhu = (TextView) this.findViewById(R.id.mxkmainviewmyguanzhu);
		myguanzhu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				projectService.navShowNewInforView();
			}

		});
		
		loginOut = (TextView) this.findViewById(R.id.mxkmainviewmylogout);
		loginOut.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				service.loginOut();
			}

		});
		
		listView = (ListView) this.findViewById(R.id.MXKMainViewList);
	}
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case SHOW_LOGIN_WAIT:
			return buildDialogForLoginWait();
		case SHOW_LOAD_ERROR:
			return buildDialogForLoadError();
		default:
			return null;
		}
	}
	
    private Dialog buildDialogForLoadError() {
		AlertDialog.Builder ad = new AlertDialog.Builder(context);
		ad.setMessage("没有找到相关的工程数据！");//R.string.MXKLoginActivityloginUserError
		return ad.create();
	}
	
    private  Dialog buildDialogForLoginWait() {
    	progressDialog = new ProgressDialog(this);  
    	progressDialog.setTitle("模型控");  
    	progressDialog.setMessage("正在加载数据...");  
    	progressDialog.setIndeterminate(false);  
    	progressDialog.setCancelable(false);  
        return progressDialog;  
    }
	
}
