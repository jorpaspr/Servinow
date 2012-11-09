package com.servinow.android;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.servinow.android.QRSystem.CameraPreview;
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
