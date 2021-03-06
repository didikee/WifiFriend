package com.didikee.wififriend;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.didikee.uitoast.UIToast;
import com.didikee.wififriend.adapters.WiFiAdapter;
import com.didikee.wififriend.interf.OnConnDialogPositiveButtonClickListener;
import com.didikee.wififriend.interf.OnRvItemClickListener;
import com.didikee.wififriend.utils.DevLog;
import com.didikee.wififriend.views.ConnDialog;
import com.didikee.wifimanager.WiFiManagerHelper;
import com.didikee.wifimanager.listener.OnWifiScanResultChangeListener;
import com.didikee.wifimanager.model.WifiProxyInfo;
import com.didikee.wifimanager.utils.WiFiEncryptionUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button bt_start;
    private Button bt_cha;
    private WiFiManagerHelper wmh;
    private WiFiAdapter wiFiAdapter;
    private ConnDialog connDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = ((RecyclerView) findViewById(R.id.recyclerView));

        bt_start = ((Button) findViewById(R.id.bt_start));
        bt_cha = ((Button) findViewById(R.id.bt_cha));

        wmh = new WiFiManagerHelper(this);
        init();
        bt_cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                init();
                wmh.update();
            }
        });

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                wifiManager.startScan();
                wmh.open();
            }
        });

        wiFiAdapter = new WiFiAdapter();

        connDialog = new ConnDialog(this);
        connDialog.setPositiveButtonClickListener(new OnConnDialogPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(WifiProxyInfo wifiProxyInfo) {
                wmh.connectWiFi(wifiProxyInfo);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(wiFiAdapter);
        wmh.setWifiScanResultChangeListener(new OnWifiScanResultChangeListener() {
            @Override
            public void onChange(List<ScanResult> scanResults) {
                wiFiAdapter.setScanResults(scanResults);
            }
        });

        wiFiAdapter.setItemClickListener(new OnRvItemClickListener<ScanResult>() {
            @Override
            public void onRvItemClick(View view, int position, ScanResult scanResult) {

                DevLog.e(scanResult.toString());
                DevLog.e(scanResult.capabilities);
                connDialog.show(scanResult.SSID, WiFiEncryptionUtil.getWiFiEncryptionMode(scanResult.capabilities));
            }
        });

    }

    private void init() {
//        recyclerView.setAdapter(adapter);
        //test
    }

    //将搜索到的wifi根据信号强度从强到弱进行排序
    private void sortByLevel(ArrayList<ScanResult> list) {
        for(int i=0;i<list.size();i++)
            for(int j=1;j<list.size();j++)
            {
                if(list.get(i).level<list.get(j).level)    //level属性即为强度
                {
                    ScanResult temp = null;
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuID = item.getItemId();
        switch (menuID){
            case R.id.refresh:
                UIToast.showStyleToast(this,"刷新WIFI");
                break;
            case R.id.share:
                UIToast.showStyleToast(this,"分享当前连接的WIFI信息给你的好朋友!");
                break;
            case R.id.apps:
                UIToast.showStyleToast(this,"展示所有更多功能");
                break;
        }
        return true;
    }
}
