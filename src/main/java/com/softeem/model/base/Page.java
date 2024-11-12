 package com.softeem.model.base;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;

public class Page implements Serializable {
     @ApiModelProperty(value = "页面大小")
     @TableField(exist = false)
     private int pageSize;
     @ApiModelProperty(value = "页号")
     @TableField(exist = false)
     private int pageNum;
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
     
     

}
