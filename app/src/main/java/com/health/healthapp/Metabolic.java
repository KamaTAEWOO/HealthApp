package com.health.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Metabolic extends AppCompatActivity {

    private EditText _et_goal = null;
    private TextView _txt_metabolic = null;
    private TextView _txt_tdee = null;
    private RadioGroup _rg_weightChange = null;
    private RadioButton _rb_diet = null;
    private RadioButton _rb_bulk = null;
    private Button _btn_send = null;
    private ImageButton _ib_back = null;

    // tdee
    private double _tdee = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolic);

        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        _et_goal = findViewById(R.id.et_gole);
        _txt_metabolic = findViewById(R.id.txt_metaboilc_value);
        _txt_tdee = findViewById(R.id.txt_tdee_value);
        _rg_weightChange = findViewById(R.id.rg_weightChange);
        _rb_bulk = findViewById(R.id.r_bulk);
        _rb_diet = findViewById(R.id.r_diet);
        _btn_send = findViewById(R.id.btn_metabolic_nextPage);
        _ib_back = findViewById(R.id.ib_back);

        // 기초대사량
        double metabolic = 66 + (13.7 * Person.getWeight()) + (5 * Person.getHelm()) - (6.8 * Person.getAge());
        Log.d("Metabilic()", String.valueOf(metabolic) + " " + Person.getWeight() + " " + Person.getHelm() + " " + Person.getAge());
        _txt_metabolic.setText(String.valueOf(Math.round(metabolic)) + "kcal");
        // 유지칼로리
        _tdee = metabolic * Person.getActivity();
        Log.d("Metabilic()", String.valueOf(_tdee));
        _txt_tdee.setText(String.valueOf(Math.round(_tdee)) + "kcal");

        _btn_send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckAndNext();
            }
        });

        _ib_back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyInformation.class);
                startActivity(intent);//액티비티 띄우기
            }
        });
    }

    private void isCheckAndNext() {
        boolean isCheck = false;
        // 라디오 그룹 체크
        int rbNum = _rg_weightChange.getCheckedRadioButtonId();
        if (rbNum == _rb_diet.getId()) {
            // 다이어트
            Person.setDiet(_tdee - (_tdee * 15.0 / 100.0));
            isCheck = true;
        } else if (rbNum == _rb_bulk.getId()) {
            // 벌크업
            Person.setDiet(_tdee + (_tdee * 15.0 / 100.0));
            isCheck = true;
        }

        // 목표 체크
        if (_et_goal.getText().toString().isEmpty() && !isCheck) {
            Toast myToast = Toast.makeText(this.getApplicationContext(), "목표를 적어주세요.", Toast.LENGTH_SHORT);
            myToast.show();
        } else {
            Person.setGoal(_et_goal.getText().toString());
            Intent intent = new Intent(getApplicationContext(), KcalConversion.class);
            startActivity(intent);//액티비티 띄우기
        }
    }
}