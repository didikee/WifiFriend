package com.didikee.wififriend;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.didikee.wifimanager.WiFiManagerHelper;
import com.didikee.wifimanager.interf.OnWifiScanResultChangeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button bt_start;
    private Button bt_cha;
    private WiFiManagerHelper wmh;
    private List<ScanResult> scanResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = ((ListView) findViewById(R.id.listView));

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

        wmh.setWifiScanResultChangeListener(new OnWifiScanResultChangeListener() {
            @Override
            public void onChange(List<ScanResult> scanResults) {
                MainActivity.this.scanResults=scanResults;
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void init() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        listView.setAdapter(adapter);
    }

    private BaseAdapter adapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return scanResults==null ? 0 :scanResults.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView==null){
                holder=new ViewHolder();
                convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_main,null);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            }
            holder= (ViewHolder) convertView.getTag();
            holder.tv_name.setText(position+1+".  "+scanResults.get(position).describeContents());
            return convertView;
        }
    };

    private class ViewHolder{
        public TextView tv_name;
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
