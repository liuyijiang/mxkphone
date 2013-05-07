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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mxkapp.common.adapter.MXKProjectLogAdapter;
import com.mxkapp.common.application.Application;
import com.mxkapp.dao.project.MxkProjectService;
import com.mxkapp.vo.UserVO;

public class MxkProjectLogViewActivity extends Activity {

	private final static int SHOW_LOGIN_WAIT = 1;

	private ProgressDialog progressDialog;
	private UserVO uvo;
	private Context context = this;
	private ListView listView;
	private List<HashMap<String, Object>> adapterData;
	private MxkProjectService service;

	private ImageView imageview;
	private TextView username;//我的关注 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxkprojectlogviewactivity);
		init();
	}

	private void init() {
		service = new MxkProjectService();
		service.setContext(this);
		initializeComponent();
		initShowData();
	}

	private Handler logHandler = new Handler() {

		public void handleMessage(Message msg) {
			progressDialog.dismiss();
			if(adapterData != null){
				MXKProjectLogAdapter functionAdapter = new MXKProjectLogAdapter(context, adapterData);
	    	    listView.setAdapter(functionAdapter);	
			}
		};
	};

	private void initializeComponent() {
		
		username = (TextView) this.findViewById(R.id.mxkprojectlogvieusername);//显示用户名
		imageview = (ImageView) this.findViewById(R.id.mxkprojectlogviewuserimage);//用户头像
		
		listView = (ListView) this.findViewById(R.id.mxkprojectlogvielist);
	}
	
	private void initShowData() {
		showDialog(SHOW_LOGIN_WAIT);// 加载
		
		uvo = (UserVO) Application.key.get(Application.CURRENT_USER);
		if(uvo != null){
			username.setText(uvo.getName());
			imageview.setImageBitmap(uvo.getImageBitMap());
		}
		
		new Thread(new Runnable() {

			public void run() {
				uvo = (UserVO) Application.key.get(Application.CURRENT_USER);
				if (uvo != null) {
					adapterData = service.findUserProjectLogs(uvo.getId());
				}
				Message message = new Message();
				logHandler.sendMessage(message);
			}
		}).start();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case SHOW_LOGIN_WAIT:
			return buildDialogForLogWait();
		default:
			return null;
		}

	}

	private Dialog buildDialogForLogWait() {
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("模型控");
		progressDialog.setMessage("正在登陆..");
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		return progressDialog;
	}

}
