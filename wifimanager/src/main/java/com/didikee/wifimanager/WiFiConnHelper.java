package com.didikee.wifimanager;

import android.net.wifi.WifiManager;

import com.didikee.wifimanager.interf.IWifiConnHelper;
import com.didikee.wifimanager.model.WifiIPInfo;
import com.didikee.wifimanager.model.WifiProxyInfo;

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
    public void connByProxy(String password, WifiProxyInfo proxyInfo, WifiIPInfo ipInfo) {

    }

    @Override
    public void destroy() {

    }
}
