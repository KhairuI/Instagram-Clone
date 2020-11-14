package com.example.instagramclone.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.instagramclone.model.Login;
import com.example.instagramclone.repository.AuthRepository;

public class AuthViewModel extends AndroidViewModel {

    private AuthRepository repository;
    public LiveData<String> haveFirebase;
    public LiveData<Login> loginFirebase;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository= new AuthRepository();
    }

    // check user have in firebase or not .....
    public void checkUser(){
        haveFirebase= repository.checkUser();
    }

    // login existing user .....

    public void userLogin(Login login){
        loginFirebase = repository.userLogin(login);
    }


}
