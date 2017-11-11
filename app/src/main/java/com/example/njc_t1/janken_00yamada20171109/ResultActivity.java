package com.example.njc_t1.janken_00yamada20171109;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    //前の画面に戻る
    public void onBackButtonTapped(View view){
        finish();
    }
}
