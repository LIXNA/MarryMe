package com.cy.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.cy.view.activity.MainActivity;
import com.cy.R;
import com.cy.imp.StopTimer;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by chenyu.
 * Created on  2018/6/28.
 * Author'github https://github.com/PrettyAnt
 */
public class BackgroundService extends Service {
    private static int count = 0;
    private Timer mTimer;

    public BackgroundService() {
        Log.i("wb", "service---BackgroundService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("wb", "onBind");
        return new Binder();
        //return 到ServiceConnection的onServiceConnected中
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG", "service---onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(GohnsonInnerService.GOHNSON_ID, new Notification());
        } else {
            Intent innerIntent = new Intent(this, GohnsonInnerService.class);
            startService(innerIntent);
            startForeground(GohnsonInnerService.GOHNSON_ID, new Notification());

        }
        Log.i("wb", "onStartCommand---- flags:" + flags + "--- startId:" + startId);
        mTimer = new Timer(true);
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("wb", "------" + count++ + "---");
                if (count%5==0) {
                    Log.i("wb", "----+++--" + count++ + "--++++---");
                    Intent intent1 = new Intent(BackgroundService.this, MainActivity.class);
                    startActivity(intent1);
                }
            }
        }, 0, 1000);
        return super.onStartCommand(intent, flags, startId);
        //返回值不同，Service被杀掉的情况也不同
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            count = 0;
        }
        Log.i("wb", "service---onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.i("wb", "service---onUnbind");
        return super.onUnbind(intent);
    }

    public void stopTimer(Context context,StopTimer stopTimer) {
        stopTimer.stop(context.getResources().getString(R.string.msg_phone_control));
        if (mTimer != null) {
            mTimer.cancel();
            count = 0;
        }
    }
}
