package com.zust.itee.exam.dto;

import java.util.Date;

/**
 * 全部驾驶员首页
 * @author liy
 *
 */
public class DriverIndex {

	private Integer id;
	private String org_name;
	private String name;
	private String birthPlace;
	private Date birth;
	private String gen;
	private String sfzNo;
	private String photo;
	private String address;
	private String postcode;
	private String mobile;
	private String password;
	private String email;
	private String emergencyContact;
	private String emergencyContactMobile;
	private String driveLicenceNo;
	private String driveLincencePhoto;
	private Date driveLicenceStartTime;
	private Date driveLicenceEndTime;
	private String safeCentificateNo;
	private String safeCentificatePhoto;
	private Date safeCentificateStartTime;
	private Date safeCentificateEndTime;
	private short status;			//-2 不可用-1->删除；0->待审核；1->可用 (可能会增加状态）

	public DriverIndex() {
		// TODO Auto-generated constructor stub
	}



	public DriverIndex(Integer id,String org_name, String name, String birthPlace,
			String gen, String sfzNo, String mobile, String driveLicenceNo) {
		this.id = id;
		this.org_name = org_name;
		this.name = name;
		this.birthPlace = birthPlace;
		this.gen = gen;
		this.sfzNo = sfzNo;
		this.mobile = mobile;
		this.driveLicenceNo = driveLicenceNo;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

	public String getSfzNo() {
		return sfzNo;
	}

	public void setSfzNo(String sfzNo) {
		this.sfzNo = sfzNo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyContactMobile() {
		return emergencyContactMobile;
	}

	public void setEmergencyContactMobile(String emergencyContactMobile) {
		this.emergencyContactMobile = emergencyContactMobile;
	}

	public String getDriveLicenceNo() {
		return driveLicenceNo;
	}

	public void setDriveLicenceNo(String driveLicenceNo) {
		this.driveLicenceNo = driveLicenceNo;
	}

	public String getDriveLincencePhoto() {
		return driveLincencePhoto;
	}

	public void setDriveLincencePhoto(String driveLincencePhoto) {
		this.driveLincencePhoto = driveLincencePhoto;
	}

	public Date getDriveLicenceStartTime() {
		return driveLicenceStartTime;
	}

	public void setDriveLicenceStartTime(Date driveLicenceStartTime) {
		this.driveLicenceStartTime = driveLicenceStartTime;
	}

	public Date getDriveLicenceEndTime() {
		return driveLicenceEndTime;
	}

	public void setDriveLicenceEndTime(Date driveLicenceEndTime) {
		this.driveLicenceEndTime = driveLicenceEndTime;
	}

	public String getSafeCentificateNo() {
		return safeCentificateNo;
	}

	public void setSafeCentificateNo(String safeCentificateNo) {
		this.safeCentificateNo = safeCentificateNo;
	}

	public String getSafeCentificatePhoto() {
		return safeCentificatePhoto;
	}

	public void setSafeCentificatePhoto(String safeCentificatePhoto) {
		this.safeCentificatePhoto = safeCentificatePhoto;
	}

	public Date getSafeCentificateStartTime() {
		return safeCentificateStartTime;
	}

	public void setSafeCentificateStartTime(Date safeCentificateStartTime) {
		this.safeCentificateStartTime = safeCentificateStartTime;
	}

	public Date getSafeCentificateEndTime() {
		return safeCentificateEndTime;
	}

	public void setSafeCentificateEndTime(Date safeCentificateEndTime) {
		this.safeCentificateEndTime = safeCentificateEndTime;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DriverIndex{" +
				"id=" + id +
				", org_name='" + org_name + '\'' +
				", name='" + name + '\'' +
				", birthPlace='" + birthPlace + '\'' +
				", birth=" + birth +
				", gen='" + gen + '\'' +
				", sfzNo='" + sfzNo + '\'' +
				", photo='" + photo + '\'' +
				", address='" + address + '\'' +
				", postcode='" + postcode + '\'' +
				", mobile='" + mobile + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", emergencyContact='" + emergencyContact + '\'' +
				", emergencyContactMobile='" + emergencyContactMobile + '\'' +
				", driveLicenceNo='" + driveLicenceNo + '\'' +
				", driveLincencePhoto='" + driveLincencePhoto + '\'' +
				", driveLicenceStartTime=" + driveLicenceStartTime +
				", driveLicenceEndTime=" + driveLicenceEndTime +
				", safeCentificateNo='" + safeCentificateNo + '\'' +
				", safeCentificatePhoto='" + safeCentificatePhoto + '\'' +
				", safeCentificateStartTime=" + safeCentificateStartTime +
				", safeCentificateEndTime=" + safeCentificateEndTime +
				", status=" + status +
				'}';
	}
}
