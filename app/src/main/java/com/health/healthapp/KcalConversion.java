package com.health.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class KcalConversion extends AppCompatActivity {

    private TextView _txt_goalView = null;
    private TextView _txt_carbohydrate = null;
    private TextView _txt_fat = null;
    private TextView _txt_protein = null;
    private Button _btn_send = null;
    private ImageButton _ib_backK = null;
    private Button _btn_valueChange = null;
    private TextView _txt_goalKcal = null;

    private boolean isCheck = false;
    private double _g_carbohydrate = 0.0;
    private double _g_fat = 0.0;
    private double _g_protein = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcal_conversion);

        init();
    }

    @SuppressLint("WrongViewCast")
    private void init() {
        _txt_goalView = findViewById(R.id.txt_goalContent);
        _txt_carbohydrate = findViewById(R.id.txt_carbohydrate_value);
        _txt_fat = findViewById(R.id.txt_fat_value);
        _txt_protein = findViewById(R.id.txt_protein_value);
        _btn_send = findViewById(R.id.btn_kcal_nextPage);
        _ib_backK = findViewById(R.id.ib_backK);
        _btn_valueChange = findViewById(R.id.btn_GorKcalChange);
        _txt_goalKcal = findViewById(R.id.txt_goalKcal_value);

        _txt_goalView.setText(Person.getGoal());

        GToKcal();

        _btn_send.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SaveWeight.class);
                startActivity(intent);//액티비티 띄우기
            }
        });

        _ib_backK.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Metabolic.class);
                startActivity(intent);//액티비티 띄우기
            }
        });

        // kcal -> g or g -> kcal
        _btn_valueChange.setOnClickListener(new Button.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(!isCheck) {
                    // g
                    _txt_carbohydrate.setText(String.valueOf(Math.round(_g_carbohydrate)) + "g");
                    _txt_fat.setText(String.valueOf(Math.round(_g_fat)) + "g");
                    _txt_protein.setText(String.valueOf(Math.round(_g_protein)) + "g");
                    _btn_valueChange.setText("K");
                    isCheck = true;
                }else {
                    GToKcal();
                    _btn_valueChange.setText("G");
                    isCheck = false;
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void GToKcal() {
        double carbohydrate = 0.0;
        double fat = 0.0;
        double protein = 0.0;
        if(Person.getDiet() != 0.0) {
            // Diet 일 때
            _txt_goalKcal.setText(String.valueOf(Math.round(Person.getDiet())) + "Kcal");
            // 탄수화물
            carbohydrate = Person.getDiet() * 20.0 / 100.0;
            // 지방
            fat = Person.getDiet() * 30.0 / 100.0;
            // 단백질
            protein = Person.getDiet() * 50.0 / 100.0;
        } else {
            // Bulk 일 때
            _txt_goalKcal.setText(String.valueOf(Person.getBulk()) + "Kcal");
            // 탄수화물
            carbohydrate = Person.getBulk() * 20.0 / 100.0;
            // 지방
            fat = Person.getBulk() * 30.0 / 100.0;
            // 단백질
            protein = Person.getBulk() * 50.0 / 100.0;
        }

        // g계산
        _g_carbohydrate = carbohydrate / 9;
        _g_fat = fat / 4;
        _g_protein = protein / 4;

        _txt_carbohydrate.setText(String.valueOf(Math.round(carbohydrate)) + "Kcal");
        _txt_fat.setText(String.valueOf(Math.round(fat)) + "Kcal");
        _txt_protein.setText(String.valueOf(Math.round(protein)) + "Kcal");
    }
}