package com.myapp.fake_call;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Params> dataList;

    public RecyclerViewAdapter(Context context, ArrayList<Params> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    // Where to get the single card as viewholder Object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onecard, parent, false);
        return new ViewHolder(view);
    }

    // What will happen after we create the viewholder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Params params = dataList.get(position);

        File file = new File(params.imageUri);
        Uri uri1 = Uri.fromFile(file);

        holder.userName.setText(params.getName());
        holder.phoneNumber.setText(params.getNumber());
        holder.userImage.setImageURI(uri1);

    }

    // How many items?
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView userName;
        public TextView phoneNumber;
        public ImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            userName = itemView.findViewById(R.id.textName);
            phoneNumber = itemView.findViewById(R.id.textNumber);
            userImage = itemView.findViewById(R.id.img);

        }

        @Override
        public void onClick(View view) {

            String idNo  = String.valueOf(getAdapterPosition());
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("id",idNo);
            context.startActivity(intent);

        }

    }

}
