package com.zust.itee.exam.enums.insurance;

/**
 * Created by liy on 2016/12/7.
 * 载货状况
 */
public enum Data3Enum {

    EMPTY((short)0, "空车"),
    FULL((short)1, "重车");

    private short status;

    private String info;

    Data3Enum(short status, String info) {
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

    public static Data3Enum getIndexOf(Short idx){
    	for(Data3Enum data3Enum: Data3Enum.values()){
    		if(data3Enum.getStatus()==idx){
    			return data3Enum;
    		}
    	}
    	return null;
    }
}
