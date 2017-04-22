package com.didikee.wifimanager.interf;

import com.didikee.wifimanager.model.WifiInfo;
import com.didikee.wifimanager.model.WifiProxyInfo;

/**
 * Created by didik on 2016/12/21.
 */

public interface IWifiConnHelper extends IHelper{
    /**
     * 无密码,直连
     */
    void connByNull();

    /**
     * 密码连接
     * @param password 密码
     */
    void connByPassword(String password);

    /**
     * 高级链接,包含代理设置等
     * @param password
     * @param proxyInfo
     * @param ipInfo
     */
    void connByProxy(String password, WifiProxyInfo proxyInfo, WifiInfo ipInfo);
}
