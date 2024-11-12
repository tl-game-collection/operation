 package com.softeem.model.room;

 public class RoomCardReq {
     public long uid;                    // 玩家id
     public int amount;                  // 房卡
     public int type;                    // 类型 对应 EMoneyExpendType
     public long fromUid;                // 亲友圈ID 或者 联盟ID 没有为-1
     public long operatorUid;            // 被赠送的玩家id
     public String sign; // md5(uid + amount + type + key)
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public long getFromUid() {
        return fromUid;
    }
    public void setFromUid(long fromUid) {
        this.fromUid = fromUid;
    }
    public long getOperatorUid() {
        return operatorUid;
    }
    public void setOperatorUid(long operatorUid) {
        this.operatorUid = operatorUid;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public RoomCardReq(long uid, int amount, int type, long fromUid, long operatorUid, String sign) {
        super();
        this.uid = uid;
        this.amount = amount;
        this.type = type;
        this.fromUid = fromUid;
        this.operatorUid = operatorUid;
        this.sign = sign;
    }

    
    public RoomCardReq() {
        super();
    }
    
    
     
     
     

}
