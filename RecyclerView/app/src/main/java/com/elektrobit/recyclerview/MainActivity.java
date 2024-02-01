package com.elektrobit.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EmployeeAdapter adapter;
    RecyclerView recyclerView;
    ClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<EmployeeData> list = new ArrayList<>();
        list = getData();

        recyclerView
                = (RecyclerView)findViewById(
                R.id.recyclerView);
        listener = new ClickListener() {
            @Override
            public void click(int index){
                Toast.makeText(getApplicationContext(), "Clicked item index is "+index, Toast.LENGTH_SHORT).show();
            }
        };
        adapter
                = new EmployeeAdapter(
                list, getApplication(),listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(MainActivity.this));
    }



    private List<EmployeeData> getData()
    {
        List<EmployeeData> list = new ArrayList<>();
        list.add(new EmployeeData("Ayush",
                "123456",
                "COS"));
        list.add(new EmployeeData("Vivek",
                "923456",
                "COS"));
        list.add(new EmployeeData("Akshay",
                "823456",
                "COS"));
        list.add(new EmployeeData("Ayush",
                "123456",
                "COS"));
        list.add(new EmployeeData("Vivek",
                "923456",
                "COS"));
        list.add(new EmployeeData("Akshay",
                "823456",
                "COS"));
        list.add(new EmployeeData("Ayush",
                "123456",
                "COS"));
        list.add(new EmployeeData("Vivek",
                "923456",
                "COS"));
        list.add(new EmployeeData("Akshay",
                "823456",
                "COS"));
        list.add(new EmployeeData("Ayush",
                "123456",
                "COS"));
        list.add(new EmployeeData("Vivek",
                "923456",
                "COS"));
        list.add(new EmployeeData("Akshay",
                "823456",
                "COS"));

        return list;
    }
}