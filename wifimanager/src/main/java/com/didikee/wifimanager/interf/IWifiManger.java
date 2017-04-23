package com.didikee.wifimanager.interf;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;

import com.didikee.wifimanager.model.WifiProxyInfo;

import java.util.List;

/**
 * Created by didik on 2016/12/21.
 */

public interface IWifiManger {
    void open();
    void close();
    void update();

    void registerReceiver();
    void unRegisterReceiver();

    void onScanResultChange(List<ScanResult> scanResults);

    WifiInfo getCurrentConnectionWifiInfo();

    boolean connectWiFi(WifiProxyInfo wifiProxyInfo);
}
