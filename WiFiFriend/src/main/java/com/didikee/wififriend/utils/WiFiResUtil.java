package com.didikee.wififriend.utils;

import android.support.annotation.DrawableRes;

import com.didikee.wififriend.R;

/**
 * Created by didik 
 * Created time 2017/1/10
 * Description: 
 */

public class WiFiResUtil {

    /**
     * 图标带小锁
     * @param level 等级: 0 ~~
     * @return 返回对于等级的icon id
     */
    public static @DrawableRes int getLockWifiIconByLevel(int level){
        int id ;
        switch (level){
            case 0:
                id=R.drawable.connect_locked_signal_level_0;
                break;
            case 1:
                id=R.drawable.connect_locked_signal_level_1;
                break;
            case 2:
                id=R.drawable.connect_locked_signal_level_2;
                break;
            case 3:
                id=R.drawable.connect_locked_signal_level_3;
                break;
            default:
                id=R.drawable.connect_locked_signal_level_0;
                break;
        }
        return id;
    }

    /**
     * 图标不带小锁
     * @param level 等级: 0 ~~
     * @return 返回对于等级的icon id
     */
    public static @DrawableRes int getWifiIconByLevel(int level){
        int id ;
        switch (level){
            case 0:
                id=R.drawable.connect_signal_level_0;
                break;
            case 1:
                id=R.drawable.connect_signal_level_1;
                break;
            case 2:
                id=R.drawable.connect_signal_level_2;
                break;
            case 3:
                id=R.drawable.connect_signal_level_3;
                break;
            default:
                id=R.drawable.connect_signal_level_0;
                break;
        }
        return id;
    }
}
