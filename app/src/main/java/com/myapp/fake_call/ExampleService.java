package com.myapp.fake_call;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.security.spec.ECField;

import static com.myapp.fake_call.App.CHANNEL_ID;

public class ExampleService extends Service  implements SensorEventListener{
//    String input;
    public static SensorManager sensorManager;
    private static Sensor accelerometerSensor;
    private static boolean isAccelerometerAvailable;
    //    Sensor accelerometerSensor;
    boolean  itIsNotFirstTime = false;
    float currentX, currentY, currentZ, lastX, lastY, lastZ;
    float xDifference, yDifference, zDifference;
    float shakeThreshold = 5f;
    private Vibrator vibrator;

    public static boolean isRunningBtn = false;
    public static boolean isRunningSensor = false;





    @Override
    public void onCreate() {
        super.onCreate();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

//        Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();
        Log.d("sens","create");

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isAccelerometerAvailable = true;
        }else {
//            x.setText("accelerometer sensor is not available");
            isAccelerometerAvailable = false;
        }


        runSensor();


    }
    public void runSensor(){
        if (isAccelerometerAvailable && isRunningSensor == true){
            sensorManager.registerListener(this,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }else {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (intent.getData() != null)
//        input = intent.getStringExtra("putExtra");



        startForeground(1, getMyActivityNotification(this, SettingActivity.input));

        return START_STICKY;

    }

    public static Notification getMyActivityNotification(Context context, String input){
        Intent intent1 = new Intent(context,SettingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID);

                builder.setContentTitle(input)
//                .setContentText(input)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("a")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setOnlyAlertOnce(true)
                .setOngoing(true)
                .setContentIntent(pendingIntent);

        return builder.build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        sensorManager.unregisterListener(this);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        onCreate();


//        x.setText(event.values[0] + "m/s2");
//        y.setText(event.values[1] + "m/s2");
//        z.setText(event.values[2] + "m/s2");
//        Toast.makeText(this, "sens", Toast.LENGTH_SHORT).show();
        Log.d("sens","sens");

        currentX = event.values[0];
        currentY = event.values[1];
        currentZ = event.values[2];

        if (itIsNotFirstTime){
            xDifference = Math.abs(lastX - currentX);
            yDifference = Math.abs(lastY - currentY);
            zDifference = Math.abs(lastZ - currentZ);

            if ((xDifference > shakeThreshold && yDifference > shakeThreshold)
                    && (yDifference > shakeThreshold && zDifference >shakeThreshold)
                    && (xDifference > shakeThreshold && zDifference >shakeThreshold)
                    ){
                if (calling.fragmentIsAvailable){

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        Intent intent = new Intent(this, calling.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        vibrator.vibrate(500);
                        Intent intent = new Intent(this, calling.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }

            }

        }

        lastX = currentX;
        lastY = currentY;
        lastZ = currentZ;

        itIsNotFirstTime = true;


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        sensorManager.unregisterListener(this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (isAccelerometerAvailable){
//            sensorManager.registerListener(this,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
//        }
//    }

}
