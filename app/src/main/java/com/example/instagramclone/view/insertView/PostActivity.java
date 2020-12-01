package com.example.instagramclone.view.insertView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.utils.UniversalImageLoader;

public class PostActivity extends AppCompatActivity {

    private ImageView postBackImage,postConfirmImage,postImage;
    private EditText captionEditText;

    private String append="file:/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        findSection();

        postBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        postConfirmImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        setImage();

    }

    private void setImage(){
        String value= getIntent().getStringExtra("select");
        UniversalImageLoader.setImage(value,postImage,null,append);

    }





    private void findSection() {
        postBackImage= findViewById(R.id.postBackId);
        postConfirmImage= findViewById(R.id.postConfirmId);
        postImage= findViewById(R.id.postImageId);
        captionEditText= findViewById(R.id.postCaptionId);
    }
}