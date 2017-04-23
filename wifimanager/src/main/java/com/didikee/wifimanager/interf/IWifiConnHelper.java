package com.didikee.wifimanager.interf;

/**
 * Created by didik on 2016/12/21.
 */

public interface IWifiConnHelper extends IHelper{

    /**
     * 判断采用何种连接,包括连接之前连接过的WiFi热点
     */
    boolean connect();

    /**
     * 加锁,防止休眠
     */
    void lock();

    void unLock();

    /**
     * 忘记当前连接的WiFi密码
     * @return 是否操作成功
     */
    boolean forgetWIFI();
}
