package com.pyp.traffic.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRegisterRequest extends BaseRequest {

    private Long pcardId;
    private String UserName;
    private String passWord;
    private String pname;
    private int psex;//1男0女
    private Long ptel;

    @Override
    public String getType() {
        return "user_register";
    }


    @Override
    public BaseRequest setParams(Object[] params) {
        this.pcardId = Long.parseLong(params[0].toString());
        this.UserName = params[1].toString();
        this.passWord = params[2].toString();
        this.pname = params[3].toString();
        this.psex = Integer.parseInt(params[4].toString());
        this.ptel = Long.parseLong(params[5].toString());
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("pcardId", this.pcardId);
            object.put("UserName", this.UserName);
            object.put("passWord", this.passWord);
            object.put("pname", this.pname);
            object.put("psex", this.psex);
            object.put("ptel", this.ptel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        Log.i("hhh","UserRegisterRequest:"+parse);
        try {
            return new JSONObject(parse).getString("RESULT");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }
}
