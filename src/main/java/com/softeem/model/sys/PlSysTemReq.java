 package com.softeem.model.sys;

 public class PlSysTemReq {
     public int zipVersion;
     public int zipSize;
     public String zipMd5;
     public int resVersion;
     private int version;
    public int getZipVersion() {
        return zipVersion;
    }
    public void setZipVersion(int zipVersion) {
        this.zipVersion = zipVersion;
    }
    public int getZipSize() {
        return zipSize;
    }
    public void setZipSize(int zipSize) {
        this.zipSize = zipSize;
    }
    public String getZipMd5() {
        return zipMd5;
    }
    public void setZipMd5(String zipMd5) {
        this.zipMd5 = zipMd5;
    }
    public int getResVersion() {
        return resVersion;
    }
    public void setResVersion(int resVersion) {
        this.resVersion = resVersion;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
     
     
     

}
