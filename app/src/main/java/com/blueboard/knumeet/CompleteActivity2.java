package com.blueboard.knumeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompleteActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete2);
        Button btn_go_home = (Button)findViewById(R.id.btn_go_home2);
        btn_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteActivity2.this, MainActivity.class));
                finish();
            }
        });
    }
}