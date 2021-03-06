package com.example.instagramclone.viewmodel.authViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.instagramclone.model.m.authModel.Login;
import com.example.instagramclone.model.m.authModel.Register;
import com.example.instagramclone.model.m.profileModel.UserInfo;
import com.example.instagramclone.model.m.profileModel.UserSettings;
import com.example.instagramclone.repository.authRepository.AuthRepository;

public class AuthViewModel extends AndroidViewModel {

    private AuthRepository repository;
    public LiveData<String> haveFirebase;
    public LiveData<Login> loginFirebase;
    public LiveData<Register> signUpFirebase;
    public LiveData<String> checkName;
    public LiveData<String> setNameFirebase;
    public LiveData<String> confirmPassword;
    public LiveData<String> isEmailExist;
    public LiveData<String> updateAuth;

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

    // Sign In new  user .....
    public void userSignUp(Register register){
        signUpFirebase = repository.userSignUp(register);
    }

    // check name is exist or Not....
    public void userNameExist(String name){
        checkName= repository.userNameExist(name);
    }

    // save user Name here.........
    public void setUserName(UserSettings userSettings, UserInfo userInfo){
        setNameFirebase= repository.setUserName(userSettings,userInfo);
    }

    // re - Authenticate the user. When user goes to change email and password.........
    // the password is match or not....
    public void CheckConfirmPassword(String password){
        confirmPassword= repository.CheckConfirmPassword(password);
    }

    // check the email is exist or not in firebase.......

    public void CheckEmailIsExist(String email){
        isEmailExist= repository.CheckEmailIsExist(email);
    }

    // update email and password
    public void UpdateEmailAndPassword(String email,String password){
        updateAuth= repository.UpdateEmailAndPassword(email, password);
    }
}
