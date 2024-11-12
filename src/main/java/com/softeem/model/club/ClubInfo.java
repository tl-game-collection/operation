package com.softeem.model.club;

public class ClubInfo {
	private Long uid;
	private String name;
	private Integer clubType;//1亲友圈,2比赛场
	private int playerNum;
	private long gold;//金币
	private long rewardValue;//奖励分
	private long moneyExpend;//房卡消耗
	private long moneySurplus;//房卡剩余
	private boolean syncope;//是否合圈
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getClubType() {
		return clubType;
	}
	public void setClubType(Integer clubType) {
		this.clubType = clubType;
	}
	public int getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	public long getGold() {
		return gold;
	}
	public void setGold(long gold) {
		this.gold = gold;
	}
	public long getRewardValue() {
		return rewardValue;
	}
	public void setRewardValue(long rewardValue) {
		this.rewardValue = rewardValue;
	}
	public long getMoneyExpend() {
		return moneyExpend;
	}
	public void setMoneyExpend(long moneyExpend) {
		this.moneyExpend = moneyExpend;
	}
	public long getMoneySurplus() {
		return moneySurplus;
	}
	public void setMoneySurplus(long moneySurplus) {
		this.moneySurplus = moneySurplus;
	}
	public boolean isSyncope() {
		return syncope;
	}
	public void setSyncope(boolean syncope) {
		this.syncope = syncope;
	}
	
	

	
	
	
	
	
	
	
	
	

}
