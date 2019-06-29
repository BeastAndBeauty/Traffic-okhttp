package com.pyp.traffic.Request;

import com.google.gson.Gson;
import com.pyp.traffic.Bean.AllSenseBean;

import org.json.JSONException;
import org.json.JSONObject;

public class GetAllSenseRequest extends BaseRequest {
    private String UserName;

    @Override
    public String getType() {
        return "GetAllSense";
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        try {
            return new Gson().fromJson(parse, AllSenseBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AllSenseBean("F");
    }
}
