package com.example.instagramclone.view.profileView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.instagramclone.R;
import com.example.instagramclone.adapter.SectionStatePageAdapter;

import java.util.ArrayList;


public class AccountSetting extends Fragment {

    private ListView accountListView;
    private ImageView backImage;
    private ArrayList option= new ArrayList();
    private SectionStatePageAdapter statePageAdapter;
    private ViewPager viewPager;
    private RelativeLayout relativeLayout;

    public AccountSetting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_account_setting, container, false);
        accountListView= view.findViewById(R.id.accountSettingListId);
        viewPager= view.findViewById(R.id.viewPagerId);
        relativeLayout= view.findViewById(R.id.accSetAllId);
        backImage= view.findViewById(R.id.accountSettingBackId);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getActivity().onBackPressed();
            }
        });
        setUpList();
        setUpFragments();
        return view;
    }

    private void setUpFragments(){

        statePageAdapter= new SectionStatePageAdapter(getChildFragmentManager());
        statePageAdapter.addStateFragment(new EditProfileFragment(),"Edit Profile");
        statePageAdapter.addStateFragment(new SignOutFragment(),"Sign Out");

    }
    private void setViewPager(int fragmentNo){
        relativeLayout.setVisibility(View.GONE);
        viewPager.setAdapter(statePageAdapter);
        viewPager.setCurrentItem(fragmentNo);
    }

    private void setUpList(){

        option.add("Edit Profile");
        option.add("Sign Out");
        ArrayAdapter adapter= new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,option);
        accountListView.setAdapter(adapter);

        accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                setViewPager(position);
            }
        });

    }

}