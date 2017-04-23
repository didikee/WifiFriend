package com.didikee.wifimanager.model;

/**
 * Created by didik on 2017/4/8.
 */

public class WifiProxyInfo {

    private String pwd;
    private String ssid;
    private int type;
    //advance mode


    public WifiProxyInfo(String pwd, String ssid, int type) {
        this.pwd = pwd;
        this.ssid = ssid;
        this.type = type;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
