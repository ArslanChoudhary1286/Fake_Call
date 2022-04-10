package com.myapp.fake_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.w3c.dom.Text;

public class SettingActivity extends AppCompatActivity  {

    Switch buttonSwitch, shakeSwitch;
    TextView scheduleTxt, shareTxt;
    public static String input = "Call detector is running";
    public static String shakeTxt = "";
    public static String btnTxt = "";
    public static String scheduleText = "";
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setTitle("Setting");

        buttonSwitch = findViewById(R.id.buttonSwitch);
        buttonSwitch.setSelected(true);
        shakeSwitch = findViewById(R.id.shakeSwitch);
        shakeSwitch.setSelected(true);
        scheduleTxt = findViewById(R.id.scheduleTxt);
        shareTxt = findViewById(R.id.shareTxt);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
                mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                super.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                super.onAdClosed();
            }
        });


        if (isServiceRunningInForeground(SettingActivity.this,ExampleService.class)){
            if (ExampleService.isRunningBtn){
                buttonSwitch.setChecked(true);
            }
            if (ExampleService.isRunningSensor){
                shakeSwitch.setChecked(true);
            }
        }else {
            buttonSwitch.setChecked(false);
            shakeSwitch.setChecked(false);
        }

        shakeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!isServiceRunningInForeground(SettingActivity.this,ExampleService.class)){
                        ExampleService.isRunningSensor = true;
                        startService();
                        getTxt();
                    }
                    else {
                        stopService();
                        ExampleService.isRunningSensor = true;
                        getTxt();
                        startService();
                    }
                }

                else{
                    if (ExampleService.isRunningBtn || Sechdual.isScheduleRunning){

                        ExampleService.isRunningSensor = false;
                        updateNotification(getTxt());
                    }
                    else {
                        stopService();
                        ExampleService.isRunningSensor = false;
                    }
                }
            }
        });

        buttonSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!isServiceRunningInForeground(SettingActivity.this,ExampleService.class)){
                        ExampleService.isRunningBtn = true;
                        getTxt();
                        startService();
                        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
                        filter.addAction(Intent.ACTION_SCREEN_OFF);
                        PowerBtnReceiver mReceiver = new PowerBtnReceiver(SettingActivity.this);
                        registerReceiver(mReceiver, filter);
                    }
                    else {
                        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
                        filter.addAction(Intent.ACTION_SCREEN_OFF);
                        PowerBtnReceiver mReceiver = new PowerBtnReceiver(SettingActivity.this);
                        registerReceiver(mReceiver, filter);
                        ExampleService.isRunningBtn = true;

                        updateNotification(getTxt());
                    }
                }
                else{
                    if (ExampleService.isRunningSensor || Sechdual.isScheduleRunning){
                        ExampleService.isRunningBtn = false;
                        updateNotification(getTxt());

                    }
                    else {
                        stopService();
                        ExampleService.isRunningBtn = false;
                    }

                }
            }
        });

        scheduleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,Sechdual.class));
            }
        });

        shareTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nDownload this Application:-\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }

            }
        });

    }

    public static String getTxt (){
        if (Sechdual.isScheduleRunning){
            scheduleText = "schedule ";
        }else {
            scheduleText = "";
        }
        if (ExampleService.isRunningBtn){
            btnTxt = "Power button ";
        }else {
            btnTxt = "";
        }
        if (ExampleService.isRunningSensor){
            shakeTxt = "shake ";
        }else {
            shakeTxt = "";
        }
        input = scheduleText+btnTxt+shakeTxt+ "detector is running";
        return input;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SettingActivity.this,MainActivity.class));
        finish();
    }

    private void updateNotification(String text) {

        Notification notification = ExampleService.getMyActivityNotification(SettingActivity.this, text);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notification);
    }

    private void startService(){

        Intent intent1 = new Intent(this,ExampleService.class);
        ContextCompat.startForegroundService(this,intent1);

    }
    private void stopService(){

        Intent intent1 = new Intent(this,ExampleService.class);
        stopService(intent1);
    }

    public static boolean isServiceRunningInForeground(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                if (service.foreground) {
                    return true;
                }

            }
        }
        return false;
    }


}