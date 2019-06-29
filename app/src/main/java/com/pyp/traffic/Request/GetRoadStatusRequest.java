package com.pyp.traffic.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class GetRoadStatusRequest extends BaseRequest {
    private int RoadId;

    @Override
    public String getType() {
        return "GetRoadStatus";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.RoadId = Integer.parseInt(params[0].toString());
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("RoadId", this.RoadId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        try {
            if (new JSONObject(parse).getString("RESULT").equals("S"))
                return new JSONObject(parse).getString("Status");
            return "F";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }
}
