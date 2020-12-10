package com.blueboard.knumeet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static maes.tech.intentanim.CustomIntent.customType;

public class ShowActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btn_select_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        customType(ShowActivity.this,"bottom-to-up");
        recyclerView = findViewById(R.id.rv_show);//d
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);//d
        adapter = new Adapter_show(this);//d
        recyclerView.setAdapter(adapter);//어뎁터 연결//d
        btn_select_save = findViewById(R.id.btn_select_save);

        btn_select_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");
                String Test_Message = "약속시간 결과입니다!(투표순)\n" +
                        "2020-12-11  9:00\n" +
                        "2020-12-11  10:00\n" +
                        "2020-12-11  11:00\n" +
                        "2020-12-12  9 : 00\n" +
                        "2020-12-12  10 : 00\n" +
                        "2020-12-13  9 : 00\n" +
                        "2020-12-13  10 : 00\n" +
                        "2020-12-13  11 : 00\n" +
                        "\nfrom.약속시간을 가장 쉽게 정하는 방법\n\"everyMEET\"";// 멘트

                Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                startActivity(Sharing);
            }
        });

    }
}