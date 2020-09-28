package com.blueboard.knumeet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScheduleRoomActivity extends Activity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        String result = intent.getStringExtra("result");
        tv=new TextView(this);
        tv.setText(result);
        setContentView(tv);
    }
}
