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

import com.mxkapp.common.adapter.MXKShowNewInforAdapter;
import com.mxkapp.common.application.Application;
import com.mxkapp.dao.project.MxkProjectService;
import com.mxkapp.dao.vister.MxkVistirService;
import com.mxkapp.vo.UserProjectPlanVO;
import com.mxkapp.vo.UserVO;

/**
 * 用户关注界面
 * 
 * @author liuyijiang
 * 
 */
public class MxkShowNewInforViewActivity extends Activity {

	private final static int SHOW_LOGIN_WAIT = 1;
	private final static int SHOW_LOAD_ERROR = 2;
	
	private final static int LOAD_DATA = 1;
	private final static int RE_LOAD_DATA = 2;
	
	private ListView listView = null;
	private List<HashMap<String, Object>> adapterData = null;
	private Context context = this;

	private TextView myproject,loginOut,mylog;//我的工程 
	
	private ImageView imageview;
	private TextView username;//
	
	private UserVO uvo;
	private ProgressDialog progressDialog;
	private MxkVistirService mxkVistirService;
	private MxkProjectService projectService;
	private Button addmore;
	private int currentPage;
	private MXKShowNewInforAdapter functionAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxkshownewinforviewactivity);
		init();
	}
	
	 private Handler vistirtHandler = new Handler(){  
	        
	        public void handleMessage(Message msg) {  
	        	
	        	int action = msg.what;
	        	if (action == LOAD_DATA) {
	        		if(adapterData != null){
	        			functionAdapter = new MXKShowNewInforAdapter(context,adapterData);
	        			listView.setAdapter(functionAdapter);
	        			currentPage = currentPage + 1;
	        		}
	        	} else if( action == RE_LOAD_DATA) {
	        		functionAdapter.notifyDataSetChanged();
	        	}
	        	progressDialog.dismiss();
	        }; 
		};  
		
	
	private void init() {
		mxkVistirService = new MxkVistirService();
		mxkVistirService.setContext(this);
		projectService = new MxkProjectService();
		projectService.setContext(this);
		initializeComponent();
		initData();
	}
	
	private void initData() {
		uvo = (UserVO) Application.key.get(Application.CURRENT_USER);
		if(uvo != null){
			username.setText(uvo.getName());
			imageview.setImageBitmap(uvo.getImageBitMap());
		}
		
		new Thread(new Runnable() {
           
			public void run() {
				if(uvo != null){
					currentPage = 1;
					adapterData = mxkVistirService.findNewInforPlans(adapterData,uvo.getId(),currentPage);
				}
				Message message = new Message();  
				message.what = LOAD_DATA;
				vistirtHandler.sendMessage(message);
			} 
		}).start();
		showDialog(SHOW_LOGIN_WAIT);
	}
	
	
	private void initializeComponent() {
		
		myproject = (TextView) this.findViewById(R.id.mxkmainviewmyguanzhu);
		myproject.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				projectService.navToMainView();
			}

		});
		
		
		mylog = (TextView) this.findViewById(R.id.mxkmainviewmylog);
		mylog.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				projectService.navMxkProjectLogView();
			}

		});
		
		
		loginOut = (TextView) this.findViewById(R.id.mxkmainviewmylogout);
		loginOut.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				projectService.loginOut();
			}

		});
		
		
		username = (TextView) this.findViewById(R.id.mxkshownewinforviewusername);
		imageview = (ImageView) this.findViewById(R.id.mxkshownewinforviewuserimage);
		listView = (ListView) this.findViewById(R.id.mxknewinforview);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				UserProjectPlanVO vo = (UserProjectPlanVO)adapterData.get(arg2).get("UserProjectPlanVO");
				mxkVistirService.navToMxkVisitrSeePlanView(vo);
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
		ad.setMessage("没有找到相关的数据！");
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
