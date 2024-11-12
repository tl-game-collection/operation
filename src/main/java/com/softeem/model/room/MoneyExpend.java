package com.softeem.model.room;
/**
 *房卡消耗
 * @author ysy
 *
 */
public class MoneyExpend {
	private Long playerUid;
	private String name;
	private Float money;
	private Float summoney;
	private float hallmoney;//大厅
	private float clubmoney;//亲友圈
	private float arenamoney;//比赛 场
    private long clubUid;//俱乐部id
    private String clubName;//俱乐部id
	private int  roomType;//类型（0别的、1大厅、2亲友圈、3比赛场）
	private String time;
	private int expendType;//消耗 或增长类型
	private Long expendTime;
	public Long getPlayerUid() {
		return playerUid;
	}
	public void setPlayerUid(Long playerUid) {
		this.playerUid = playerUid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public float getHallmoney() {
		if(roomType==3) {
			return this.money;
		}
		return hallmoney;
	}
	public void setHallmoney(float hallmoney) {
		this.hallmoney = hallmoney;
	}
	public float getClubmoney() {
		if(roomType==4) {
			return this.money;
		}
		
		return clubmoney;
	}
	public void setClubmoney(float clubmoney) {
		this.clubmoney = clubmoney;
	}
	public float getArenamoney() {
		if(roomType==5) {
			return this.money;
		}
		return arenamoney;
	}
	public void setArenamoney(float arenamoney) {
		this.arenamoney = arenamoney;
	}
	public long getClubUid() {
		return clubUid;
	}
	public void setClubUid(long clubUid) {
		this.clubUid = clubUid;
	}
	public int getRoomType() {
		return roomType;
	}
	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getExpendTime() {
		return expendTime;
	}
	public void setExpendTime(Long expendTime) {
		this.expendTime = expendTime;
	}
	public Float getSummoney() {
		return summoney;
	}
	public void setSummoney(Float summoney) {
		this.summoney = summoney;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public int getExpendType() {
		return expendType;
	}
	public void setExpendType(int expendType) {
		this.expendType = expendType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
