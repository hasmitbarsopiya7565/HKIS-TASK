package com.example.hkinfosoft.SplashScreen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.hkinfosoft.R;
import com.example.hkinfosoft.Users.Activity.Users;
import com.example.hkinfosoft.Util.Helper;

public class SplashScreen extends AppCompatActivity {

    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //bind
        bind();

        //action
        action();
    }

    private void action() {
        //show full screen
        helper.showFullScreen(SplashScreen.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, Users.class));
                finish();
            }
        },3000);
    }

    private void bind() {
        helper = new Helper();
    }
}