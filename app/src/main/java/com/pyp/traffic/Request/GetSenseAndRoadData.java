package com.pyp.traffic.Request;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.Entity.AllSenseAndRoadStatus;
import com.pyp.traffic.Entity.AllSenseAndRoadStatusDao;
import com.pyp.traffic.Entity.AllSenseAndRoadStatusMinute;
import com.pyp.traffic.Entity.AllSenseAndRoadStatusMinuteDao;

import java.util.List;

public class GetSenseAndRoadData {
    /**
     * 5秒一次
     *
     * @return
     */
    public static List<AllSenseAndRoadStatus> getSecondData() {
        return MyApplication.getDaoSession().getAllSenseAndRoadStatusDao().queryBuilder()
                .orderDesc(AllSenseAndRoadStatusDao.Properties.Id)//降序
                .limit(50)//前20条
                .list();
    }

    /**
     * 1分钟一次平均值
     *
     * @return
     */
    public static List<AllSenseAndRoadStatusMinute> getMinuteData() {
        return MyApplication.getDaoSession().getAllSenseAndRoadStatusMinuteDao().queryBuilder()
                .orderDesc(AllSenseAndRoadStatusMinuteDao.Properties.Id)//降序
                .limit(50)//前20条
                .list();
    }
}
