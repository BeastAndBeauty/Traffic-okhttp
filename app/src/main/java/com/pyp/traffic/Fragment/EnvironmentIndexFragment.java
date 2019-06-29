package com.pyp.traffic.Fragment;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pyp.traffic.Activity.EnvironmentChartActivity;
import com.pyp.traffic.Activity.EnvironmentIndexSettingActivity;
import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.Entity.AllSenseAndRoadStatus;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.GetAllSenseAndRoadStatusRequest;
import com.pyp.traffic.Request.GetSenseAndRoadData;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 环境指标
 * A simple {@link Fragment} subclass.
 */
public class EnvironmentIndexFragment extends Fragment {


    @BindView(R.id.open_menu)
    ImageView openMenu;
    @BindView(R.id.setting)
    TextView setting;
    @BindView(R.id.text_temperature)
    TextView textTemperature;
    @BindView(R.id.temperature_number)
    TextView temperatureNumber;
    @BindView(R.id.text_humidity)
    TextView textHumidity;
    @BindView(R.id.humidity_number)
    TextView humidityNumber;
    @BindView(R.id.text_sun_shine)
    TextView textSunShine;
    @BindView(R.id.sun_shine_number)
    TextView sunShineNumber;
    @BindView(R.id.text_co_temperature)
    TextView textCoTemperature;
    @BindView(R.id.co_number)
    TextView coNumber;
    @BindView(R.id.text_pm)
    TextView textPm;
    @BindView(R.id.pm_number)
    TextView pmNumber;
    @BindView(R.id.text_road_status)
    TextView textRoadStatus;
    @BindView(R.id.road_status_number)
    TextView roadStatusNumber;
    Unbinder unbinder;
    @BindView(R.id.rlTemperature)
    RelativeLayout rlTemperature;
    @BindView(R.id.rlHumidity)
    RelativeLayout rlHumidity;
    @BindView(R.id.rlSunShine)
    RelativeLayout rlSunShine;
    @BindView(R.id.rlCo2)
    RelativeLayout rlCo2;
    @BindView(R.id.rlPM)
    RelativeLayout rlPM;
    @BindView(R.id.rlRoadStatus)
    RelativeLayout rlRoadStatus;

    private Timer timer;
    private TimerTask timerTask;
    private AllSenseAndRoadStatus senseBean;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getAllsense();

        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_environment_index, container, false);
        unbinder = ButterKnife.bind(this, view);

        init();

        return view;
    }

    private void init() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.i("papa","run");
                if ((boolean) MyApplication.get("IsOpen", false)) {
                    handler.sendEmptyMessage(1);
                }
            }
        };
        timer.schedule(timerTask,0,3000);

        if ((boolean)MyApplication.get("IsOpen",false)){


            setBack();

        }



    }

    private void setBack(){
        rlTemperature.setBackgroundColor(Color.parseColor("#ffff00"));
        rlHumidity.setBackgroundColor(Color.parseColor("#ffff00"));
        rlSunShine.setBackgroundColor(Color.parseColor("#ffff00"));
        rlCo2.setBackgroundColor(Color.parseColor("#ffff00"));
        rlPM.setBackgroundColor(Color.parseColor("#ffff00"));
        rlRoadStatus.setBackgroundColor(Color.parseColor("#ffff00"));
    }

    private void getAllsense() {
        List<AllSenseAndRoadStatus> list=GetSenseAndRoadData.getSecondData();
        senseBean=list.get(0);
        Log.i("papa","senseBean.getStatus()"+senseBean.getStatus());

        checkSenseValue();

    }

    //检查是否要变为红色
    private void checkSenseValue() {
        Log.i("papa","checkSenseValue");
        String tem=(String) MyApplication.get("edtTemperature", "-1");
        if (-1 != Integer.valueOf(tem)) {
            temperatureNumber.setText(tem+"");
            if (senseBean.getTemperature() < Integer.valueOf(tem)) {
                rlTemperature.setBackgroundColor(Color.parseColor("#ff0000"));
                sendNotifigation(senseBean.getTemperature(), Integer.valueOf(tem));
            } else
                rlTemperature.setBackgroundColor(Color.parseColor("#00ff00"));
        }
        String hum=(String) MyApplication.get("edtHumidity", "-1");
        if (-1 != Integer.valueOf(hum)) {
            humidityNumber.setText(hum+"");
            if (senseBean.getHumidity() < Integer.valueOf(hum)) {
                sendNotifigation(senseBean.getHumidity(), Integer.valueOf(hum));
                rlHumidity.setBackgroundColor(Color.parseColor("#ff0000"));
            } else
                rlHumidity.setBackgroundColor(Color.parseColor("#00ff00"));
        }
        String sun=(String) MyApplication.get("edtSunShine", "-1");

        if (-1 != Integer.valueOf(sun)) {
            sunShineNumber.setText(sun+"");
            if (senseBean.getLightIntensity() < Integer.valueOf(sun)) {
                sendNotifigation(senseBean.getLightIntensity(), Integer.valueOf(sun));
                rlSunShine.setBackgroundColor(Color.parseColor("#ff0000"));
            } else
                rlSunShine.setBackgroundColor(Color.parseColor("#00ff00"));
        }
        String co=(String) MyApplication.get("edtCo", "-1");

        if (-1 != Integer.valueOf(co+"")) {
            coNumber.setText(co);
            if (senseBean.getCo2() < Integer.valueOf(co)) {
                rlCo2.setBackgroundColor(Color.parseColor("#ff0000"));
                sendNotifigation(senseBean.getCo2(), Integer.valueOf(co));
            } else
                rlCo2.setBackgroundColor(Color.parseColor("#00ff00"));
        }
        String pm=(String) MyApplication.get("edtPm", "-1");

        if (-1 != Integer.valueOf(pm+"")) {
            pmNumber.setText(pm);
            if (senseBean.get_$Pm2544() < Integer.valueOf(pm)) {
                rlPM.setBackgroundColor(Color.parseColor("#ff0000"));
                sendNotifigation(senseBean.get_$Pm2544(), Integer.valueOf(pm));
            } else
                rlCo2.setBackgroundColor(Color.parseColor("#00ff00"));
        }
        String road=(String) MyApplication.get("edtRoadStatus", "-1");

        if (-1 != Integer.valueOf(road+"")) {
            roadStatusNumber.setText(road);
            if (senseBean.getStatus() < Integer.valueOf(road)) {
                rlRoadStatus.setBackgroundColor(Color.parseColor("#ff0000"));
                sendNotifigation(senseBean.getStatus(), Integer.valueOf(road));
            } else
                rlRoadStatus.setBackgroundColor(Color.parseColor("#00ff00"));
        }



    }

    //发送通知 当前值和阈值
    private void sendNotifigation(int cunrentValue, int yuZhi) {

        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity());


        mBuilder.setContentTitle("【PM2.5】告警")
                .setContentText("当前值" + cunrentValue + "/" + yuZhi + "(阈值)")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setWhen(System.currentTimeMillis())
                .setTicker("我是测试内容")
                .setDefaults(Notification.DEFAULT_SOUND);
        notificationManager.notify(10, mBuilder.build());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.open_menu, R.id.setting, R.id.text_temperature, R.id.text_humidity, R.id.text_sun_shine, R.id.text_co_temperature, R.id.text_pm, R.id.text_road_status})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.open_menu:
                break;
            case R.id.setting:
                intent = new Intent(getActivity(), EnvironmentIndexSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.text_temperature:
                intent = new Intent(getActivity(), EnvironmentChartActivity.class);
                intent.putExtra("position", 0);
                startActivity(intent);
                break;
            case R.id.text_humidity:
                intent = new Intent(getActivity(), EnvironmentChartActivity.class);
                intent.putExtra("position", 1);
                startActivity(intent);
                break;
            case R.id.text_sun_shine:
                intent = new Intent(getActivity(), EnvironmentChartActivity.class);
                intent.putExtra("position", 2);
                startActivity(intent);
                break;
            case R.id.text_co_temperature:
                intent = new Intent(getActivity(), EnvironmentChartActivity.class);
                intent.putExtra("position", 3);
                startActivity(intent);
                break;
            case R.id.text_pm:
                intent = new Intent(getActivity(), EnvironmentChartActivity.class);
                intent.putExtra("position", 4);
                startActivity(intent);
                break;
            case R.id.text_road_status:
                intent = new Intent(getActivity(), EnvironmentChartActivity.class);
                intent.putExtra("position", 5);
                startActivity(intent);
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
