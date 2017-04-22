package com.didikee.wififriend;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.didikee.wififriend.adapters.WiFiAdapter;
import com.didikee.wififriend.interf.OnRvItemClickListener;
import com.didikee.wififriend.utils.DevLog;
import com.didikee.wifimanager.WiFiManagerHelper;
import com.didikee.wifimanager.listener.OnWifiScanResultChangeListener;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void check(){
        //判断用户是否已经授权，未授权则向用户申请授权，已授权则直接进行呼叫操作
        if(ContextCompat.checkSelfPermission(MainActivity.this,"Manifest.permission.CALL_PHONE")
                != PackageManager.PERMISSION_GRANTED)
        {
            //注意第二个参数没有双引号
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
        }
        else
        {
            //call()是封装好的拨打电话的方法
//            call();
        }
    }
}
