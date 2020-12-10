package com.blueboard.knumeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CodeActivity extends AppCompatActivity {

    private String enter_code = "-MNrOk8lJqRA1mHmON-Z";
    private Button btn_code;
    private EditText et_code;
    private int keynum = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        btn_code = findViewById(R.id.btn_code);
        et_code = findViewById(R.id.et_code);

        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp = et_code.getText().toString();

                if(temp.equals(enter_code) ) {
                    Toast.makeText(CodeActivity.this, "입장 성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CodeActivity.this, MakeInfoActivity.class);
                    intent.putExtra("Keynum",keynum);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(CodeActivity.this, "방을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}