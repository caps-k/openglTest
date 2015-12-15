package com.aas.x32.opengltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MySurfaceView mySurfaceView = new MySurfaceView(this);
        setContentView(mySurfaceView);
    }
}
