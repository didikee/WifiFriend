package com.didikee.wifimanager;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.didikee.wifimanager.interf.IWifiConnHelper;
import com.didikee.wifimanager.model.WiFiEncryptionMode;
import com.didikee.wifimanager.model.WifiProxyInfo;

import java.util.Iterator;

/**
 * Created by didik on 2016/12/21.
 */

public class WiFiConnectionImpl implements IWifiConnHelper {

    private final WifiManager mWifiManager;

    private WifiManager.WifiLock mWifiLock;//wifi 锁

    private WifiProxyInfo mWifiInfo;

    public WiFiConnectionImpl(WifiManager wifiManager) {
        this.mWifiManager = wifiManager;
    }

    /**
     * 如果要连接WiFi,必须更新之前的WiFi信息,否则将会出异常
     * @param newWifiInfo 新的WiFi
     */
    public void updateWIfiModel(WifiProxyInfo newWifiInfo) {
        this.mWifiInfo = newWifiInfo;
    }

    @Override
    public void destroy() {
        mWifiInfo = null;
        mWifiLock = null;
    }

    @Override
    public boolean connect() {
        boolean result ;
        if (mWifiInfo==null){
            return false;
        }
        //by no pwd
        if (mWifiInfo.getType() == WiFiEncryptionMode.WIFI_NO){
            connect2Network(createWifiCfg(mWifiInfo.getSsid(),"", WiFiEncryptionMode.WIFI_NO));
            return true;
        }
        //by pwd
        if (true){
            connect2Network(createWifiCfg(mWifiInfo.getSsid(),mWifiInfo.getPwd(),mWifiInfo.getType()));
        }
        return true;


    }


    @Override
    public void lock() {//锁定WifiLock，当下载大文件时需要锁定
        createWifiLock();
        mWifiLock.acquire();
    }

    @Override
    public void unLock() {
        createWifiLock();
        if (mWifiLock.isHeld()) {//判断是否锁定
            mWifiLock.acquire();
        }
    }

    @Override
    public boolean forgetWIFI() {
        WifiInfo connectionInfo = mWifiManager.getConnectionInfo();
        if (connectionInfo == null){
            return false;
        }
        int forgetID = connectionInfo.getNetworkId();
        return mWifiManager.removeNetwork(forgetID);
    }

    private void createWifiLock() {
        if (mWifiLock == null) {
            mWifiLock = mWifiManager.createWifiLock("WIFI");
        }
    }

    /**
     * 添加到指定Wifi网络 /切换到指定Wifi网络
     * @param wf
     * @return
     */
    public void connect2Network(WifiConfiguration wf) {
        //连接新的连接
        int netId = mWifiManager.addNetwork(wf);
        mWifiManager.enableNetwork(netId, true);
        mWifiManager.saveConfiguration();
        mWifiManager.reconnect();
    }

    /**
     * 关闭当前的Wifi网络
     * @return
     */
    public boolean disconnectCurrentNetwork() {
        if (mWifiManager != null && mWifiManager.isWifiEnabled()) {
            int netId = mWifiManager.getConnectionInfo().getNetworkId();
            mWifiManager.disableNetwork(netId);
            return mWifiManager.disconnect();
        }
        return false;
    }

    /**
     * 创建WifiConfiguration
     *
     * @param SSID
     * @param Password
     * @param Type
     * @return
     */
    public WifiConfiguration createWifiCfg(String SSID, String Password, int Type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";
        WifiConfiguration tempConfig = isExsits(SSID);
        if (tempConfig != null) {
//            mWifiManager.removeNetwork(tempConfig.networkId);
            return  tempConfig;
        }
        if (Type == WiFiEncryptionMode.WIFI_NO) //WIFICIPHER_NOPASS
        {
          /*  config.wepKeys[0] = "";//连接无密码热点时加上这两句会出错
            config.wepTxKeyIndex = 0;*/
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        }
        if (Type == WiFiEncryptionMode.WIFI_WEP) //WIFICIPHER_WEP
        {
            config.hiddenSSID = true;
            config.wepKeys[0] = "\"" + Password + "\"";
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == WiFiEncryptionMode.WIFI_WPA) //WIFICIPHER_WPA
        {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            //config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }
        return config;
    }

    /**
     * 是否存在网络信息
     * @param str  热点名称
     * @return
     */
    public WifiConfiguration isExsits(String str) {
        Iterator<WifiConfiguration> localIterator = this.mWifiManager.getConfiguredNetworks()
                .iterator();
        WifiConfiguration localWifiConfiguration;
        do {
            if (!localIterator.hasNext()) return null;
            localWifiConfiguration = localIterator.next();
        } while (!localWifiConfiguration.SSID.equals("\"" + str + "\""));
        return localWifiConfiguration;
    }


}
