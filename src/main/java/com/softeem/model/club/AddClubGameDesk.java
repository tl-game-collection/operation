package com.softeem.model.club;

public class AddClubGameDesk {
	 	public long playerUid;
	    public long clubUid;
	    public long floorUid;
	    public int type; //1.2人场 2.3人场
	    public int robotDeskMin;    //最小桌子数
	    public int robotDeskMax;    //最大桌子数
	    public int randomTime;      //随机时间
	    public String sign;                     // md5(playerUid + clubUid + floorUid + type + robotDeskMin + robotDeskMax + randomTime + key)
		public long getPlayerUid() {
			return playerUid;
		}
		public void setPlayerUid(long playerUid) {
			this.playerUid = playerUid;
		}
		public long getClubUid() {
			return clubUid;
		}
		public void setClubUid(long clubUid) {
			this.clubUid = clubUid;
		}
		public long getFloorUid() {
			return floorUid;
		}
		public void setFloorUid(long floorUid) {
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
	    
	    


}
