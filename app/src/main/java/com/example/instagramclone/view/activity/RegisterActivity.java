package com.example.instagramclone.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.instagramclone.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText registerEmailEditText,registerPasswordEditText,registerNameEditText;
    private TextView registerTextView;
    private ProgressBar registerProgress;
    private Button emailRegisterButton,googleRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findSection();

        emailRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        googleRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void findSection() {
        registerEmailEditText= findViewById(R.id.registerEmailEditTextId);
        registerPasswordEditText= findViewById(R.id.registerPasswordEditTextId);
        registerNameEditText= findViewById(R.id.registerNameEditTextId);
        registerTextView= findViewById(R.id.registerToolbarTextId);
        registerProgress= findViewById(R.id.registerProgressId);
        registerProgress.setVisibility(View.GONE);
        emailRegisterButton= findViewById(R.id.registerButtonId);
        googleRegisterButton= findViewById(R.id.googleRegisterButtonId);
    }
}