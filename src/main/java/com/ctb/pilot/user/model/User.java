package com.ctb.pilot.user.model;

import java.util.Date;

public class User {

	private int sequence;
	private String userId;
	private String password;
	private String nickname;
	private String countryCode;
	private String role;
	private Date joinDate;
	private boolean deleted;

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "User [sequence=" + sequence + ", userId=" + userId
				+ ", password=" + password + ", nickname=" + nickname
				+ ", countryCode=" + countryCode + ", role=" + role
				+ ", joinDate=" + joinDate + ", deleted=" + deleted + "]";
	}

}
