package com.mxkapp.view;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.mxkapp.common.adapter.MXKCommentsAdapter;
import com.mxkapp.common.application.Application;
import com.mxkapp.common.util.StringUtil;
import com.mxkapp.dao.projectplan.UserProjectPlanService;
import com.mxkapp.dao.vister.MxkVistirService;
import com.mxkapp.vo.UserProjectPlanVO;
import com.mxkapp.vo.UserVO;

public class MxkVisitrSeePlanViewActivity extends Activity {

	private final static int SHOW_LOGIN_WAIT = 1;
	private MxkVistirService mxkVistirService;
	private TextView addcoment;
	private ProgressDialog progressDialog;
	private ImageView imageview;
	private TextView username;//�ҵĹ�ע 
	
	private TextView projectshowmanem,plandesc,planform,planrealdesc;
	private UserProjectPlanVO ppvo;
	private UserVO uvo;
	private ListView listView;
	private Bitmap userimagemap,planimagemap;
	private ImageView userimage,planimage;
	private List<HashMap<String, Object>> adapterData;
	private TextView  comemets,share,pg;
	private UserProjectPlanService planservcie;
	private Context context = this;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mxkvistirseeplanviewactivity);
        init();
    }
	
	private void init() {
		mxkVistirService = new MxkVistirService();
		mxkVistirService.setContext(this);
		planservcie = new UserProjectPlanService();
		planservcie.setContext(this);
		initializeComponent();
	}
	
	private Handler seeHandler = new Handler() {

		public void handleMessage(Message msg) {
			
			progressDialog.dismiss();
			
			if(msg.what == 2){
				MXKCommentsAdapter functionAdapter = new MXKCommentsAdapter(context, adapterData);
	    	    listView.setAdapter(functionAdapter);	
			}else {
				userimage.setImageBitmap(userimagemap);
				planimage.setImageBitmap(planimagemap);
			}
			
		};
	};
	
	private void initializeComponent(){
		
		userimage = (ImageView) this.findViewById(R.id.mxkmainviewuserimage);//�û�ͷ��
		planimage = (ImageView) this.findViewById(R.id.mxkvistirseeplanviewplanimage);//�û�ͷ��
		
		planimage.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mxkVistirService.navShowBigImage(ppvo.getBigImageUrl());
			}

		});
		
		comemets = (TextView) this.findViewById(R.id.mxkvistirseeplanviewcomment);//�û�ͷ��
		share = (TextView) this.findViewById(R.id.mxkvistirseeplanviewshare);//�û�ͷ��
		pg = (TextView) this.findViewById(R.id.mxkvistirseeplanviewplanpg);
		planimage = (ImageView)this.findViewById(R.id.mxkvistirseeplanviewplanimage);
		planrealdesc = (TextView) this.findViewById(R.id.mxkvistirseeplanviewplandesc);//��ʾ�û���
		username = (TextView) this.findViewById(R.id.mxkvistirseeplanviewusername);//��ʾ�û���
		imageview = (ImageView) this.findViewById(R.id.mxkvistirseeplanviewuserimage);//�û�ͷ��
		uvo = (UserVO) Application.key.get(Application.CURRENT_USER);
		if(uvo != null){
			username.setText(uvo.getName());
			imageview.setImageBitmap(uvo.getImageBitMap());
		}
		projectshowmanem = (TextView) this.findViewById(R.id.mxkvistirseeplanviewname);//��ʾ�û���
		plandesc = (TextView) this.findViewById(R.id.mxkvistirseeplanviewdesc);//��ʾ�û���
		planform = (TextView) this.findViewById(R.id.mxkvistirseeplanviewfrom);//��ʾ�û���
		Bundle extras = getIntent().getExtras(); 
		ppvo = (UserProjectPlanVO) extras.get("UserProjectPlanVO");
		if (ppvo != null) {
			projectshowmanem.setText(ppvo.getOwnername()+"\\"+ppvo.getProjectName());
			pg.setText("���� "+ ppvo.getPg() + "%");
			if(StringUtil.stringIsEmpty(ppvo.getPlanfrom())){
				plandesc.setText("�����ڣ�ģ��Fan web��");
			}else {
				plandesc.setText(ppvo.getPlanfrom());
			}
			planform.setText(ppvo.getCreateDay()+" "+ppvo.getCreateTime());
			comemets.setText("���� "+ppvo.getCommints());
			comemets.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					
					showDialog(SHOW_LOGIN_WAIT);//����
					new Thread(new Runnable() {

						public void run() {
							adapterData = planservcie.findUserProjectPlanComments(adapterData, ppvo.getId());
							Message message = new Message();  
							message.what = 2;
							seeHandler.sendMessage(message);
						} 
					}).start();
					
					
				}

			});
			showDialog(SHOW_LOGIN_WAIT);//����
			//share.setText("���� "+ppvo.getShare());
			new Thread(new Runnable() {

				public void run() {
					userimagemap = mxkVistirService.getBitmapFromUrl(ppvo.getUserimage());
					planimagemap = mxkVistirService.getBitmapFromUrl(ppvo.getImageUrl());
					Message message = new Message();
					message.what = 1;
					seeHandler.sendMessage(message);
					
				}
			}).start();
		}
		listView = (ListView) this.findViewById(R.id.mxkvis);
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
		
		addcoment= (TextView) this.findViewById(R.id.mxkvistirseeplanviewaddcoment);
		addcoment.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				String planid = ppvo.getId();
				String username = uvo.getName();
				String projectid = ppvo.getProjectId();
				String ownerid = ppvo.getUserId();
				mxkVistirService.navToMxkAddComentView(planid,username,projectid,ownerid);
			}

		});
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
    	progressDialog.setTitle("ģ��Fan");  
    	progressDialog.setMessage("���ڼ�������...");  
    	progressDialog.setIndeterminate(false);  
    	progressDialog.setCancelable(false);  
        return progressDialog;  
    }
	
	
}
