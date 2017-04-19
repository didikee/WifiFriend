package com.didikee.wifimanager.interf;

import com.didikee.wifimanager.model.WifiIPInfo;
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
     * 高级设置
     * @param password 密码
     * @param proxyInfo 代理
     * @param ipInfo ipInfo
     */
    void connByProxy(String password, WifiProxyInfo proxyInfo, WifiIPInfo ipInfo);
}
