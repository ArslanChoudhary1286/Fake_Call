package com.myapp.fake_call;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.io.File;

public class callDisplay extends AppCompatActivity {

    TextView textView;
    ImageView imgLeft, imgRight, imageViewMain;
    Button select;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    Bitmap bitmap;
    File imgFile;

    static int counter = 1;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_display);

        setTitle("Choose Calling Display");

        textView = findViewById(R.id.textView21);
        imgLeft = findViewById(R.id.imageView28);
        imgRight = findViewById(R.id.imageView27);
        select = findViewById(R.id.button);
        imageViewMain = findViewById(R.id.imageView26);

        sharedPreferences = getSharedPreferences("ui",MODE_PRIVATE);
        editor = sharedPreferences.edit();


        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-5386510617974960/4259323951", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });




        textView.setText("UI is " + counter);

        imgFile = new  File("/storage/emulated/0/Download/images/ui"+counter+".jpg");
        bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        imageViewMain.setImageBitmap(bitmap);


        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 5){
                    counter = 1;
                }
                else
                    counter++;

                imgFile = new  File("/storage/emulated/0/Download/images/ui"+counter+".jpg");

                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageViewMain.setImageBitmap(bitmap);

                textView.setText("UI is " + counter);

            }
        });

        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 1){
                    counter = 5;
                }
                else
                    counter--;

                imgFile = new  File("/storage/emulated/0/Download/images/ui"+counter+".jpg");
                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageViewMain.setImageBitmap(bitmap);

                textView.setText("UI is " + counter);

            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("ui1",String.valueOf(counter));
                editor.apply();
                finish();
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(callDisplay.this);
                }
            }
        });

    }

}