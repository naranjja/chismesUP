package com.example.jose.chismesup.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jose.chismesup.Comment;
import com.example.jose.chismesup.R;
import com.example.jose.chismesup.adapter.CommentAdapter;
import com.example.jose.chismesup.common.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Activity
 * Created by Jose on 4/6/2016
 */

public class MainActivity extends Activity {

    FrameLayout bSocial;
    FrameLayout bCourses;
    FrameLayout bLogout;
    ImageView camera;
    ListView lvComments;
    EditText etComment;
    List<Comment> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        bSocial = (FrameLayout) findViewById(R.id.bSocial);
        bCourses = (FrameLayout) findViewById(R.id.bCourses);
        bLogout = (FrameLayout) findViewById(R.id.bLogout);
        camera = (ImageView) findViewById(R.id.camera);
        etComment = (EditText) findViewById(R.id.etComment);

        etComment.setHorizontallyScrolling(false);
        etComment.setMaxLines(Integer.MAX_VALUE);

        lvComments = (ListView) findViewById(R.id.lvComments);

        commentList = new ArrayList<>();
        CommentAdapter commentAdapter;

        commentAdapter = new CommentAdapter(commentList, this);
        lvComments.setAdapter(commentAdapter);

        etComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String comment = etComment.getText().toString();
                    commentList.add(new Comment(SaveSharedPreference.getUserName(MainActivity.this), comment));
                    lvComments.invalidateViews();
                    return true;
                }
                return false;
            }
        });

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

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
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

    public void logOut() {
        SaveSharedPreference.clearUserName(MainActivity.this);
        launchValidationActivity();
    }

    private void launchValidationActivity() {
        Intent intent = new Intent(MainActivity.this, ValidationActivity.class);
        startActivity(intent);
        finish();
    }

}
