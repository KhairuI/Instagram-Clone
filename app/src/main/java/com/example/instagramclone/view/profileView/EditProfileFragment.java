package com.example.instagramclone.view.profileView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.model.m.profileModel.UserInfo;
import com.example.instagramclone.model.m.profileModel.UserSettings;
import com.example.instagramclone.utils.UniversalImageLoader;
import com.example.instagramclone.viewmodel.profileViewModel.ProfileViewModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment {
    private CircleImageView editProfileImage;
    private ProgressBar progressBar;
    private ImageView editProfileBack;
    private ImageView editProfileSave;
    private ProfileViewModel profileViewModel;
    private TextView imageChangeText,userName;
    private NavController navController;
    private EditText displayName,website,bio,email,phone,gender,birthday;


    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initialViewModel();
        setInfo();
        setUser();
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_edit_profile, container, false);
        editProfileImage= view.findViewById(R.id.editProfileImageId);
        initImageLoader();
        editProfileBack= view.findViewById(R.id.editProfileBackId);
        editProfileSave= view.findViewById(R.id.editProfileSaveId);
        imageChangeText= view.findViewById(R.id.editProfilePhotoChangeId);
        displayName= view.findViewById(R.id.editProfileDisplayNameEditTextId);
        userName= view.findViewById(R.id.editProfileUserNameEditTextId);
        website= view.findViewById(R.id.editProfileWebsiteEditTextId);
        bio= view.findViewById(R.id.editProfileBioEditTextId);
        email= view.findViewById(R.id.editProfileEmailEditTextId);
        phone= view.findViewById(R.id.editProfilePhoneEditTextId);
        gender= view.findViewById(R.id.editProfileGenderEditTextId);
        birthday= view.findViewById(R.id.editProfileBirthdayEditTextId);
        progressBar= view.findViewById(R.id.editProfileProgressId);
        progressBar.setVisibility(View.GONE);



        // back button.....
        editProfileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        // Save button.....

        editProfileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // edit name click listener
        userName= view.findViewById(R.id.editProfileUserNameEditTextId);
        navController= Navigation.findNavController(view);
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_editProfileFragment_to_setNameFragment);
            }
        });
    }

    private void setUser() {
        profileViewModel.getUserData();
        profileViewModel.userData.observe(getActivity(), new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                try {
                    email.setText(userInfo.getEmail());
                    phone.setText(userInfo.getPhone());
                    gender.setText(userInfo.getGender());
                    birthday.setText(userInfo.getBirthday());

                }catch (NullPointerException e){
                    Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setInfo() {
        profileViewModel.getUserSettingData();
        profileViewModel.userSettingData.observe(getActivity(), new Observer<UserSettings>() {
            @Override
            public void onChanged(UserSettings userSettings) {

                try {
                    userName.setText(userSettings.getUserName());
                    setProfileImage(userSettings.getProfilePhoto());
                    displayName.setText(userSettings.getDisplayName());
                    bio.setText(userSettings.getDescription());
                    website.setText(userSettings.getWebsite());

                }catch (NullPointerException e){
                    Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void initImageLoader() {
        UniversalImageLoader universalImageLoader= new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfiguration());

    }

    private void setProfileImage(String url) {
        if(url.equals("Empty")){
            url= "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png";
            UniversalImageLoader.setImage(url,editProfileImage,progressBar,"");
        }
        else {
            UniversalImageLoader.setImage(url,editProfileImage,progressBar,"");
        }
    }

    private void initialViewModel() {
        profileViewModel= new ViewModelProvider(getActivity(),ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(ProfileViewModel.class);
    }
}