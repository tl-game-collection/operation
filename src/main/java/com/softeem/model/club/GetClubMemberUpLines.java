package com.softeem.model.club;

public class GetClubMemberUpLines {
		private int page;
		private int pageSize;
		private long clubUid;
		private long playerUid;
		private String sign;//clubUid + playerUid + key
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public long getClubUid() {
			return clubUid;
		}
		public void setClubUid(long clubUid) {
			this.clubUid = clubUid;
		}
		public long getPlayerUid() {
			return playerUid;
		}
		public void setPlayerUid(long playerUid) {
			this.playerUid = playerUid;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		
		
		
	
		
}
