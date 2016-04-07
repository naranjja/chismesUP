package com.example.jose.chismesup.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jose.chismesup.R;

/**
 * Login activity
 * Created by Jose on 4/6/2016
 */

public class ValidationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_validation);

        findViewById(R.id.bValidate).setOnClickListener(new LoginClickListener());
    }

    private void launchMainActivity() {
        Intent intent = new Intent(ValidationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class LoginClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (validateUser()) {
                launchMainActivity();
            } else {
                showErrorMessage();
            }
        }

    }

    private boolean validateUser() {
        EditText etStudentID = (EditText) findViewById(R.id.etStudentID);
        String studentID = etStudentID.getText().toString();
        return studentID.length() == 8 && (studentID.charAt(0) == '1' || studentID.charAt(0) == '2');
    }

    private void showErrorMessage() {
        Toast.makeText(this, getString(R.string.invalid_user), Toast.LENGTH_LONG).show();
    }

}

