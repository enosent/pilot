package com.ctb.pilot.chat.model;

import java.util.Arrays;
import java.util.Date;

public class Message {

	private int sequence;
	private Date createdTime;
	private String formattedCreatedTime;
	private int userSequence;
	private String nickname;
	private String message;
	private String language;
	private String facebookUsername;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getFormattedCreatedTime() {
		return formattedCreatedTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
		this.formattedCreatedTime = String.format("%TF %TT", createdTime,
				createdTime);
	}

	public int getUserSequence() {
		return userSequence;
	}

	public void setUserSequence(int userSequence) {
		this.userSequence = userSequence;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFacebookUsername() {
		return facebookUsername;
	}

	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
	}

	@Override
	public String toString() {
		return "Message [sequence=" + sequence + ", createdTime=" + createdTime
				+ ", formattedCreatedTime=" + formattedCreatedTime
				+ ", userSequence=" + userSequence + ", nickname=" + nickname
				+ ", message=" + message + ", language=" + language
				+ ", facebookUsername=" + facebookUsername + "]";
	}

}
