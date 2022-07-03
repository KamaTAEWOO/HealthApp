package com.health.healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.health.healthapp.room.WeightAdapter;
import com.health.healthapp.room.WeightDB;
import com.health.healthapp.room.WeightResult;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SaveWeight extends AppCompatActivity {

    private EditText _et_currentWeight = null;
    private Button _btn_save = null;
    private Button _btn_first = null;
    private ImageButton _ib_logout = null;
    private ImageButton _ib_back = null;
    public static String format_yyyyMMdd = "yyyyMMdd";

    private List<WeightResult> weightList;
    private WeightDB weightDB = null;
    private Context mContext = null;
    private WeightAdapter weightAdapter;
    private Button mAddButton;
    private RecyclerView mRecyclerView;

    // 현재 몸무게
    double currentWeight = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_weight);

        init();
    }

    private void init() {
        _et_currentWeight = findViewById(R.id.et_weightValue);
        _btn_save = findViewById(R.id.btn_dataSave);
        _btn_first = findViewById(R.id.btn_first);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        _ib_back = findViewById(R.id.ib_backK);
        _ib_logout = findViewById(R.id.ib_logout);

        mContext = getApplicationContext();
        weightAdapter = new WeightAdapter(weightList);

        // DB 생성
        weightDB = WeightDB.getInstance(this);

        getData();

        //  현재 날짜 구하기
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat(format_yyyyMMdd, Locale.getDefault());
        String current = format.format(currentTime); // 현재 날짜
        
        // 과거 몸무게
        double pastWeight = Math.round(Person.getWeight());

        class InsertRunnable1 implements Runnable {

            @Override
            public void run() {
                WeightResult weight = new WeightResult();
                weight.date = current;
                weight.pastWeight = pastWeight;
                weight.currentWeight = currentWeight;
                WeightDB.getInstance(mContext).weightDao().insertAll(weight);
            }
        }

        _btn_save.setOnClickListener(new Button.OnClickListener() {
            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
            @Override
            public void onClick(View v) {
                //dataSave
                // 저장할 데이터는  날짜  /  운동 전 몸무게(Person.getWeight())  / 운동 후 몸무게(_et_currentWeight)
                // 현재 몸무게
                currentWeight = Double.parseDouble(_et_currentWeight.getText().toString());
                Log.d("weight()" , current + "  " + String.valueOf(pastWeight) + "   " +  String.valueOf(currentWeight));
                // 삽입
                InsertRunnable1 insertRunnable1 = new InsertRunnable1();
                Thread addThread = new Thread(insertRunnable1);
                addThread.start();

                // 가지고오기.
                finish();//인텐트 종료
                overridePendingTransition(0, 0);//인텐트 효과 없애기
                Intent intent = getIntent(); //인텐트
                startActivity(intent); //액티비티 열기
                overridePendingTransition(0, 0);//인텐트 효과 없애기

                toast(); //저장완료 메세지 띄우기
            }
        });

        _ib_back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KcalConversion.class);
                startActivity(intent);//액티비티 띄우기
            }
        });

        _ib_logout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);//액티비티 띄우기
            }
        });

        // 처음으로 가는 버튼
        _btn_first.setOnClickListener(new Button.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyInformation.class);
                startActivity(intent);//액티비티 띄우기
            }
        });
    }

    private void getData() {
        // main thread에서 DB 접근 불가 => data 읽고 쓸 때 thread 사용하기
        class InsertRunnable implements Runnable {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                try {
                    weightList = WeightDB.getInstance(mContext).weightDao().getAll();
                    weightAdapter = new WeightAdapter(weightList);
                    weightAdapter.notifyDataSetChanged();

                    mRecyclerView.setAdapter(weightAdapter);
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                }
                catch (Exception e) {
                    Log.d("Run()", "데이터를 가지고 오지 못 했습니다.");
                }
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();
    }
    
    private void toast() {
        Toast myToast = Toast.makeText(this.getApplicationContext(), "저장을 성공하였습니다.", Toast.LENGTH_SHORT);
        myToast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WeightDB.destroyInstance();
        weightDB = null;
    }
}