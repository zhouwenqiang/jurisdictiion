package com.jurisdiction.inforeport.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author zwq
 * @email qq@qq.com
 * @date 2018-06-06 13:59:16
 */
public class Marketing implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long imeiId;
	//
	private Long meansId;
	//
	private Integer status;
	//
	private String remarks;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setImeiId(Long imeiId) {
		this.imeiId = imeiId;
	}
	/**
	 * 获取：
	 */
	public Long getImeiId() {
		return imeiId;
	}
	/**
	 * 设置：
	 */
	public void setMeansId(Long meansId) {
		this.meansId = meansId;
	}
	/**
	 * 获取：
	 */
	public Long getMeansId() {
		return meansId;
	}
	/**
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：
	 */
	public String getRemarks() {
		return remarks;
	}
}
