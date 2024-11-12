package com.softeem.model.room;

public class ClubDissolveRoom {
    public int roomUid;         // 房间UID
    public String sign; // md5(roomUid, key)
    
    public int getRoomUid() {
        return roomUid;
    }

    public String getSign() {
        return sign;
    }

    public void setRoomUid(int roomUid) {
        this.roomUid = roomUid;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "ClubDissolveRoom{" +
                "roomUid=" + roomUid +
                ", sign='" + sign + '\'' +
                '}';
    }
}
