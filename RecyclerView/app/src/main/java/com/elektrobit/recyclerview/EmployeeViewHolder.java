package com.elektrobit.recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeViewHolder extends RecyclerView.ViewHolder{

    EditText name, id, dept;
    Button btnAdd;
    View view;
    public EmployeeViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.editTextName);
        id = itemView.findViewById(R.id.editTextId);
        dept = itemView.findViewById(R.id.editTextDepartment);
        btnAdd = itemView.findViewById(R.id.btnAdd);
        view = itemView;
    }
}
