 package com.softeem.model.release;

import java.math.BigInteger;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class White {
    @TableId(type = IdType.AUTO)
     private BigInteger id;
     private String uuid;
     private boolean status ;
    public BigInteger getId() {
        return id;
    }
    public String getUuid() {
        return uuid;
    }
    public boolean isStatus() {
        return status;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
     
     
    
     
     

}
