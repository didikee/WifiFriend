package com.didikee.wififriend.views;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.didikee.wififriend.R;
import com.didikee.wififriend.interf.OnConnDialogPositiveButtonClickListener;
import com.didikee.wifimanager.model.WifiProxyInfo;

/**
 * Created by didikee on 2017/4/22.
 */

public class ConnDialog  {
    private final Context context;
    private String mWifiTitle ="连接 ";
    private View myLayout;
    private AlertDialog mDialog;
    private EditText etPwd;

    private String ssid;
    private int type;

    public ConnDialog(Context context) {
        this.context = context;
        if (context == null)return;
        prepareLayout();
        createDialog();
    }

    private void prepareLayout() {
        myLayout = LayoutInflater.from(context).inflate(R.layout.layout_dialog_conn, null);
        etPwd = ((EditText) myLayout.findViewById(R.id.et_pwd));
    }

    public void show(String ssid,int type){
        this.ssid = ssid;
        this.type = type;
        mWifiTitle = "连接 "+ssid;
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
                        if (positiveButtonClickListener!=null){
                            positiveButtonClickListener.onPositiveButtonClick(new WifiProxyInfo(etPwd.getText().toString(),ssid,type));
                        }
                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
//        mDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.wf_red));
//        mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.wf_red));
    }

    private OnConnDialogPositiveButtonClickListener positiveButtonClickListener;

    public void setPositiveButtonClickListener(OnConnDialogPositiveButtonClickListener
                                                       positiveButtonClickListener) {
        this.positiveButtonClickListener = positiveButtonClickListener;
    }
}
