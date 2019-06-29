package com.pyp.traffic.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pyp.traffic.Activity.HomepageActivity;
import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.ChildFragment.fg_mc_controlFragment;
import com.pyp.traffic.ChildFragment.fg_mc_messageFragment;
import com.pyp.traffic.ChildFragment.fg_mc_moneyFragment;
import com.pyp.traffic.R;

public class MyCarFragment extends Fragment implements View.OnClickListener {
    private Button fg_mc_black;
    private TextView fg_mc_user;
    private TextView fg_mc_moneyBT;
    private TextView fg_mc_control;
    private TextView fg_mc_message;
    private FrameLayout fg_mc_Framelayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_mycar, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        fg_mc_black = (Button) view.findViewById(R.id.fg_mc_black);
        fg_mc_user = (TextView) view.findViewById(R.id.fg_mc_user);
        fg_mc_moneyBT = (TextView) view.findViewById(R.id.fg_mc_moneyBT);
        fg_mc_control = (TextView) view.findViewById(R.id.fg_mc_control);
        fg_mc_message = (TextView) view.findViewById(R.id.fg_mc_message);
        fg_mc_Framelayout = (FrameLayout) view.findViewById(R.id.fg_mc_Framelayout);

        fg_mc_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomepageActivity.class));
                getActivity().finish();
            }
        });
        fg_mc_moneyBT.setOnClickListener(this);
        fg_mc_control.setOnClickListener(this);
        fg_mc_message.setOnClickListener(this);
        fg_mc_user.setText("当前用户："+ MyApplication.getUserName());
        fg_mc_moneyBT.performClick();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction=getChildFragmentManager().beginTransaction();
        setAllTextStyle();
        switch (v.getId()) {
            case R.id.fg_mc_moneyBT:
                fg_mc_moneyBT.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                transaction.replace(R.id.fg_mc_Framelayout,new fg_mc_moneyFragment());
                break;
            case R.id.fg_mc_control:
                fg_mc_control.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                transaction.replace(R.id.fg_mc_Framelayout,new fg_mc_controlFragment());
                break;
            case R.id.fg_mc_message:
                fg_mc_message.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                transaction.replace(R.id.fg_mc_Framelayout,new fg_mc_messageFragment());
                break;
        }
        transaction.commit();
    }

    public void setAllTextStyle(){
        fg_mc_moneyBT.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        fg_mc_control.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        fg_mc_message.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
    }
}
