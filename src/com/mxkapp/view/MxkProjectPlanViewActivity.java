package com.mxkapp.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxkapp.common.application.Application;
import com.mxkapp.common.util.StringUtil;
import com.mxkapp.dao.projectplan.UserProjectPlanService;
import com.mxkapp.vo.UserProjectPlanVO;
import com.mxkapp.vo.UserVO;

public class MxkProjectPlanViewActivity extends Activity {

	private UserProjectPlanVO ppvo;
	private TextView planinfo,comment,share,palnfrom,plantime;
	private ImageView imageview,planiamge;
	private TextView username;
	private UserVO uvo;
	private Context context = this;
	
	private UserProjectPlanService planservcie;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxkprojectplanviewactivity);
		initializeComponent();
		init();
	}
	
	private void initializeComponent() {
		username = (TextView) this.findViewById(R.id.mxkprojectplanviewusername);//显示用户名
		imageview = (ImageView) this.findViewById(R.id.mxkprojectplanviewuserimage);//用户头像
		
		planiamge = (ImageView) this.findViewById(R.id.mxkprojectplanviewplanimage);
		comment = (TextView) this.findViewById(R.id.mxkprojectplanviewplancomments);//显示用户名
		share = (TextView) this.findViewById(R.id.mxkprojectplanviewplanshare);//显示用户名
		planinfo = (TextView) this.findViewById(R.id.mxkprojectplanadaptarmessage);//显示用户名
		plantime = (TextView) this.findViewById(R.id.mxkprojectplanviewplantime);//显示用户名
		palnfrom = (TextView) this.findViewById(R.id.mxkprojectplanviewplanfrom);//显示用户名
		
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
			share.setText("分享："+ppvo.getShare());
			planinfo.setText(ppvo.getInfo());
			plantime.setText(ppvo.getCreateDay() + " " + ppvo.getCreateTime()+" 发布");
			if(StringUtil.stringIsEmpty(ppvo.getPlanfrom())){
				palnfrom.setText("发布于：模型Fan web版");
			}else {
				palnfrom.setText(ppvo.getPlanfrom());
			}
		}
		uvo = Application.CURRENT_USER;
		if(uvo != null){
			username.setText(uvo.getName());
			imageview.setImageBitmap(uvo.getImageBitMap());
		}
		
		
	}
	
	
}
