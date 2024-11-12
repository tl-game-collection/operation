 package com.softeem.model.sys;

 public class ModifyUserPrivilege {
     public long uid;
     public int level;
     public String sign; // md5(uid + level + add + key)
     
     
     
     
     

     public ModifyUserPrivilege(long uid, int level, String sign) {
        super();
        this.uid = uid;
        this.level = level;
        this.sign = sign;
    }



    public long getUid() {
        return uid;
    }



    public void setUid(long uid) {
        this.uid = uid;
    }



    public int getLevel() {
        return level;
    }



    public void setLevel(int level) {
        this.level = level;
    }



    public String getSign() {
        return sign;
    }



    public void setSign(String sign) {
        this.sign = sign;
    }



    @Override
     public String toString() {
         return "ModifyUserPrivilege{" +
                 "uid=" + uid +
                 ", level=" + level +
                 ", sign='" + sign + '\'' +
                 '}';
     }
 }