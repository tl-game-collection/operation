package com.softeem.model.room;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 玩家信息
 * @author ysy
 *
 */


public class PlayerInfo {
	 public long uid;                // 玩家ID
     public String name;             // 玩家名称
     public byte type;               // 登录类型
     public String phone;            // 手机号登录就有手机号码否则为 null
     public int diamond;             // 房卡
     public long referrerUid;        // 推荐人ID
     public String recommend;//推荐信息
     public long createTimestamp;         // 注册时间
     public String createTim;
     public long lastLoginTime;      // 最后登录时间
     public String lastLoginIp;      // 最后登录IP
     public int state;               // 状态, 0: 正常, 1: 删除, 2: 封号
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getDiamond() {
		return diamond;
	}
	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}
	public long getReferrerUid() {
		return referrerUid;
	}
	public void setReferrerUid(long referrerUid) {
		this.referrerUid = referrerUid;
	}
	

	public long getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(long createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getCreateTim() {
		return createTim;
	}
	public void setCreateTim(String createTim) {
		this.createTim = createTim;
	}
	
	
	
	
	
	
	
     
     
     
     
     
     

}
