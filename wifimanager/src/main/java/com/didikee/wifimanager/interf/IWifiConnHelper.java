package com.didikee.wifimanager.interf;

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
}
