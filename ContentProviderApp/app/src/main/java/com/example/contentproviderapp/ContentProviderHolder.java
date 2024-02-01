package com.example.contentproviderapp;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class ContentProviderHolder extends RecyclerView.ViewHolder{


        EditText name, id, des, sal;
        Button insertButton;
    Button loadButton;
        View view;

        @SuppressLint("WrongViewCast")
        public ContentProviderHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.employee_Username);
            name = itemView.findViewById(R.id.employee_name);
            des = itemView.findViewById(R.id.employee_designation);
            sal = itemView.findViewById(R.id.employee_salary);
            view = itemView;
        }
    }

