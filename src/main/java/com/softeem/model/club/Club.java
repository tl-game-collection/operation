package com.softeem.model.club;

public class Club {
	public Long playerUid;//圈主id
    public Long clubUid;//圈id
    public String clubName;//圈id
    public Long floorUid;//楼层id
    public String floorName;
    public int type; //1.2人场 2.3人场
    public int robotDeskMin;    //最小桌子数
    public int robotDeskMax;    //最大桌子数
    public int randomTime;      //随机时间
    
    public int curRobotDesk2;// 当前已有2人场机器人桌子数量
    public int curRobotDesk3;// 当前已有3人场机器人桌子数量
    public int curRobotDesk4;// 当前已有4人场机器人桌子数量
    
    
    public String sign;
	public Long getPlayerUid() {
		return playerUid;
	}
	public void setPlayerUid(Long playerUid) {
		this.playerUid = playerUid;
	}
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
	public Long getFloorUid() {
		return floorUid;
	}
	public void setFloorUid(Long floorUid) {
		this.floorUid = floorUid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRobotDeskMin() {
		return robotDeskMin;
	}
	public void setRobotDeskMin(int robotDeskMin) {
		this.robotDeskMin = robotDeskMin;
	}
	public int getRobotDeskMax() {
		return robotDeskMax;
	}
	public void setRobotDeskMax(int robotDeskMax) {
		this.robotDeskMax = robotDeskMax;
	}
	public int getRandomTime() {
		return randomTime;
	}
	public void setRandomTime(int randomTime) {
		this.randomTime = randomTime;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public int getCurRobotDesk2() {
		return curRobotDesk2;
	}
	public void setCurRobotDesk2(int curRobotDesk2) {
		this.curRobotDesk2 = curRobotDesk2;
	}
	public int getCurRobotDesk3() {
		return curRobotDesk3;
	}
	public void setCurRobotDesk3(int curRobotDesk3) {
		this.curRobotDesk3 = curRobotDesk3;
	}
	public int getCurRobotDesk4() {
		return curRobotDesk4;
	}
	public void setCurRobotDesk4(int curRobotDesk4) {
		this.curRobotDesk4 = curRobotDesk4;
	}
	
	
	
    
    


}
