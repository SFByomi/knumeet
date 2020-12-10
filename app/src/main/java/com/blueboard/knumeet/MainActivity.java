package com.blueboard.knumeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MeetInfo> arrayList;


    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private GoogleSignInClient mGoogleSignInClient;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private void signOut() {
        mAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_LONG).show();
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//메인액티비티
        ImageButton btn_go_select = (ImageButton) findViewById(R.id.btn_go_select);
        ImageButton btn_go_enter = (ImageButton) findViewById(R.id.btn_go_enter);
        ImageButton btn_logout = (ImageButton) findViewById(R.id.btn_logout);

        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_main);//d
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);//d
        adapter = new Adapter_main(this);//d

        recyclerView.setAdapter(adapter);//어뎁터 연결//d


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btn_go_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectActivity.class);
                Adapter_main.addList();//방 늘어나게
                startActivity(intent);
            }
        });
        btn_go_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CodeActivity.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("정말로 로그아웃 하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                signOut();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();
            }
        });







    }

    //액티비티가 화면 복귀시마다 효과 넣기위해 Resume
    @Override
    protected void onResume() {

        super.onResume();
        customType(MainActivity.this,"up-to-bottom");//animation,,화면표시될 때 마다 위에서 밑으로
    }

}