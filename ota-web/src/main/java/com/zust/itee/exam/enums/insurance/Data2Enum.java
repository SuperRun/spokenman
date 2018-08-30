package com.zust.itee.exam.enums.insurance;

/**
 * Created by liy on 2016/12/7.
 * 制动状况
 */
public enum Data2Enum {

    BEFORE((short)0, "制动前"),
    AFTER((short)1, "制动后");

    private short status;

    private String info;

    Data2Enum(short status, String info) {
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

    public static Data2Enum getIndexOf(Short idx){
    	for(Data2Enum data2Enum: Data2Enum.values()){
    		if(data2Enum.getStatus()==idx){
    			return data2Enum;
    		}
    	}
    	return null;
    }

}
