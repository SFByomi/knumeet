package com.blueboard.knumeet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SelectActivity extends AppCompatActivity {
    private int Start_time_hour=0,Start_time_min=0;
    private int End_time_hour=0,End_time_min=0;
    private TextView tv_Start_time, tv_End_time;
    private TextView tv_start_date,tv_end_date;
    private TimePickerDialog timePickerDialog1;
    private ImageButton btn_back_select_to_home;
    private Intent intent;
    private EditText et_title;
    private int cnt = 0;
    public void hideKeyboard(){
        InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(et_title.getWindowToken(), 0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Button btn_go_makeinfo = (Button) findViewById(R.id.btn_go_makeinfo);
        LinearLayout btn_start_calendar_1 = (LinearLayout)findViewById(R.id.btn_start_calendar_1);
        LinearLayout btn_start_calendar_2 = (LinearLayout)findViewById(R.id.  btn_start_calendar_2);
        LinearLayout btn_how_long = (LinearLayout) findViewById(R.id.btn_how_long);
        LinearLayout btn_how_many_people = (LinearLayout) findViewById(R.id.btn_how_many_people);
        final LinearLayout btn_Start_time = (LinearLayout) findViewById(R.id.btn_Start_time);
        final LinearLayout btn_End_time = (LinearLayout) findViewById(R.id.btn_end_time);

        tv_Start_time= (TextView) findViewById(R.id.tv_Start_time);
        tv_End_time= (TextView) findViewById(R.id.tv_End_time);
        tv_start_date=(TextView) findViewById(R.id.tv_start_date);
        tv_end_date=(TextView) findViewById(R.id.tv_end_date);



        btn_back_select_to_home = (ImageButton) findViewById(R.id.btn_back_select_to_home);

        et_title = (EditText)findViewById(R.id.edit_title);
        et_title.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    et_title.setSelected(false);
                    et_title.clearFocus();
                    hideKeyboard();
                    ImageView image_title = (ImageView)findViewById(R.id.image_title);
                    image_title.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_gray_blue));
                    return true;
                }
                return false;
            }
        });



        Spinner spinner = (Spinner) findViewById(R.id.spinner);

//        if(intent!=null){
//                Log.d("hibaby",intent.getStringExtra("Start_date"));
//                tv_start_date.setText(intent.getStringExtra("Start_date"));
//                tv_end_date.setText(intent.getStringExtra("End_date"));
//        }

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
                timePickerDialog1 = new TimePickerDialog(
                        SelectActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        tv_Start_time.setText("시작 시간:  "+String.valueOf(i)+"시 "+String.valueOf(i1)+"분");
                        tv_Start_time.setTextColor(getColor(R.color.colorBlack));
                        ImageView image = (ImageView)findViewById(R.id.image_start_time);
                        image.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_gray_blue));
                    }
                }, Start_time_hour,Start_time_min,false);
                timePickerDialog1.show();

            }
        });
        btn_End_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog1 = new TimePickerDialog(
                        SelectActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        tv_End_time.setText("마감 시간:  "+String.valueOf(i)+"시 "+String.valueOf(i1)+"분");
                        tv_End_time.setTextColor(getColor(R.color.colorBlack));
                        ImageView image = (ImageView)findViewById(R.id.image_end_time);
                        image.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_gray_blue));
                    }
                }, End_time_hour,End_time_min,false);
                timePickerDialog1.show();
            }
        });
//달력선택

        btn_start_calendar_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),SelectCalendarActivity.class);
                startActivityForResult(intent, 1234);
            }
        });
        btn_start_calendar_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),SelectCalendarActivity.class);
                startActivityForResult(intent, 1234);
            }
        });


        btn_go_makeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MakeInfoActivity.class);
                startActivity(intent);
                finish();
            }

        });

        btn_back_select_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234 && resultCode == RESULT_OK){
            String Start_date = data.getStringExtra("Start_date");
            String End_date = data.getStringExtra("End_date");
            TextView tv_start_date = (TextView)findViewById(R.id.tv_start_date); ;
            TextView tv_end_date = (TextView)findViewById(R.id.tv_end_date);
            tv_start_date.setText(Start_date);
            tv_start_date.setTextColor(getColor(R.color.colorBlack));

            tv_end_date.setText(Start_date);
            tv_end_date.setTextColor(getColor(R.color.colorBlack));

            ImageView image_start_date = (ImageView)findViewById(R.id.image_start_date);
            image_start_date.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_gray_blue));
            ImageView image_end_date = (ImageView)findViewById(R.id.image_end_date);
            image_end_date.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_gray_blue));

        }else{

        }
    }
}