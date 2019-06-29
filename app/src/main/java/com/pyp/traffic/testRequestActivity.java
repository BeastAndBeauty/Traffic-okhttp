package com.pyp.traffic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pyp.traffic.Activity.HomepageActivity;
import com.pyp.traffic.Bean.AllSenseBean;
import com.pyp.traffic.Bean.BusStationInfoBean;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.Entity.AllSenseAndRoadStatus;
import com.pyp.traffic.Entity.AllSenseAndRoadStatusMinute;
import com.pyp.traffic.Entity.CarAccountRecharge;
import com.pyp.traffic.Request.GetAllSenseAndRoadStatusRequest;
import com.pyp.traffic.Request.GetAllSenseRequest;
import com.pyp.traffic.Request.GetBusStationInfoRequest;
import com.pyp.traffic.Request.GetCarAccountBalanceRequest;
import com.pyp.traffic.Request.GetCarMoveRequest;
import com.pyp.traffic.Request.GetCarRechargeLog;
import com.pyp.traffic.Request.GetRoadStatusRequest;
import com.pyp.traffic.Request.GetSenseAndRoadData;
import com.pyp.traffic.Request.GetSenseByNameRequest;
import com.pyp.traffic.Request.GetTrafficLightConfigActionRequest;
import com.pyp.traffic.Request.SetCarAccountRechargeRequest;
import com.pyp.traffic.Request.SetCarMoveRequest;
import com.pyp.traffic.Request.SetRoadLightStatusActionRequest;
import com.pyp.traffic.Request.SetTrafficLightNowStatusRequest;
import com.pyp.traffic.Request.UserLoginRequest;
import com.pyp.traffic.Request.UserRegisterRequest;
import com.pyp.traffic.Service.AllSenseAndRoadStatusService;

import java.util.List;

public class testRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new UserLoginRequest().setParams(new Object[]{"user1", "123456"})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("登陆结果", response.toString());
                    }
                });
        new UserRegisterRequest().setParams(new Object[]{445221199810151252l, "user6", "123456",
                "英雄", 1, 13060885249l}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                Log.i("注册结果", response.toString());
            }
        });
        new GetBusStationInfoRequest().setParams(new Object[]{1})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        List<BusStationInfoBean> busStationInfoBeans = (List<BusStationInfoBean>) response;

                        Log.i("1号公交距离1号站台", busStationInfoBeans.get(0).getDistance() + "");
                    }
                });
        new GetCarAccountBalanceRequest().setParams(new Object[]{"user1", 1})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("1号小车余额", response.toString());
                    }
                });
        new SetCarMoveRequest().setParams(new Object[]{"user1", 1, SetCarMoveRequest.CarAction.Start})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("设置1号小车动作", response.toString());
                    }
                });
        new SetCarAccountRechargeRequest().setParams(new Object[]{"user1", 1, 100})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("充值1号小车", response.toString());
                    }
                });
        for (CarAccountRecharge i : GetCarRechargeLog.getData()) {
            Log.i("小车充值记录", i.getTime());
        }
        new GetRoadStatusRequest().setParams(new Object[]{1})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("1路状态", response.toString());
                    }
                });
        new GetTrafficLightConfigActionRequest().setParams(new Object[]{"user1", 1})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("1号红绿灯绿灯时长", response.toString());
                    }
                });
        new SetTrafficLightNowStatusRequest().setParams(new Object[]{"user1", 1, 10})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("设置1号红绿灯绿灯时长", response.toString());
                    }
                });
        new GetTrafficLightConfigActionRequest().setParams(new Object[]{"user1", 1})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("1号红绿灯绿灯时长", response.toString());
                    }
                });
        new SetRoadLightStatusActionRequest().setParams(new Object[]{"user1", 1, SetRoadLightStatusActionRequest.Action.Open})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("设置1号路灯状态", response.toString());

                    }
                });
        new GetSenseByNameRequest().setParams(new Object[]{"user1"})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("光照强度", response.toString());
                    }
                });
        new GetAllSenseRequest().setParams(new Object[]{"user1"})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        AllSenseBean allSenseBean = (AllSenseBean) response;
                        if (allSenseBean.getRESULT().equals("S"))
                            Log.i("PM2.5", allSenseBean.get_$Pm2544() + "");
                    }
                });
        Log.i("service开始启动", "成功");
        startService(new Intent(testRequestActivity.this, AllSenseAndRoadStatusService.class));


        for (AllSenseAndRoadStatus i : GetSenseAndRoadData.getSecondData()) {
            Log.i("5秒一次", i.get_$Pm2544() + "");
        }
        for (AllSenseAndRoadStatusMinute i : GetSenseAndRoadData.getMinuteData()) {
            Log.i("一分钟一次", i.get_$Pm2544() + "");
        }

        new GetCarMoveRequest().setParams(new Object[]{"user1", 1})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("小车状态", response.toString());
                    }
                });

    }
}
