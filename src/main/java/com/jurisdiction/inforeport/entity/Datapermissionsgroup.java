package com.jurisdiction.inforeport.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户与数据权限组
 * 
 * @author zwq
 * @email 332368523@qq.com
 * @date 2018-05-17 11:22:43
 */
public class Datapermissionsgroup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户ID
	private Long userId;
	//数据权限ID
	private Long groupId;
	//创建时间
	private Date createDate;
	//选中状态：半选 还是全选
	private int selectedState;
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
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：数据权限ID
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	/**
	 * 获取：数据权限ID
	 */
	public Long getGroupId() {
		return groupId;
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

	public int getSelectedState() {
		return selectedState;
	}

	public void setSelectedState(int selectedState) {
		this.selectedState = selectedState;
	}
}
