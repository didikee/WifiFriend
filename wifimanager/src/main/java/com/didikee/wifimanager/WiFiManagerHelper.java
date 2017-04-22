package com.didikee.wifimanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.didikee.wifimanager.interf.WifiManger;
import com.didikee.wifimanager.listener.OnWifiScanResultChangeListener;

import java.util.List;

/**
 * Created by didik 
 * Created time 2016/12/20
 * Description:
 * NOTE:
 * 当你使用Android6.0及其以上sdk时,你需要额外的 危险权限
 * <p>android:name="android.permission.ACCESS_COARSE_LOCATION"<p/>
 * <p>android:name="android.permission.ACCESS_FINE_LOCATION"<p/>
 * 这是位置权限,至于为什么需要,你可以问 Google =.=
 * https://developer.android.com/reference/android/net/wifi/WifiManager.html#getScanResults()
 * {@link WifiManager}
 * if you miss permissions,you will get ZERO when you call this method WifiManager.getScanResults()
 *
 */

public class WiFiManagerHelper implements WifiManger {

    private final Context context;
    private final WifiManager wifiManager;
    private boolean isRegister = false;

    public WiFiManagerHelper(Context context) {
        this.context = context;
        wifiManager= (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        initWifi();
    }

    private void initWifi(){

    }

    @Override
    public void open() {
        wifiManager.setWifiEnabled(true);
        wifiManager.startScan();
        registerReceiver();
        isRegister = true;
    }

    @Override
    public void close() {
        wifiManager.setWifiEnabled(false);
        unRegisterReceiver();
    }

    @Override
    public void update() {
        if (!wifiManager.isWifiEnabled()){
            open();
            return;
        }

        if (!isRegister){
            registerReceiver();
            isRegister = true;
        }
        wifiManager.startScan();
    }

    @Override
    public void registerReceiver() {
        if (isRegister)return;
        IntentFilter filter = new IntentFilter();
        // filter.addAction(WifiManager.ERROR_AUTHENTICATING);
        filter.addAction(WifiManager.ACTION_PICK_WIFI_NETWORK);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        // 测试wifi验证密码错误问题
        filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        context.registerReceiver(wifiBroadcastReceiver,filter);
    }

    @Override
    public void unRegisterReceiver() {
        if (context!=null)context.unregisterReceiver(wifiBroadcastReceiver);
    }

    @Override
    public void onScanResultChange(List<ScanResult> scanResults) {
        if (scanResults!=null && wifiScanResultChangeListener!=null){
            wifiScanResultChangeListener.onChange(scanResults);
            Log.e("test","scanresult: "+ scanResults.size());
        }
    }


    private BroadcastReceiver wifiBroadcastReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            onScanResultChange(scanResults);
            String action = intent.getAction();
            Log.e("WifiReceiver", action);
            // / Wifi 状态变化
            if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(action)) {
                WifiInfo info = wifiManager.getConnectionInfo();
                SupplicantState state = info.getSupplicantState();
                if (state == SupplicantState.COMPLETED) {
                    Log.e("WifiReceiver", "(验证成功)");
                }
                int errorCode = intent.getIntExtra(
                        WifiManager.EXTRA_SUPPLICANT_ERROR, -1);
                if (errorCode == WifiManager.ERROR_AUTHENTICATING) {
                    Log.e("WifiReceiver", "(验证失败)");
                }
            }
        }
    };

    private OnWifiScanResultChangeListener wifiScanResultChangeListener;

    public void setWifiScanResultChangeListener(OnWifiScanResultChangeListener
                                                        wifiScanResultChangeListener) {
        this.wifiScanResultChangeListener = wifiScanResultChangeListener;
    }
}
