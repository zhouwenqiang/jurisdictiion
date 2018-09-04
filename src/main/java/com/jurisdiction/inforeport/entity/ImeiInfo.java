package com.jurisdiction.inforeport.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 从能力平台获取的IMEI表
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-17 11:33:36
 */
public class ImeiInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer imeiId;
	//
	private String modelCode;
	//
	private String bandName;
	//
	private String dateTime;
	//
	private String imeiNo;
	//
	private String status;
	//
	private String typeCode;
	//
	private Date createDate;
	//
	private Integer enable;
	//
	private Long groupId;
	// 组名
	private  String name;

	/**
	 * 设置：
	 */
	public void setImeiId(Integer imeiId) {
		this.imeiId = imeiId;
	}
	/**
	 * 获取：
	 */
	public Integer getImeiId() {
		return imeiId;
	}
	/**
	 * 设置：
	 */
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	/**
	 * 获取：
	 */
	public String getModelCode() {
		return modelCode;
	}
	/**
	 * 设置：
	 */
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	/**
	 * 获取：
	 */
	public String getBandName() {
		return bandName;
	}
	/**
	 * 设置：
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	/**
	 * 获取：
	 */
	public String getDateTime() {
		return dateTime;
	}
	/**
	 * 设置：
	 */
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	/**
	 * 获取：
	 */
	public String getImeiNo() {
		return imeiNo;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * 获取：
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	/**
	 * 获取：
	 */
	public Integer getEnable() {
		return enable;
	}
	/**
	 * 设置：
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	/**
	 * 获取：
	 */
	public Long getGroupId() {
		return groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
