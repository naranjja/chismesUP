package com.example.jose.chismesup.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.jose.chismesup.R;
import com.example.jose.chismesup.common.SaveSharedPreference;

/**
 * Splash activity
 * Created by Jose on 4/6/2016
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        findViewById(R.id.splash).setOnClickListener(new SplashClickListener());

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!isFinishing()) {
                    if (isLoggedIn()) {
                        launchMainActivity();
                    } else {
                        launchValidationActivity();
                    }
                }
            }
        }, 2000);
    }

    private boolean isLoggedIn() {
        return SaveSharedPreference.getUserName(SplashActivity.this).length() != 0;
    }

    private void launchValidationActivity() {
        Intent intent = new Intent(SplashActivity.this, ValidationActivity.class);
        startActivity(intent);
        finish();
    }

    private void launchMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class SplashClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (isLoggedIn()) {
                launchMainActivity();
            } else {
                launchValidationActivity();
            }
        }

    }
}