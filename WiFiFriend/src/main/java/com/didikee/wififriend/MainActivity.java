package com.didikee.wififriend;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.didikee.wififriend.adapters.WiFiAdapter;
import com.didikee.wifimanager.WiFiManagerHelper;
import com.didikee.wifimanager.interf.OnWifiScanResultChangeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button bt_start;
    private Button bt_cha;
    private WiFiManagerHelper wmh;
    private WiFiAdapter wiFiAdapter;

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
            }
        });

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                wifiManager.startScan();
                wmh.open();
                wmh.registerReceiver();
            }
        });

        wiFiAdapter = new WiFiAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(wiFiAdapter);
        wmh.setWifiScanResultChangeListener(new OnWifiScanResultChangeListener() {
            @Override
            public void onChange(List<ScanResult> scanResults) {
                wiFiAdapter.setScanResults(scanResults);
            }
        });

    }

    private void init() {
//        recyclerView.setAdapter(adapter);
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
}
