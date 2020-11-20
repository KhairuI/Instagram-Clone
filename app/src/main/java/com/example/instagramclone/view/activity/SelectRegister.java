package com.example.instagramclone.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.R;

public class SelectRegister extends AppCompatActivity {

    private Button selectEmailButton,selectGoogleButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_register);

        textView= findViewById(R.id.selectRegisterToolbarTextId);
        selectEmailButton= findViewById(R.id.selectEmailId);
        selectGoogleButton= findViewById(R.id.selectGoogleId);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SelectRegister.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        selectEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SelectRegister.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
        selectGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectRegister.this, "Google SignUp", Toast.LENGTH_SHORT).show();
            }
        });
    }

}