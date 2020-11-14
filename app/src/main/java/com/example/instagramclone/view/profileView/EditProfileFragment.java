package com.example.instagramclone.view.profileView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.instagramclone.R;
import com.example.instagramclone.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EditProfileFragment extends Fragment {
    private ImageView editProfileImage;
    private ImageView editProfileBack;
    private ImageView editProfileSave;


    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_edit_profile, container, false);
        editProfileImage= view.findViewById(R.id.editProfileImageId);
        initImageLoader();
        setProfileImage();
        editProfileBack= view.findViewById(R.id.editProfileBackId);
        editProfileSave= view.findViewById(R.id.editProfileSaveId);

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

    private void initImageLoader() {
        UniversalImageLoader universalImageLoader= new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfiguration());

    }

    private void setProfileImage() {
        String imageUrl="https://hindikiguide.com/wp-content/uploads/2018/11/Cat-image-300x166.jpg";
        UniversalImageLoader.setImage(imageUrl,editProfileImage,null,"");
    }
}