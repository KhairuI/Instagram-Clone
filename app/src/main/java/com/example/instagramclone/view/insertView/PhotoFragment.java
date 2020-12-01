package com.example.instagramclone.view.insertView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.utils.Permissions;


public class PhotoFragment extends Fragment {
    private ImageView camera;


    private static final int PHOTO_FRAGMENT_NUM=1;
    private static final int GALLERY_FRAGMENT_NUM=2;
    private static final int CAMERA_REQUEST_CODE=3;

    public PhotoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_photo, container, false);

        camera= view.findViewById(R.id.cameraId);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== CAMERA_REQUEST_CODE){

        }
    }
}