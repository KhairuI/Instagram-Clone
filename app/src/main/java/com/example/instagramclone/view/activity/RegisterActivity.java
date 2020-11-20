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
import com.example.instagramclone.model.m.authModel.Register;
import com.example.instagramclone.viewmodel.authViewModel.AuthViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText registerEmailEditText,registerPasswordEditText;
    private TextView registerTextView,verificationText;
    private ProgressBar registerProgress;
    private Button registerRequestButton,confirmButton;
    private AuthViewModel authViewModel;
    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findSection();
        initViewModel();

        registerRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email= registerEmailEditText.getText().toString();
                String password= registerPasswordEditText.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

                    if(password.length()>=6){
                        registerProgress.setVisibility(View.VISIBLE);
                        Register register= new Register(email,password);
                        authViewModel.userSignUp(register);
                        authViewModel.signUpFirebase.observe(RegisterActivity.this, new Observer<Register>() {
                            @Override
                            public void onChanged(Register register) {
                                if(register.isRegister){
                                    Toast.makeText(RegisterActivity.this, ""+register.uId, Toast.LENGTH_SHORT).show();
                                    registerProgress.setVisibility(View.GONE);
                                    verificationText.setVisibility(View.VISIBLE);
                                    confirmButton.setVisibility(View.VISIBLE);
                                    registerEmailEditText.setText("");
                                    registerPasswordEditText.setText("");
                                    /*Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();*/
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, ""+register.uId, Toast.LENGTH_SHORT).show();
                                    registerProgress.setVisibility(View.GONE);
                                }
                            }
                        });

                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Password at least 6 Character", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Please fill all Field", Toast.LENGTH_SHORT).show();
                }

            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
               finish();
            }
        });
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findSection() {
        registerEmailEditText= findViewById(R.id.registerEmailEditTextId);
        registerPasswordEditText= findViewById(R.id.registerPasswordEditTextId);
        registerTextView= findViewById(R.id.registerToolbarTextId);
        registerProgress= findViewById(R.id.registerProgressId);
        registerProgress.setVisibility(View.GONE);
        registerRequestButton= findViewById(R.id.registerRequestButtonId);
        confirmButton= findViewById(R.id.confirmButtonId);
        confirmButton.setVisibility(View.GONE);
        verificationText= findViewById(R.id.verificationTextId);
        verificationText.setVisibility(View.GONE);
    }

    private void initViewModel() {
        authViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication())).get(AuthViewModel.class);
    }
}