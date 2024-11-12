package com.softeem.model.club;

public class GetClubRobotNumResp {
	    private long clubUid;
	    private long floorUid;
	    private long playerUid;
	    private int curRobotDesk2;// 当前已有2人场机器人桌子数量
	    private int curRobotDesk3;// 当前已有3人场机器人桌子数量
	    private int curRobotDesk4;// 当前已有4人场机器人桌子数量
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
		public long getPlayerUid() {
			return playerUid;
		}
		public void setPlayerUid(long playerUid) {
			this.playerUid = playerUid;
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
