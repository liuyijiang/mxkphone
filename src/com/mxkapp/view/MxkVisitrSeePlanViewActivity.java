package com.mxkapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.mxkapp.dao.vister.MxkVistirService;

public class MxkVisitrSeePlanViewActivity extends Activity {

	private MxkVistirService mxkVistirService;
	private TextView addcoment;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mxkvistirseeplanviewactivity);
        init();
    }
	
	private void init() {
		mxkVistirService = new MxkVistirService();
		mxkVistirService.setContext(this);
		initializeComponent();
	}
	
	private void initializeComponent(){
		addcoment= (TextView) this.findViewById(R.id.mxkvistirseeplanviewaddcoment);
		addcoment.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mxkVistirService.navToMxkAddComentView("ss");
			}

		});
	}
}
