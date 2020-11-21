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
import com.example.instagramclone.model.m.profileModel.UserInfo;
import com.example.instagramclone.model.m.profileModel.UserSettings;
import com.example.instagramclone.viewmodel.authViewModel.AuthViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class SetNameActivity extends AppCompatActivity {

    private TextInputEditText nameEditText;
    private TextView finalNameText,user;
    private Button setName,finalNameSubmitButton;
    private ProgressBar setNameProgress;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name);
        findSection();
        initViewModel();

        setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNameProgress.setVisibility(View.VISIBLE);

                String name= nameEditText.getText().toString();
                name= name.replace(" ","");
                String newName= name.toLowerCase().trim();
                if(!TextUtils.isEmpty(newName)){
                    authViewModel.userNameExist(newName);
                    authViewModel.checkName.observe(SetNameActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s.equals("Exist")){
                                setNameProgress.setVisibility(View.GONE);
                                Toast.makeText(SetNameActivity.this, "This name is already Exist", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                setNameProgress.setVisibility(View.GONE);
                                nameEditText.setText("");
                                finalNameText.setVisibility(View.VISIBLE);
                                user.setVisibility(View.VISIBLE);
                                finalNameSubmitButton.setVisibility(View.VISIBLE);
                                finalNameText.setText(newName);

                            }
                        }
                    });

                }
                else {
                    Toast.makeText(SetNameActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                }


            }
        });

        finalNameSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNameProgress.setVisibility(View.VISIBLE);

                String name= finalNameText.getText().toString().toLowerCase().trim();
                UserInfo userInfo= new UserInfo(name,"Empty","Empty","Empty","Empty","Empty");
                UserSettings userSettings= new UserSettings("I am using Instagram",name,"0","0","0","Empty",name,"Website");
                authViewModel.setUserName(userSettings,userInfo);
                authViewModel.setNameFirebase.observe(SetNameActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if(s.equals("true")){
                            setNameProgress.setVisibility(View.GONE);
                            Toast.makeText(SetNameActivity.this, "Set name Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(SetNameActivity.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            setNameProgress.setVisibility(View.GONE);
                            Toast.makeText(SetNameActivity.this, "Set name Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });



            }
        });

    }

    private void findSection() {

        nameEditText= findViewById(R.id.fullNameEditTextId);
        setName= findViewById(R.id.setNameButtonId);
        setNameProgress= findViewById(R.id.setNameProgressId);
        setNameProgress.setVisibility(View.GONE);
        finalNameText= findViewById(R.id.finalUserNameId);
        finalNameText.setVisibility(View.GONE);
        user= findViewById(R.id.user);
        user.setVisibility(View.GONE);
        finalNameSubmitButton= findViewById(R.id.finalNameSubmitButtonId);
        finalNameSubmitButton.setVisibility(View.GONE);

    }

    private void initViewModel() {
        authViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication())).get(AuthViewModel.class);
    }
}