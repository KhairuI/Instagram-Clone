package com.example.instagramclone.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.instagramclone.model.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthRepository {

    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    private Login login= new Login();

    // check user have in firebase or not .....

    public MutableLiveData<String> checkUser(){

        MutableLiveData<String> haveFirebase= new MutableLiveData<>();
        FirebaseUser currentUser= firebaseAuth.getCurrentUser();
        if (currentUser==null){

            haveFirebase.setValue("false");
        }
        else {
            haveFirebase.setValue("true");
        }

        return haveFirebase;

    }

    // login existing user .....

    public MutableLiveData<Login> userLogin(Login login){

        MutableLiveData<Login> loginFirebase= new MutableLiveData<>();

        firebaseAuth.signInWithEmailAndPassword(login.getEmail(), login.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    login.isLogin= true;
                    FirebaseUser currentUser= firebaseAuth.getCurrentUser();
                    login.uId= currentUser.getUid();
                    loginFirebase.setValue(login);

                }
                else {
                    login.uId= task.getException().toString();
                    loginFirebase.setValue(login);
                }

            }
        });

        return loginFirebase;

    }

}
