package com.example.contentproviderapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentProviderHolder> {

    List<EmployeeData> employeeList;
    Context context;
    ClickListener listener;

    public ContentAdapter(List<EmployeeData> employeeList,
                           Context context,
                           ClickListener listener) {
        this.employeeList = employeeList;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ContentProviderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);
        View employeeView = inflater.inflate(R.layout.activity_main2, parent, false);
        ContentProviderHolder viewHolder = new ContentProviderHolder(employeeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContentProviderHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        holder.name.setText(employeeList.get(position).name);
        holder.id.setText(employeeList.get(position).id);
        holder.des.setText(employeeList.get(position).des);
        holder.sal.setText(employeeList.get(position).sal);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
