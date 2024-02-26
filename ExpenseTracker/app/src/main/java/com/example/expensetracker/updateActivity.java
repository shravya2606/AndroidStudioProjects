package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.expensetracker.databinding.UpdateActivityBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class updateActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    UpdateActivityBinding binding;
    String newType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UpdateActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        String id = getIntent().getStringExtra("id");
        String amount = getIntent().getStringExtra("amount");
        String  note = getIntent().getStringExtra("note");
        String type = getIntent().getStringExtra("type");

        binding.userAmountAdd.setText(amount);
        binding.userNoteAdd.setText(note);
        switch(type)
        {
            case "Income":
                newType = "Income";
                binding.incomeCheckBox.setChecked(true);
                break;
            case "Expense":
                newType = "Expense";
                binding.expenseCheckBox.setChecked(true);
                break;
        }
        binding.incomeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newType = "Income";
                binding.incomeCheckBox.setChecked(true);
                binding.expenseCheckBox.setChecked(false);
            }
        });
        binding.expenseCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newType = "Expense";
                binding.incomeCheckBox.setChecked(false);
                binding.expenseCheckBox.setChecked(true);
            }
        });

        binding.btnTransctionUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = binding.userAmountAdd.getText().toString();
                String note = binding.userNoteAdd.getText().toString();
                firebaseFirestore.collection("Expenses").document(firebaseAuth.getUid()).collection("Notes").document(id).
                        update("amount", amount, "note", note, "type", type)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(updateActivity.this, "Updated", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(updateActivity.this, "Failed", Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

        binding.btnTransctionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("Expenses").document(firebaseAuth.getUid()).collection("Notes").document(id).
                        delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(updateActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                            }

                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(updateActivity.this, " Failed to Delete", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

    }
}