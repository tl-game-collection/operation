package com.softeem.model.room;

public class ServiceFee {
	private Long clubUid;
	private String clubName;
	private  Long ownerId;//群主id
	private Long playerUid;//受益人id
	private String playerName;
	private Long money;//服务费
	
	private Long optTime;//操作时间
	public Long getClubUid() {
		return clubUid;
	}
	public void setClubUid(Long clubUid) {
		this.clubUid = clubUid;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public Long getPlayerUid() {
		return playerUid;
	}
	public void setPlayerUid(Long playerUid) {
		this.playerUid = playerUid;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public Long getOptTime() {
		return optTime;
	}
	public void setOptTime(Long optTime) {
		this.optTime = optTime;
	}
	
	

}
