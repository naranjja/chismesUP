package com.example.jose.chismesup.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.jose.chismesup.R;

/**
 * Main Activity
 * Created by Jose on 4/6/2016
 */

public class MainActivity extends Activity {

    FrameLayout bSocial;
    FrameLayout bCourses;
    ImageView camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        bSocial = (FrameLayout) findViewById(R.id.bSocial);
        bCourses = (FrameLayout) findViewById(R.id.bCourses);
        camera = (ImageView) findViewById(R.id.camera);

        bSocial.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                hightlightButton(bSocial);
                unhightlightButton(bCourses);
            }
        });

        bCourses.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                hightlightButton(bCourses);
                unhightlightButton(bSocial);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });

    }

    private void hightlightButton(FrameLayout button) {
        button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLighter));
    }

    private void unhightlightButton(FrameLayout button) {
        button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
    }

}
