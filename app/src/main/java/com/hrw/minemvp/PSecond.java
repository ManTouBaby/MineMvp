package com.hrw.minemvp;

import android.os.Handler;

import com.hrw.mvplibrary.mvp.BasePresenter;

/**
 * @desc:
 * @author:Hrw
 * @date:2018/05/17 上午 9:18
 * @version:1.0.0
 */
public class PSecond extends BasePresenter<ViewSecond> {
    Handler handler = new Handler();

    public void mvpTest() {
        mView.startLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.stopLoading();
                    }
                });
            }
        }).start();
    }
}
