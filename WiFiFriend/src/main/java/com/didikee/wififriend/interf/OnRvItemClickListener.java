package com.didikee.wififriend.interf;

import android.view.View;

/**
 * Created by didik 
 * Created time 2017/1/10
 * Description: 
 */

public interface OnRvItemClickListener<T> {
    void onRvItemClick(View view,int position, T t);
}
