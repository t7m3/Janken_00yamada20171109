package com.example.njc_t1.janken_00yamada20171109;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onJankenButtonTapped(View view){
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
