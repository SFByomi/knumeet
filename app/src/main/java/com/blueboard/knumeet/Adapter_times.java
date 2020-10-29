package com.blueboard.knumeet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_times extends RecyclerView.Adapter<Adapter_times.CustomViewHolder> {

    private int[] arr_times = new int [12];
    private Context context;

    public Adapter_times(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_times.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_times,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_times.CustomViewHolder holder, int position) {
       holder.tv_times.setText((position + 12) +" : " + "00");
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_times;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.tv_times = itemView.findViewById(R.id.tv_times);

            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if(arr_times[pos] == 0) {
                            view.setBackgroundColor(Color.LTGRAY);
                            arr_times[pos] = 1;
                        }
                        else
                        {
                            view.setBackgroundColor(Color.WHITE);
                            arr_times[pos] = 0;
                        }
                    }
                }
            });

        }
    }
}
