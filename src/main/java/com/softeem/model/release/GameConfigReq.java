 package com.softeem.model.release;

import java.math.BigInteger;

public class GameConfigReq {
     private int phone;
     private String uuid;
     private int bizChannel=-1;
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public int getBizChannel() {
        return bizChannel;
    }
    public void setBizChannel(int bizChannel) {
        this.bizChannel = bizChannel;
    }
     
     

     


}
