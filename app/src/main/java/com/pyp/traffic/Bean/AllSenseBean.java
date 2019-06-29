package com.pyp.traffic.Bean;

import com.google.gson.annotations.SerializedName;

public class AllSenseBean {

    /**
     * RESULT : S
     * ERRMSG : 成功
     * pm2.5 : 92
     * co2 : 4672
     * lightIntensity : 304
     * humidity : 43
     * temperature : 33
     */

    private String RESULT;
    private String ERRMSG;
    @SerializedName("pm2.5")
    private int _$Pm2544; // FIXME check this code
    private int co2;
    private int lightIntensity;
    private int humidity;
    private int temperature;

    public AllSenseBean(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public int get_$Pm2544() {
        return _$Pm2544;
    }

    public void set_$Pm2544(int _$Pm2544) {
        this._$Pm2544 = _$Pm2544;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(int lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
