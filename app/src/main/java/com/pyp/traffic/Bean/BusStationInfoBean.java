package com.pyp.traffic.Bean;

public class BusStationInfoBean {
    private int BusId;
    private int Distance;
    private int time;

    public int getBusId() {
        return BusId;
    }

    public void setBusId(int busId) {
        BusId = busId;
    }

    public int getDistance() {
        return Distance;
    }

    public void setDistance(int distance) {
        Distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public BusStationInfoBean(int busId, int distance, int time) {

        BusId = busId;
        Distance = distance;
        this.time = time;
    }

    public BusStationInfoBean() {

    }
}
