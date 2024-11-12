package com.softeem.model.club;

public class GetClubMemberList {
	    private Long playerUid;
	    private Long clubUid;
	    private Long searchUid;
	    private int page;
	    private int pageSize;
	    private String sign;                     // md5(playerUid + clubUid + searchUid + page + pageSize + key)

	    @Override
	    public String toString() {
	        return "GetClubMemberList{" +
	                "playerUid=" + playerUid +
	                ", clubUid=" + clubUid +
	                ", searchUid=" + searchUid +
	                ", page=" + page +
	                ", pageSize=" + pageSize +
	                ", sign='" + sign + '\'' +
	                '}';
	    }

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

		public Long getSearchUid() {
			return searchUid;
		}

		public void setSearchUid(Long searchUid) {
			this.searchUid = searchUid;
		}

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

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}
	    
	    
	

}
