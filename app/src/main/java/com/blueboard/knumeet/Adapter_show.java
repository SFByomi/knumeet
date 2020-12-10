package com.blueboard.knumeet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_show extends RecyclerView.Adapter<Adapter_show.CustomViewHolder> {

    private static int cnt = 8;
    private static int[] arr_now = {5,5,4,3,3,1,1,1};//현재 사람수
    private int[] arr_full = {5,5,5,5,5,5,5,5};// 최대인원
    private Context context;
    final String[] str = {"2020-12-11  9:00", "2020-12-11  10:00","2020-12-11  11:00","2020-12-12  9:00",
            "2020-12-12  10:00","2020-12-13  9:00","2020-12-13  10:00", "2020-12-13  11:00"};
    private OnItemClickListener mListener = null;

    public Adapter_show(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_show.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull Adapter_show.CustomViewHolder holder, int position) {

       holder.tv_result_time.setText(str[position] + "   ");
       holder.tv_result_vote.setText(arr_now[position]+"/"+arr_full[position]);
       if(arr_now[position] == arr_full[position]){
           holder.image_result.setImageResource(R.drawable.ic_baseline_sentiment_very_satisfied_24); // 웃는 얼굴
           holder.tv_result_vote.setTextColor(Color.parseColor("#000000"));
       }
       else if(arr_now[position]>2){
           holder.image_result.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_orange); // 살짝 웃는 얼굴
       }
       else {
           holder.image_result.setImageResource(R.drawable.ic_baseline_sentiment_very_disatisfied_red); // 우는 얼굴
       }
    }

    @Override
    public int getItemCount() {
        return cnt;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_result_time;
        TextView tv_result_vote;
        ImageView image_result;
        Button btn_result_share;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.tv_result_time = itemView.findViewById(R.id.tv_result_time);
            this.tv_result_vote = itemView.findViewById(R.id.tv_result_vote);
            this.image_result = itemView.findViewById(R.id.image_result);
            this.btn_result_share = itemView.findViewById(R.id.btn_result_share);

            btn_result_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                    Sharing_intent.setType("text/plain");

                    int pos = getAdapterPosition();
                    String Test_Message = "안녕하세요! 우리 \"" + str[pos] + "\"에 만나요!\n\nfrom.약속시간을 가장 쉽게 정하는 방법\n\"everyMEET\"";// 멘트

                    Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                    Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                    context.startActivity(Sharing);
                }
            });

        }
    }
}
