package com.blueboard.knumeet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_main extends RecyclerView.Adapter<Adapter_main.CustomViewHolder> {

    private static int cnt = 1;
    private static int[] arr_now = {5,1};//현재 사람수
    private int[] arr_full = {5,3};// 최대인원
    private Context context;
    final String[] str = {"테스트3", "최종발표모임"};
    private OnItemClickListener mListener = null;

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

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull Adapter_main.CustomViewHolder holder, int position) {

       holder.tv_main.setText(str[position] + "   ");
       //arr_now[position]+"/"+arr_full[position]
       if(arr_now[position] == arr_full[position]){
           holder.tv_number.setText(arr_now[position]+"/"+arr_full[position]);
           holder.iv_check.setImageResource(R.drawable.ic_baseline_done_gray_blue);
           holder.tv_number.setTextColor(Color.parseColor("#3440D4"));
       }else{
           holder.tv_number.setText(arr_now[position]+"/"+arr_full[position]);
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


    public static void addList() {
        cnt++;
    }
    public static void addUser() {
        arr_now[1]++;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_main;
        TextView tv_number;
        ImageView iv_check;
        Button btn_list;
        LinearLayout layout_meet;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.tv_main = itemView.findViewById(R.id.tv_main);
            this.tv_number = itemView.findViewById(R.id.tv_number);
            this.iv_check = itemView.findViewById(R.id.iv_check);
            layout_meet = itemView.findViewById(R.id.layout_meet);
            btn_list = itemView.findViewById(R.id.btn_list);

            btn_list.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("삭제하시면 만들었던 방을 복구할 수 없습니다.\n그래도 진행하시겠습니까?");
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //취소
                        }
                    });
                    builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cnt--;
                            notifyDataSetChanged();
                           Toast.makeText(view.getContext(),"삭제되었습니다",Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alertDialog= builder.create();
                    alertDialog.show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if(mListener !=null){
                            mListener.onItemClick(view,position);
                        }
                        Intent intent = new Intent(view.getContext(),ShowActivity.class);
                        view.getContext().startActivity(intent);
                    }
                }
            });

        }
    }
}
