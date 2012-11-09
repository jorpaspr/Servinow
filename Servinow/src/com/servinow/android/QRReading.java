package com.servinow.android;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.servinow.android.QRSystem.QRReadingSystem;

public class QRReading extends SherlockActivity {

	private QRReadingSystem qrReadingSystem;

	static {
		System.loadLibrary("iconv");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrreading);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		qrReadingSystem = new QRReadingSystem(this, ((FrameLayout) findViewById(R.id.QRReading_cameraPreview)));
		//qrReadingSystem.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		qrReadingSystem.releaseCamera();
	}

	@Override
	protected void onResume() {
		super.onResume();
		qrReadingSystem.start();
	}
}
