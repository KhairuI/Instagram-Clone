package com.example.instagramclone.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.instagramclone.viewmodel.AuthViewModel;

public class SplashActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        checkUser();
    }

    private void checkUser() {
        authViewModel.checkUser();
        authViewModel.haveFirebase.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("true")){
                    goToMainActivity();
                }
                else {
                    goToLoginActivity();
                }
            }
        });

    }

    private void goToLoginActivity() {
        Intent intent= new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToMainActivity() {
        Intent intent= new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void initViewModel() {
        authViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication())).get(AuthViewModel.class);
    }
}