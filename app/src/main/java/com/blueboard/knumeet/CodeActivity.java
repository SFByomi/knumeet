package com.blueboard.knumeet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static maes.tech.intentanim.CustomIntent.customType;

public class CodeActivity extends AppCompatActivity {

    private String enter_code = "-MNrOk8lJqRA1mHmON-Z";
    private Button btn_code;
    private EditText et_code;
    private int keynum = 2;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        customType(CodeActivity.this,"bottom-to-up");
        btn_back = findViewById(R.id.btn_back_code_to);
        btn_code = findViewById(R.id.btn_code);
        et_code = findViewById(R.id.et_code);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_code.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {

                String temp = et_code.getText().toString();

                if(temp.equals(enter_code) ) {
                    Toast.makeText(CodeActivity.this, "가능하신 날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CodeActivity.this, MakeInfoActivity.class);
                    intent.putExtra("Keynum",keynum);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(CodeActivity.this, "방을 찾을 수 없습니다.\n코드를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    et_code.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colorRed)));

                }
            }
        });


    }
}