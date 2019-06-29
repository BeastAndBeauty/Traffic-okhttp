package com.pyp.traffic.ChildFragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.Dialog.fg_MyCar_Money_rechargedialog;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.GetCarAccountBalanceRequest;

import java.util.Timer;
import java.util.TimerTask;

public class fg_mc_moneyFragment extends Fragment implements View.OnClickListener {
    private TextView fg_mc_money_message1;
    private TextView fg_mc_money_money1;
    private View fg_mc_money_car1;
    private TextView fg_mc_money_car1no;
    private TextView fg_mc_money_message2;
    private TextView fg_mc_money_money2;
    private View fg_mc_money_car2;
    private TextView fg_mc_money_car2no;
    private TextView fg_mc_money_message3;
    private TextView fg_mc_money_money3;
    private View fg_mc_money_car3;
    private TextView fg_mc_money_car3no;
    private TextView fg_mc_money_message4;
    private TextView fg_mc_money_money4;
    private View fg_mc_money_car4;
    private TextView fg_mc_money_car4no;
    private Timer timer;
    private TimerTask timerTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_mc_money, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        fg_mc_money_message1 = (TextView) view.findViewById(R.id.fg_mc_money_message1);
        fg_mc_money_money1 = (TextView) view.findViewById(R.id.fg_mc_money_money1);
        fg_mc_money_car1 = (View) view.findViewById(R.id.fg_mc_money_car1);
        fg_mc_money_car1no = (TextView) view.findViewById(R.id.fg_mc_money_car1no);
        fg_mc_money_message2 = (TextView) view.findViewById(R.id.fg_mc_money_message2);
        fg_mc_money_money2 = (TextView) view.findViewById(R.id.fg_mc_money_money2);
        fg_mc_money_car2 = (View) view.findViewById(R.id.fg_mc_money_car2);
        fg_mc_money_car2no = (TextView) view.findViewById(R.id.fg_mc_money_car2no);
        fg_mc_money_message3 = (TextView) view.findViewById(R.id.fg_mc_money_message3);
        fg_mc_money_money3 = (TextView) view.findViewById(R.id.fg_mc_money_money3);
        fg_mc_money_car3 = (View) view.findViewById(R.id.fg_mc_money_car3);
        fg_mc_money_car3no = (TextView) view.findViewById(R.id.fg_mc_money_car3no);
        fg_mc_money_message4 = (TextView) view.findViewById(R.id.fg_mc_money_message4);
        fg_mc_money_money4 = (TextView) view.findViewById(R.id.fg_mc_money_money4);
        fg_mc_money_car4 = (View) view.findViewById(R.id.fg_mc_money_car4);
        fg_mc_money_car4no = (TextView) view.findViewById(R.id.fg_mc_money_car4no);

        fg_mc_money_car1.setOnClickListener(this);
        fg_mc_money_car2.setOnClickListener(this);
        fg_mc_money_car3.setOnClickListener(this);
        fg_mc_money_car4.setOnClickListener(this);
    }

    public void setTimer(){
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                GetCarAccountBalanceRequest(1);
            }
        };
        timer.schedule(timerTask,0,5000);
    }

    public void CancelTimer(){
        timer.cancel();
        timerTask.cancel();
        timerTask=null;
        timer=null;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTimer();
    }

    @Override
    public void onStop() {
        super.onStop();
        CancelTimer();
    }

    public void GetCarAccountBalanceRequest(final int CarId){
        new GetCarAccountBalanceRequest().setParams(new Object[]{MyApplication.getUserName(),CarId}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if(!response.toString().equals("F")){
                    int money=Integer.valueOf(response.toString());
                    switch (CarId){
                        case 1:
                            if(money<100) {
                                fg_mc_money_car1.setBackgroundColor(Color.RED);
                                fg_mc_money_message1.setText("警告");
                            }
                            else {
                                fg_mc_money_car1.setBackgroundColor(Color.GREEN);
                                fg_mc_money_message1.setText("正常");
                            }
                            fg_mc_money_money1.setText(response.toString());
                            break;
                        case 2:
                            if(money<100) {
                                fg_mc_money_car2.setBackgroundColor(Color.RED);
                                fg_mc_money_message2.setText("警告");
                            }
                            else {
                                fg_mc_money_car2.setBackgroundColor(Color.GREEN);
                                fg_mc_money_message2.setText("正常");
                            }
                            fg_mc_money_money2.setText(response.toString());
                            break;
                        case 3:
                            if(money<100) {
                                fg_mc_money_car3.setBackgroundColor(Color.RED);
                                fg_mc_money_message3.setText("警告");
                            }
                            else {
                                fg_mc_money_car3.setBackgroundColor(Color.GREEN);
                                fg_mc_money_message3.setText("正常");
                            }
                            fg_mc_money_money3.setText(response.toString());
                            break;
                        case 4:
                            if(money<100) {
                                fg_mc_money_car4.setBackgroundColor(Color.RED);
                                fg_mc_money_message4.setText("警告");
                            }
                            else {
                                fg_mc_money_car4.setBackgroundColor(Color.GREEN);
                                fg_mc_money_message4.setText("正常");
                            }
                            fg_mc_money_money4.setText(response.toString());
                            break;
                    }
                }
                else {
                    switch (CarId){
                        case 1:
                            fg_mc_money_car1no.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            fg_mc_money_car2no.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            fg_mc_money_car3no.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            fg_mc_money_car4no.setVisibility(View.VISIBLE);
                            break;
                    }
                }
                if(CarId<4)
                    GetCarAccountBalanceRequest(CarId+1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Dialog dialog = null;
        switch (v.getId()){
            case R.id.fg_mc_money_car1:
                dialog=new fg_MyCar_Money_rechargedialog(getContext(),1);
                break;
            case R.id.fg_mc_money_car2:
                dialog=new fg_MyCar_Money_rechargedialog(getContext(),2);
                break;
            case R.id.fg_mc_money_car3:
                dialog=new fg_MyCar_Money_rechargedialog(getContext(),3);
                break;
            case R.id.fg_mc_money_car4:
                dialog=new fg_MyCar_Money_rechargedialog(getContext(),4);
                break;
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
