package com.pyp.traffic.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.Entity.AllSenseAndRoadStatus;
import com.pyp.traffic.Entity.AllSenseAndRoadStatusDao;
import com.pyp.traffic.Entity.AllSenseAndRoadStatusMinute;
import com.pyp.traffic.Request.GetAllSenseAndRoadStatusRequest;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AllSenseAndRoadStatusService extends Service {

    private TimerTask timerTask = null;
    private Timer timer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                new GetAllSenseAndRoadStatusRequest().setParams(new Object[]{MyApplication.getUserName()})
                        .sendRequest(new OnResponseListening() {
                            @Override
                            public void onResponse(Object response) {
                                Log.i("请求环境指标", response.toString());
                                if (response.equals("S")) {
                                    AllSenseAndRoadStatusDao allSenseAndRoadStatusDao =
                                            MyApplication.getDaoSession().getAllSenseAndRoadStatusDao();
                                    if (allSenseAndRoadStatusDao.count() % 20 == 0) {
                                        List<AllSenseAndRoadStatus> list = allSenseAndRoadStatusDao.queryBuilder()
                                                .orderDesc(AllSenseAndRoadStatusDao.Properties.Id)//降序
                                                .limit(20)//前20条
                                                .list();
                                        int _$Pm2544 = 0;
                                        int co2 = 0;
                                        int lightIntensity = 0;
                                        int humidity = 0;
                                        int temperature = 0;
                                        int status = 0;
                                        for (AllSenseAndRoadStatus i : list) {
                                            _$Pm2544 += i.get_$Pm2544();
                                            co2 += i.getCo2();
                                            lightIntensity += i.getLightIntensity();
                                            humidity += i.getHumidity();
                                            temperature += i.getTemperature();
                                            status += i.getStatus();
                                        }
                                        MyApplication.getDaoSession().getAllSenseAndRoadStatusMinuteDao()
                                                .insert(new AllSenseAndRoadStatusMinute(_$Pm2544 / 20,
                                                        co2 / 20, lightIntensity / 20, humidity / 20,
                                                        temperature / 20, status / 20));
                                    }
                                }
                            }
                        });

            }
        };
        if (timer == null)
            timer = new Timer();
        else {
            timer.cancel();
            timer = new Timer();
        }
        timer.schedule(timerTask, 0, 5000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
