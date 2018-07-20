package com.cy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cy.service.BackgroundService;
import com.cy.view.widge.T;

/**
 * Created by chenyu.
 * Created on 下午2:42 2018/6/29.
 * Author'github https://github.com/PrettyAnt
 */
public class BootCompletedReceiver extends BroadcastReceiver {

    @Override 
    public void onReceive(Context context, Intent intent) {
        Log.d("wb", "----------监听到开机广播-----------");
        T.show(context,"监听到开机广播",false);
        Intent it = new Intent(context, BackgroundService.class);
        context.startService(it); 
    } 
}