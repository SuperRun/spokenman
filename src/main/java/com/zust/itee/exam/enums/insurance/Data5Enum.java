package com.zust.itee.exam.enums.insurance;

/**
 * Created by liy on 2016/12/7.
 * 车速状况
 */
public enum Data5Enum {

    OVERSPEED((short)0, "超速"),
    BELOWSPEED((short)1, "未超速");

    private short status;

    private String info;

    Data5Enum(short status, String info) {
        this.status = status;
        this.info = info;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static Data5Enum getIndexOf(Short idx){
    	for(Data5Enum data5Enum: Data5Enum.values()){
    		if(data5Enum.getStatus()==idx){
    			return data5Enum;
    		}
    	}
    	return null;
    }


}
