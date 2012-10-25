package com.servinow.android;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;

public class MainActivity extends SherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); /*A esta actividad/ventana le pones este layout*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { /*Al pulsar la tecla menú o al generarse "el menú" de la app*/
        getSupportMenuInflater().inflate(R.menu.activity_main, menu);/*Al "menú" correspondiente le pones este menú (activity_main)*/
        return true;
    }
}
