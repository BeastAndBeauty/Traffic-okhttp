package com.pyp.traffic.Request;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.Bean.AllSenseBean;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.Entity.AllSenseAndRoadStatus;

public class GetAllSenseAndRoadStatusRequest {
    private String UserName;

    public GetAllSenseAndRoadStatusRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        return this;
    }

    public void sendRequest(final OnResponseListening onResponseListening) {
        new GetAllSenseRequest().setParams(new Object[]{UserName})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        final AllSenseBean allSenseBean = (AllSenseBean) response;
                        if (allSenseBean.getRESULT().equals("S")) {
                            new GetRoadStatusRequest().setParams(new Object[]{1})
                                    .sendRequest(new OnResponseListening() {
                                        @Override
                                        public void onResponse(Object response) {
                                            try {
                                                String road = response.toString();
                                                if (!road.equals("F")) {
                                                    MyApplication.getDaoSession().
                                                            getAllSenseAndRoadStatusDao()
                                                            .insert(new AllSenseAndRoadStatus(
                                                                    allSenseBean.get_$Pm2544(),
                                                                    allSenseBean.getCo2(),
                                                                    allSenseBean.getLightIntensity(),
                                                                    allSenseBean.getHumidity(),
                                                                    allSenseBean.getTemperature(),
                                                                    Integer.parseInt(road)));
                                                    onResponseListening.onResponse("S");
                                                    return;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            onResponseListening.onResponse("F");
                                        }
                                    });
                        }
                    }
                });
    }
}
