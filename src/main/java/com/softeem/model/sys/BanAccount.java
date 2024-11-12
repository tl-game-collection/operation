package com.softeem.model.sys;

public class BanAccount {
	    private long playerUid;
	    private boolean ban;
	    private  String sign;
		public long getPlayerUid() {
			return playerUid;
		}
		public void setPlayerUid(long playerUid) {
			this.playerUid = playerUid;
		}
		public boolean isBan() {
			return ban;
		}
		public void setBan(boolean ban) {
			this.ban = ban;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
	    
	    


}
