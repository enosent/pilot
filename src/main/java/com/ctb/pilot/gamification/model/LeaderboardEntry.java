package com.ctb.pilot.gamification.model;

public class LeaderboardEntry {

	private int rank;
	private int userSequence;
	private String nickname;
	private int point;
	private String countryCode;

	public LeaderboardEntry() {
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getUserSequence() {
		return userSequence;
	}

	public void setUserSequence(int userSequence) {
		this.userSequence = userSequence;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "LeaderboardEntry [rank=" + rank + ", userSequence="
				+ userSequence + ", nickname=" + nickname + ", point=" + point
				+ ", countryCode=" + countryCode + "]";
	}

}
