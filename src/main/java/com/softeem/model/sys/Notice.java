 package com.softeem.model.sys;

import java.math.BigInteger;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName("notice")
public class Notice {
    @TableId(type = IdType.AUTO)
     private Integer id;
    @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String name;
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private Integer type;
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String context;
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private Boolean status;
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getType() {
        return type;
    }
    public String getContext() {
        return context;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
     
     
     
     
     
    
    
     
     
     
  
     
     
    
     
     

}
