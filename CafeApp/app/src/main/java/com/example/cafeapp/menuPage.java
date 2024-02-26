package com.example.cafeapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class menuPage extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    Button button;
    MyViewPagerAdatper myViewPageAdatper;


    public menuPage() {
        // Required empty public constructor
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.menu_page, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager1);
        button = view.findViewById(R.id.orderBtnLayout);

        myViewPageAdatper = new MyViewPagerAdatper(this);



        return view;
    }
}