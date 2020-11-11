package com.example.instagramclone.view.homeView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagramclone.R;
import com.example.instagramclone.adapter.SectionPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_home, container, false);
        viewPager= view.findViewById(R.id.viewPagerId);
        tabLayout= view.findViewById(R.id.homeTabId);
        setUpViewPager();

        return view;
    }

    // for 3 page camera, refresh, message
    private void setUpViewPager(){

        SectionPageAdapter adapter= new SectionPageAdapter(getChildFragmentManager());
        adapter.addFragment(new HomeCamera());
        adapter.addFragment(new RefreshHome());
        adapter.addFragment(new HomeMessage());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);

        tabLayout.getTabAt(1).setIcon(R.drawable.instagram);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_message);


    }
}