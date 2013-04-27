package com.mxkapp.vo;

import java.io.Serializable;

public class UserProjectPlanVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5793961193333493847L;
	private String id;
	private String userId;
	private String userimage;
	
	private String username;
	private String projectId;
	private String projectName;
	private String createTime;//时间
	private String createDay;//日期
	private String imageUrl;
	private String bigImageUrl;
	private String minImageUrl;
	private String info;//文本描述
	private String androidinfo;//在android上显示
	private String pg;//进度
	private String commints;//评论
	private String share;
    private String like;
	private String type;//shell base;//是分享的还是自己本来的
	private String ownerid;
	private String ownername;
	private String ownerimage;
	private String planfrom;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserimage() {
		return userimage;
	}
	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateDay() {
		return createDay;
	}
	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getBigImageUrl() {
		return bigImageUrl;
	}
	public void setBigImageUrl(String bigImageUrl) {
		this.bigImageUrl = bigImageUrl;
	}
	public String getMinImageUrl() {
		return minImageUrl;
	}
	public void setMinImageUrl(String minImageUrl) {
		this.minImageUrl = minImageUrl;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getAndroidinfo() {
		return androidinfo;
	}
	public void setAndroidinfo(String androidinfo) {
		this.androidinfo = androidinfo;
	}
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
	}
	public String getCommints() {
		return commints;
	}
	public void setCommints(String commints) {
		this.commints = commints;
	}
	public String getShare() {
		return share;
	}
	public void setShare(String share) {
		this.share = share;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getOwnerimage() {
		return ownerimage;
	}
	public void setOwnerimage(String ownerimage) {
		this.ownerimage = ownerimage;
	}
	public String getPlanfrom() {
		return planfrom;
	}
	public void setPlanfrom(String planfrom) {
		this.planfrom = planfrom;
	}
	
    	
	
}
