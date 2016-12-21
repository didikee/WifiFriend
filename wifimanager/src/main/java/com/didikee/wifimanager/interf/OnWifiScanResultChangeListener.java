package com.didikee.wifimanager.interf;

import android.net.wifi.ScanResult;

import java.util.List;

/**
 * Created by didik on 2016/12/21.
 */

public interface OnWifiScanResultChangeListener {
    void onChange(List<ScanResult> scanResults);
}
