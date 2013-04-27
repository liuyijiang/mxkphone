package com.mxkapp.vo;

import java.io.Serializable;

public class UserProjectVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6797760171248722331L;
	private String id;
	private String name;
	private String ownerid;
	private String ownername;

	private String type;//��������
	private String projectType;//��������
	private String proxyWorkId;
	
	private String userid;
	private String startDate;
	private String endDate;
	private String progress;
	private String desc;// ����
	private String ispublic;// 1�ǹ��� 0�ǲ�����
    
	private String planDay;//�ƻ�����
	private String comments;
	private String compromise;//
	private String plans;//�����ٸ�����
	
	private String pdfUrl;
	private String excelUrl;
	private String materExcUrl;
	
	private String leavel;
	private String leavelnum;
	private String highMoney; 
	
	private String canLeavel;//��������
	private String canMoney;//���Զ���
	
	private String remind;//�������� 1 �ǿ��� 0�ǲ�����

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProxyWorkId() {
		return proxyWorkId;
	}

	public void setProxyWorkId(String proxyWorkId) {
		this.proxyWorkId = proxyWorkId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIspublic() {
		return ispublic;
	}

	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}

	public String getPlanDay() {
		return planDay;
	}

	public void setPlanDay(String planDay) {
		this.planDay = planDay;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCompromise() {
		return compromise;
	}

	public void setCompromise(String compromise) {
		this.compromise = compromise;
	}

	public String getPlans() {
		return plans;
	}

	public void setPlans(String plans) {
		this.plans = plans;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public String getExcelUrl() {
		return excelUrl;
	}

	public void setExcelUrl(String excelUrl) {
		this.excelUrl = excelUrl;
	}

	public String getMaterExcUrl() {
		return materExcUrl;
	}

	public void setMaterExcUrl(String materExcUrl) {
		this.materExcUrl = materExcUrl;
	}

	public String getLeavel() {
		return leavel;
	}

	public void setLeavel(String leavel) {
		this.leavel = leavel;
	}

	public String getLeavelnum() {
		return leavelnum;
	}

	public void setLeavelnum(String leavelnum) {
		this.leavelnum = leavelnum;
	}

	public String getHighMoney() {
		return highMoney;
	}

	public void setHighMoney(String highMoney) {
		this.highMoney = highMoney;
	}

	public String getCanLeavel() {
		return canLeavel;
	}

	public void setCanLeavel(String canLeavel) {
		this.canLeavel = canLeavel;
	}

	public String getCanMoney() {
		return canMoney;
	}

	public void setCanMoney(String canMoney) {
		this.canMoney = canMoney;
	}

	public String getRemind() {
		return remind;
	}

	public void setRemind(String remind) {
		this.remind = remind;
	}

    
	
	
}
