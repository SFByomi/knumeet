package com.blueboard.knumeet.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.blueboard.knumeet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


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
    final View view = inflater.inflate(R.layout.fragment_main, container, false);




    return view;
  }

}
