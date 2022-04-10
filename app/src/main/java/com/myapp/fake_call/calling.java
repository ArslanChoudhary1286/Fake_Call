package com.myapp.fake_call;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class calling extends AppCompatActivity {
    int fragmentNo = 1;
    public static boolean fragmentIsAvailable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        getSupportActionBar().hide();
        int callKey = getIntent().getIntExtra("key",0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {

            setShowWhenLocked(true);
            setTurnScreenOn(true);
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            keyguardManager.requestDismissKeyguard(this, null);
        }
        else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("ui",MODE_PRIVATE);
        String data = sharedPreferences.getString("ui1","1");

        fragmentNo = Integer.parseInt(data);

        if (fragmentNo == 1){

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment,new fragmentCalling1()).commit();
            fragmentIsAvailable = false;
            if (Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn && callKey != 1){
                stopService();
                Sechdual.isScheduleRunning = false;
            }else if (!Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn){

            }
            else {
                updateNotification(SettingActivity.getTxt());
            }

        }
        else if (fragmentNo == 2){

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment,new fragmentCalling2()).commit();
            fragmentIsAvailable = false;
            if (Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn && callKey != 1){
                stopService();
                Sechdual.isScheduleRunning = false;
            }else if (!Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn){

            }
            else {
                updateNotification(SettingActivity.getTxt());
            }

        }
        else if (fragmentNo == 3){

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment,new fragmentCalling3()).commit();
            fragmentIsAvailable = false;
            if (Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn && callKey != 1){
                stopService();
                Sechdual.isScheduleRunning = false;
            }else if (!Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn){

            }
            else {
                updateNotification(SettingActivity.getTxt());
            }

        }
        else if (fragmentNo == 4){

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment,new fragmentCalling4()).commit();
            fragmentIsAvailable = false;
            if (Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn && callKey != 1){
                stopService();
                Sechdual.isScheduleRunning = false;
            }else if (!Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn){

            }
            else {
                updateNotification(SettingActivity.getTxt());
            }

        }
        else if (fragmentNo == 5){

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment,new fragmentCalling5()).commit();
            fragmentIsAvailable = false;
            if (Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn && callKey != 1){
                stopService();
                Sechdual.isScheduleRunning = false;
            }else if (!Sechdual.isScheduleRunning && !ExampleService.isRunningSensor && !ExampleService.isRunningBtn){

            }
            else {
                updateNotification(SettingActivity.getTxt());
            }

        }

    }
    private void updateNotification(String text) {

        Notification notification = ExampleService.getMyActivityNotification(calling.this, text);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notification);
    }

    private void stopService(){

        Intent intent1 = new Intent(this,ExampleService.class);
        stopService(intent1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            //Do something
            if (fragmentCalling1.mediaPlayer != null){
                fragmentCalling1.mediaPlayer.stop();
            }else if (fragmentCalling2.mediaPlayer != null){
                fragmentCalling2.mediaPlayer.stop();
            }else if (fragmentCalling3.mediaPlayer != null){
                fragmentCalling3.mediaPlayer.stop();
            }else if (fragmentCalling4.mediaPlayer != null){
                fragmentCalling4.mediaPlayer.stop();
            }else if (fragmentCalling5.mediaPlayer != null){
                fragmentCalling5.mediaPlayer.stop();
            }

        }
        return true;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)){
            //Do something
            if (fragmentCalling1.mediaPlayer != null){
                fragmentCalling1.mediaPlayer.stop();
            }else if (fragmentCalling2.mediaPlayer != null){
                fragmentCalling2.mediaPlayer.stop();
            }else if (fragmentCalling3.mediaPlayer != null){
                fragmentCalling3.mediaPlayer.stop();
            }else if (fragmentCalling4.mediaPlayer != null){
                fragmentCalling4.mediaPlayer.stop();
            }else if (fragmentCalling5.mediaPlayer != null){
                fragmentCalling5.mediaPlayer.stop();
            }
        }
        return true;
    }


    @Override
    protected void onPause() {
//        mediaPlayer.stop();
//        finish();
        super.onPause();
    }

}