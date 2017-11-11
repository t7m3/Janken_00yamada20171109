package com.example.njc_t1.janken_00yamada20171109;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 起動時に、共有ぷリファレンスのデータをクリアする
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    //他のアクティビティに遷移する
    public void onJankenButtonTapped(View view){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("MY_HAND",view.getId());     //インテントに追加情報を格納する。
        startActivity(intent);
    }
}
