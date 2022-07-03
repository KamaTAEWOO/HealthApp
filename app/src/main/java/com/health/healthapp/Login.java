package com.health.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private EditText _et_id = null;
    private EditText _et_password = null;
    private Button _btn_login = null;
    private TextView _txt_loginFail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        _et_id = findViewById(R.id.et_id);
        _et_password = findViewById(R.id.et_password);
        _btn_login = findViewById(R.id.btn_login);
        _txt_loginFail = findViewById(R.id.txt_loginFail);

        _btn_login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(); //로그인
            }
        });
    }

    private void login() {
        String id = "id";
        String password = "pass";
        boolean isCheck = false;

        if (id.equals(_et_id.getText().toString()) && password.equals(_et_password.getText().toString())) {
            isCheck = true;
        } else {
            _txt_loginFail.setVisibility(View.VISIBLE);
        }

        // 다음페이지 이동
        if(isCheck) {
            Intent intent = new Intent(getApplicationContext(),MyInformation.class);
            startActivity(intent);//액티비티 띄우기
        }
    }
}