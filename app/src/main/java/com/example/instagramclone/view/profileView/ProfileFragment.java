package com.example.instagramclone.view.profileView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.instagramclone.R;
import com.example.instagramclone.adapter.GridImageAdapter;
import com.example.instagramclone.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private Toolbar profileToolbar;
    private GridView profileGridView;
    private ImageView profileMenu;
    ImageView profileImageView;
    private NavController navController;
    private ProgressBar profileProgressBar;
    private static final int gridColumn=3;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        profileToolbar= view.findViewById(R.id.profileToolbarId);
        profileImageView= view.findViewById(R.id.profileImageViewId);
        profileGridView= view.findViewById(R.id.profileGridViewId);
        profileProgressBar= view.findViewById(R.id.profileProgressBarId);
        profileProgressBar.setVisibility(View.GONE);
        //setHasOptionsMenu(true); for adding menu in fragment
        setUpToolbar();
        initImageLoader();
        setProfileImage();
        tempGridImage();

        return view;
    }

    private void tempGridImage(){
        List<String> tempImage= new ArrayList<>();
        tempImage.add("https://static.toiimg.com/photo/msid-67586673/67586673.jpg?3918697");
        tempImage.add("https://www.aljazeera.com/wp-content/uploads/2020/04/ecab8c7af42a439d9043b0ade6e1f05b_18.jpeg?resize=770%2C513");
        tempImage.add("https://cdn.mos.cms.futurecdn.net/VSy6kJDNq2pSXsCzb6cvYF.jpg");
        tempImage.add("https://images.unsplash.com/photo-1496284777878-ce2e3e4dd028?ixlib=rb-1.2.1&w=1000&q=80");
        tempImage.add("https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png");
        tempImage.add("https://cdnuploads.aa.com.tr/uploads/Contents/2020/05/14/thumbs_b_c_88bedbc66bb57f0e884555e8250ae5f9.jpg?v=140708");
        tempImage.add("https://static.theprint.in/wp-content/uploads/2020/04/Cat-representational-image-e1587622908176-696x391.jpg");
        tempImage.add("https://www.humanesociety.org/sites/default/files/styles/1240x698/public/2020-07/orange-cat-490017.jpg?h=e22bf01e&itok=znSKLMy-");

        setImageGrid(tempImage);
    }

    private void setImageGrid(List<String> imageList){

        int gridWidth= getResources().getDisplayMetrics().widthPixels;
        int imageWidth= gridWidth/gridColumn;
        profileGridView.setColumnWidth(imageWidth);


        GridImageAdapter adapter= new GridImageAdapter(getActivity(),R.layout.profile_single_grid_item,"",imageList);
        profileGridView.setAdapter(adapter);

    }

    private void setProfileImage() {
        String imageUrl="https://hindikiguide.com/wp-content/uploads/2018/11/Cat-image-300x166.jpg";
        UniversalImageLoader.setImage(imageUrl,profileImageView,profileProgressBar,"");
    }
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader= new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfiguration());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        profileMenu= view.findViewById(R.id.profileMenuImageId);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_profileFragment_to_accountSetting2);
            }
        });
    }

    private void setUpToolbar(){

        ((AppCompatActivity)getActivity()).setSupportActionBar(profileToolbar);


    }

}