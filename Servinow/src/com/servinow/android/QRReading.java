package com.servinow.android;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.servinow.android.QRSystem.QRReadingSystem;
import com.servinow.android.QRSystem.QRResultCallback;

public class QRReading extends SherlockActivity implements QRResultCallback{

	private QRReadingSystem qrReadingSystem;

	/*public static enum RESULT {
		GOODREAD,
		RESTAURANTID,
		PLACEID;
	};*/

	static {
		System.loadLibrary("iconv");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrreading);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		FrameLayout cameraContainer = (FrameLayout) findViewById(R.id.QRReading_cameraPreview);
		qrReadingSystem = new QRReadingSystem(cameraContainer, this);
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

	//http://stackoverflow.com/questions/10407159/android-how-to-manage-start-activity-for-result
	@Override
	public void onAnswer(int restaurantID, int placeID) {
		/*Intent returnIntent = new Intent();
		returnIntent.putExtra(RESULT.GOODREAD.toString() , true);
		returnIntent.putExtra(RESULT.RESTAURANTID.toString(), restaurantID);
		returnIntent.putExtra(RESULT.PLACEID.toString(), placeID);
		
		setResult(RESULT_OK,returnIntent);
		
		finish();*/
	}

	@Override
	public void onBadCode() {
		/*Intent returnIntent = new Intent();
		returnIntent.putExtra(RESULT.GOODREAD.toString() , false);
		
		setResult(RESULT_OK,returnIntent);
		
		finish();*/
	}
}
