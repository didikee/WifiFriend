package com.didikee.wifimanager;

import android.net.wifi.WifiManager;

import com.didikee.wifimanager.interf.IWifiConnHelper;

/**
 * Created by didik on 2016/12/21.
 */

public class WiFiConnHelper implements IWifiConnHelper {
    private final WifiManager wifiManager;

    public WiFiConnHelper(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    @Override
    public void connByNull() {

    }

    @Override
    public void connByPassword(String password) {

    }

    @Override
    public void destroy() {

    }
}
