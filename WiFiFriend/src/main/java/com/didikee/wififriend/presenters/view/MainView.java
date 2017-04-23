package com.didikee.wififriend.presenters.view;

import com.didikee.wifimanager.model.WifiProxyInfo;

/**
 * Created by didikee on 2017/4/22.
 */

public interface MainView {
    /**
     * share wifi friend by QrCode
     */
    void showQRCodeShareDialog();

    /**
     * conn to wifi
     * @param withProxy help by proxy
     */
    void connection2Wifi(boolean withProxy);

    void updateWifiInfo(WifiProxyInfo wifiProxyInfo);
}
