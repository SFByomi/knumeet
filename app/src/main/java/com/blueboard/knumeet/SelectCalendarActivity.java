package com.blueboard.knumeet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ReceiverCallNotAllowedException;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    SimpleDateFormat transFormat;
    String pattern="YYYY-MM-dd";
    char[] day={'일','일','월','화','수','목','금','토'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_calendar);

        btn_select_save=(Button) findViewById(R.id.btn_select_save);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .inMode(RANGE);

        Log.d("################",calendar.getSelectedDates().toString());

        transFormat = new SimpleDateFormat(pattern);

        btn_select_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(calendar.getSelectedDates().get(0));

                Date Start_date=calendar.getSelectedDates().get(0);

                String Start=transFormat.format(calendar.getSelectedDates().get(0));
                char Start_day=day[cal.get(Calendar.DAY_OF_WEEK)];
                String End=transFormat.format(calendar.getSelectedDates().get(calendar.getSelectedDates().size()-1));
                Log.d("################", String.valueOf(day[cal.get(Calendar.DAY_OF_WEEK)]));
                Log.d("################", End);
                cal.setTime(calendar.getSelectedDates().get(calendar.getSelectedDates().size()-1));
                Log.d("################", String.valueOf(day[cal.get(Calendar.DAY_OF_WEEK)]));
            }
        });
    }

}