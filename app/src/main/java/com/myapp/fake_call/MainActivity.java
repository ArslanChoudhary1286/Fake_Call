package com.myapp.fake_call;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    CardView call, caller, charach, ringtone, voice, scheduale, ui, share, setting;
    Uri uri;
    public SharedPreferences.Editor editor, editor2, editor3;
    ImageView img;
    Uri selectImage;
    String id, pic;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        call = findViewById(R.id.call);
        caller = findViewById(R.id.caller);
        charach = findViewById(R.id.character);
        ringtone = findViewById(R.id.ringtone);
        voice = findViewById(R.id.voice);
        scheduale = findViewById(R.id.schedule);
        ui = findViewById(R.id.ui);
        share = findViewById(R.id.share);
        setting = findViewById(R.id.setting);

        DataBase db = new DataBase(MainActivity.this);

        SharedPreferences sharedPreferences = getSharedPreferences("tone",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        SharedPreferences sharedPreferences2 = getSharedPreferences("id",MODE_PRIVATE);
        editor2 = sharedPreferences2.edit();

        SharedPreferences sharedPreferences3 = getSharedPreferences("pic",MODE_PRIVATE);
        editor3 = sharedPreferences3.edit();



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
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


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pic = intent.getStringExtra("pic");



        if (id != null){
            editor2.putString("id1",id);
            editor2.apply();

            editor3.putString("pic1","-1");
            editor3.apply();

        }
        if (pic != null){
            editor3.putString("pic1",pic);
            editor3.apply();

            editor2.putString("id1","-1");
            editor2.apply();
        }

        call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,calling.class);
                Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
                intent.putExtra("key",1);
                startActivity(intent, b);

            }
        });


        caller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(MainActivity.this,caller.class);
                startActivity(intent1);
            }
        });


        charach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,charachter.class);
                startActivity(intent);

            }
        });


        ringtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTune();

            }

        });


        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, voice.class);
                startActivity(intent);
            }
        });

        scheduale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(MainActivity.this,Sechdual.class);
                startActivity(intent1);

            }
        });

        ui.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
                    Intent intent1 = new Intent(MainActivity.this,callDisplay.class);
                    Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
                    startActivity(intent1, b);
                }
                else {
                    startActivity(new Intent(MainActivity.this, callDisplay.class));
                }



            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "in" + background.getShapeType(), Toast.LENGTH_SHORT).show();

//                ApplicationInfo api = getApplicationContext().getApplicationInfo();
//                String apkPath = api.sourceDir;
//
//
//                Intent intent1 = new Intent(Intent.ACTION_SEND);
//                intent1.setType("text/plain");
//                String linkData = "Download this App:-https://play.google.com/store/apps/details?id=com.myapp.a4kofflinewallpaper&hl=en";
//                String titleData = "prank call app";
//
//                intent1.putExtra(Intent.EXTRA_SUBJECT, titleData);
//                intent1.putExtra(Intent.EXTRA_TEXT, linkData);
//
////                intent1.setType("application/vnd.android.package-archive");
////                intent1.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
//
//                startActivity(Intent.createChooser(intent1,"share apk using"));

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
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void getTune(){
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        startActivityForResult(intent,3);

        //Intent to select Ringtone.
//                final Uri currentTone=
//                        RingtoneManager.getActualDefaultRingtoneUri(MainActivity.this,
//                                RingtoneManager.TYPE_ALARM);
//
//                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
//                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);
//                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
//                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, currentTone);
//                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, true);
//                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
//                startActivityForResult(intent, 999);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    selectImage = data.getData();
                    img.setImageURI(selectImage);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    img.setImageBitmap(bitmap);

                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
//                    txtView.setText("From :" + uri.getPath());

                    if (uri == null){
                        editor.putString("tone1", "");
                        editor.apply();
                        Toast.makeText(this, "Default tone is set", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        editor.putString("tone1", uri.toString());
                        editor.apply();
                    }

                    Log.d("tune","the path  is " + uri);
                }
                break;
        }

    }


}