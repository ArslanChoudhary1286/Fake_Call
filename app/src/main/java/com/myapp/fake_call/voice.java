package com.myapp.fake_call;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class voice extends AppCompatActivity {
    MediaPlayer mediaPlayer, mediaPlayer2;
    ArrayList<String> audioList;
    ArrayList<String> nameList;
    ListView audioView;
    SharedPreferences.Editor editor;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        audioView = findViewById(R.id.myListView);

        audioList = new ArrayList<String>();
        nameList = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("voice",MODE_PRIVATE);
        editor = sharedPreferences.edit();


        audioList = (ArrayList<String>) getsonglist();
        getsonglist();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1, nameList);
        audioView.setAdapter(adapter);





        audioView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                try {

                    if (mediaPlayer2 != null){
                        mediaPlayer2.stop();
                    }
                    pos = i;

                    Uri uri = Uri.parse(audioList.get(i));
                    mediaPlayer = MediaPlayer.create(voice.this,uri);
                    mediaPlayer.start();
                    mediaPlayer2 = mediaPlayer;

                }
                catch (Exception e){
                    Toast.makeText(voice.this, "error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });

    }

    List<String> getsonglist() {

        List<String> songlist = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor == null) {
            // query failed, handle error.
        } else if (!cursor.moveToFirst()) {
            // no media on the device
        } else {
            do {

                @SuppressLint("Range") String fullPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                // ...process entry...
                Log.e("Full Path : ", fullPath);
                int audioIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);

                nameList.add(cursor.getString(audioIndex));


                songlist.add(fullPath);
            } while (cursor.moveToNext());
        }


        return songlist;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.doneButton:

                if (audioList.get(pos) != null){
                    editor.putString("voice1",audioList.get(pos));
                    editor.apply();
                }
                else {
                    Toast.makeText(this, "Please Select any Voice", Toast.LENGTH_SHORT).show();
                }

                if (mediaPlayer == null){
                    Toast.makeText(this, "please select any voice", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mediaPlayer.stop();
                    finish();

                }

                break;
        }
        return true;
    }

}