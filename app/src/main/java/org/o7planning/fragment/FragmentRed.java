package org.o7planning.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by USER on 4/1/2017.
 */

public class FragmentRed extends Fragment implements FragmentCallbacks{
    MainActivity main;
    TextView txtRed;
    Button buttonFirst, buttonNext, buttonBack, buttonLast;
    public static FragmentRed newInstance(String strArg1) {
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activities containing this fragment must implement interface: MainCallbacks
        if (!(getActivity() instanceof MainCallBack)) {
            throw new IllegalStateException( " Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate res/layout_red.xml which includes a textview and a button
        LinearLayout view_layout_red = (LinearLayout) inflater.inflate(
                R.layout.layout_red, null);
        // plumbing - get a reference to widgets in the inflated layout
        txtRed = (TextView) view_layout_red.findViewById(R.id.textView1Red);
        buttonFirst = (Button) view_layout_red.findViewById(R.id.buttonFirst);
        buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", "0");
            }
        });
        buttonNext = (Button) view_layout_red.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", "1");
            }
        });
        buttonBack = (Button) view_layout_red.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", "2");
            }
        });
        buttonLast = (Button) view_layout_red.findViewById(R.id.buttonLast);
        buttonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", "3");
            }
        });
        // show string argument supplied by constructor (if any!)
        try {
            Bundle arguments = getArguments();
            String redMessage = arguments.getString("arg1", "");
            txtRed.setText(redMessage);
        } catch (Exception e) {
            Log.e("RED BUNDLE ERROR - ", "" + e.getMessage());
        }
        // clicking the button changes the time displayed and sends a copy to MainActivity
        
        return view_layout_red;
    }
    public void onMsgFromMainToFragment(String strValue) {
        // receiving a message from MainActivity (it may happen at any point in time)
        txtRed.setText("THIS MESSAGE COMES FROM MAIN:" + strValue);
    }
}
