package com.cy.view.widge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cy.R;

public class T {

    private static Toast toast;
    /**
     * Toast 传id
     * @param context
     * @param textId
     * @param isShort
     */
    public static void show(Context context, int textId, boolean isShort){
        synchronized (new Object()) {
            if(toast != null){
                toast.cancel();
            }
            toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
            TextView textView = (TextView) view.findViewById(R.id.toast_tv);
            textView.setText(textId);
            toast.setView(view);
            if(isShort){
                toast.setDuration(Toast.LENGTH_SHORT);
            } else {
                toast.setDuration(Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }

    /**
     * Toast 传文本
     * @param context
     * @param text
     * @param isShort
     */
    public static void show(Context context,String text,boolean isShort){
        synchronized (new Object()) {
            if(toast != null){
                toast.cancel();
            }
            toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
            TextView textView = (TextView) view.findViewById(R.id.toast_tv);
            textView.setText(text);
            toast.setView(view);
            if(isShort){
                toast.setDuration(Toast.LENGTH_SHORT);
            } else {
                toast.setDuration(Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }

}