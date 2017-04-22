package com.didikee.wififriend.views;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.didikee.wififriend.R;

/**
 * Created by didikee on 2017/4/22.
 */

public class ConnDialog  {
    private final Context context;
    private String mWifiTitle ="连接 ";
    private View myLayout;
    private AlertDialog mDialog;

    public ConnDialog(Context context) {
        this.context = context;
        if (context == null)return;
        prepareLayout();
        createDialog();
    }

    private void prepareLayout() {
        myLayout = LayoutInflater.from(context).inflate(R.layout.layout_dialog_conn, null);
    }

    public void setTitle(String wifiName){
        mWifiTitle = "连接 "+wifiName;
    }

    public void show(){
        if (mDialog!=null && !mDialog.isShowing()){
            mDialog.show();
        }
    }

    private void createDialog() {
        mDialog = new AlertDialog.Builder(context)
                .setTitle(mWifiTitle)
                .setView(myLayout)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
//        mDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.wf_red));
//        mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.wf_red));
    }
}
