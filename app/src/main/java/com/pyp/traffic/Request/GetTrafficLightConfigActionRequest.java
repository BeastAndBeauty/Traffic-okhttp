package com.pyp.traffic.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class GetTrafficLightConfigActionRequest extends BaseRequest {

    private String UserName;
    private int TrafficLightId;

    @Override
    public String getType() {
        return "GetTrafficLightConfigAction";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        this.TrafficLightId = Integer.parseInt(params[1].toString());
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", this.UserName);
            object.put("TrafficLightId", this.TrafficLightId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        try {
            if (new JSONObject(parse).getString("RESULT").equals("S")) {
                return new JSONObject(parse).getJSONObject("ROWS_DETAIL").getString("greentime");
            }
            return "F";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }
}
