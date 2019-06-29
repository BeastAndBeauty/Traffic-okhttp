package com.pyp.traffic.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class SetRoadLightStatusActionRequest extends BaseRequest {
    private String UserName;
    private int RoadLightId;
    private Action Action;

    public enum Action {Open, Close}


    @Override
    public String getType() {
        return "SetRoadLightStatusAction";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        this.RoadLightId = Integer.parseInt(params[1].toString());
        this.Action = (Action) params[2];
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", this.UserName);
            object.put("RoadLightId", this.RoadLightId);
            object.put("Action", this.Action);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        try {
            return new JSONObject(parse).getString("RESULT");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }
}
