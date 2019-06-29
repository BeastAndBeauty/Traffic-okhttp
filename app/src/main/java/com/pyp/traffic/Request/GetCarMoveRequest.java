package com.pyp.traffic.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class GetCarMoveRequest extends BaseRequest {


    private String UserName;
    private int CarId;

    @Override
    public String getType() {
        return "GetCarMove";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        this.CarId = Integer.parseInt(params[1].toString());
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", this.UserName);
            object.put("CarId", this.CarId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        Log.i("hhh","parse:"+parse);
        try {
            if (new JSONObject(parse).getString("RESULT").equals("S"))
                return new JSONObject(parse).getString("CarAction");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }
}
