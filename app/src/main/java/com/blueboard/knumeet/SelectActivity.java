package com.blueboard.knumeet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

import static maes.tech.intentanim.CustomIntent.customType;

public class SelectActivity extends AppCompatActivity {
    private int Start_time_hour=0,Start_time_min=0;
    private int End_time_hour=0,End_time_min=0;
    private TextView tv_Start_time, tv_End_time;
    private TextView tv_people_myeng,tv_how_long,tv_how_many,tv_time_sigan;
    private TimePickerDialog timePickerDialog1;
    private ImageButton btn_back_select_to_home;
    private Intent intent;
    private EditText et_title;
    private EditText et_time;
    private EditText et_people;
    private int cnt = 0;
    private DatabaseReference reference;
    public void hideKeyboard(){
        InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(et_title.getWindowToken(), 0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        customType(SelectActivity.this,"bottom-to-up");//시작될때 animation

        Button btn_go_makeinfo = (Button) findViewById(R.id.btn_go_makeinfo);
        LinearLayout btn_start_calendar_1 = (LinearLayout)findViewById(R.id.btn_start_calendar_1);
        LinearLayout btn_start_calendar_2 = (LinearLayout)findViewById(R.id.  btn_start_calendar_2);
        //LinearLayout btn_how_long = (LinearLayout) findViewById(R.id.btn_how_long);
        //LinearLayout btn_how_many_people = (LinearLayout) findViewById(R.id.btn_how_many_people);
        final LinearLayout btn_Start_time = (LinearLayout) findViewById(R.id.btn_Start_time);
        final LinearLayout btn_End_time = (LinearLayout) findViewById(R.id.btn_end_time);

        tv_Start_time= (TextView) findViewById(R.id.tv_Start_time);
        tv_End_time= (TextView) findViewById(R.id.tv_End_time);
        tv_people_myeng=(TextView) findViewById(R.id.tv_people_myeng);
        tv_time_sigan=(TextView) findViewById(R.id.tv_time_sigan);
        tv_how_long = (TextView) findViewById(R.id.tv_how_long);
        tv_how_many = (TextView) findViewById(R.id.tv_how_many_people);

        btn_back_select_to_home = (ImageButton) findViewById(R.id.btn_back_select_to_home);

        et_title = (EditText)findViewById(R.id.edit_title);
        et_title.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
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
        et_time = (EditText)findViewById(R.id.edit_time);
        et_time.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    et_time.setSelected(false);
                    et_time.clearFocus();
                    hideKeyboard();
                    ImageView image_time = (ImageView)findViewById(R.id.image_time);
                    image_time.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_gray_blue));
                    tv_time_sigan.setTextColor(getColor(R.color.colorBlack));
                    tv_how_long.setTextColor(getColor(R.color.colorBlack));
                }
                return false;
            }
        });
        et_people = (EditText)findViewById(R.id.edit_people);
        et_people.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    et_people.setSelected(false);
                    et_people.clearFocus();
                    hideKeyboard();
                    ImageView image_people = (ImageView)findViewById(R.id.image_people);
                    image_people.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_gray_blue));
                    tv_people_myeng.setTextColor(getColor(R.color.colorBlack));
                    tv_how_many.setTextColor(getColor(R.color.colorBlack));
                }
                return false;
            }
        });



//        if(intent!=null){
//                Log.d("hibaby",intent.getStringExtra("Start_date"));
//                tv_start_date.setText(intent.getStringExtra("Start_date"));
//                tv_end_date.setText(intent.getStringExtra("End_date"));
//        }

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


        //방만들기
        btn_go_makeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SelectActivity.this)
                        .setMessage("정확한 정보를 입력하였습니까??\n")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                HashMap<Object,Object> data = new HashMap<>();
                                data.put(FirebasePost.title,et_title.getText().toString());
                                data.put(FirebasePost.spend_time,et_time.getText().toString());
                                reference = FirebaseDatabase.getInstance().getReference("Room").push();
                                String key = reference.getKey();


                                reference.setValue(data);


                                Intent intent = new Intent(getApplicationContext(), MakeInfoActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();

            }

        });

        btn_back_select_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(SelectActivity.this)
                        .setMessage("방만들기를 취소하시겠습니까??")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(SelectActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();
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

            tv_end_date.setText(End_date);
            tv_end_date.setTextColor(getColor(R.color.colorBlack));

            ImageView image_start_date = (ImageView)findViewById(R.id.image_start_date);
            image_start_date.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_gray_blue));
            ImageView image_end_date = (ImageView)findViewById(R.id.image_end_date);
            image_end_date.setImageDrawable(getDrawable(R.drawable.ic_baseline_done_gray_blue));

        }else{

        }
    }
}