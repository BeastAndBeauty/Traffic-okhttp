package com.pyp.traffic.Request;

import com.google.gson.Gson;
import com.pyp.traffic.Bean.BusStationInfoBean;
import com.pyp.traffic.GsonBean.BusStationInfoRequestBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetBusStationInfoRequest extends BaseRequest {

    private String BusStationId;

    @Override
    public String getType() {
        return "GetBusStationInfo";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.BusStationId = params[0].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("BusStationId", this.BusStationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        List<BusStationInfoBean> busStationInfoBeans = new ArrayList<>();
        try {
            BusStationInfoRequestBean busStationInfoRequestBean = new Gson().fromJson(parse, BusStationInfoRequestBean.class);
            if (busStationInfoRequestBean.getRESULT().equals("S")) {
                List<BusStationInfoRequestBean.ROWSDETAILBean> beans = busStationInfoRequestBean.getROWS_DETAIL();
                for (BusStationInfoRequestBean.ROWSDETAILBean i : beans) {
                    busStationInfoBeans.add(new BusStationInfoBean(i.getBusId(), i.getDistance() / 100,
                            i.getBusId() / 3600));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return busStationInfoBeans;
    }
}
