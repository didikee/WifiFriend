package com.didikee.wifimanager;

import android.net.wifi.WifiManager;

import com.didikee.wifimanager.interf.WifiConnection;

/**
 * Created by didik on 2016/12/21.
 */

public class WiFiConnectionImpl implements WifiConnection {
    private final WifiManager wifiManager;

    public WiFiConnectionImpl(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void connWifi(String password) {

    }
}
