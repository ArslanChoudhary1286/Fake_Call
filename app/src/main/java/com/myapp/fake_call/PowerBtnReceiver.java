package com.myapp.fake_call;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class PowerBtnReceiver extends BroadcastReceiver {


    static int countPowerOff=0;
    private Activity activity=null;
    public PowerBtnReceiver(Activity activity)
    {
        this.activity=activity;
    }



    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("on", "Power button is pressed 1" + countPowerOff);

        Toast.makeText(context, "power button clicked" + countPowerOff, Toast.LENGTH_SHORT)
                .show();

        if (ExampleService.isRunningBtn == true){
            Log.d("on", "Power button is pressed 2" + countPowerOff);
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                countPowerOff++;
                Log.d("on", "Power button is pressed 3" + countPowerOff);
            }else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
//                countPowerOff++;
                Log.d("on", "Power button is pressed 4" + countPowerOff);
                if (countPowerOff == 1) {
                    Log.d("on", "Power button is pressed 5" + countPowerOff);
                    countPowerOff = 0;
                    Intent i = new Intent(activity, calling.class);
                    activity.startActivity(i);
                }
            }
//            if (countPowerOff > 1) {
//                Log.d("on", "Power button is pressed 7");
//                countPowerOff = 0;
////                Intent i = new Intent(activity, calling.class);
////                activity.startActivity(i);
//
//            }
        }

    }
}
