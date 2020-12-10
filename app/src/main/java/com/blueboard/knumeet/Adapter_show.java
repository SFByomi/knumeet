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
    final String[] str = {"시간1등", "시간2등","시간3등","시간4등", "시간5등","시간6등","시간7등", "시간8등"};
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
           holder.image_result.setImageResource(R.drawable.ic_baseline_check_24_grey); // 웃는 얼굴
       }
       else if(arr_now[position]/arr_full[position] > 1/2){
           holder.image_result.setImageResource(R.drawable.ic_baseline_check_24_grey); // 살짝 웃는 얼굴
       }
       else {
           holder.image_result.setImageResource(R.drawable.ic_baseline_check_24_grey); // 우는 얼굴
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
                    String Test_Message = "약속시간은  " + str[pos] + "  입니다!";// 멘트

                    Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                    Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                    context.startActivity(Sharing);
                }
            });

        }
    }
}
