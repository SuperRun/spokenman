package com.zust.itee.exam.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * DataDictionary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_dictionary")
public class DataDictionary implements java.io.Serializable {

	// Fields

	private Long id;
	private DataDictionary dataDictionaryByCtrlId;
	private DataDictionary dataDictionaryByUplink;
	private String remark;
	private Long actionBy;
	private Timestamp actionTime;
	private String codeType;
	private String subCodeType;
	private int level;
	private Long globalSeq;
	private String valueStr;
	private String valueRule;
	private String link;
	private String picPath;


	private Long rank;
	private Short status;
	private List<DataDictionary> dataDictionariesForUplink = new ArrayList<DataDictionary>(0);

	// Constructors

	/** default constructor */
	public DataDictionary() {
	}

	/** full constructor */
	public DataDictionary(DataDictionary dataDictionaryByCtrlId, DataDictionary dataDictionaryByUplink, String remark, Long actionBy, Timestamp actionTime, String codeType,int level,Long globalSeq, String valueStr, String valueRule,String link, Long rank, Short status, List<DataDictionary> dataDictionariesForUplink) {
		this.dataDictionaryByCtrlId = dataDictionaryByCtrlId;
		this.dataDictionaryByUplink = dataDictionaryByUplink;
		this.remark = remark;
		this.actionBy = actionBy;
		this.actionTime = actionTime;
		this.codeType = codeType;
		this.level=level;
		this.globalSeq=globalSeq;
		this.valueStr = valueStr;
		this.valueRule = valueRule;
		this.link=link;
		this.rank = rank;
		this.status = status;
		this.dataDictionariesForUplink = dataDictionariesForUplink;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ctrl_id")
	public DataDictionary getDataDictionaryByCtrlId() {
		return this.dataDictionaryByCtrlId;
	}

	public void setDataDictionaryByCtrlId(DataDictionary dataDictionaryByCtrlId) {
		this.dataDictionaryByCtrlId = dataDictionaryByCtrlId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uplink")
	public DataDictionary getDataDictionaryByUplink() {
		return this.dataDictionaryByUplink;
	}

	public void setDataDictionaryByUplink(DataDictionary dataDictionaryByUplink) {
		this.dataDictionaryByUplink = dataDictionaryByUplink;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "action_by")
	public Long getActionBy() {
		return this.actionBy;
	}

	public void setActionBy(Long actionBy) {
		this.actionBy = actionBy;
	}

	@Column(name = "action_time", length = 19)
	public Timestamp getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Timestamp actionTime) {
		this.actionTime = actionTime;
	}

	@Column(name = "code_type", length = 200)
	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	@Column(name = "sub_code_type", length = 200)
	public String getSubCodeType() {
		return subCodeType;
	}

	public void setSubCodeType(String subCodeType) {
		this.subCodeType = subCodeType;
	}
	@Column(name = "pic_path", length = 255)
	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	@Column(name = "value_str", length = 100)
	public String getValueStr() {
		return this.valueStr;
	}

	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}

	@Column(name = "value_rule", length = 200)
	public String getValueRule() {
		return this.valueRule;
	}

	public void setValueRule(String valueRule) {
		this.valueRule = valueRule;
	}
	@Column(name = "link", length = 255)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "rank")
	public Long getRank() {
		return this.rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	@Column(name = "level")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	@Column(name = "global_seq")
	public Long getGlobalSeq() {
		return globalSeq;
	}

	public void setGlobalSeq(Long globalSeq) {
		this.globalSeq = globalSeq;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dataDictionaryByUplink")
	@OrderBy(value="globalSeq asc")
	public List<DataDictionary> getDataDictionariesForUplink() {
		return this.dataDictionariesForUplink;
	}

	public void setDataDictionariesForUplink(List<DataDictionary> dataDictionariesForUplink) {
		this.dataDictionariesForUplink = dataDictionariesForUplink;
	}

	public void makeGlobalSeq() {
		// TODO Auto-generated method stub
		if(this.dataDictionaryByUplink==null)
			this.globalSeq=this.id*10000+level*100;
		else
			this.globalSeq=this.dataDictionaryByUplink.getId()*10000+level*100;
		if(rank!=null)
			globalSeq+=rank;
	}

}