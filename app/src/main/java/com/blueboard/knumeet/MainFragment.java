package com.blueboard.knumeet;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

  public static String DATE="";
  /**
   * 연/월 텍스트뷰
   */
  private TextView tvDate;
  /**
   * 일 저장 할 리스트
   */
  private ArrayList<String> dayList;
  /**
   * 그리드뷰 어댑터
   */
  private GridAdapter gridAdapter;
  /**
   * 그리드뷰
   */
  private GridView gridView;
  /**
   * 캘린더 변수
   */
  private Calendar mCal;


  ImageView iv_last;
  ImageView iv_next;

  public static int color=0;
  SharedPreferences pref;
  Date date;
  SimpleDateFormat curYearFormat;
  SimpleDateFormat curMonthFormat;
  int dayNum;
  int test=0;
  int showM,showY;
  Integer today;
  int day;

  private View view;

  public MainFragment() {
    // Required empty public constructor
  }

  public static MainFragment newInstance() {
    MainFragment fragment = new MainFragment();
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_main, container, false);

    tvDate = (TextView)view.findViewById(R.id.tv_curdate);

    gridView = (GridView)view.findViewById(R.id.gv_calender);
    iv_last =  (ImageView)view.findViewById(R.id.iv_lastmonth);
    iv_next =  (ImageView)view.findViewById(R.id.iv_nextmonth);

    iv_last.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        clickLeft++;
        //gridview 요일 표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");


        showM--;

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(showY, showM-1, 1);
        dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //Toast.makeText(getApplicationContext(), "dayNum = " + dayNum , Toast.LENGTH_SHORT).show();
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
          dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        lastM = Integer.parseInt(curMonthFormat.format(date))  - clickLeft;
        lastY = Integer.parseInt(curYearFormat.format(date));

        gridAdapter = new GridAdapter(view.getContext().getApplicationContext(), dayList, lastM);
        gridView.setAdapter(gridAdapter);

        if(showM<1){
          showY--;
          showM += 12;
          //Toast.makeText(this, showM+"  "+showY, Toast.LENGTH_LONG).show();
        }
        if(showM<10) {
          tvDate.setText(showY + "/" + "0" + showM);
        }
        else if(showM<=12){
          tvDate.setText(showY + "/"  + showM);
        }
      }
    });
    iv_next.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        clickRight++;
        //gridview 요일 표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");


        showM++;
        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(showY, showM-1, 1);
        dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //Toast.makeText(getApplicationContext(), "dayNum = " + dayNum , Toast.LENGTH_SHORT).show();
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
          dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        nextM = Integer.parseInt(curMonthFormat.format(date))  + clickRight;
        nextY = Integer.parseInt(curYearFormat.format(date));

        gridAdapter = new GridAdapter(view.getContext().getApplicationContext(), dayList, nextM);
        gridView.setAdapter(gridAdapter);

        if(showM>12){
          showY++;
          showM-=12;

        }
        if(showM<10) {
          tvDate.setText(showY + "/" + "0" + showM);
        }
        else if(nextM<=12){
          tvDate.setText(showY + "/"  + showM);
        }
      }
    });

    pref =  this.getContext().getSharedPreferences("pref", this.getContext().MODE_PRIVATE);
    color = pref.getInt("key2", -8331542);

    iv_last.setColorFilter(null);
    iv_last.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    iv_next.setColorFilter(null);
    iv_next.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    // 오늘 날짜를 세팅 해준다.
    long now = System.currentTimeMillis();

    date = new Date(now);
    /**
     * 현재 년도, 월, 일 가져오기
     */
    SimpleDateFormat simpleDate=new SimpleDateFormat("yyyyMMdd");
    /**
     * 달력 눌렀을 때 Time-Table이 나오는 것이 있는데 그 때 이 DATE 변수를 Key 값으로 이용해서 데이터를 불러옵니다.
     */
    DATE=simpleDate.format(date);

    //연,월,일을 따로 저장
    curYearFormat = new SimpleDateFormat("yyyy");
    curMonthFormat = new SimpleDateFormat("MM");
    //final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);
    Log.d("########",curYearFormat.format(date));
    //현재 날짜 텍스트뷰에 뿌려줌
    tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));

    //gridview 요일 표시
    dayList = new ArrayList<String>();
    dayList.add("일");
    dayList.add("월");
    dayList.add("화");
    dayList.add("수");
    dayList.add("목");
    dayList.add("금");
    dayList.add("토");

    mCal = Calendar.getInstance();

    //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
    mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
    dayNum = mCal.get(Calendar.DAY_OF_WEEK);
    //1일 - 요일 매칭 시키기 위해 공백 add
    for (int i = 1; i < dayNum; i++) {
      dayList.add("");
      test++;
    }
    //Toast.makeText(getApplicationContext(), "dayNum = " + dayNum , Toast.LENGTH_SHORT).show();
    setCalendarDate(mCal.get(Calendar.MONTH) + 1);

    showM = Integer.parseInt(curMonthFormat.format(date));
    showY = Integer.parseInt(curYearFormat.format(date));

    gridAdapter = new GridAdapter(this.getActivity().getApplicationContext(), dayList, showM);
    gridView.setAdapter(gridAdapter);

    today = mCal.get(Calendar.DAY_OF_MONTH);

    PaintDrawable pd = new PaintDrawable(color);
    pd.setAlpha(70);
    gridView.setSelector(pd);

    return view;
  }




  /**
   * 해당 월에 표시할 일 수 구함
   *
   * @param month
   */
  private void setCalendarDate(int month) {
    mCal.set(Calendar.MONTH, month - 1);

    for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
      dayList.add("" + (i + 1));
    }

  }
  //해당 날짜가 안에 있는지 검사하는 메소드
  private boolean Day_in(String year, String month, int day){
    if(day < 1) return false;
    int m = Integer.parseInt(month);
    int y = Integer.parseInt(year);

    switch(m){
      case 1: case 3: case 5: case 7: case 8: case 10: case 12:
        if(day > 31) return false;
        break;
      case 4: case 6: case 9: case 11:
        if(day > 30) return false;
        break;
      case 2:
        if(y%4 == 0 && day > 29) return false;
        if(y%4 > 0 && day > 28) return false;
    }

    return true;
  }

  int clickLeft,clickRight;
  int lastM,lastY,nextM,nextY;

  public void lastMonth(View v){


  }

  public void nextMonth(View v){
  }

}
