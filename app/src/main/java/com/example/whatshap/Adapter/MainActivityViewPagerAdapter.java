package com.example.whatshap.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatshap.Fragment.ChatFragment;
import com.example.whatshap.Fragment.ProfileFragment;
import com.example.whatshap.Fragment.StatusFragment;



public class MainActivityViewPagerAdapter extends FragmentPagerAdapter {

    public MainActivityViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new ChatFragment();
        } else if (position==1) {
            return new StatusFragment();
        }
        else if(position==2) {
            return new ProfileFragment();
        }
        else{ return null;}
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "CHAT";
        } else if (position==1) {
            return "STATUS";
        }
        else return "PROFILE";
    }
}
