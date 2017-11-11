package com.example.njc_t1.janken_00yamada20171109;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    //グー、チョキ、パーの設定
    final int JANKEN_GU = 0;
    final int JANKEN_CHOKI = 1;
    final int JANKEN_PA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //前のアクティビティで選んだ手を、idに取得する。（インテントに格納されている情報を取得する。）
        Intent intent = getIntent();
        int id = intent.getIntExtra("MY_HAND",0);

        //自分の手を格納する変数の宣言
        int myHand = 0;

        //idから、前のアクティビティで選んだ手を表示して、変数の格納しておく
        ImageView myHandImageView = (ImageView) findViewById(R.id.my_hand_image);     //ImageViewの参照値を取得する。findViewById()メソッドで。
        switch (id) {
            case R.id.gu:
                myHandImageView.setImageResource(R.drawable.gu);     //グーの画像を表示する
                myHand = JANKEN_GU;     //グーを保存
                break;
            case R.id.choki:
                myHandImageView.setImageResource(R.drawable.choki);     //チョキの画像を表示する
                myHand = JANKEN_CHOKI;     //チョキを保存
                break;
            case R.id.pa:
                myHandImageView.setImageResource(R.drawable.pa);     //パーの画像を表示する
                myHand = JANKEN_PA;     //パーを保存
                break;
            default:
                break;
        }

        // コンピュータの手を決める（乱数）
        int comHand =(int) (Math.random() * 3);

        //コンピュータの手を表示する。
        ImageView comHandImageView =
                (ImageView) findViewById(R.id.com_hand_image);     //ImageViewの参照値を取得する。findViewById()メソッドで。
        switch (comHand) {
            case JANKEN_GU:
                comHandImageView.setImageResource(R.drawable.com_gu);     //グーの画像を表示する
                break;
            case JANKEN_CHOKI:
                comHandImageView.setImageResource(R.drawable.com_choki);     //チョキの画像を表示する
                break;
            case JANKEN_PA:
                comHandImageView.setImageResource(R.drawable.com_pa);     //パーの画像を表示する
                break;
        }


        // 勝敗を判定する
        int gameResult = (comHand - myHand + 3) % 3;

        //勝敗を表示する
        TextView resultLabel = (TextView) findViewById(R.id.result_label);
        switch (gameResult) {
            case 0:
                // あいこの場合
                resultLabel.setText(R.string.result_draw);
                break;
            case 1:
                // 勝った場合
                resultLabel.setText(R.string.result_win);
                break;
            case 2:
                // 負けた場合
                resultLabel.setText(R.string.result_lose);
                break;
        }

    }

    //前の画面に戻る
    public void onBackButtonTapped(View view){
        finish();
    }
}
