package com.softeem.model.sys;

public class ChangeAccountPhone {
	public long playerUid;
    public long phoneNumber;
    public String sign;//playerUid + phoneNumber + key

    @Override
    public String toString() {
        return "ChangeAccountPhone{" +
                "playerUid=" + playerUid +
                ", phoneNumber=" + phoneNumber +
                ", sign='" + sign + '\'' +
                '}';
    }

}
