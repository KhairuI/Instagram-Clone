package com.example.instagramclone.view.insertView;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagramclone.R;
import com.example.instagramclone.adapter.SectionPageAdapter;
import com.example.instagramclone.utils.Permissions;
import com.google.android.material.tabs.TabLayout;

public class InsertFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private static final int PERMISSION_REQUEST=1;

    public InsertFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_insert, container, false);
        viewPager= view.findViewById(R.id.viewPagerId);
        tabLayout= view.findViewById(R.id.insertTabId);

        if(checkPermission(Permissions.PERMISSIONS)){
            setUpViewpager();
        }
        else {
            takePermission(Permissions.PERMISSIONS);
        }


        return view;
    }

    private void setUpViewpager() {
        SectionPageAdapter adapter= new SectionPageAdapter(getChildFragmentManager());
        adapter.addFragment(new GalleryFragment());
        adapter.addFragment(new PhotoFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("GALLERY");
        tabLayout.getTabAt(1).setText("PHOTO");

    }

    public void takePermission(String[] permissions) {
        ActivityCompat.requestPermissions(getActivity(),permissions,PERMISSION_REQUEST);
        setUpViewpager();
    }

    private boolean checkPermission(String[] permissions) {
        for(int i=0; i<permissions.length;i++){

            String single= permissions[i];
            if(!singlePermission(single)){
                return false;
            }
                
        }
        return true;
    }

    public boolean singlePermission(String single) {
        int permissionRequest= ActivityCompat.checkSelfPermission(getActivity(),single);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            return false;
        }
        else {
            return true;
        }
    }
}