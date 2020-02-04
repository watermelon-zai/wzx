package com.wzx.springbootblog.utils;

import java.io.Serializable;

/**
 * 所有实体类的父类（需要分页情况下）
 * @author Administrator
 *  select * from user_info  limit 2,3
 *  limit start,length
 */
public class BaseBean implements Serializable{

	/**
	 * 起始下标
	 */
	private Integer start;
	
	/**
	 * 查询的个数
	 */
	private Integer length;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	
	
}
