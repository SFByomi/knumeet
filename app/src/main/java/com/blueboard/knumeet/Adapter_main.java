package com.blueboard.knumeet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_main extends RecyclerView.Adapter<Adapter_main.CustomViewHolder> {

    private static int cnt = 1;
    private int[] arr_now = {1,1};//현재 사람수
    private int[] arr_full = {5,8};// 최대인원
    private Context context;
    final String[] str = {"응용프로그래밍2", "테스트"};

    public Adapter_main(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_main.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_main.CustomViewHolder holder, int position) {
       holder.tv_main.setText(str[position] + "   " + arr_now[position]+"/"+arr_full[position]);
    }

    @Override
    public int getItemCount() {
        return cnt;
    }

    public static void addList()
    {
        cnt ++;
    }
    public void delList()
    {
        cnt --;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_main;
        Button btn_list;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.tv_main = itemView.findViewById(R.id.tv_main);
            btn_list = itemView.findViewById(R.id.btn_list);

            btn_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cnt--;
                    notifyDataSetChanged();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        arr_now[pos]++;
                    }
                }
            });

        }
    }
}
