package com.example.broadcast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    BroadcastReceiver receiver = new Receiver();
    public final String package_name = "com.example.broadcast";


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.S_BROADCAST");
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        getApplicationContext().registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction("com.example.S_BROADCAST");
                i.putExtra("key", package_name);
                sendOrderedBroadcast(i,null);
                sendBroadcast(i);


            }
        });

    }

}