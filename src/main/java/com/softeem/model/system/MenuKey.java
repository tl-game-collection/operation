package com.softeem.model.system;

import java.io.Serializable;

public class MenuKey implements Serializable {
	    private Integer id;
	    private String title;
	    private String href;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getHref() {
			return href;
		}
		public void setHref(String href) {
			this.href = href;
		}
	    
	    

}
