package com.pyp.traffic.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.GetSenseByNameRequest;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 光照检测
 * A simple {@link Fragment} subclass.
 */
public class SunShineDetectionFragment extends Fragment {


    @BindView(R.id.open_menu)
    ImageView openMenu;
    @BindView(R.id.btn_acquire)
    Button btnAcquire;
    @BindView(R.id.tip)
    TextView tip;
    @BindView(R.id.switch_control)
    Switch switchControl;
    Unbinder unbinder;

    private Timer timer;
    private TimerTask timerTask;
    private int lghtIntensity=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sun_shine_detection, container, false);
        unbinder = ButterKnife.bind(this, view);

        initTimer();


        return view;
    }

    private void initTimer() {
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                getLightIntensity();
            }
        };
        timer.schedule(timerTask,0,10*1000);

    }

    private void getLightIntensity(){
        new GetSenseByNameRequest().setParams(new Object[]{MyApplication.getUserName()}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if (!response.equals("F")){
                    lghtIntensity=Integer.valueOf(response.toString());
                    if (lghtIntensity<3000)
                        tip.setText("光线太暗了，为你自动\n打开所有路灯");
                    else
                        tip.setText("光线刚好，为你自动\n关闭所有路灯");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.open_menu, R.id.btn_acquire})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.open_menu:
                break;
            case R.id.btn_acquire:
                Toast.makeText(getActivity(),"当前光照强度值为："+lghtIntensity,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
        timer=null;
        timerTask.cancel();
        timerTask=null;
    }
}
