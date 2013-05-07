package com.mxkapp.vo;

import java.io.Serializable;

public class CommentsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7728151940287712120L;
	private String id;
	private String message;// 评论类容
	private String createTime;// 评论时间
	private String createUserId;// 评论人id
	private String beenCommentsId;// 被评论的类型id   加索引
	private String type;// 类型 project plan work 材料
	private String ownerid;// 所有人id
	private String createUserName;// 评论人名
    private String projectid;
	private String userImage;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getBeenCommentsId() {
		return beenCommentsId;
	}
	public void setBeenCommentsId(String beenCommentsId) {
		this.beenCommentsId = beenCommentsId;
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
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	
	
}
