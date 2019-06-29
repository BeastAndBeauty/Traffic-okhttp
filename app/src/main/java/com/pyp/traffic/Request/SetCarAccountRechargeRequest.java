package com.pyp.traffic.Request;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.Entity.CarAccountRecharge;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SetCarAccountRechargeRequest extends BaseRequest {
    private String UserName;
    private int CarId;
    private int Money;

    @Override
    public String getType() {
        return "SetCarAccountRecharge";
    }


    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        this.CarId = Integer.parseInt(params[1].toString());
        this.Money = Integer.parseInt(params[2].toString());
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", this.UserName);
            object.put("CarId", this.CarId);
            object.put("Money", this.Money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onResponseParse(String parse) {
        try {
            if (new JSONObject(parse).getString("RESULT").equals("S"))
                MyApplication.getDaoSession().getCarAccountRechargeDao().insert(new CarAccountRecharge(CarId, Money,
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            return new JSONObject(parse).getString("RESULT");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }
}
