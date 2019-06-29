package com.pyp.traffic.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class UserLoginRequest extends BaseRequest {
    private String UserName;
    private String passWord;

    @Override
    public String getType() {
        return "user_login";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        this.passWord = params[1].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", this.UserName);
            object.put("passWord", this.passWord);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        try {
            if (new JSONObject(parse).getString("RESULT").equals("S"))
                return new JSONObject(parse).getString("UserRole");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }
}
