package com.didikee.wifimanager.interf;

import android.net.wifi.ScanResult;

import java.util.List;

/**
 * Created by didik on 2016/12/21.
 */

public interface IWifiMangerHelper {
    void open();
    void close();
    void update();

    void registerReceiver();
    void unRegisterReceiver();

    void onScanResultChange(List<ScanResult> scanResults);
}
