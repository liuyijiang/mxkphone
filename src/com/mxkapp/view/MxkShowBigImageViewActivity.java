package com.mxkapp.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mxkapp.dao.projectplan.UserProjectPlanService;

public class MxkShowBigImageViewActivity extends Activity {

	private UserProjectPlanService planservcie;
	private final static int SHOW_LOGIN_WAIT = 3;
    private Context context = this;
	private Bitmap bigimage = null;
	private ProgressDialog progressDialog;
	private String imageurl;
	private ImageView image;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxkshowbigimageviewactivity);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		initializeComponent();
		init();
		
	}
	
	private void init(){
		planservcie = new UserProjectPlanService();
		planservcie.setContext(context);
		initData();
	}
	
	 private Handler iamgeHandler = new Handler(){  
         
	        public void handleMessage(Message msg) {  
	        	
	        	image.setImageBitmap(bigimage);
	        	//progressDialog.dismiss();
	        	
	        }; 
		};  
	
	public void initializeComponent() {
		image = (ImageView) this.findViewById(R.id.bigimage);
	}
	
	public void initData(){
		
		Bundle extras = getIntent().getExtras(); 
		imageurl = (String) extras.get("iamgeurl");
		
		//showDialog(SHOW_LOGIN_WAIT);
		new Thread( new Runnable() {     
			public void run() {     
				bigimage = planservcie.getBitmapFromUrl(imageurl);
		    	Message message = new Message();
		    	iamgeHandler.sendMessage(message);
		    }            
		}).start(); 
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

	private Dialog buildDialogForLoginWait() {
		 progressDialog = new ProgressDialog(context);  
		 progressDialog.setTitle("模型控");  
		 progressDialog.setMessage("正在加载..");  
		 progressDialog.setIndeterminate(true);  
		 progressDialog.setCancelable(true);  
         return progressDialog;  
	}
	
}
