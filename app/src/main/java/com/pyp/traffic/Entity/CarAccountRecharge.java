package com.pyp.traffic.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;


import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CarAccountRecharge {
    @Property
    private int CarId;
    @Property
    private int Money;
    @Property
    private String time;

    @Generated(hash = 261747234)
    public CarAccountRecharge() {
    }

    @Generated(hash = 1952599080)
    public CarAccountRecharge(int CarId, int Money, String time) {
        this.CarId = CarId;
        this.Money = Money;
        this.time = time;
    }

    public int getCarId() {
        return this.CarId;
    }

    public void setCarId(int CarId) {
        this.CarId = CarId;
    }

    public int getMoney() {
        return this.Money;
    }

    public void setMoney(int Money) {
        this.Money = Money;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
