package com.blueboard.knumeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            try {

                Thread.sleep(2000); //대기 초 설정
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));//이미지 후 로그인액티비티

            finish();
        } catch (Exception e) {
            Log.e("Error", "SplashActivity ERROR", e);//에러 로그
        }
    }
}