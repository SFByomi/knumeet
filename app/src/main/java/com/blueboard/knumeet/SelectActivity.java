package com.blueboard.knumeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Button btn_go_makeinfo = (Button) findViewById(R.id.btn_go_makeinfo);

        LinearLayout btn_start_calendar_1 = (LinearLayout)findViewById(R.id.btn_start_calendar_1);
        LinearLayout btn_start_calendar_2 = (LinearLayout)findViewById(R.id.  btn_start_calendar_2);

        btn_start_calendar_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectCalendarActivity.class);
                startActivity(intent);
            }
        });
        btn_start_calendar_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectCalendarActivity.class);
                startActivity(intent);
            }
        });

        btn_go_makeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MakeInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}