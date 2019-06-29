package com.pyp.traffic.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AllSenseAndRoadStatusMinute {
    @Id
    private Long id;
    @Property
    private int _$Pm2544;
    @Property
    private int co2;
    @Property
    private int lightIntensity;
    @Property
    private int humidity;
    @Property
    private int temperature;
    @Property
    private int status;
    @Generated(hash = 1234766593)
    public AllSenseAndRoadStatusMinute(Long id, int _$Pm2544, int co2,
            int lightIntensity, int humidity, int temperature, int status) {
        this.id = id;
        this._$Pm2544 = _$Pm2544;
        this.co2 = co2;
        this.lightIntensity = lightIntensity;
        this.humidity = humidity;
        this.temperature = temperature;
        this.status = status;
    }
    @Generated(hash = 1463766308)
    public AllSenseAndRoadStatusMinute() {
    }

    public AllSenseAndRoadStatusMinute(int _$Pm2544, int co2, int lightIntensity, int humidity, int temperature, int status) {
        this._$Pm2544 = _$Pm2544;
        this.co2 = co2;
        this.lightIntensity = lightIntensity;
        this.humidity = humidity;
        this.temperature = temperature;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int get_$Pm2544() {
        return this._$Pm2544;
    }
    public void set_$Pm2544(int _$Pm2544) {
        this._$Pm2544 = _$Pm2544;
    }
    public int getCo2() {
        return this.co2;
    }
    public void setCo2(int co2) {
        this.co2 = co2;
    }
    public int getLightIntensity() {
        return this.lightIntensity;
    }
    public void setLightIntensity(int lightIntensity) {
        this.lightIntensity = lightIntensity;
    }
    public int getHumidity() {
        return this.humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public int getTemperature() {
        return this.temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}
