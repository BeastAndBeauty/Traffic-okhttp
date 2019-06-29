package com.pyp.traffic.Request;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.Entity.CarAccountRecharge;
import com.pyp.traffic.Entity.CarAccountRechargeDao;


import java.util.List;

/**
 * 得到小车的充值记录
 */
public class GetCarRechargeLog {
    public static List<CarAccountRecharge> getData() {
        return MyApplication.getDaoSession().getCarAccountRechargeDao().queryBuilder()
                .orderDesc(CarAccountRechargeDao.Properties.Time)//降序
                .list();
    }
}
