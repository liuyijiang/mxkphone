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
	private String exeTime;//ִ��ʱ��
	private String userid;
	
	private String logbl;//�ƻ�����ܼƻ��ı���
	private String workstutic;// ״̬ ������ɵ�����
	private String logfback;//��� f bcak
	private String fbackbl;//ʵ������ܼƻ��ı���
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
