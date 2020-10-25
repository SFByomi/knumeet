package com.blueboard.knumeet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SelectActivity extends AppCompatActivity {
    int Start_time_hour=0,Start_time_min=0;
    int End_time_hour=0,End_time_min=0;
    TextView tv_Start_time;
    TextView tv_End_time;
    TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Button btn_go_makeinfo = (Button) findViewById(R.id.btn_go_makeinfo);

        LinearLayout btn_start_calendar_1 = (LinearLayout)findViewById(R.id.btn_start_calendar_1);
        LinearLayout btn_start_calendar_2 = (LinearLayout)findViewById(R.id.  btn_start_calendar_2);
        LinearLayout btn_how_long = (LinearLayout) findViewById(R.id.btn_how_long);
        LinearLayout btn_how_many_people = (LinearLayout) findViewById(R.id.btn_how_many_people);
        LinearLayout btn_Start_time = (LinearLayout) findViewById(R.id.btn_Start_time);
        LinearLayout btn_End_time = (LinearLayout) findViewById(R.id.btn_end_time);

        tv_Start_time= (TextView) findViewById(R.id.tv_Start_time);
        tv_End_time= (TextView) findViewById(R.id.tv_End_time);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_Start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(
                        SelectActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        tv_Start_time.setText("탐색 시작 시간: "+String.valueOf(i)+"시 "+String.valueOf(i1)+"분");
                    }
                }, Start_time_hour,Start_time_min,false);
                timePickerDialog.show();
            }
        });
        btn_End_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(
                        SelectActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        tv_End_time.setText("탐색 마감 시간: "+String.valueOf(i)+"시 "+String.valueOf(i1)+"분");
                    }
                }, End_time_hour,End_time_min,false);
                timePickerDialog.show();
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