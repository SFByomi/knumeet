package com.blueboard.knumeet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter_Days extends RecyclerView.Adapter<Adapter_Days.CustomViewHolder> {

    private int click_cnt = 0;
    private int[] arr_days = new int [32];
    private Context context;

    public Adapter_Days(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_Days.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_days,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Days.CustomViewHolder holder, final int position) {

        final String temp =  (position + 1) + " " +  "일";
        holder.tv_days.setText(temp);

    }

    @Override
    public int getItemCount() {
        return 31;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_days;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.tv_days = itemView.findViewById(R.id.tv_days);

            itemView.setOnClickListener(new View.OnClickListener() {
                //@SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(click_cnt == 0 && arr_days[pos] != 1) {
                        click_cnt = 1;
                        arr_days[pos] = 1;
                        //itemView.setBackgroundColor(R.color.colorClicked);
                        view.setBackgroundColor(Color.GREEN);
                    }
                    else if(click_cnt == 1 && arr_days[pos] == 1)
                    {
                        click_cnt = 0;
                        arr_days[pos] = 0;
                        view.setBackgroundColor(Color.WHITE);
                    }

                }
            });
        }
    }
}
