package com.zust.itee.exam.dto;

import java.io.Serializable;
import java.util.Date;

public class DataDictionaryModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long parentId;
	private String parentValueStr;
	private Long ctrlId;
	private String ctrlValueStr;
	private String remark;
	private Long actionBy;
	private Date actionTime;
	private String codeType;
	private String subCodeType;
	private String valueStr;
	private String valueRule;
	private String link;
	private String picPath;

	private Long rank;
	private Short status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getParentValueStr() {
		return parentValueStr;
	}
	public void setParentValueStr(String parentValueStr) {
		this.parentValueStr = parentValueStr;
	}

	public Long getCtrlId() {
		return ctrlId;
	}
	public void setCtrlId(Long ctrlId) {
		this.ctrlId = ctrlId;
	}
	public String getCtrlValueStr() {
		return ctrlValueStr;
	}
	public void setCtrlValueStr(String ctrlValueStr) {
		this.ctrlValueStr = ctrlValueStr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getActionBy() {
		return actionBy;
	}
	public void setActionBy(Long actionBy) {
		this.actionBy = actionBy;
	}
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getSubCodeType() {
		return subCodeType;
	}
	public void setSubCodeType(String subCodeType) {
		this.subCodeType = subCodeType;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getValueStr() {
		return valueStr;
	}
	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}
	public String getValueRule() {
		return valueRule;
	}
	public void setValueRule(String valueRule) {
		this.valueRule = valueRule;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
