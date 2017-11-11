package com.example.njc_t1.janken_00yamada20171109;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
       // int comHand =(int) (Math.random() * 3);

        //コンピュータの手を決める（じゃんけんロジックのメソッドで）
        int comHand = getHand();

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

        // じゃんけんの結果を保存する
        saveData(myHand, comHand, gameResult);     //共有ぷリファレンスでの保存に伴い、追加したもの

    }

    //前の画面に戻る
    public void onBackButtonTapped(View view){
        finish();
    }



    //前回のデータを読み込み、今回の情報をデータに反映する
    //データは共有ぷリファレンスで保存する
    private void saveData(int myHand, int comHand, int gameResult) {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();

        int gameCount = pref.getInt("GAME_COUNT", 0);
        int winningStreakCount = pref.getInt("WINNING_STREAK_COUNT", 0);
        int lastComHand = pref.getInt("LAST_COM_HAND", 0);
        int lastGameResult = pref.getInt("GAME_RESULT", -1);

        editor.putInt("GAME_COUNT", gameCount + 1);

        if (lastGameResult == 2 && gameResult == 2) {
            // コンピュータが連勝した場合
            editor.putInt("WINNING_STREAK_COUNT", winningStreakCount + 1);
        } else {
            editor.putInt("WINNING_STREAK_COUNT", 0);
        }
        editor.putInt("LAST_MY_HAND", myHand);
        editor.putInt("LAST_COM_HAND", comHand);
        editor.putInt("BEFORE_LAST_COM_HAND", lastComHand);
        editor.putInt("GAME_RESULT", gameResult);
        editor.commit();
    }

    //じゃんけんロジック
    private int getHand() {
        int hand = (int) (Math.random() * 3);
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        int gameCount = pref.getInt("GAME_COUNT", 0);
        int winningStreakCount = pref.getInt("WINNING_STREAK_COUNT", 0);
        int lastMyHand = pref.getInt("LAST_MY_HAND", 0);
        int lastComHand = pref.getInt("LAST_COM_HAND", 0);
        int beforeLastComHand = pref.getInt("BEFORE_LAST_COM_HAND", 0);
        int gameResult = pref.getInt("GAME_RESULT", -1);

        if (gameCount == 1) {

            if (gameResult == 2) {
                // 前回の勝負が1回目で、コンピュータが勝った場合、
                // コンピュータは次に出す手を変える
                while (lastComHand == hand) {
                    hand = (int) (Math.random() * 3);
                }
            } else if (gameResult == 1) {
                // 前回の勝負が１回目で、コンピュータが負けた場合
                // 相手の出した手に勝つ手を出す
                hand = (lastMyHand - 1 + 3) % 3;
            }
        } else if (winningStreakCount > 0) {
            if (beforeLastComHand == lastComHand) {
                // 同じ手で連勝した場合は手を変える
                while (lastComHand == hand) {
                    hand = (int) (Math.random() * 3);
                }
            }
        }
        return hand;
    }
}
