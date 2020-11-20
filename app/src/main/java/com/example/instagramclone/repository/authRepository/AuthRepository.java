package com.example.instagramclone.repository.authRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.instagramclone.model.m.authModel.Login;
import com.example.instagramclone.model.m.authModel.Register;
import com.example.instagramclone.model.m.profileModel.UserInfo;
import com.example.instagramclone.model.m.profileModel.UserSettings;
import com.example.instagramclone.view.profileView.AccountSetting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AuthRepository {

    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
    private Login login= new Login();
    private Register register= new Register();

    // check user have in firebase or not .....

    public MutableLiveData<String> checkUser() {

        MutableLiveData<String> haveFirebase = new MutableLiveData<>();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null && currentUser.isEmailVerified()) {

            final DocumentReference userRef = firebaseFirestore.collection("users").document(currentUser.getUid());
            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {

                        if (task.getResult().exists()) {
                            haveFirebase.setValue("true");

                        }
                        else {
                            haveFirebase.setValue("false");
                        }

                    }
                }
            });

        }
        else {
            haveFirebase.setValue("false");

        }
        return haveFirebase;
    }

 /*   //check user in database exist.....
    public String userExistOrNot(String uId){
        final String[] isExist = new String[1];
        String a;

        final DocumentReference userRef = firebaseFirestore.collection("users").document(uId);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document==null){
                        isExist[0] ="not_exist";
                    }
                    else {
                        isExist[0] ="exist";
                    }

                }
            }
        });
        a= isExist[0];
        return a;
    }*/

            // login existing user .....

    public MutableLiveData<Login> userLogin(Login login){

        MutableLiveData<Login> loginFirebase= new MutableLiveData<>();

        firebaseAuth.signInWithEmailAndPassword(login.getEmail(), login.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser currentUser= firebaseAuth.getCurrentUser();
                    try{
                        if(currentUser.isEmailVerified()){
                            login.isLogin= true;
                            String uId= currentUser.getUid();
                            final DocumentReference userRef = firebaseFirestore.collection("users").document(uId);
                            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                    if(task.isSuccessful()){
                                        // DocumentSnapshot document = task.getResult();
                                        if(task.getResult().exists()){
                                            login.uId= "exist";
                                            loginFirebase.setValue(login);
                                        }
                                        else {
                                            login.uId= "not_exist";
                                            loginFirebase.setValue(login);
                                        }

                                    }

                                }
                            });

                        }
                        else {
                            login.isLogin= false;
                            login.uId="Not verified. Please Check your email.";
                            firebaseAuth.signOut();
                            loginFirebase.setValue(login);

                        }
                    }catch (NullPointerException e){
                        login.uId= e.getMessage();
                        loginFirebase.setValue(login);
                    }

                    /*login.uId= userExistOrNot(currentUser.getUid());
                    loginFirebase.setValue(login);*/
                }
                else {
                    login.isLogin= false;
                    login.uId= task.getException().toString();
                    loginFirebase.setValue(login);
                }

            }
        });

        return loginFirebase;

    }

    // Sign In new  user .....

    public MutableLiveData<Register> userSignUp(Register register){
        MutableLiveData<Register> registerFirebase= new MutableLiveData<>();
        //FirebaseUser currentUser= firebaseAuth.getCurrentUser();

        firebaseAuth.createUserWithEmailAndPassword(register.getEmail(),register.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser currentUser= firebaseAuth.getCurrentUser();

                            currentUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    register.isRegister= true;
                                    register.uId= currentUser.getUid();
                                    registerFirebase.setValue(register);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    register.isRegister= false;
                                    firebaseAuth.signOut();
                                    register.uId= e.toString();
                                    registerFirebase.setValue(register);

                                }
                            });

                        }
                        else {
                            register.isRegister= false;
                            register.uId= task.getException().toString();
                            registerFirebase.setValue(register);

                        }

                    }
                });

        return registerFirebase;
    }

    // check user name is exist or Not .....

    public MutableLiveData<String> userNameExist(String name){
        MutableLiveData<String> checkName= new MutableLiveData<>();

        CollectionReference usersRef = firebaseFirestore.collection("users");
        Query query = usersRef.whereEqualTo("user_name", name);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot: task.getResult()){
                        String getName = documentSnapshot.getString("user_name");
                        if(getName.equals(name)){
                            checkName.setValue("Exist");
                        }
                        else {
                            checkName.setValue("Not_Exist");
                        }
                    }
                }
                if(task.getResult().size()==0)
                {
                    checkName.setValue("Not_Exist");
                }
            }
        });
        return checkName;
    }

    // save user Name here.........

    public MutableLiveData<String> setUserName(UserSettings userSettings, UserInfo userInfo){

        MutableLiveData<String> setNameFirebase= new MutableLiveData<>();
        FirebaseUser currentUser= firebaseAuth.getCurrentUser();

        if(userSettings!=null && userInfo!= null){
            Map<String,String> infoMap= new HashMap<>();
            infoMap.put("user_name",userInfo.getUserName());
            infoMap.put("birthday",userInfo.getBirthday());
            infoMap.put("email",currentUser.getEmail());
            infoMap.put("gender",userInfo.getGender());
            infoMap.put("user_id",currentUser.getUid());
            infoMap.put("phone_number",userInfo.getPhone());
            firebaseFirestore.collection("users").document(currentUser.getUid()).set(infoMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Map<String,String> settingMap= new HashMap<>();
                                settingMap.put("description",userSettings.getDescription());
                                settingMap.put("display_name",userSettings.getDisplayName());
                                settingMap.put("following",userSettings.getFollowing());
                                settingMap.put("followres",userSettings.getFollowers());
                                settingMap.put("posts",userSettings.getPosts());
                                settingMap.put("profile_photo",userSettings.getProfilePhoto());
                                settingMap.put("user_name",userSettings.getUserName());
                                settingMap.put("website",userSettings.getWebsite());

                                firebaseFirestore.collection("user_account_settings").document(currentUser.getUid()).set(settingMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    setNameFirebase.setValue("true");
                                                }
                                                else {
                                                    setNameFirebase.setValue("false");
                                                }

                                            }
                                        });


                            }
                            else {
                                setNameFirebase.setValue("false");
                            }

                        }
                    });

        }
        return setNameFirebase;
    }

}
