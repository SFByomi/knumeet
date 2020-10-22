package com.blueboard.knumeet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment {

  private View view;
  private FirebaseAuth mAuth = FirebaseAuth.getInstance();
  private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

  public static ProfileFragment newInstance() {
    ProfileFragment fragment = new ProfileFragment();
    return fragment;
  }



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_profile, container, false);
    FirebaseUser user = mAuth.getCurrentUser();


    return view;

  }
}
