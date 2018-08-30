package com.zust.itee.exam.enums.insurance;

/**
 * Created by liy on 2016/12/7.
 * 行驶状况
 */
public enum Data1Enum {

    CURVED((short)0, "转弯"),
    STRAIGHT((short)1, "直行");

    private short status;

    private String info;

    Data1Enum(short status, String info) {
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

    public static Data1Enum getIndexOf(Short idx){
    	for(Data1Enum data1Enum: Data1Enum.values()){
    		if(data1Enum.getStatus()==idx){
    			return data1Enum;
    		}
    	}
    	return null;
    }

}
