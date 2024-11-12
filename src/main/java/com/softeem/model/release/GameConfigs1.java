 package com.softeem.model.release;

import java.io.Serializable;
import java.math.BigInteger;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
@TableName("GameConfigs1")
public class GameConfigs1 implements Serializable {
     private Integer id;
     @TableField(exist = false)
     private Integer phone;
     
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String GameDownload;
     
     @ApiModelProperty(value = "游戏加载图片")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String LoadImage;
     @ApiModelProperty(value = "文件路径")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String FilePath;
     @ApiModelProperty(value = "服务器域名")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String GameApi;
     @ApiModelProperty(value = "静态资源")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String Statistics;
     @ApiModelProperty(value = "")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String AgencyApiUrl;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String AgencyUrl;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String VerDataUrl;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private Integer zipVersion;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private Integer zipSize;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String zipMd5;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private Integer resVersion;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private Integer version;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String plistUrl;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private String resourceUrl;
     @ApiModelProperty(value = "游戏下载地址")
     @TableField(strategy = FieldStrategy.NOT_EMPTY)
     private boolean isInWhiteList;
    public Integer getId() {
        return id;
    }
    public Integer getPhone() {
        return phone;
    }
    public String getGameDownload() {
        return GameDownload;
    }
    public String getLoadImage() {
        return LoadImage;
    }
    public String getFilePath() {
        return FilePath;
    }
    public String getGameApi() {
        return GameApi;
    }
    public String getStatistics() {
        return Statistics;
    }
    public String getAgencyApiUrl() {
        return AgencyApiUrl;
    }
    public String getAgencyUrl() {
        return AgencyUrl;
    }
    public String getVerDataUrl() {
        return VerDataUrl;
    }
    public Integer getZipVersion() {
        return zipVersion;
    }
    public Integer getZipSize() {
        return zipSize;
    }
    public String getZipMd5() {
        return zipMd5;
    }
    public Integer getResVersion() {
        return resVersion;
    }
    public Integer getVersion() {
        return version;
    }
    public String getPlistUrl() {
        return plistUrl;
    }
    public String getResourceUrl() {
        return resourceUrl;
    }
    public boolean isInWhiteList() {
        return isInWhiteList;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setPhone(Integer phone) {
        this.phone = phone;
    }
    public void setGameDownload(String gameDownload) {
        GameDownload = gameDownload;
    }
    public void setLoadImage(String loadImage) {
        LoadImage = loadImage;
    }
    public void setFilePath(String filePath) {
        FilePath = filePath;
    }
    public void setGameApi(String gameApi) {
        GameApi = gameApi;
    }
    public void setStatistics(String statistics) {
        Statistics = statistics;
    }
    public void setAgencyApiUrl(String agencyApiUrl) {
        AgencyApiUrl = agencyApiUrl;
    }
    public void setAgencyUrl(String agencyUrl) {
        AgencyUrl = agencyUrl;
    }
    public void setVerDataUrl(String verDataUrl) {
        VerDataUrl = verDataUrl;
    }
    public void setZipVersion(Integer zipVersion) {
        this.zipVersion = zipVersion;
    }
    public void setZipSize(Integer zipSize) {
        this.zipSize = zipSize;
    }
    public void setZipMd5(String zipMd5) {
        this.zipMd5 = zipMd5;
    }
    public void setResVersion(Integer resVersion) {
        this.resVersion = resVersion;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public void setPlistUrl(String plistUrl) {
        this.plistUrl = plistUrl;
    }
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
    public void setInWhiteList(boolean isInWhiteList) {
        this.isInWhiteList = isInWhiteList;
    }
     
     
   
     
     
  
     
     
     
     
}
  