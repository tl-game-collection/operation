 package com.softeem.model.sys;

import java.math.BigInteger;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName("customer")
public class Customer {
    @TableId(type = IdType.AUTO)
     private BigInteger id;
    @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String context;
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String qq;
     private String remark;
     private Date createTime; 
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String url;//地址
     private boolean status ;// 0 可用，1不可用
    public BigInteger getId() {
        return id;
    }
    public String getContext() {
        return context;
    }
    public String getQq() {
        return qq;
    }
  
    public String getUrl() {
        return url;
    }
    
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public boolean isStatus() {
        return status;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    
     
     
     
  
     
     
    
     
     

}
