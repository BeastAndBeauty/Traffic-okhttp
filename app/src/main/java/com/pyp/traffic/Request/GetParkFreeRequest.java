package com.pyp.traffic.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetParkFreeRequest extends BaseRequest {

    private String UserName;

    @Override
    public String getType() {
        return "GetParkFree";
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
        List<Integer> list = new ArrayList<>();
        try {
            if (new JSONObject(parse).getString("RESULT").equals("S")) {
                JSONArray array = new JSONObject(parse).getJSONArray("ROWS_DETAIL");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = (JSONObject) array.get(i);
                    list.add(Integer.parseInt(object.getString("parkFreeId")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
