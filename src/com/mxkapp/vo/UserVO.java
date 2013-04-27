package com.mxkapp.vo;

import java.io.Serializable;

import android.graphics.Bitmap;

public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3022510278485653841L;
	
	private String id;
	private String name;
	private String email;
	private String password;
	private String createTime;
	private String hobby;
	private String image;
	private String sex;
	private String info;
	private String supposeproxy; // 1支持代工      0不支持代工
	private String province;//省
	private String address;//地址
	private String followors;//粉丝
	private String focus;//关注
	private String projects;//项目数
	private String messages;//消息数量
	
	private Bitmap imageBitMap;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSupposeproxy() {
		return supposeproxy;
	}
	public void setSupposeproxy(String supposeproxy) {
		this.supposeproxy = supposeproxy;
	}
	public String getFollowors() {
		return followors;
	}
	public void setFollowors(String followors) {
		this.followors = followors;
	}
	public String getFocus() {
		return focus;
	}
	public void setFocus(String focus) {
		this.focus = focus;
	}
	public String getProjects() {
		return projects;
	}
	public void setProjects(String projects) {
		this.projects = projects;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public Bitmap getImageBitMap() {
		return imageBitMap;
	}
	public void setImageBitMap(Bitmap imageBitMap) {
		this.imageBitMap = imageBitMap;
	}

	
	
}
