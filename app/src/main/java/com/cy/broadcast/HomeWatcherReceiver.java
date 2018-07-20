package com.cy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cy.view.activity.MainActivity;

/**
 * 监听home键和多任务，最新版通过服务实现，目前不用
 */
public class HomeWatcherReceiver extends BroadcastReceiver {

    final String SYSTEM_DIALOG_REASON_KEY = "reason";

    final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";

    final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)) {
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            if (reason != null) {
                if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {//home键
                    Toast.makeText(context, "按HOME键也没用", Toast.LENGTH_SHORT).show();
//                        MainActivity.this.finish();
                    Intent intent2 = new Intent(context, MainActivity.class);
                    context.startActivity(intent2);
                } else if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {//多任务
//                        MainActivity.this.finish();
                    Intent intent3 = new Intent(context, MainActivity.class);
                    context.startActivity(intent3);
                    Toast.makeText(context, "按啥都没用", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}