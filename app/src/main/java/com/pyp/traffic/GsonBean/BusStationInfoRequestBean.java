package com.pyp.traffic.GsonBean;

import java.util.List;

public class BusStationInfoRequestBean {

    /**
     * RESULT : S
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"BusId":1,"Distance":15805},{"BusId":2,"Distance":44974}]
     */

    private String RESULT;
    private String ERRMSG;
    private List<ROWSDETAILBean> ROWS_DETAIL;

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

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * BusId : 1
         * Distance : 15805
         */

        private int BusId;
        private int Distance;

        public int getBusId() {
            return BusId;
        }

        public void setBusId(int BusId) {
            this.BusId = BusId;
        }

        public int getDistance() {
            return Distance;
        }

        public void setDistance(int Distance) {
            this.Distance = Distance;
        }
    }
}
