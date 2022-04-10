package com.myapp.fake_call;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import static android.content.ContentValues.TAG;

public class Sechdual extends AppCompatActivity {
    TextView sec10, mnt1, mnt5, mnt10, mnt20, mnt30;
    CountDownTimer timer1;
    boolean timerIsRunning = false;
    public static boolean isScheduleRunning = false;

    private AdView mAdView;
    private RewardedAd mRewardedAd;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sechdual);
        getSupportActionBar().hide();

        sec10 = findViewById(R.id.sec10);
        mnt1 = findViewById(R.id.mnt1);
        mnt5 = findViewById(R.id.mnt5);
        mnt10 = findViewById(R.id.mnt10);
        mnt20 = findViewById(R.id.mnt20);
        mnt30 = findViewById(R.id.mnt30);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView1);
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


        RewardedAd.load(this, "ca-app-pub-5386510617974960/8006997270",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d(TAG, "Ad was loaded.");
                    }
                });




        sec10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerIsRunning == true){
                    timer1.cancel();
                    schedule(10000);

                }else {
                    schedule(10000);
                }

            }
        });
        mnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timerIsRunning == true){
                    timer1.cancel();
                    runAds();
                    schedule(60000);
                }else {
                    runAds();
                    schedule(60000);
                }

            }
        });
        mnt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timerIsRunning == true){
                    timer1.cancel();
                    runAds();
                    schedule(300000);
                }else {
                    runAds();
                    schedule(300000);
                }

            }
        });
        mnt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timerIsRunning == true){
                    timer1.cancel();
                    runAds();
                    schedule(600000);
                }else {
                    runAds();
                    schedule(600000);
                }

            }
        });

        mnt20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timerIsRunning == true){
                    timer1.cancel();
                    runAds();
                    schedule(1200000);
                }else {
                    runAds();
                    schedule(1200000);
                }

            }
        });

        mnt30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (timerIsRunning == true){
                    timer1.cancel();
                    runAds();
                    schedule(1800000);
                }else {
                    runAds();
                    schedule(1800000);
                }

            }
        });


    }

    private void runAds(){
        if (mRewardedAd != null) {
            Activity activityContext = Sechdual.this;
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d(TAG, "The user earned the reward.");
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                }
            });
        } else {
            Log.d(TAG, "The rewarded ad wasn't ready yet.");
        }
    }

    private void updateNotification(String text) {

        Notification notification = ExampleService.getMyActivityNotification(Sechdual.this, text);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notification);
    }
    public void schedule(int timer){
        if (!SettingActivity.isServiceRunningInForeground(Sechdual.this, ExampleService.class)){
            isScheduleRunning = true;
            SettingActivity.getTxt();
            startService();

        }else {
            isScheduleRunning = true;
            updateNotification(SettingActivity.getTxt());

        }

            timer1 = new CountDownTimer(timer,timer){
                @Override
                public void onTick(long l) {
                    timerIsRunning = true;
                    if (timer == 5000){
                        Toast.makeText(Sechdual.this, "you will receive call after 5 sec", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Sechdual.this, "you will receive call after " +timer/60000 + " minute", Toast.LENGTH_SHORT).show();
                    }

                }

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onFinish() {
                    timerIsRunning = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        Intent intent = new Intent(Sechdual.this, calling.class);
                        Bundle b = ActivityOptions.makeSceneTransitionAnimation(Sechdual.this).toBundle();
                        startActivity(intent, b);
                    }
                    else {
                        startActivity(new Intent(Sechdual.this,calling.class));
                    }


                }
            }.start();

    }

    private void startService(){

        Intent intent1 = new Intent(this,ExampleService.class);
        ContextCompat.startForegroundService(this,intent1);

    }


}