package com.myapp.fake_call;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddData extends AppCompatActivity {
    ImageView addImage;
    Uri imageUri;
    EditText name, number;
    String Name, Number, ImagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        setTitle("Add Contact");

        addImage = findViewById(R.id.addImage);
        name = findViewById(R.id.editTextName);
        number = findViewById(R.id.editTextPhone);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });

    }
    public void getImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    imageUri = data.getData();
                    addImage.setImageURI(imageUri);
                }
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.save:

                Name = name.getText().toString();
                Number = number.getText().toString();
                ImagePath = getImagePath(imageUri).trim();

                DataBase db = new DataBase(AddData.this);
                Params params = new Params();

                params.setName(Name);
                params.setNumber(Number);
                params.setImageUri(ImagePath);

                try {
                    if (Name != null && Number != null && ImagePath != null){
                        db.addData(params);
                        Toast.makeText(this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                        Toast.makeText(this, "Please insert Full data", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }


                break;
        }
        return true;
    }
    public String getImagePath(Uri currentUri){
        String path = "no-path-found";
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(currentUri, projection, null,
                null, null);
        if(cursor.moveToFirst()) {
            int column_Index =
                    cursor.getColumnIndex(MediaStore.Images.Media.DATA);

            path = cursor.getString(column_Index);
        }
        cursor.close();
        return path;
    }

}