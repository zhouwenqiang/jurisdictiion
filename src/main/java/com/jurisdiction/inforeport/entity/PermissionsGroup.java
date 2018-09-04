package com.jurisdiction.inforeport.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 数据权限组
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-15 14:33:08
 */
public class PermissionsGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long groupId;
	//上级ID，一级ID为0
	private Long parentId;
	//上级名称
	private String name;
	//排序
	private Integer orderNum;
	//创建时间
	private Date createDate;
	//更新时间
	private Date updateDate;
	//是否删除  -1：已删除  1：正常
	private Integer delFlag;

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
	/**
	 * 设置：上级ID，一级ID为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级ID，一级ID为0
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：上级名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：上级名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：是否删除  -1：已删除  1：正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除  -1：已删除  1：正常
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
}
