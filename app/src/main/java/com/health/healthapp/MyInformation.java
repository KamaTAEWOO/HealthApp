package com.health.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyInformation extends AppCompatActivity {

   private EditText _txt_name = null;
   private EditText _txt_age = null;
   private EditText _txt_helm = null;
   private EditText _txt_weight = null;
   private Button _btn_send = null;
   private RadioGroup _rg_activity = null;
   private RadioButton _rb_activity1 = null;
   private RadioButton _rb_activity2 = null;
   private RadioButton _rb_activity3 = null;

    public double _radioValue = 0.0;
//    public int _ageValue = 0;
//    public double _helmValue = 0.0;
//    public double _weightValue = 0.0;
    Person person = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);

        init();
    }

    private void init() {
        _txt_name = findViewById(R.id.et_name);
        _txt_age = findViewById(R.id.et_age);
        _txt_helm = findViewById(R.id.et_helm);
        _txt_weight = findViewById(R.id.et_weight);
        _btn_send = findViewById(R.id.btn_information_nextPage);
        _rg_activity = findViewById(R.id.rg_activity);
        _rb_activity1 = findViewById(R.id.r_btn1);
        _rb_activity2 = findViewById(R.id.r_btn2);
        _rb_activity3 = findViewById(R.id.r_btn3);

        _btn_send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckAndNext();
            }
        });
    }

    private void isCheckAndNext() {
        // 라디오 그룹 체크
        int rbNum = _rg_activity.getCheckedRadioButtonId();
        if(rbNum == _rb_activity1.getId()) {
            isCheckRadioButton(1);
        } else if(rbNum == _rb_activity2.getId()) {
            isCheckRadioButton(2);
        } else {
            isCheckRadioButton(3);
        }

        // editText Check
        if (_txt_name.getText().toString().isEmpty() || _txt_age.getText().toString().isEmpty() ||
                _txt_helm.getText().toString().isEmpty() || _txt_weight.getText().toString().isEmpty() || String.valueOf(_radioValue).isEmpty()) {
            Toast myToast = Toast.makeText(this.getApplicationContext(), "빈칸을 채워주세요.", Toast.LENGTH_SHORT);
            myToast.show();
        } else {
            save(_txt_age.getText().toString(), _txt_helm.getText().toString(), _txt_weight.getText().toString(), _radioValue);
            Intent intent = new Intent(getApplicationContext(), Metabolic.class);
            startActivity(intent);//액티비티 띄우기
        }
    }

    // Person Class Save
    private void save(String age, String helm, String weight, double activity) {
        int personAge = Integer.parseInt(age);
        double personHelm = Double.parseDouble(helm);
        double personWeight = Double.parseDouble(weight);
        Log.d("save()", String.valueOf(personAge) + "  " +  String.valueOf(personHelm) + "  " + String.valueOf(personWeight) + "  " + String.valueOf(activity));
        Person.setAge(personAge);
        Person.setHelm(personHelm);
        Person.setWeight(personWeight);
        Person.setActivity(activity);
    }

    // radio Check value
    private void isCheckRadioButton(int i) {
        switch (i) {
            case 1:
                _radioValue = 1.725;
                break;
            case 2:
                _radioValue = 1.55;
                break;
            case 3:
                _radioValue = 1.2;
        }
    }
}