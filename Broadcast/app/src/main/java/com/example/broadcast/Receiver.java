package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String a = intent.getAction();
        if(a.equals("com.example.S_BROADCAST")) {
//     Toast.makeText(context, "Custom message received", Toast.LENGTH_SHORT).show();
        }
        else if (isAirplaneModeOn(context.getApplicationContext())) {
            Toast.makeText(context, "AirPlane mode is on", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "AirPlane mode is off", Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}
