package com.blueboard.knumeet;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class GridAdapter extends BaseAdapter {

    private final ArrayList<String> list;

    private final LayoutInflater inflater;

    /**
     * 캘린더 변수
     */
    private Calendar mCal;
    Integer today;
    Integer nowMonth;
    int showM,showY;
    String sunday="#DFE3264F";
    String saturday="#DF3851DD";
    String day="#000000";

    /**
     * 생성자
     *
     * @param context
     * @param list
     */
    public GridAdapter(Context context, ArrayList<String> list, int showM) {
        this.showM = showM;
        this.list = list;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
            holder = new ViewHolder();

            holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvItemGridView.setText("" + getItem(position));

        //해당 날짜 텍스트 컬러,배경 변경
        mCal = Calendar.getInstance();
        //오늘 day 가져옴
        today = mCal.get(Calendar.DAY_OF_MONTH);
        nowMonth = mCal.get(Calendar.MONTH);

        String sToday = String.valueOf(today);
        if (position%7==0){
            holder.tvItemGridView.setTextColor(Color.parseColor(sunday));
        }
        if (position%7==6){
            holder.tvItemGridView.setTextColor(Color.parseColor(saturday));
        }
        if (sToday.equals(getItem(position)) && (showM == (nowMonth+1))) { //오늘 day 텍스트 컬러 변경
            holder.tvItemGridView.setTextColor(Color.parseColor(day));
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tvItemGridView;
    }
}