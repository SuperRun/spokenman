package com.zust.itee.exam.enums.insurance;

/**
 * Created by liy on 2016/12/7.
 * 天气状况
 */
public enum Data4Enum {

    SUNNY((short)0, "晴天"),
    RAIN((short)1, "雨天"),
    SNOW((short)2,"雪天"),
    CLOUDY((short)3,"阴天"),
    HAZE((short)4,"雾霾");


    private short status;

    private String info;

    Data4Enum(short status, String info) {
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

    public static Data4Enum getIndexOf(Short idx){
    	for(Data4Enum data4Enum: Data4Enum.values()){
    		if(data4Enum.getStatus()==idx){
    			return data4Enum;
    		}
    	}
    	return null;
    }
}
