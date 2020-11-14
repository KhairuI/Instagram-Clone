package com.example.instagramclone.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.model.Login;
import com.example.instagramclone.viewmodel.AuthViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText loginEmailEditText,loginPasswordEditText;
    private TextView loginTextView;
    private AuthViewModel authViewModel;
    private ProgressBar loginProgress;
    private Button emailLoginButton,googleLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findSection();
        initViewModel();
        emailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email= loginEmailEditText.getText().toString();
                String passWord= loginPasswordEditText.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(email)){
                    if(passWord.length()>=6){
                        loginProgress.setVisibility(View.VISIBLE);
                        Login login= new Login(email,passWord);
                        authViewModel.userLogin(login);
                        authViewModel.loginFirebase.observe(LoginActivity.this, new Observer<Login>() {
                            @Override
                            public void onChanged(Login login) {
                                if(login.isLogin){
                                    Toast.makeText(LoginActivity.this, ""+login.uId, Toast.LENGTH_SHORT).show();
                                    loginProgress.setVisibility(View.GONE);
                                    goToMainActivity();
                                }
                                else {
                                    loginProgress.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, ""+login.uId, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Password at least 6 Character", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(LoginActivity.this, "Please fill all Field", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void goToMainActivity() {
        Intent intent= new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void findSection() {
        loginEmailEditText= findViewById(R.id.loginEmailEditTextId);
        loginPasswordEditText= findViewById(R.id.loginPasswordEditTextId);
        loginTextView= findViewById(R.id.loginToolbarTextId);
        loginProgress= findViewById(R.id.loginProgressId);
        loginProgress.setVisibility(View.GONE);
        emailLoginButton= findViewById(R.id.loginButtonId);
        googleLoginButton= findViewById(R.id.googleLogInButtonId);
    }

    private void initViewModel() {
        authViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication())).get(AuthViewModel.class);
    }
}