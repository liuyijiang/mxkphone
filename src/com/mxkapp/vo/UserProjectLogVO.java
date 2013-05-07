package com.mxkapp.vo;

import java.io.Serializable;

public class UserProjectLogVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7479298378701202193L;
	private String id;
	private String projectid;
	private String projectName;
	private String loginfo;
	private String createTime;
	private String exeTime;//执行时间
	private String userid;
	
	private String logbl;//计划完成总计划的比例
	private String workstutic;// 状态 今天完成的评级
	private String logfback;//完成 f bcak
	private String fbackbl;//实际完成总计划的比例
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getLoginfo() {
		return loginfo;
	}
	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getExeTime() {
		return exeTime;
	}
	public void setExeTime(String exeTime) {
		this.exeTime = exeTime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLogbl() {
		return logbl;
	}
	public void setLogbl(String logbl) {
		this.logbl = logbl;
	}
	public String getWorkstutic() {
		return workstutic;
	}
	public void setWorkstutic(String workstutic) {
		this.workstutic = workstutic;
	}
	public String getLogfback() {
		return logfback;
	}
	public void setLogfback(String logfback) {
		this.logfback = logfback;
	}
	public String getFbackbl() {
		return fbackbl;
	}
	public void setFbackbl(String fbackbl) {
		this.fbackbl = fbackbl;
	}
	
	
	
}
