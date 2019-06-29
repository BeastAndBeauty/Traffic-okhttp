package com.pyp.traffic.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class GetSenseByNameRequest extends BaseRequest {

    private String UserName;

    @Override
    public String getType() {
        return "GetSenseByName";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", this.UserName);
            object.put("SenseName", "lightIntensity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        try {
            if (new JSONObject(parse).getString("RESULT").equals("S"))
                return new JSONObject(parse).getString("lightIntensity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }
}
