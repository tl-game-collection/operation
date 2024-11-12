package com.softeem.model.club;

public class GetClubMemberDownLine {
		private int page;
		private int pageSize;
	    private long clubUid;
	    private long managerUid;
	    private  String sign; // md5(clubUid + managerUid + key)
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
		public long getManagerUid() {
			return managerUid;
		}
		public void setManagerUid(long managerUid) {
			this.managerUid = managerUid;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
	    
	    
   
}
