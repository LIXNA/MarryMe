package com.cy.view.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cy.R;
import com.cy.imp.StopTimer;
import com.cy.service.BackgroundService;
import com.cy.view.widge.T;
import com.cy.broadcast.HomeWatcherReceiver;

import java.util.Random;
/**
 * Created by chenyu.
 * Created on 下午2:42 2018/6/29.
 * Author'github https://github.com/PrettyAnt
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, StopTimer {

    private static int mIndex = 2;
    private static int count = 100;
    private String[] msgDatas = new String[]{};
    //    private  int[] pics =new int[]{};
//TypedArray pictures = this.getResource().obtainTypedArray(R.array.pictures);
    private TypedArray pic = null;
    private TextView mTv_content;
    private TextView mBtn_yes;
    private TextView mBtn_no;
    private TextView mTv_tp;
    private RelativeLayout mCl;
    private boolean isAllowExit = false;
    private HomeWatcherReceiver mHomeWatcherReceiver;
    private ImageView mIv_pic;
    private long duringTime = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(0x80000000, 0x80000000);
        setContentView(R.layout.activity_main);
//        registerReceiver();
        initView();
    }

    private void initView() {
        msgDatas = getResources().getStringArray(R.array.msg);
        pic = getResources().obtainTypedArray(R.array.pic);
//        pics= getResources().getIntArray(R.array.pic);
        mCl = (RelativeLayout) findViewById(R.id.cl);
        mTv_content = (TextView) findViewById(R.id.tv_content);
        mTv_tp = (TextView) findViewById(R.id.tv_tp);
//        mIv_pic = (ImageView) findViewById(R.id.iv_pic);
        mBtn_yes = (TextView) findViewById(R.id.btn_yes);
        mBtn_no = (TextView) findViewById(R.id.btn_no);
        mTv_tp.setText(msgDatas[2]);
        mTv_tp.setTextSize(24);
        mBtn_yes.setOnClickListener(this);
        mBtn_no.setOnClickListener(this);


//        findViewById(R.id.ll).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("wb","getX:"+v.getX()+"---getY:"+v.getY()
//                        +"\n---getWidth:"+v.getWidth()+"---getHeight:"+v.getHeight()
//                        +"\n---getTranslationX:"+v.getTranslationX()+"---getTranslationY:"+v.getTranslationY());
//                return false;
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                mTv_content.setText(R.string.msg_success);
                mTv_content.setGravity(Gravity.CENTER_HORIZONTAL);
                mBtn_no.setVisibility(View.INVISIBLE);
                mBtn_yes.setVisibility(View.INVISIBLE);
//                mTv_tp.setVisibility(View.INVISIBLE);
                mTv_tp.setBackgroundResource(R.drawable.success);
                mTv_tp.setText("💗💗");
                isAllowExit = true;
                //对文字设置动画
                ObjectAnimator rotation1 = ObjectAnimator.ofFloat(mTv_content, "rotation", 0, -180, 0);
                ObjectAnimator translationY1 = ObjectAnimator.ofFloat(mTv_content, "translationY", 200);
                ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(mTv_content, "scaleX", 0.5f, 2, 3);
                ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(mTv_content, "scaleY", 0.5f, 2, 3);


                //对图片设置动画
                ObjectAnimator rotation = ObjectAnimator.ofFloat(mTv_tp, "rotation", 0, 360, 0);
                ObjectAnimator translationY = ObjectAnimator.ofFloat(mTv_tp, "translationY", 200);
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTv_tp, "scaleX", 0.4f, 1, 0.5f, 1);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTv_tp, "scaleY", 0.4f, 1, 0.5f, 1);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(duringTime);
                animatorSet.playTogether(rotation1, translationY1, scaleX1, scaleY1, rotation, translationY, scaleX, scaleY);
                animatorSet.start();

                //                if (mHomeWatcherReceiver != null) {
//                    unregisterReceiver(mHomeWatcherReceiver);
//                    mHomeWatcherReceiver = null;
//                }
                break;
            case R.id.btn_no:
//                Toast.makeText(this, "就是不让你点!", Toast.LENGTH_SHORT).show();

                Log.i("wb", "index:" + mIndex + "  msgDatas[index]" + msgDatas[mIndex]);
                if (count == 0) {
                    T.show(this, getString(R.string.msg_retry), false);
                    count = 100;
                } else {
                    initShowView();
                }
                WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                int width = wm.getDefaultDisplay().getWidth() - 200;
                int height = wm.getDefaultDisplay().getHeight() - 200;
                Log.e("wb", "width:" + width + "height:" + height);

                Log.e("wb", "mBtn_no.getX()" + mBtn_no.getX() + "mBtn_no.getY()" + mBtn_no.getY());
                int w = -new Random().nextInt(width);//0 ~（频幕宽-200）
                w = w + 300;
                int h = -new Random().nextInt(height);//0 ~（屏幕高-200）
                h = h + 100;
                Log.e("wb", "w:" + w + "h:" + h);
                int picRandom = new Random().nextInt(pic.length());
                mTv_tp.setBackground(pic.getDrawable(picRandom));
                setBillAnimation(mBtn_no, w, h, 100);
                break;
        }
    }

    private void initShowView() {
        mIndex = new Random().nextInt(msgDatas.length);
        mTv_tp.setText(msgDatas[mIndex]);
        T.show(this, "还要点" + count + "下哦~ ~", true);
        switch (mIndex) {
            case 0:
                mTv_tp.setTextColor(Color.BLUE);
                break;
            case 1:
                mTv_tp.setTextColor(Color.RED);
                mTv_tp.setTextSize(32);
                break;
            case 2:
                mTv_tp.setTextColor(Color.RED);
                mTv_tp.setTextSize(34);
                break;
            case 3:
                mTv_tp.setTextColor(Color.RED);
                mTv_tp.setTextSize(32);
                break;
            case 4:
                mTv_tp.setTextColor(Color.BLUE);
                mTv_tp.setTextSize(28);
                break;
            case 5:
                mTv_tp.setTextColor(Color.parseColor("#48a8fa"));
                mTv_tp.setTextSize(24);
                break;
            default:
                mTv_tp.setTextColor(Color.parseColor("#FF7404F4"));
                mTv_tp.setTextSize(24);
                break;
        }
        count--;
        mTv_tp.setVisibility(View.VISIBLE);
    }

    /**
     * mBtn_no 的属性动画
     *
     * @param view
     * @param xValue
     * @param yValue
     * @param durTime
     */
    private void setBillAnimation(View view, float xValue, float yValue, long durTime) {
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationX", translationX, xValue);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", translationY, yValue);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(durTime);
        animatorSet.playTogether(animator, animator2);
        animatorSet.start();
    }


    @Override
    protected void onResume() {
        Log.v("wb", "activity---onresume");
        super.onResume();
        new BackgroundService().stopTimer(this,this);
        if (!isAllowExit) {
            Intent intent = new Intent(this, BackgroundService.class);
            this.stopService(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isAllowExit) {
            T.show(this, getString(R.string.msg_auto_release), true);
            Intent intent = new Intent(this, BackgroundService.class);
            this.startService(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isAllowExit) {
            finish();
            System.exit(0);
            return super.onKeyDown(keyCode, event);
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mTv_tp.setText("按返回键是我老婆！！！");
            T.show(this, getString(R.string.msg_back_useless), true);
            Log.i("wb", "-------------按返回键是我老婆--------------");
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            mTv_tp.setText("home键用不了！！！");
            Log.i("wb", "-------------home键用不了--------------");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void registerReceiver() {
        mHomeWatcherReceiver = new HomeWatcherReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(mHomeWatcherReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        Log.v("wb", "---activity---ondestroy");
        super.onDestroy();
    }

    @Override
    public void stop(String msg) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        T.show(this, msg, true);
    }
}
