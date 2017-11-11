package com.example.njc_t1.janken_00yamada20171109;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //前のアクティビティで選んだ手を、idに取得する。（インテントに格納されている情報を取得する。）
        Intent intent = getIntent();
        int id = intent.getIntExtra("MY_HAND",0);

        //idから、前のアクティビティで選んだ手を表示する
        ImageView myHandImageView = (ImageView) findViewById(R.id.my_hand_image);
        switch (id) {
            case R.id.gu:
                myHandImageView.setImageResource(R.drawable.gu);
                break;
            case R.id.choki:
                myHandImageView.setImageResource(R.drawable.choki);
                break;
            case R.id.pa:
                myHandImageView.setImageResource(R.drawable.pa);
                break;
            default:
                break;
        }
    }

    //前の画面に戻る
    public void onBackButtonTapped(View view){
        finish();
    }
}
