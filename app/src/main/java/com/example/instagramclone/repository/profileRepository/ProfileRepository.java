package com.example.instagramclone.repository.profileRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.instagramclone.model.m.profileModel.UserInfo;
import com.example.instagramclone.model.m.profileModel.UserSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ProfileRepository {
    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();

    //  retrieve account setting data like following, posts etc.....

    public MutableLiveData<UserSettings> getUserSettingData(){
        String currentUser= firebaseAuth.getCurrentUser().getUid();
        final MutableLiveData<UserSettings> userSettingData= new MutableLiveData<>();
        firebaseFirestore.collection("user_account_settings").document(currentUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot ds= task.getResult();
                     String description= ds.getString("description");
                     String displayName = ds.getString("display_name");
                     String following = ds.getString("following");
                     String followers = ds.getString("followres");
                     String posts = ds.getString("posts");
                     String profilePhoto = ds.getString("profile_photo");
                     String userName = ds.getString("user_name");
                     String website = ds.getString("website");
                     UserSettings settings= new UserSettings(description,displayName,following,followers,posts,profilePhoto,userName,website);
                     userSettingData.setValue(settings);

                }
                else {

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        return userSettingData;
    }

    //  retrieve users data like following, posts etc.....
    public MutableLiveData<UserInfo> getUserData(){

        String currentUser= firebaseAuth.getCurrentUser().getUid();
        final MutableLiveData<UserInfo> userData= new MutableLiveData<>();
        firebaseFirestore.collection("users").document(currentUser).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot ds= task.getResult();

                     String userName = ds.getString("user_name");
                     String birthday = ds.getString("birthday");
                     String email = ds.getString("email");
                     String gender = ds.getString("gender");
                     String uId = ds.getString("user_id");
                     String phone = ds.getString("phone_number");
                     UserInfo info= new UserInfo(userName,birthday,email,gender,uId,phone);
                     userData.setValue(info);
                }

            }
        });


        return userData;
    }


}
