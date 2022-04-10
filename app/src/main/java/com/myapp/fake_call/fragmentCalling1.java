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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class fragmentCalling1 extends Fragment {

    SeekBar seekBar;
    int n = 200;
    public static MediaPlayer mediaPlayer, mpVoice;
    View layout;
    ImageView keypad_image, record_image, onhold_image, mute_image, addCall_image, contact_image, speaker_image, end_image;
    ImageView dot1, dot2, dot3, dot4, dot5, dot6,imgEnd,imgYes,imgReminder,imgShare;
    TextView keypad, record, onhold, mute, addcall, contact, speaker, timer, Reply, Reminder;
    int time = 10000;
    int second = 0, minut = 0;
    int newSecond;
    int i = 0;
    int end = 0;
    public String id;
    ImageView mainImage;
    TextView mainText;
    Uri uri1;
    String textData;
    int idNo;
    int idValue;
    AudioManager mAudioManager;
    File imgFile;

    CountDownTimer dot1Timer, dot2Timer, dot3Timer, secondTimer;
    CountDownTimer seek1Timer, seek2Timer, seek3Timer, seek4Timer, seek5Timer, seek6Timer, seek7Timer, seek8Timer, seek9Timer, seek10Timer, seek11Timer;

    List<Boolean> timerCheck;

    public fragmentCalling1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calling1, container, false);


        seekBar = view.findViewById(R.id.seekBar);
        layout = view.findViewById(R.id.constraint);

        keypad_image = view.findViewById(R.id.image1);
        record_image = view.findViewById(R.id.image2);
        onhold_image = view.findViewById(R.id.image3);
        mute_image = view.findViewById(R.id.image4);
        addCall_image = view.findViewById(R.id.image5);
        speaker_image = view.findViewById(R.id.image7);
        end_image = view.findViewById(R.id.image8);
        contact_image = view.findViewById(R.id.image6);
        mainImage = view.findViewById(R.id.imageView6);

        keypad = view.findViewById(R.id.textView13);
        record = view.findViewById(R.id.textView10);
        onhold = view.findViewById(R.id.textView11);
        mute = view.findViewById(R.id.textView12);
        addcall = view.findViewById(R.id.textView);
        contact = view.findViewById(R.id.textView7);
        speaker = view.findViewById(R.id.textView8);
        timer = view.findViewById(R.id.textView4);
        mainText = view.findViewById(R.id.name);

        Reply = view.findViewById(R.id.textView5);
        Reminder = view.findViewById(R.id.textView6);

        dot1 = view.findViewById(R.id.imagedot1);
        dot2 = view.findViewById(R.id.imagedot4);
        dot3 = view.findViewById(R.id.imagedot2);
        dot4 = view.findViewById(R.id.imagedot5);
        dot5 = view.findViewById(R.id.imagedot3);
        dot6 = view.findViewById(R.id.imagedot6);

        imgEnd = view.findViewById(R.id.imageViewend);
        imgYes = view.findViewById(R.id.imageView2);
        imgShare = view.findViewById(R.id.imageView7);
        imgReminder = view.findViewById(R.id.imageView8);

        timerCheck = new ArrayList<>();
        timerCheck.add(0,false);
        timerCheck.add(1,false);
        timerCheck.add(2,false);
        timerCheck.add(3,false);
        timerCheck.add(4,false);
        timerCheck.add(5,false);
        timerCheck.add(6,false);
        timerCheck.add(7,false);
        timerCheck.add(8,false);
        timerCheck.add(9,false);
        timerCheck.add(10,false);
        timerCheck.add(11,false);
        timerCheck.add(12,false);
        timerCheck.add(13,false);

        setupData();



        dot1.setImageResource(R.drawable.dot2);
        dot2.setImageResource(R.drawable.dot2);
        dot3.setImageResource(R.drawable.dot2);
        dot4.setImageResource(R.drawable.dot2);
        dot5.setImageResource(R.drawable.dot2);
        dot6.setImageResource(R.drawable.dot2);

        dot1.setAlpha((float) 0.1);
        dot2.setAlpha((float) 0.1);
        dot3.setAlpha((float) 0.1);
        dot4.setAlpha((float) 0.1);
        dot5.setAlpha((float) 0.1);
        dot6.setAlpha((float) 0.1);

        dot1Timer = new CountDownTimer(19200,400){
            @Override
            public void onTick(long l) {
                timerCheck.set(0,true);
                i++;
                if      (i == 1 || i == 5 || i == 9 || i == 13 || i == 17 || i == 21 || i == 25 || i == 29 || i == 33 || i == 37 || i == 41 || i == 45){
                    dot1.setAlpha((float) 0.9);
                    dot2.setAlpha((float) 0.9);
                }
                else if (i == 2 || i == 6 || i == 10 || i == 14 || i == 18 || i == 22 || i == 26 || i == 30 || i == 34 || i == 38 || i == 42 || i == 46){
                    dot3.setAlpha((float) 0.9);
                    dot4.setAlpha((float) 0.9);
                }
                else if (i == 3 || i == 7 || i == 11 || i == 15 || i == 19 || i == 23 || i == 27 || i == 31 || i == 35 || i == 39 || i == 43 || i == 47){
                    dot5.setAlpha((float) 0.9);
                    dot6.setAlpha((float) 0.9);
                }
                else if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28 || i == 32 || i == 36 || i == 40 || i == 44 || i == 48){
                    dot1.setAlpha((float) 0.1);
                    dot2.setAlpha((float) 0.1);
                    dot3.setAlpha((float) 0.1);
                    dot4.setAlpha((float) 0.1);
                    dot5.setAlpha((float) 0.1);
                    dot6.setAlpha((float) 0.1);

                }
            }

            @Override
            public void onFinish() {

                dot1.setAlpha((float) 0.9);
                dot2.setAlpha((float) 0.9);
                dot3.setAlpha((float) 0.9);
                dot4.setAlpha((float) 0.9);
                dot5.setAlpha((float) 0.9);
                dot6.setAlpha((float) 0.9);
                timerCheck.set(0,false);
                dot2Timer = new CountDownTimer(5000,5000){
                    @Override
                    public void onTick(long l) {
                        timerCheck.set(1,true);

                    }

                    @Override
                    public void onFinish() {
                        i = 0;
                        dot1.setAlpha((float) 0.1);
                        dot2.setAlpha((float) 0.1);
                        dot3.setAlpha((float) 0.1);
                        dot4.setAlpha((float) 0.1);
                        dot5.setAlpha((float) 0.1);
                        dot6.setAlpha((float) 0.1);
                        timerCheck.set(1,false);
                        dot3Timer = new CountDownTimer(19200,400){
                            @Override
                            public void onTick(long l) {
                                timerCheck.set(2,true);
                                i++;
                                if      (i == 1 || i == 5 || i == 9 || i == 13 || i == 17 || i == 21 || i == 25 || i == 29 || i == 33 || i == 37 || i == 41 || i == 45){
                                    dot1.setAlpha((float) 0.9);
                                    dot2.setAlpha((float) 0.9);
                                }
                                else if (i == 2 || i == 6 || i == 10 || i == 14 || i == 18 || i == 22 || i == 26 || i == 30 || i == 34 || i == 38 || i == 42 || i == 46){
                                    dot3.setAlpha((float) 0.9);
                                    dot4.setAlpha((float) 0.9);
                                }
                                else if (i == 3 || i == 7 || i == 11 || i == 15 || i == 19 || i == 23 || i == 27 || i == 31 || i == 35 || i == 39 || i == 43 || i == 47){
                                    dot5.setAlpha((float) 0.9);
                                    dot6.setAlpha((float) 0.9);
                                }
                                else if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28 || i == 32 || i == 36 || i == 40 || i == 44 || i == 48){
                                    dot1.setAlpha((float) 0.1);
                                    dot2.setAlpha((float) 0.1);
                                    dot3.setAlpha((float) 0.1);
                                    dot4.setAlpha((float) 0.1);
                                    dot5.setAlpha((float) 0.1);
                                    dot6.setAlpha((float) 0.1);

                                }
                            }

                            @Override
                            public void onFinish() {
                                dot1.setAlpha((float) 0.9);
                                dot2.setAlpha((float) 0.9);
                                dot3.setAlpha((float) 0.9);
                                dot4.setAlpha((float) 0.9);
                                dot5.setAlpha((float) 0.9);
                                dot6.setAlpha((float) 0.9);

                                timerCheck.set(2,false);


                            }
                        }.start();

                    }
                }.start();



            }
        }.start();

        seekBar.setProgress(200);

        seek1Timer = new CountDownTimer(2000,100){
            @Override
            public void onTick(long l) {
                timerCheck.set(3,true);
                seekBar.setProgress(200);
            }

            @Override
            public void onFinish() {
                timerCheck.set(3,false);

                seek2Timer = new CountDownTimer(500,50){
                    @Override
                    public void onTick(long l) {
                        timerCheck.set(4,true);
                        n++;

                        seekBar.setProgress(n);

                    }

                    @Override
                    public void onFinish() {
                        timerCheck.set(4,false);
                       seek3Timer = new CountDownTimer(1000,50){
                            @Override
                            public void onTick(long l) {
                                timerCheck.set(5,true);
                                n--;

                                seekBar.setProgress(n);

                            }

                            @Override
                            public void onFinish() {
                                timerCheck.set(5,false);
                               seek4Timer = new CountDownTimer(1000,50){
                                    @Override
                                    public void onTick(long l) {
                                        timerCheck.set(6,true);
                                        n++;
                                        seekBar.setProgress(n);
                                    }

                                    @Override
                                    public void onFinish() {
                                        timerCheck.set(6,false);
                                       seek5Timer = new  CountDownTimer(500,50){
                                            @Override
                                            public void onTick(long l) {
                                                timerCheck.set(7,true);
                                                n--;
                                                seekBar.setProgress(n);
                                            }

                                            @Override
                                            public void onFinish() {
                                                timerCheck.set(7,false);
                                               seek6Timer = new  CountDownTimer(2000,100){
                                                    @Override
                                                    public void onTick(long l) {
                                                        timerCheck.set(8,true);
                                                        seekBar.setProgress(n);
                                                    }

                                                    @Override
                                                    public void onFinish() {
                                                        timerCheck.set(8,false);

                                                       seek7Timer = new CountDownTimer(500,50){
                                                            @Override
                                                            public void onTick(long l) {
                                                                timerCheck.set(9,true);
                                                                n++;

                                                                seekBar.setProgress(n);

                                                            }

                                                            @Override
                                                            public void onFinish() {
                                                                timerCheck.set(9,false);
                                                               seek8Timer = new CountDownTimer(1000,50){
                                                                    @Override
                                                                    public void onTick(long l) {
                                                                        timerCheck.set(10,true);
                                                                        n--;

                                                                        seekBar.setProgress(n);

                                                                    }

                                                                    @Override
                                                                    public void onFinish() {
                                                                        timerCheck.set(10,false);
                                                                       seek9Timer = new CountDownTimer(1000,50){
                                                                            @Override
                                                                            public void onTick(long l) {
                                                                                timerCheck.set(11,true);
                                                                                n++;
                                                                                seekBar.setProgress(n);
                                                                            }

                                                                            @Override
                                                                            public void onFinish() {
                                                                                timerCheck.set(11,false);
                                                                               seek10Timer = new  CountDownTimer(500,50){
                                                                                    @Override
                                                                                    public void onTick(long l) {
                                                                                        timerCheck.set(12,true);
                                                                                        n--;
                                                                                        seekBar.setProgress(n);
                                                                                    }

                                                                                    @Override
                                                                                    public void onFinish() {
                                                                                        timerCheck.set(12,false);
                                                                                       seek11Timer = new  CountDownTimer(33400,200){
                                                                                            @Override
                                                                                            public void onTick(long l) {
                                                                                                timerCheck.set(13,true);
                                                                                                seekBar.setProgress(n);
                                                                                            }

                                                                                            @Override
                                                                                            public void onFinish() {
                                                                                                timerCheck.set(13,false);
//                                                                                                if (end == 0){
                                                                                                    mediaPlayer.stop();
//                                                                                                getActivity().onBackPressed();
//                                                                                                }
//                                                                                                Toast.makeText(getContext(), "f", Toast.LENGTH_SHORT).show();

                                                                                                try {
                                                                                                    moveToNewActivity();
                                                                                                }catch (Exception e){
                                                                                                    Log.e("sens",e.getMessage());
                                                                                                }finally {
//                                                                                                    getActivity().finish();

                                                                                                }


                                                                                            }
                                                                                        }.start();

                                                                                    }
                                                                                }.start();


                                                                            }
                                                                        }.start();


                                                                    }
                                                                }.start();

                                                            }
                                                        }.start();

                                                    }
                                                }.start();

                                            }
                                        }.start();


                                    }
                                }.start();


                            }
                        }.start();

                    }
                }.start();

            }
        }.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int p = seekBar.getProgress();
//                Log.d("log","value of p " + p);
                if (p<10){
                    // stop the timers
                    stopTimer();
//                    end++;
                    mediaPlayer.stop();

                    voiceMethod();


                    secondTimer = new  CountDownTimer(300000,1000){
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
//                            getActivity().onBackPressed();

                            moveToNewActivity();
                        }
                    }.start();
                    seekBar.setVisibility(View.GONE);
                    dot1.setVisibility(View.GONE);
                    dot2.setVisibility(View.GONE);
                    dot3.setVisibility(View.GONE);
                    dot4.setVisibility(View.GONE);
                    dot5.setVisibility(View.GONE);
                    dot6.setVisibility(View.GONE);
                    imgEnd.setVisibility(View.GONE);
                    imgYes.setVisibility(View.GONE);
                    imgReminder.setVisibility(View.GONE);
                    imgShare.setVisibility(View.GONE);
                    Reminder.setVisibility(View.GONE);
                    Reply.setVisibility(View.GONE);

                    keypad_image.setImageResource(R.drawable.dialpad);
                    record_image.setImageResource(R.drawable.record);
                    onhold_image.setImageResource(R.drawable.hold);
                    mute_image.setImageResource(R.drawable.mute);
                    addCall_image.setImageResource(R.drawable.add);
                    contact_image.setImageResource(R.drawable.contacts);
                    speaker_image.setImageResource(R.drawable.speaker);
                    end_image.setImageResource(R.drawable.callend2);
                    end_image.setBackgroundResource(R.drawable.thumb);

                    keypad.setText(R.string.k);
                    record.setText(R.string.r);
                    onhold.setText(R.string.o);
                    mute.setText(R.string.m);
                    addcall.setText(R.string.a);
                    contact.setText(R.string.c);
                    speaker.setText(R.string.s);


                    end_image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mpVoice.stop();
                            secondTimer.cancel();
                            mAudioManager.setSpeakerphoneOn(true);
//                            getActivity().onBackPressed();
                            moveToNewActivity();
                        }
                    });

                }
                else if (p > 390){
                    // enter when the call cut
//                    end++;
                    mediaPlayer.stop();
//                     stop the dot timer
                    stopTimer();
//                    getActivity().onBackPressed();
                    moveToNewActivity();
                }

            }
        });


        return view;
    }
    private void moveToNewActivity () {
        calling.fragmentIsAvailable = true;

        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
    void stopTimer(){
        if (timerCheck.get(0) == true)
        dot1Timer.cancel();
        if (timerCheck.get(1) == true)
        dot2Timer.cancel();
        if (timerCheck.get(2) == true)
        dot3Timer.cancel();
        if (timerCheck.get(3) == true)
        seek1Timer.cancel();
        if (timerCheck.get(4) == true)
        seek2Timer.cancel();
        if (timerCheck.get(5) == true)
        seek3Timer.cancel();
        if (timerCheck.get(6) == true)
        seek4Timer.cancel();
        if (timerCheck.get(7) == true)
        seek5Timer.cancel();
        if (timerCheck.get(8) == true)
        seek6Timer.cancel();
        if (timerCheck.get(9) == true)
        seek7Timer.cancel();
        if (timerCheck.get(10) == true)
        seek8Timer.cancel();
        if (timerCheck.get(11) == true)
        seek9Timer.cancel();
        if (timerCheck.get(12) == true)
        seek10Timer.cancel();
        if (timerCheck.get(13) == true)
        seek11Timer.cancel();
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


        idValue = Integer.parseInt(idData);



        if (idValue != -1){
            DataBase db = new DataBase(getContext());
            ArrayList<Params> arrayList = new ArrayList<>();
            idNo = Integer.parseInt(idData);
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

            }

            mainImage.setImageURI(uri1);
            mainText.setText(textData);

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
            mainImage.setImageBitmap(myBitmap0);
        }


        SharedPreferences getPreferences = getContext().getSharedPreferences("tone",MODE_PRIVATE);
        String data = getPreferences.getString("tone1","");


        Uri uri2 = Uri.parse(data);
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getContext(), uri2);

        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getContext(),R.raw.tone);
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