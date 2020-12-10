package com.blueboard.knumeet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MakeInfoActivity extends AppCompatActivity {
    ImageButton btn_back_info_to;

    private RecyclerView recyclerView_days,recyclerView_times;
    private RecyclerView.Adapter adapter_days,adapter_times;
    private RecyclerView.LayoutManager layoutManager_days,layoutManager_times;
    private int key = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_info);

        recyclerView_days = findViewById(R.id.rv_days);//d

        recyclerView_times = findViewById(R.id.rv_times);//t

        layoutManager_days = new LinearLayoutManager(this);
        layoutManager_times = new LinearLayoutManager(this);

        recyclerView_days.setLayoutManager(layoutManager_days);//d

        recyclerView_times.setLayoutManager(layoutManager_times);//t

        adapter_days = new Adapter_Days(this);//d

        recyclerView_days.setAdapter(adapter_days);//어뎁터 연결//d

        adapter_times = new Adapter_times(this);//t

        recyclerView_times.setAdapter(adapter_times);//t


        Intent intent = getIntent();
        key = intent.getExtras().getInt("Keynum");


        btn_back_info_to = (ImageButton)findViewById(R.id.btn_back_info_to);

        btn_back_info_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button btn_complete = (Button)findViewById(R.id.btn_complete);

        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MakeInfoActivity.this)
                        .setMessage("가능시간을 정확히 입력하였습니까??")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(key == 2){
                                    startActivity(new Intent(MakeInfoActivity.this, CompleteActivity2.class));
                                    finish();
                                }
                                else{
                                    startActivity(new Intent(MakeInfoActivity.this, CompleteActivity.class));
                                    finish();
                                }
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();
            }
        });




    }
}