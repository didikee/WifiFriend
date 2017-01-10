package com.didikee.wififriend.adapters;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.didikee.wififriend.R;
import com.didikee.wififriend.interf.OnRvItemClickListener;
import com.didikee.wififriend.utils.WiFiResUtil;

import java.util.List;

/**
 * Created by didik on 2017/1/9.
 */

public class WiFiAdapter extends RecyclerView.Adapter<WiFiAdapter.ViewHolder> {

    private List<ScanResult> scanResults;
    private OnRvItemClickListener<ScanResult> itemClickListener;

    public void setScanResults(List<ScanResult> scanResults) {
        this.scanResults = scanResults;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_rv_wifi,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ScanResult scanResult = scanResults.get(position);
        int level = WifiManager.calculateSignalLevel(scanResult.level, 4);
        holder.iv_show.setImageResource(WiFiResUtil.getLockWifiIconByLevel(level));
        holder.tv_name.setText("等级: "+level);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener!=null){
                    itemClickListener.onRvItemClick(v,holder.getAdapterPosition(),scanResult);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return scanResults == null ? 0 : scanResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_show;
        public ImageView iv_action;
        public TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_show = (ImageView) itemView.findViewById(R.id.iv_show);
            iv_action = (ImageView) itemView.findViewById(R.id.iv_action);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public void setItemClickListener(OnRvItemClickListener<ScanResult> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
