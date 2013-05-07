package com.mxkapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.mxkapp.common.application.Application;
import com.mxkapp.dao.comments.MxkCommentsService;
import com.mxkapp.vo.UserVO;

public class MxkAddComentViewActivity extends Activity {

	private EditText text;
	private String planid;
	private String username,projectid,ownerid;
	private final static int SHOW_OK = 4;
	private MxkCommentsService service;
	private Button addcoment,share;
	private UserVO uvo;
	private Context context = this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxkaddcomentviewactivity);
		init();
	}
	
	private void init(){
		initializeComponent();
		initData();
	}
	
	private void initData(){
		Bundle extras = getIntent().getExtras(); 
		planid = (String) extras.get("planid");
		projectid = (String) extras.get("projectid");
		username = (String) extras.get("username");
		ownerid = (String) extras.get("ownerid");
		uvo = (UserVO) Application.key.get(Application.CURRENT_USER) ;
		service = new MxkCommentsService();
	}
	
	private void initializeComponent() {
		text = (EditText) this.findViewById(R.id.mxkaddcomentmessage);
		addcoment = (Button) this.findViewById(R.id.mxkaddcomentviewadd);
		addcoment.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String message = text.getText().toString();
				if (username != null) {
					message = "回复@"+ username + " " + message;
			    }
				if(uvo != null){
					service.addComments(uvo.getId(), planid, message, projectid, ownerid);
				}
				text.setText(" ");
				showDialog(SHOW_OK);
				
			}

		});
		
//		share = (Button) this.findViewById(R.id.mxkaddcomentviewshare);
//		share.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				
//			}
//
//		});


	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case SHOW_OK:
			return buildDialogForOK();
		default:
			return null;
		}

	}
	
	private Dialog buildDialogForOK() {
		AlertDialog.Builder ad = new AlertDialog.Builder(context);
		ad.setMessage("操作成功！");//R.string.MXKLoginActivityloginUserError
		return ad.create();
	}
	
}
