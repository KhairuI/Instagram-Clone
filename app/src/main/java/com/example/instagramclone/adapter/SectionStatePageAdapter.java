package com.example.instagramclone.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionStatePageAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> stateFragmentList= new ArrayList<>();
    private final HashMap<Fragment,Integer> mFragments= new HashMap<>();
    private final HashMap<String,Integer> mFragmentsNumber= new HashMap<>();
    private final HashMap<Integer,String> mFragmentsName= new HashMap<>();

    public SectionStatePageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return stateFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return stateFragmentList.size();
    }

    public void addStateFragment(Fragment fragment,String fragmentName){

        stateFragmentList.add(fragment);
        mFragments.put(fragment,stateFragmentList.size()-1);
        mFragmentsNumber.put(fragmentName,stateFragmentList.size()-1);
        mFragmentsName.put(stateFragmentList.size()-1,fragmentName);

    }

    public Integer getFragmentNumber(String fragmentName){
        if(mFragmentsNumber.containsKey(fragmentName)){
            return mFragmentsNumber.get(fragmentName);
        }
        else {
            return null;
        }
    }

    public Integer getFragmentNumber(Fragment fragment){
        if(mFragmentsNumber.containsKey(fragment)){
            return mFragmentsNumber.get(fragment);
        }
        else {
            return null;
        }
    }

    public String getFragmentName(Integer fragmentNumber){
        if(mFragmentsName.containsKey(fragmentNumber)){
            return mFragmentsName.get(fragmentNumber);
        }
        else {
            return null;
        }
    }
}
