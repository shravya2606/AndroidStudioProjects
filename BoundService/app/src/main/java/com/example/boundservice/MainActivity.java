package com.example.boundservice;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyService mService;
    Boolean mIsBound;
    EditText editText1;
    EditText editText2;
    Button button;
    TextView textView;

    BroadcastReceiver receiver = new Receiver();
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView2);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.S_BROADCAST");
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        getApplicationContext().registerReceiver(receiver, filter , Context.RECEIVER_EXPORTED);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int a = Integer.parseInt(editText1.getText().toString());
                int b = Integer.parseInt(editText2.getText().toString());
                int sum = mService.getSum(a, b);
                textView.setText(String.valueOf(sum));



            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mIsBound) {
            unbindService(serviceConnection);
            Log.d("MyServiceExample", "ServiceConnection: Disconnected.");
            mIsBound = false;
        }
    }

    private void startService() {
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
        bindService();
    }

    private void bindService() {
        Intent serviceBindIntent = new Intent(this, MyService.class);
        bindService(serviceBindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder iBinder) {
            Log.d("MyServiceExample", "ServiceConnection: connected to service.");
            // We've bound to MyService, cast the IBinder and get MyBinder instance
            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            mService = binder.getService();
            mIsBound = true;
            // return a random number from the service
            getSumfromService();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d("MyServiceExample", "ServiceConnection: disconnected from service.");
            mIsBound = false;
        }
    };

    private void getSumfromService() {
        Log.d("MySum", "Get Sum from service" + mService.getSum(1,2));
    }
}