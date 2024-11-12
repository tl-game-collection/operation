package com.softeem.model.club;
/**
 * 查询
 * @author MyPC
 *
 */
public class GetClubList {
	    private int page;
	    private int pageSize;
	    private Long clubUid;
	    private String sign;     // md5(page + pageSize + clubUid + key)
	    @Override
	    public String toString() {
	        return "GetClubList{" +
	                "page=" + page +
	                ", pageSize=" + pageSize +
	                ", clubUid=" + clubUid +
	                ", sign='" + sign + '\'' +
	                '}';
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
		
		
		public Long getClubUid() {
			return clubUid;
		}
		public void setClubUid(Long clubUid) {
			this.clubUid = clubUid;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
	    
	    

}
