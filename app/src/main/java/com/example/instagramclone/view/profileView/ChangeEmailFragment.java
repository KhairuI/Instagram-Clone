package com.example.instagramclone.view.profileView;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.view.activity.LoginActivity;
import com.example.instagramclone.viewmodel.authViewModel.AuthViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class ChangeEmailFragment extends Fragment {

    private ImageView changeEmailBack;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;
    private TextInputEditText resetPassword,newEmail,newPassword;
    private TextView confirmText;
    private Button resetSubmitButton,resetChangeButton;
    private AuthViewModel authViewModel;


    public ChangeEmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initViewModel();
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_change_email, container, false);
        changeEmailBack= view.findViewById(R.id.changeEmailBackId);
        resetPassword= view.findViewById(R.id.changePasswordEditTextId);
        newEmail= view.findViewById(R.id.newEmailEditTextId);
        newPassword= view.findViewById(R.id.newPasswordEditTextId);
        confirmText= view.findViewById(R.id.confirmTextId);
        resetSubmitButton= view.findViewById(R.id.changeEmailPassSubmitButtonId);
        resetChangeButton= view.findViewById(R.id.changeEmailPassConfirmButtonId);
        resetChangeButton.setVisibility(View.GONE);
        progressBar= view.findViewById(R.id.changeEmailProgressId);
        progressBar.setVisibility(View.INVISIBLE);
        relativeLayout= view.findViewById(R.id.newEmailPassword);
        relativeLayout.setVisibility(View.GONE);

        // back image
        changeEmailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        // check password is match or not ......
        confirmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String pass= resetPassword.getText().toString();
                if(!TextUtils.isEmpty(pass) && pass.length()>= 6){

                    authViewModel.CheckConfirmPassword(pass);
                    authViewModel.confirmPassword.observe(getActivity(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s.equals("true")){
                                resetPassword.setText("");
                                progressBar.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.VISIBLE);
                            }
                            else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Password don't match. Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Password Empty or less than 6", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // check this email is exist in firebase
        resetSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= newEmail.getText().toString();
                if(!TextUtils.isEmpty(email)){
                    progressBar.setVisibility(View.VISIBLE);
                    authViewModel.CheckEmailIsExist(email);
                    authViewModel.isEmailExist.observe(getActivity(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s.equals("not_exist")){
                                progressBar.setVisibility(View.GONE);
                                resetChangeButton.setVisibility(View.VISIBLE);
                            }
                            else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "This Email is already exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // update email and password and sent verification mail to new email....
        resetChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email= newEmail.getText().toString();
                String password= newPassword.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    if(password.length()>=6){
                        authViewModel.UpdateEmailAndPassword(email,password);
                        authViewModel.updateAuth.observe(getActivity(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                if(s.equals("true")){
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "A Verification mail sent. Check your Email", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // clear the activity stack
                                    startActivity(intent);
                                }
                                else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), ""+s, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Password at least 6 Character", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Fill Email & Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    private void initViewModel() {
        authViewModel= new ViewModelProvider(getActivity(),ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
    }
}