package com.softeem.model.system;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable {
	
	  private MenuKey key;
	    private Integer pid;
	    private String icon;
	    private String target;
	    private Integer sort;
	    private Boolean status;
	    private String remark;
	    private Date create_at;
	    private Date update_at;
	    private Date delete_at;
		public MenuKey getKey() {
			return key;
		}
		public void setKey(MenuKey key) {
			this.key = key;
		}
		public Integer getPid() {
			return pid;
		}
		public void setPid(Integer pid) {
			this.pid = pid;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getTarget() {
			return target;
		}
		public void setTarget(String target) {
			this.target = target;
		}
		public Integer getSort() {
			return sort;
		}
		public void setSort(Integer sort) {
			this.sort = sort;
		}
		public Boolean getStatus() {
			return status;
		}
		public void setStatus(Boolean status) {
			this.status = status;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Date getCreate_at() {
			return create_at;
		}
		public void setCreate_at(Date create_at) {
			this.create_at = create_at;
		}
		public Date getUpdate_at() {
			return update_at;
		}
		public void setUpdate_at(Date update_at) {
			this.update_at = update_at;
		}
		public Date getDelete_at() {
			return delete_at;
		}
		public void setDelete_at(Date delete_at) {
			this.delete_at = delete_at;
		}
}
