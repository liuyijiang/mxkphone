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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mxkapp.common.adapter.MXKProjectPlanAdpater;
import com.mxkapp.common.application.Application;
import com.mxkapp.dao.project.MxkProjectService;
import com.mxkapp.dao.projectplan.UserProjectPlanService;
import com.mxkapp.vo.UserProjectPlanVO;
import com.mxkapp.vo.UserProjectVO;
import com.mxkapp.vo.UserVO;

public class MxkProjectViewActivity extends Activity {

	private final static int SHOW_LOGIN_WAIT = 1;
	private final static int SHOW_LOAD_ERROR = 2;
	
	private final static int LOAD_DATA = 1;
	private final static int RE_LOAD_DATA = 2;
	
	private ListView listView = null;
	private TextView createPlan,projectstuts,projetcname,projectdesc;
	private List<HashMap<String, Object>> adapterData = null;
	private Context context = this;

	private ImageView imageview;
	private TextView username;
	private Button addmore;
	
	private MxkProjectService service;
	private UserProjectPlanService planservcie;
    private UserProjectVO pvo;
    private UserVO uvo;
    private ProgressDialog progressDialog;
    private MXKProjectPlanAdpater functionAdapter;
    private int currentPage;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxkprojectviewactivity);
		init();
	}

	private void init() {
		service = new MxkProjectService();
		planservcie = new UserProjectPlanService();
		service.setContext(context);
		planservcie.setContext(context);
		initializeComponent();
		initData();
	} 

	private void initData() {
		uvo = (UserVO) Application.key.get(Application.CURRENT_USER);
		if(uvo != null){
			username.setText(uvo.getName());
			imageview.setImageBitmap(uvo.getImageBitMap());
		}
		pvo  = (UserProjectVO) Application.key.get(Application.CURRENT_PROJECT);
		if(pvo != null){
			projectstuts.setText("进度数量："+pvo.getPlans()+" | "+"总进度："+pvo.getProgress() + "%");
			projetcname.setText(pvo.getName()+"( "+pvo.getType() + " )");
			projectdesc.setText(pvo.getDesc());
		}
		showDialog(SHOW_LOGIN_WAIT);//加载
		
		new Thread(new Runnable() {

			public void run() {
				if(uvo != null){
					currentPage = 1;
					adapterData = planservcie.findUserProjectPlans(adapterData,pvo.getId(),currentPage);
				}
				Message message = new Message();  
				message.what = LOAD_DATA;
				projectHandler.sendMessage(message);
			} 
		}).start();
		
	}
	
	// 异步加载数据
	private Handler projectHandler = new Handler(){  
        
        public void handleMessage(Message msg) {  
        	
        	int action = msg.what;
        	if (action == LOAD_DATA) {
        		if(adapterData != null){
        			functionAdapter = new MXKProjectPlanAdpater(
        					context, adapterData);
        			listView.setAdapter(functionAdapter);
        		}
        	} else if( action == RE_LOAD_DATA) {
        		functionAdapter.notifyDataSetChanged();
        	}
        	currentPage = currentPage + 1;
        	progressDialog.dismiss();
        }; 
	};  
	
	
	private void initializeComponent() {

		username = (TextView) this.findViewById(R.id.mxkprojectviewusername);//显示用户名
		imageview = (ImageView) this.findViewById(R.id.mxkprojectviewuserimage);//用户头像
		
		projectstuts = (TextView) this.findViewById(R.id.mxkprojectviewprojectstatus);//显示用户名
		projetcname = (TextView) this.findViewById(R.id.mxkprojectviewprojectname);//显示用户名
		projectdesc = (TextView) this.findViewById(R.id.mxkprojectviewprojectdesc);//显示用户名
		
		createPlan = (TextView) this.findViewById(R.id.mxkprojectviewcreateplan);
		createPlan.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				service.navCreateProjectPlanView();
			}

		});
		listView = (ListView) this.findViewById(R.id.mxkprojectviewplans);

		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				UserProjectPlanVO vo = (UserProjectPlanVO)adapterData.get(arg2).get("userProjectPlanVO");
				service.navMxkProjectPlanView(vo);
			}

		});
    	
		addmore = (Button) this.findViewById(R.id.mxkprojectviewAddMore);
		addmore.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				showDialog(SHOW_LOGIN_WAIT);
				new Thread( new Runnable() {     
					public void run() {  
						
						adapterData = planservcie.findUserProjectPlans(adapterData,pvo.getId(),currentPage);
				    	Message message = new Message();
				    	message.what = RE_LOAD_DATA;
				    	projectHandler.sendMessage(message);
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
    	progressDialog.setIndeterminate(true);  
    	progressDialog.setCancelable(true);  
        return progressDialog;  
    }
	
	
	

}
