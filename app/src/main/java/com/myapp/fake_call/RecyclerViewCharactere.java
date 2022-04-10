package com.myapp.fake_call;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class RecyclerViewCharactere extends RecyclerView.Adapter<RecyclerViewCharactere.ViewHolder> {

    private Context context;
    String picno;

    public RecyclerViewCharactere(Context context) {
        this.context = context;
    }

    // Where to get the single card as viewholder Object
    @NonNull
    @Override
    public RecyclerViewCharactere.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.charactere, parent, false);
        return new ViewHolder(view);
    }

    // What will happen after we create the viewholder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCharactere.ViewHolder holder, int position) {


        if (position == 0){

            File imgFile = new  File("/storage/emulated/0/Download/images/mom.jpg");
            Bitmap myBitmap0 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.girlImage.setImageBitmap(myBitmap0);
            holder.girlText.setText("Mom");
        }
        else if (position == 1){

            File imgFile = new  File("/storage/emulated/0/Download/images/father.jpg");
            Bitmap myBitmap1 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.girlImage.setImageBitmap(myBitmap1);
            holder.girlText.setText("Father");
        }
        else if (position == 2){

            File imgFile = new  File("/storage/emulated/0/Download/images/husband.jpg");
            Bitmap myBitmap2 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.girlImage.setImageBitmap(myBitmap2);
            holder.girlText.setText("husband");
        }
        else if (position == 3){

            File imgFile = new  File("/storage/emulated/0/Download/images/wife.jpg");
            Bitmap myBitmap3 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgFile.isHidden();

            holder.girlImage.setImageBitmap(myBitmap3);
            holder.girlText.setText("wife");
        }
        else if (position == 4){

            File imgFile = new  File("/storage/emulated/0/Download/images/boy.jpg");
            Bitmap myBitmap4 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.girlImage.setImageBitmap(myBitmap4);
            holder.girlText.setText("boy");
        }
        else if (position == 5){

            File imgFile = new  File("/storage/emulated/0/Download/images/girl.jpg");
            Bitmap myBitmap5 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.girlImage.setImageBitmap(myBitmap5);
            holder.girlText.setText("girl");
        }
        else if (position == 6){

            File imgFile = new  File("/storage/emulated/0/Download/images/police.jpg");
            Bitmap myBitmap6 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.girlImage.setImageBitmap(myBitmap6);
            holder.girlText.setText("police");
        }
        else if (position == 7){

            File imgFile = new  File("/storage/emulated/0/Download/images/pizza.jpg");
            Bitmap myBitmap7 = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.girlImage.setImageBitmap(myBitmap7);
            holder.girlText.setText("pizza");
        }


    }

    // How many items?
    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView girlText;
        public ImageView girlImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            girlText = itemView.findViewById(R.id.girlText);
            girlImage = itemView.findViewById(R.id.girlImage);

//            userImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            picno = String.valueOf(getAdapterPosition());
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("pic",picno);
            context.startActivity(intent);




        }

    }

}

