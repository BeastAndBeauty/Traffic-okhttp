package com.pyp.traffic.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SetTrafficLightNowStatusRequest extends BaseRequest {

    private String UserName;
    private int TrafficLightId;
    private int Time;

    @Override
    public String getType() {
        return "SetTrafficLightNowStatus";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        this.TrafficLightId = Integer.parseInt(params[1].toString());
        this.Time = Integer.parseInt(params[2].toString());
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", this.UserName);
            object.put("TrafficLightId", this.TrafficLightId);
            object.put("Time", this.Time);
            object.put("Status", "Green");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        try {
            if (new JSONObject(parse).getString("RESULT").equals("S"))
                return new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date());
            return "F";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }
}
