package com.example.instagramclone.viewmodel.profileViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.instagramclone.model.m.profileModel.UpdateProfile;
import com.example.instagramclone.model.m.profileModel.UserInfo;
import com.example.instagramclone.model.m.profileModel.UserSettings;
import com.example.instagramclone.repository.profileRepository.ProfileRepository;
import com.google.firebase.firestore.auth.User;

public class ProfileViewModel extends AndroidViewModel {

    private ProfileRepository repository;

    public LiveData<UserSettings> userSettingData;
    public LiveData<UserInfo> userData;
    public LiveData<String> userName;
    public LiveData<String> updateName;
    public LiveData<String> updateProfile;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repository= new ProfileRepository();
    }

    public void getUserSettingData(){

        userSettingData= repository.getUserSettingData();
    }

    public void getUserData(){
        userData= repository.getUserData();
    }

    public void getUserName(){
        userName= repository.getUserName();

    }

    public void UpdateUserName(String name){
        updateName= repository.UpdateUserName(name);
    }

    public void UpdateProfile(UpdateProfile profile){
        updateProfile= repository.UpdateProfile(profile);
    }
}
