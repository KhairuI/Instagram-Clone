package com.example.instagramclone.view.profileView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.instagramclone.R;

public class ProfileFragment extends Fragment {

    private Toolbar profileToolbar;
    private ImageView profileMenu;
    private FrameLayout frameLayout;
    private NavController navController;
    private ProgressBar profileProgressBar;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        profileToolbar= view.findViewById(R.id.profileToolbarId);
        profileProgressBar= view.findViewById(R.id.profileProgressBarId);
        profileProgressBar.setVisibility(View.GONE);
        //setHasOptionsMenu(true); for adding menu in fragment
        setUpToolbar();


        return view;
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