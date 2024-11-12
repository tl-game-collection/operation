 package com.softeem.model.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PLSystemConfigsInfo {
     public static class GameConfigs {
         public String GameDownload;
         public String LoadImage;
         public String FilePath;
         public String GameApi;
         public String Statistics;
         public String AgencyApiUrl;
         public String AgencyUrl;
         public String VerDataUrl;
         @JsonIgnore
         public String phone;//1苹果，2安卓
         
         public String getPhone() {
             return phone;
         }
         public void setPhone(String phone) {
             this.phone = phone;
         }

        @Override
         public String toString() {
             return "GameConfigs{" +
                     "GameDownload=" + GameDownload + '\'' +
                     ", LoadImage=" + LoadImage + '\'' +
                     ", FilePath=" + FilePath + '\'' +
                     ", GameApi=" + GameApi + '\'' +
                     ", Statistics=" + Statistics + '\'' +
                     ", AgencyApiUrl=" + AgencyApiUrl + '\'' +
                     ", AgencyUrl=" + AgencyUrl + '\'' +
                     ", VerDataUrl=" + VerDataUrl + '\'' +
                     '}';
         }
     }

     public static class ZipInfo {
         public int zipVersion;
         public int zipSize;
         public String zipMd5;
         public int resVersion;

         @Override
         public String toString() {
             return "ZipInfo{" +
                     "zipVersion=" + zipVersion +
                     ",zipSize=" + zipSize +
                     ",resVersion =" + resVersion +
                     ", zipMd5=" + zipMd5 + '\'' +
                     '}';
         }
     }

     public GameConfigs gameConfigs = new GameConfigs();
     public ZipInfo zipInfo = new ZipInfo();
     public int version;
     public String plistUrl;
     public String resourceUrl;
     public boolean isInWhiteList;

     @Override
     public String toString() {
         return "PLSystemConfigsInfo{" +
                 "gameConfigs='" + gameConfigs + '\'' +
                 ", zipInfo='" + zipInfo + '\'' +
                 ", version=" + version +
                 ", plistUrl=" + plistUrl + '\'' +
                 ", resourceUrl=" + resourceUrl + '\'' +
                 ", isInWhiteList=" + isInWhiteList +
                 '}';
     }

    public GameConfigs getGameConfigs() {
        return gameConfigs;
    }

    public void setGameConfigs(GameConfigs gameConfigs) {
        this.gameConfigs = gameConfigs;
    }

    public ZipInfo getZipInfo() {
        return zipInfo;
    }

    public void setZipInfo(ZipInfo zipInfo) {
        this.zipInfo = zipInfo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPlistUrl() {
        return plistUrl;
    }

    public void setPlistUrl(String plistUrl) {
        this.plistUrl = plistUrl;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public boolean getIsInWhiteList() {
        return isInWhiteList;
    }

    public void setInWhiteList(boolean isInWhiteList) {
        this.isInWhiteList = isInWhiteList;
    }
    
    
     
     

}
