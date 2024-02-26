package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.expensetracker.databinding.ActivityDashboardBinding;

import java.util.ArrayList;
import java.util.Objects;


public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    int sumExpense = 0;
    int sumIncome = 0;

    ArrayList<TransactionModel> transactionModelArrayList;
    TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        transactionModelArrayList = new ArrayList<>();

        binding.historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.historyRecyclerView.setHasFixedSize(true);
        binding.addFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(DashboardActivity.this, AddTransactionActivity.class));

            }
        });

        binding.addFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(DashboardActivity.this, AddTransactionActivity.class));
                } catch (Exception e) {

                }
            }
        });
        /*Whenever a user add new transaction details it will refresh that added details*/
        binding.refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                    finish();
                } catch (Exception e) {

                }
            }
        });
        loadData();
    }
    /*for fetching added transaction details and showing calculated value on dashboard Activity*/
    private void loadData() {
        firebaseFirestore.collection("Expenses").document((Objects.requireNonNull(firebaseAuth.getUid()))).collection("Notes")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    int sumExpense = 0;
                    int sumIncome = 0;
                    int amount;
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        // Inside the onComplete method
                        for (DocumentSnapshot ds : task.getResult()) {
                            String id = ds.getString("id");
                            String note = ds.getString("note");
                            String amountString = ds.getString("amount");
                            String type = ds.getString("type");
                            String date = ds.getString("date");


                            if (id != null && note != null && amountString != null && type != null && date != null) {

                            TransactionModel model = new TransactionModel(id, note, amountString, type, date);

                            try {
                                int amount = Integer.parseInt(amountString);
                                if ("Expense".equals(type)) {
                                    sumExpense += amount;
                                } else {
                                    sumIncome += amount;
                                }
                            } catch (NumberFormatException e) {
                                // Handle the case where 'amountString' is not a valid integer
                                e.printStackTrace();
                            }
                            transactionModelArrayList.add(model);
                        }
                    }
                        binding.totalIncome.setText(String.valueOf(sumIncome));
                        binding.totalExpense.setText(String.valueOf(sumExpense));
                        binding.totalBalance.setText(String.valueOf(sumIncome - sumExpense));

                        transactionAdapter = new TransactionAdapter(DashboardActivity.this, transactionModelArrayList);
                        binding.historyRecyclerView.setAdapter(transactionAdapter);

                    }
                });
    }
}