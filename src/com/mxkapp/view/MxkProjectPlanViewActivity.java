package com.mxkapp.view;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
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

import com.mxkapp.common.adapter.MXKCommentsAdapter;
import com.mxkapp.common.application.Application;
import com.mxkapp.common.util.StringUtil;
import com.mxkapp.dao.projectplan.UserProjectPlanService;
import com.mxkapp.vo.UserProjectPlanVO;
import com.mxkapp.vo.UserVO;

public class MxkProjectPlanViewActivity extends Activity {
	
	private final static int SHOW_LOGIN_WAIT = 1;
	private UserProjectPlanVO ppvo;
	private TextView planinfo,comment,share,palnfrom,plantime;
	private ImageView imageview,planiamge;
	private TextView username;
	private UserVO uvo;
	private Context context = this;
	private ListView listView;
	private List<HashMap<String, Object>> adapterData;
	private UserProjectPlanService planservcie;
	private ProgressDialog progressDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxkprojectplanviewactivity);
		initializeComponent();
		init();
	}
	
	 private Handler mainnHandler = new Handler(){  
         
	        public void handleMessage(Message msg) {  
	        	progressDialog.dismiss();
	        	if(adapterData!= null && !adapterData.isEmpty()){//显示用户名 头像
	        		//加载工程信息
	        		MXKCommentsAdapter functionAdapter = new MXKCommentsAdapter(context, adapterData);
		    	    listView.setAdapter(functionAdapter);	
	        	}
	        }; 
		};  
	
	private void initializeComponent() {
		username = (TextView) this.findViewById(R.id.mxkprojectplanviewusername);//显示用户名
		imageview = (ImageView) this.findViewById(R.id.mxkprojectplanviewuserimage);//用户头像
	
		planiamge = (ImageView) this.findViewById(R.id.mxkprojectplanviewplanimage);
		
		comment = (TextView) this.findViewById(R.id.mxkprojectplanviewplancomments);//显示用户名
		comment.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				showDialog(SHOW_LOGIN_WAIT);//加载
				new Thread(new Runnable() {

					public void run() {
						adapterData = planservcie.findUserProjectPlanComments(adapterData, ppvo.getId());
						Message message = new Message();  
						mainnHandler.sendMessage(message);
					} 
				}).start();
				
				
			}

		});
		
		share = (TextView) this.findViewById(R.id.mxkprojectplanviewplanshare);//显示用户名
		planinfo = (TextView) this.findViewById(R.id.mxkprojectplanadaptarmessage);//显示用户名
		plantime = (TextView) this.findViewById(R.id.mxkprojectplanviewplantime);//显示用户名
		palnfrom = (TextView) this.findViewById(R.id.mxkprojectplanviewplanfrom);//显示用户名
		listView = (ListView) this.findViewById(R.id.mxkprojectviewplancomments);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String planid = (String) adapterData.get(arg2).get("planid");
				String username = (String) adapterData.get(arg2).get("username");
				String projectid = (String) adapterData.get(arg2).get("projectid");
				String ownerid = (String) adapterData.get(arg2).get("ownerid");
				planservcie.navToMxkAddComentView(planid,username,projectid,ownerid);
 			}

		});
	}
	
	private void init(){
		planservcie = new UserProjectPlanService();
		planservcie.setContext(context);
		initData();
	}
	
	private void initData() {
		Bundle extras = getIntent().getExtras(); 
		ppvo = (UserProjectPlanVO) extras.get("UserProjectPlanVO");
		if (ppvo != null) {
			planiamge.setImageBitmap(planservcie.getBitmapFromUrl(ppvo.getImageUrl()));
			comment.setText("评论："+ppvo.getCommints()+" ");
			//share.setText("分享："+ppvo.getShare());
			planinfo.setText(ppvo.getInfo());
			plantime.setText(ppvo.getCreateDay() + " " + ppvo.getCreateTime()+" 发布");
			if(StringUtil.stringIsEmpty(ppvo.getPlanfrom())){
				palnfrom.setText("发布于：模型Fan web版");
			}else {
				palnfrom.setText(ppvo.getPlanfrom());
			}
			
			planiamge.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					planservcie.navShowBigImage(ppvo.getBigImageUrl());
				}

			});
			
			
		}
		uvo = (UserVO) Application.key.get(Application.CURRENT_USER);
		if(uvo != null){
			username.setText(uvo.getName());
			imageview.setImageBitmap(uvo.getImageBitMap());
		}
		
		
	}
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case SHOW_LOGIN_WAIT:
			return buildDialogForLoginWait();
		default:
			return null;
		}
	}
	
    private  Dialog buildDialogForLoginWait() {
    	progressDialog = new ProgressDialog(this);  
    	progressDialog.setTitle("模型Fan");  
    	progressDialog.setMessage("正在加载数据...");  
    	progressDialog.setIndeterminate(false);  
    	progressDialog.setCancelable(false);  
        return progressDialog;  
    }
	
}
