package com.example.boundserviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    MyService mService;
    Boolean mIsBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(mIsBound){
            unbindService(serviceConnection);
            Log.d("MyServiceExample", "ServiceConnection:Disconnected.");
            mIsBound = false;
        }
    }

    private void startService(){
        /*Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);*/

        bindService();
    }

    private void bindService(){
        Intent serviceBindIntent =  new Intent(this, MyService.class);
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

            getRandomNumberFromService(); // return a random number from the service
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d("MyServiceExample", "ServiceConnection: disconnected from service.");
            mIsBound = false;
        }
    };

    private void getRandomNumberFromService(){
        Log.d("MyServiceExample", "getRandomNumberFromService: Random number from service: " + mService.getRandomNumber());
    }


}