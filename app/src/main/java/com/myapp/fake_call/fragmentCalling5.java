package com.myapp.fake_call;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class fragmentCalling5 extends Fragment {
    ImageView imageViewCancel,imageViewCancel2, imageViewAnswer,mainImage,mainImage2;
    TextView mainText, timer, numberTxt;
    public static MediaPlayer mediaPlayer;
    AudioManager mAudioManager;
    MediaPlayer mpVoice;
    File imgFile;
    Uri uri1;
    String textData;
    int second, newSecond, minut;
    String phoneNumber;

    CountDownTimer countDownTimer1, countDownTimer2;

    public fragmentCalling5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calling5, container, false);

        imageViewCancel2 = view.findViewById(R.id.imageView10);
        imageViewCancel = view.findViewById(R.id.imageView11);
        imageViewAnswer = view.findViewById(R.id.imageView99);
        mainImage = view.findViewById(R.id.imageView25);
        mainImage2 = view.findViewById(R.id.imageView22);
        mainText = view.findViewById(R.id.textView16);
        timer = view.findViewById(R.id.textView20);
        numberTxt = view.findViewById(R.id.textView19);

        countDownTimer1 = new CountDownTimer(45000,45000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mediaPlayer.stop();
                moveToNewActivity();

            }
        }.start();

        setupData();

        imageViewAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer1.cancel();
                imageViewCancel2.setVisibility(View.GONE);
                imageViewCancel.setVisibility(View.VISIBLE);
                imageViewAnswer.setVisibility(View.GONE);
                mediaPlayer.stop();
                countDownTimer2 = new  CountDownTimer(300000,1000){
                    @Override
                    public void onTick(long l) {
                        second += 1000;
                        newSecond = second/1000;
                        if (newSecond == 60){
                            second = 0;
                            newSecond = 0;
                            minut++;
                        }

                        if(newSecond >= 0 && newSecond < 10){
                            timer.setText("0" + minut + " : 0" + newSecond);
                        }
                        else{
                            timer.setText("0" + minut + " : " + newSecond);
                        }

                    }

                    @Override
                    public void onFinish() {
                        mpVoice.stop();
                        getActivity().onBackPressed();

                    }
                }.start();
                voiceMethod();


            }
        });
        imageViewCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer1.cancel();
                mediaPlayer.stop();
                getActivity().onBackPressed();
            }
        });

        imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer2.cancel();
                mpVoice.stop();
                mAudioManager.setSpeakerphoneOn(true);
                getActivity().onBackPressed();
            }
        });




        return view;
    }
    private void moveToNewActivity () {

        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
    void voiceMethod (){
        SharedPreferences getPreferences4 = getContext().getSharedPreferences("voice",MODE_PRIVATE);
        String voiceData = getPreferences4.getString("voice1","");
        Uri uri3 = Uri.parse(voiceData);
        mpVoice = new MediaPlayer();
        mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        mpVoice = MediaPlayer.create(getContext(), uri3);
        if (mpVoice == null){
            mpVoice = MediaPlayer.create(getContext(),R.raw.voic);
        }

        mAudioManager.setSpeakerphoneOn(false);
        mpVoice.setLooping(true);
        mpVoice.start();
    }
    void setupData(){
        SharedPreferences getPreferences2 = getContext().getSharedPreferences("id",MODE_PRIVATE);
        String idData = getPreferences2.getString("id1","-1");

        SharedPreferences getPreferences3 = getContext().getSharedPreferences("pic",MODE_PRIVATE);
        String picData = getPreferences3.getString("pic1","-1");


        int idValue = Integer.parseInt(idData);



        if (idValue != -1){
            DataBase db = new DataBase(getContext());
            ArrayList<Params> arrayList = new ArrayList<>();
            int idNo = Integer.parseInt(idData);
            idNo++;

            Log.d("pic","the value of pic " + idNo);

            String query = "SELECT * FROM char_data WHERE ID = " + idNo;
            ArrayList<Params> list = db.getAllData(query);
            Params P = new Params();
            for (Params p : list){
                arrayList.add(p);

                Log.d("log","the name is " + p.getName() + " the no is " + p.getNumber());

                File file = new File(p.getImageUri());
                uri1 = Uri.fromFile(file);
                textData = p.getName();
                phoneNumber = p.getNumber();

            }

            mainImage2.setImageURI(uri1);
            mainImage.setImageURI(uri1);
            mainText.setText(textData);
            numberTxt.setText(phoneNumber);

        }

        int picValue = Integer.parseInt(picData);

        if (picValue == 0){
            imgFile = new  File("/storage/emulated/0/Download/images/mom.jpg");
            mainText.setText("mom");
        }
        else if (picValue == 1){
            imgFile = new  File("/storage/emulated/0/Download/images/father.jpg");
            mainText.setText("Dad");
        }
        else if (picValue == 2){
            imgFile = new  File("/storage/emulated/0/Download/images/husband.jpg");
            mainText.setText("Husband");
        }
        else if (picValue == 3){
            imgFile = new  File("/storage/emulated/0/Download/images/wife.jpg");
            mainText.setText("Wife");
        }
        else if (picValue == 4){
            imgFile = new  File("/storage/emulated/0/Download/images/boy.jpg");
            mainText.setText("BoyFriend");
        }
        else if (picValue == 5){
            imgFile = new  File("/storage/emulated/0/Download/images/girl.jpg");
            mainText.setText("Girl Friend");
        }
        else if (picValue == 6){
            imgFile = new  File("/storage/emulated/0/Download/images/police.jpg");
            mainText.setText("police");
        }
        else if (picValue == 7){
            imgFile = new  File("/storage/emulated/0/Download/images/pizza.jpg");
            mainText.setText("pizza");
        }
        else if (picValue == 8){
            mainImage.setImageResource(R.drawable.person);
            mainText.setText("UnKnown");
        }

        if (imgFile != null){
            Bitmap myBitmap0 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            mainImage2.setImageBitmap(myBitmap0);
            mainImage.setImageBitmap(myBitmap0);
        }


        SharedPreferences getPreferences = getContext().getSharedPreferences("tone",MODE_PRIVATE);
        String data = getPreferences.getString("tone1","");


        Uri uri2 = Uri.parse(data);
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getContext(), uri2);

        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getContext(),R.raw.tone);
            Log.d("tone","if");
        }

        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
//    @Override
//    public void onPause() {
//        mediaPlayer.stop();
//        super.onPause();
//    }

}