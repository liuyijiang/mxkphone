package com.mxkapp.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxkapp.common.application.Application;
import com.mxkapp.dao.projectplan.UserProjectPlanService;
import com.mxkapp.vo.UserProjectVO;
import com.mxkapp.vo.UserVO;

public class MxkCreateProjectPlanViewActivity extends Activity {

	
	private TextView takPicture;
	private final static int SHOW_LOGIN_WAIT = 1;
	private final static int SHOW_OK = 4;
	private Uri imageFilePath; 
	private static final int RESULT_CODE = 1;  
    private ImageView imageView,test; 
    private Button create;
	private Context context = this;
	
	private UserProjectPlanService planservcie;
    private String filepath = Environment.getExternalStorageDirectory().getAbsolutePath()+"//mxk//"+"cash.png";
	private UserVO uvo;
	private UserProjectVO pvo;
	private EditText pg,info;
	private ProgressDialog progressDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxkcreateprojectplanview);
		initializeComponent();
		//initSystrmPath(context);
		init();
	}
	
	private void init(){
		planservcie = new UserProjectPlanService();
		planservcie.setContext(context);
		uvo = (UserVO) Application.key.get(Application.CURRENT_USER);
		pvo  = (UserProjectVO) Application.key.get(Application.CURRENT_PROJECT);
	}
	
	public void initializeComponent() {
//		createFileMkdir();
		pg = (EditText) this.findViewById(R.id.mXKWorkPlaningAddViewActivityPlaning);
		info = (EditText) this.findViewById(R.id.mXKWorkPlaningAddViewActivityDescrible);
		imageView = (ImageView) this.findViewById(R.id.mxkcreateprojectplanviewpicture);
		test =  (ImageView) this.findViewById(R.id.test);
		
		takPicture = (TextView) this.findViewById(R.id.mxkcreateprojectplanviewtakepicture);
		takPicture.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
				//Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
				ContentValues values = new ContentValues(3);  
                values.put(MediaStore.Images.Media.DISPLAY_NAME, "testing");  
                values.put(MediaStore.Images.Media.DESCRIPTION, "this is description");  
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");  
                
                imageFilePath = MxkCreateProjectPlanViewActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);  
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFilePath); //�����ͽ��ļ��Ĵ洢��ʽ��uriָ������CameraӦ����  
                  
                //����������Ҫ������Camera�󣬿��Է���Camera��ȡ����ͼƬ��  
                //���ԣ�����ʹ��startActivityForResult������Camera                      
                startActivityForResult(intent, RESULT_CODE); 
				
			}

		});
		
		create = (Button) this.findViewById(R.id.mxkcreateprojectplanviewcreate);
		create.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				showDialog(SHOW_LOGIN_WAIT);
				
				String model = android.os.Build.MODEL;
				showDialog(SHOW_LOGIN_WAIT);
				planservcie.createProjectPlan(filepath,pg.getText().toString(), info.getText().toString(), uvo.getId(),pvo.getId(),model,pvo.getName());
				progressDialog.dismiss();
				info.setText("");
				pg.setText("");
				progressDialog.dismiss();
				showDialog(SHOW_OK);
				
			}

		});
		
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == RESULT_CODE){ 
			 try {  
	                //Bundle extra = data.getExtras();
				 //����ȡ����Ļ����  
	                Display display = this.getWindowManager().getDefaultDisplay();  
	                //��ȡ��Ļ�Ŀ�͸�  
	                int dw = display.getWidth();  
	                int dh = display.getHeight();  
	                /** 
	                 * Ϊ�˼������ŵı�����������Ҫ��ȡ����ͼƬ�ĳߴ磬������ͼƬ 
	                 * BitmapFactory.Options������һ�������ͱ���inJustDecodeBounds����������Ϊtrue 
	                 * ���������ǻ�ȡ���ľ���ͼƬ�ĳߴ磬�����ü���ͼƬ�ˡ� 
	                 * �������������ֵ��ʱ�����ǽ��žͿ��Դ�BitmapFactory.Options��outWidth��outHeight�л�ȡ��ֵ 
	                 */  
	                BitmapFactory.Options op = new BitmapFactory.Options();  
	                //op.inSampleSize = 8;  
	                op.inJustDecodeBounds = true;  
	                //Bitmap pic = BitmapFactory.decodeFile(imageFilePath, op);//������������Ժ�op�е�outWidth��outHeight����ֵ��  
	                //����ʹ����MediaStore�洢���������URI��ȡ����������ʽ  
	                Bitmap pic = BitmapFactory.decodeStream(this  
	                        .getContentResolver().openInputStream(imageFilePath),  
	                        null, op);  
	                int wRatio = (int) Math.ceil(op.outWidth / (float) dw); //�����ȱ���  
	                int hRatio = (int) Math.ceil(op.outHeight / (float) dh); //����߶ȱ���  
	                /** 
	                 * �����������Ǿ���Ҫ�ж��Ƿ���Ҫ�����Լ����׶Կ��Ǹ߽������š� 
	                 * ����ߺͿ���ȫ����������Ļ����ô�������š� 
	                 * ����ߺͿ���������Ļ��С�������ѡ�������ء� 
	                 * ����Ҫ�ж�wRatio��hRatio�Ĵ�С 
	                 * ���һ���������ţ���Ϊ���Ŵ��ʱ��С��Ӧ���Զ�����ͬ�������š� 
	                 * ����ʹ�õĻ���inSampleSize���� 
	                 */  
	                if (wRatio > 1 && hRatio > 1) {  
	                    if (wRatio > hRatio) {  
	                        op.inSampleSize = wRatio;  
	                    } else {  
	                        op.inSampleSize = hRatio;  
	                    }  
	                }  
	                op.inJustDecodeBounds = false; //ע�����һ��Ҫ����Ϊfalse����Ϊ�������ǽ�������Ϊtrue����ȡͼƬ�ߴ���  
	                pic = BitmapFactory.decodeStream(this.getContentResolver()  
	                        .openInputStream(imageFilePath), null, op);  
	               // imageView.setImageBitmap(pic);  
	                test.setImageBitmap(pic); 
	                saveMyBitmap(pic);
	                
			 }catch (Exception e) {
				 e.printStackTrace();
			 }       
		}
//		Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//		String sdStatus = Environment.getExternalStorageState();
//		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
//			return;
//		}
//		SDPATH = Environment.getExternalStorageDirectory().getPath();
//		File file = new File(SDPATH +"//mxk//" + "test.jpg");
//		try{
//			fos = new FileOutputStream(file);
//			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// ������д���ļ�
//			fos.close();
//		} catch (Exception e){
//			e.printStackTrace();
//		}
		
	}
	
	public void saveMyBitmap(Bitmap mBitmap) throws IOException {
        File f = new File(filepath);
        f.createNewFile();
        FileOutputStream fOut = null;
        try {
                fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
                fOut.flush();
        } catch (IOException e) {
                e.printStackTrace();
        }
        try {
                fOut.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
}
	

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case SHOW_LOGIN_WAIT:
			return buildDialogForLoginWait();
		case SHOW_OK:
			return buildDialogForOK();
		default:
			return null;
		}

	}
	
	private Dialog buildDialogForOK() {
		AlertDialog.Builder ad = new AlertDialog.Builder(context);
		ad.setMessage("�����ɹ���");//R.string.MXKLoginActivityloginUserError
		return ad.create();
	}
	
	private Dialog buildDialogForLoginWait() {
		 progressDialog = new ProgressDialog(context);  
		 progressDialog.setTitle("ģ��Fan");  
		 progressDialog.setMessage("���ڵ�½..");  
		 progressDialog.setIndeterminate(true);  
		 progressDialog.setCancelable(true);  
        return progressDialog;  
	}
	
	
}



