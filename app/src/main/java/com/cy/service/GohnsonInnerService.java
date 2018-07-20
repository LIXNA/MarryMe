package com.cy.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
/**
 * Created by chenyu.
 * Created on 下午2:42 2018/6/29.
 * Author'github https://github.com/PrettyAnt
 */
public class GohnsonInnerService extends Service {

    public final static int GOHNSON_ID = 1000;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(GOHNSON_ID, new Notification());
        stopForeground(true);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
