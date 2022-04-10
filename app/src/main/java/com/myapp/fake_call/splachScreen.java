package com.myapp.fake_call;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;

public class splachScreen extends AppCompatActivity {
    ImageView img;
    TextView textView;
    int time = 1;
    File imgFileMom, imgFileFather, imgFileHusband, imgFileWife, imgFileBoy, imgFileGirl, imgFilePolice, imgFilePizza, imgFileui1, imgFileui2, imgFileui3, imgFileui4, imgFileui5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        img = findViewById(R.id.imageView31);
        textView = findViewById(R.id.textView22);

        Dexter.withContext(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        setData();

                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        Intent intent1 = new Intent();
                        intent1.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",getPackageName(),null);
                        intent1.setData(uri);
                        startActivity(intent1);
                        finish();
                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    private void downloadImage(String urlPath, String pathName) {
        Uri uri = Uri.parse(urlPath);
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request =new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("some character is downloading");
        request.setDescription("Android data is download from download manger");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/images/"+"/"+pathName+".jpg");
        request.setMimeType("*/*");
        downloadManager.enqueue(request);
    }

    public void setData(){
        imgFileMom = new File("/storage/emulated/0/Download/images/mom.jpg");
        if (imgFileMom.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fmom.jpg?alt=media&token=786f4808-ca44-412f-81cc-8ca0303cabf9","mom");
        }
        imgFileFather = new File("/storage/emulated/0/Download/images/father.jpg");
        if (imgFileFather.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Ffather.jpg?alt=media&token=ca3d2300-6ef8-4f17-baf8-43501417c384","father");
        }
        imgFileHusband = new File("/storage/emulated/0/Download/images/husband.jpg");
        if (imgFileHusband.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fhusband.jpg?alt=media&token=6b3eb5f2-81a0-4251-89a9-9d961486b33a","husband");
        }
        imgFileWife = new File("/storage/emulated/0/Download/images/wife.jpg");
        if (imgFileWife.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fwife.jpg?alt=media&token=e5c6dc85-7edf-4f78-9fd1-c88713c4e84d","wife");
        }
        imgFileBoy = new File("/storage/emulated/0/Download/images/boy.jpg");
        if (imgFileBoy.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fboy.jpg?alt=media&token=11d74a92-cb21-4e8d-9bde-179ef92ca58d","boy");
        }
        imgFileGirl = new File("/storage/emulated/0/Download/images/girl.jpg");
        if (imgFileGirl.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fgirl.jpg?alt=media&token=f88463aa-02e0-45c8-9054-b1d7076c30a1","girl");
        }
        imgFilePolice = new File("/storage/emulated/0/Download/images/police.jpg");
        if (imgFilePolice.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fpolice.jpg?alt=media&token=eca0a428-7f4e-4cef-b745-f14d3680ad76","police");
        }
        imgFilePizza = new File("/storage/emulated/0/Download/images/pizza.jpg");
        if (imgFilePizza.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fpizza.jpg?alt=media&token=0d8efb06-ca1e-431b-bb0d-71bccdc58305","pizza");
        }

        imgFileui1 = new File("/storage/emulated/0/Download/images/ui1.jpg");
        if (imgFileui1.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fui1.jpg?alt=media&token=383ff430-d0dc-4ef7-87d7-8d2b8e22310c","ui1");
        }
        imgFileui2 = new File("/storage/emulated/0/Download/images/ui2.jpg");
        if (imgFileui2.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fui2.jpg?alt=media&token=3b0d8837-52aa-41d9-adff-659679d80add","ui2");
        }
        imgFileui3 = new File("/storage/emulated/0/Download/images/ui3.jpg");
        if (imgFileui3.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fui3.jpg?alt=media&token=d9cad03d-ac02-4e71-8ed3-dc90a1655665","ui3");
        }
        imgFileui4 = new File("/storage/emulated/0/Download/images/ui4.jpg");
        if (imgFileui4.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fui4.jpg?alt=media&token=6ab8313c-42e4-430b-9281-ab6ca32297a3","ui4");
        }
        imgFileui5 = new File("/storage/emulated/0/Download/images/ui5.jpg");
        if (imgFileui5.canRead()){

        }
        else{
            downloadImage("https://firebasestorage.googleapis.com/v0/b/call-prank-4753c.appspot.com/o/images%2Fui5.jpg?alt=media&token=b421628d-01f3-463f-8e62-d0daafdeb32e","ui5");
        }

        new CountDownTimer(1600, 200) {
            @Override
            public void onTick(long l) {
                if (time == 1)
                    textView.setText("F");
                else if (time == 2)
                    textView.setText("FA");
                else if (time == 3)
                    textView.setText("FAK");
                else if (time == 4)
                    textView.setText("FAKE");
                else if (time == 5)
                    textView.setText("FAKE C");
                else if (time == 6)
                    textView.setText("FAKE CA");
                else if (time == 7)
                    textView.setText("FAKE CAL");
                else if (time == 8)
                    textView.setText("FAKE CALL");

                time ++;

            }

            @Override
            public void onFinish() {
                Intent intent1 = new Intent(splachScreen.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        }.start();
    }


}