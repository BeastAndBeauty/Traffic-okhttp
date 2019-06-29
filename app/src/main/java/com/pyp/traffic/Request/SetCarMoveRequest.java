package com.pyp.traffic.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class SetCarMoveRequest extends BaseRequest {

    private String UserName;
    private int CarId;

    private CarAction CarAction;

    public enum CarAction {Start, Stop}


    @Override
    public String getType() {
        return "SetCarMove";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserName = params[0].toString();
        this.CarId = Integer.parseInt(params[1].toString());
        this.CarAction = (CarAction) params[2];
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", this.UserName);
            object.put("CarId", this.CarId);
            object.put("CarAction", this.CarAction);
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
