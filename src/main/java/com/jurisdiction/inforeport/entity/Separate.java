package com.jurisdiction.inforeport.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-28 15:35:49
 */
public class Separate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键标示
	private Integer id;
	//获取时间
	private String sctime;
	//绑定的imei
	private String bindImei;
	//终端类型代码
	private Long terminalType;
	//物联网卡号
	private String proMsisdn;
	//备注
	private String remarks;

	/**
	 * 设置：主键标示
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键标示
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：获取时间
	 */
	public void setSctime(String sctime) {
		this.sctime = sctime;
	}
	/**
	 * 获取：获取时间
	 */
	public String getSctime() {
		return sctime;
	}
	/**
	 * 设置：绑定的imei
	 */
	public void setBindImei(String bindImei) {
		this.bindImei = bindImei;
	}
	/**
	 * 获取：绑定的imei
	 */
	public String getBindImei() {
		return bindImei;
	}
	/**
	 * 设置：终端类型代码
	 */
	public void setTerminalType(Long terminalType) {
		this.terminalType = terminalType;
	}
	/**
	 * 获取：终端类型代码
	 */
	public Long getTerminalType() {
		return terminalType;
	}
	/**
	 * 设置：物联网卡号
	 */
	public void setProMsisdn(String proMsisdn) {
		this.proMsisdn = proMsisdn;
	}
	/**
	 * 获取：物联网卡号
	 */
	public String getProMsisdn() {
		return proMsisdn;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
}
