package com.didikee.wifimanager;

import android.net.wifi.WifiManager;

import com.didikee.wifimanager.interf.IWifiConnHelper;
import com.didikee.wifimanager.model.WifiInfo;
import com.didikee.wifimanager.model.WifiProxyInfo;

/**
 * Created by didik on 2016/12/21.
 */

public class WiFiConnectionImpl implements IWifiConnHelper {
    private final WifiManager wifiManager;

    public WiFiConnectionImpl(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    @Override
    public void connByNull() {

    }

    @Override
    public void connByPassword(String password) {

    }

    @Override
    public void connByProxy(String password, WifiProxyInfo proxyInfo, WifiInfo wifiInfo) {

    }

    @Override
    public void destroy() {

    }
}
