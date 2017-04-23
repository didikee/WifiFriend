package com.didikee.wifimanager.utils;

import com.didikee.wifimanager.model.WiFiEncryptionMode;

/**
 * Created by didikee on 2017/4/23.
 */

public class WiFiEncryptionUtil {

    public static int getWiFiEncryptionMode(String capabilities) {
        if (capabilities.contains("WPA") || capabilities.contains("wpa")) {
            return WiFiEncryptionMode.WIFI_WPA;
        } else if (capabilities.contains("WEP") || capabilities.contains("wep")) {
            return WiFiEncryptionMode.WIFI_WEP;
        } else {
            return WiFiEncryptionMode.WIFI_NO;
        }
    }
}
