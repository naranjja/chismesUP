package com.example.jose.chismesup.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jose.chismesup.R;
import com.example.jose.chismesup.common.SaveSharedPreference;

import java.util.Random;

/**
 * Login activity
 * Created by Jose on 4/6/2016
 */

public class ValidationActivity extends Activity {

    FrameLayout bValidate;
    EditText etEmail;
    String email;
    public static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_validation);

        bValidate = (FrameLayout) findViewById(R.id.bValidate);
        bValidate.setOnClickListener(new LoginClickListener());

        etEmail = (EditText) findViewById(R.id.etEmail);


        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    bValidate.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private void launchMainActivity() {
        Intent intent = new Intent(ValidationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class LoginClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            email = etEmail.getText().toString();
            hightlightButton(bValidate);

            if (validateUser(email)) {
                setUser(email);
                launchMainActivity();

            } else {
                showErrorMessage();
                unhightlightButton(bValidate);
            }
        }

    }

    private boolean validateUser(String email) {
        return !email.isEmpty() && email.contains("@alum.up.edu.pe");
    }

    private void showErrorMessage() {
        Toast.makeText(this, getString(R.string.invalid_user), Toast.LENGTH_LONG).show();
    }

    private void hightlightButton(FrameLayout button) {
        button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLighter));
    }

    private void unhightlightButton(FrameLayout button) {
        button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
    }

    private void setUser(String email) {
        Random rand = new Random();
        user = email.substring(0, 1) + email.substring(2, 5) +  Integer.toHexString(rand.nextInt(0x10) + 0x10);
        SaveSharedPreference.setUserName(ValidationActivity.this, user);
    }

}

