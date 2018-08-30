package com.zust.itee.exam.dto.exam;

import java.util.ArrayList;
import java.util.List;

import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.enums.SexEnum;
import com.zust.itee.exam.util.MyDateUtil;

/**
 * 点击驾驶员姓名模态框显示内容
 *
 * @author sdy
 */
public class DriverDetailInfo {

    //id
    private int driverId;

    //姓名
    private String name;

    //性别
    private String sex;

    //年龄
    private int age;

    //联系方式
    private String tel;

    //家庭住址
    private String address;

    public DriverDetailInfo() {
    }

    public DriverDetailInfo(Tdriver tdriver) {
        super();
        this.driverId = tdriver.getId();
        this.name = tdriver.getName();
        this.sex = SexEnum.getIndexOf(tdriver.getGender()).getInfo();
        try {
            this.age = MyDateUtil.countAge(tdriver.getBirth());
        } catch (Exception e) {
            this.age = 0;
        }
        this.tel = tdriver.getMobile();
        this.address = tdriver.getAddress();
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static List<DriverDetailInfo> transTdriversToDriverDetailInfos(List<Tdriver> tdrivers) {
        List<DriverDetailInfo> driverDetailInfos = new ArrayList<DriverDetailInfo>();
        for (Tdriver tdriver : tdrivers) {
            DriverDetailInfo detailInfo = new DriverDetailInfo(tdriver);
            driverDetailInfos.add(detailInfo);
        }
        return driverDetailInfos;
    }
}
