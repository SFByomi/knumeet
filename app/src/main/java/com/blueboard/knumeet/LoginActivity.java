package com.blueboard.knumeet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{

    private static final int RC_SIGN_IN = 6969;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;
    /* private EditText mEmail,mPassword;
     private TextView tv_signup;
     private Button btn_login;*/
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        //Log.e("asd",user+"");
        if(user != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(this, user.getEmail() + "님" + "\n자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = findViewById(R.id.google_sign_in_button);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }catch(Exception e){
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account){
        Log.e("ID","firebaseAuthWithGoogle" + account.getId());

        AuthCredential credential  = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //Log.e("myLog","login success" + mAuth.getCurrentUser().getEmail());
                            Toast.makeText(LoginActivity.this, "환영합니다!", Toast.LENGTH_SHORT).show();

                            String userId = mAuth.getCurrentUser().getUid();
                            String email = mAuth.getCurrentUser().getEmail();
                            String nickname = mAuth.getCurrentUser().getDisplayName();
                            Map<String, Object> data = new HashMap<>();
                            data.put(FirebaseID.nickname, nickname);
                            data.put(FirebaseID.email, email);
                            mStore.collection(FirebaseID.user).document(userId).set(data, SetOptions.merge());


                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        }else{
                            Toast.makeText(LoginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                });

    }

    private void signIn(){
        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent,RC_SIGN_IN);
    }

}
