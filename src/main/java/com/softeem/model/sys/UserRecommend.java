package com.softeem.model.sys;

public class UserRecommend {
	    public long uid;            // 自己的ID
	    public long targetUid;      // 目标ID
	    public String sign; // md5(uid + targetUid + key)
}
