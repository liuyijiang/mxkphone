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
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFilePath); //这样就将文件的存储方式和uri指定到了Camera应用中  
                  
                //由于我们需要调用完Camera后，可以返回Camera获取到的图片，  
                //所以，我们使用startActivityForResult来启动Camera                      
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
				 //首先取得屏幕对象  
	                Display display = this.getWindowManager().getDefaultDisplay();  
	                //获取屏幕的宽和高  
	                int dw = display.getWidth();  
	                int dh = display.getHeight();  
	                /** 
	                 * 为了计算缩放的比例，我们需要获取整个图片的尺寸，而不是图片 
	                 * BitmapFactory.Options类中有一个布尔型变量inJustDecodeBounds，将其设置为true 
	                 * 这样，我们获取到的就是图片的尺寸，而不用加载图片了。 
	                 * 当我们设置这个值的时候，我们接着就可以从BitmapFactory.Options的outWidth和outHeight中获取到值 
	                 */  
	                BitmapFactory.Options op = new BitmapFactory.Options();  
	                //op.inSampleSize = 8;  
	                op.inJustDecodeBounds = true;  
	                //Bitmap pic = BitmapFactory.decodeFile(imageFilePath, op);//调用这个方法以后，op中的outWidth和outHeight就有值了  
	                //由于使用了MediaStore存储，这里根据URI获取输入流的形式  
	                Bitmap pic = BitmapFactory.decodeStream(this  
	                        .getContentResolver().openInputStream(imageFilePath),  
	                        null, op);  
	                int wRatio = (int) Math.ceil(op.outWidth / (float) dw); //计算宽度比例  
	                int hRatio = (int) Math.ceil(op.outHeight / (float) dh); //计算高度比例  
	                /** 
	                 * 接下来，我们就需要判断是否需要缩放以及到底对宽还是高进行缩放。 
	                 * 如果高和宽不是全都超出了屏幕，那么无需缩放。 
	                 * 如果高和宽都超出了屏幕大小，则如何选择缩放呢》 
	                 * 这需要判断wRatio和hRatio的大小 
	                 * 大的一个将被缩放，因为缩放大的时，小的应该自动进行同比率缩放。 
	                 * 缩放使用的还是inSampleSize变量 
	                 */  
	                if (wRatio > 1 && hRatio > 1) {  
	                    if (wRatio > hRatio) {  
	                        op.inSampleSize = wRatio;  
	                    } else {  
	                        op.inSampleSize = hRatio;  
	                    }  
	                }  
	                op.inJustDecodeBounds = false; //注意这里，一定要设置为false，因为上面我们将其设置为true来获取图片尺寸了  
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
//			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件
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
		ad.setMessage("操作成功！");//R.string.MXKLoginActivityloginUserError
		return ad.create();
	}
	
	private Dialog buildDialogForLoginWait() {
		 progressDialog = new ProgressDialog(context);  
		 progressDialog.setTitle("模型Fan");  
		 progressDialog.setMessage("正在登陆..");  
		 progressDialog.setIndeterminate(true);  
		 progressDialog.setCancelable(true);  
        return progressDialog;  
	}
	
	
}



