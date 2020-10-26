package com.blueboard.knumeet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wisnu.datetimerangepickerandroid.CalendarPickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import static com.wisnu.datetimerangepickerandroid.CalendarPickerView.SelectionMode.RANGE;


public class SelectCalendarActivity extends AppCompatActivity {
    Button btn_select_save;
    CalendarPickerView calendar;
    SimpleDateFormat transFormat,transFormat2;
    String pattern="YYYY-MM-dd";
    TextView tv_start_date,tv_end_date;
    char[] day={'일','일','월','화','수','목','금','토'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_calendar);

        tv_start_date=(TextView) findViewById(R.id.tv_start_date);
        tv_end_date=(TextView) findViewById(R.id.tv_end_date);

        btn_select_save=(Button) findViewById(R.id.btn_select_save);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .inMode(RANGE);

        Log.d("################",calendar.getSelectedDates().toString());

        transFormat = new SimpleDateFormat(pattern);
        transFormat2 = new SimpleDateFormat("MM월 D일");

        btn_select_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                Date Start_date=calendar.getSelectedDates().get(0);
                String Start=transFormat.format(Start_date);
                Date End_date=calendar.getSelectedDates().get(calendar.getSelectedDates().size()-1);
                String End=transFormat.format(Start_date);

                Intent intent = getIntent();

                cal.setTime(Start_date);
                char Start_day=day[cal.get(Calendar.DAY_OF_WEEK)];
                intent.putExtra("Start_date", transFormat2.format(Start_date) +" ("+Start_day+")");

                cal.setTime(End_date);
                char End_day=day[cal.get(Calendar.DAY_OF_WEEK)];
                intent.putExtra("End_date",transFormat2.format(End_date)+" ("+End_day+")");

                finish();
            }
        });
    }

}