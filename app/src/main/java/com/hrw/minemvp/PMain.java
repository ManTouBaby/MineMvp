package com.hrw.minemvp;

import android.os.Handler;

import com.hrw.mvplibrary.mvp.BasePresenter;

/**
 * @desc:
 * @author:Hrw
 * @date:2018/05/15 上午 11:38
 * @version:1.0.0
 */
public class PMain extends BasePresenter<ViewMain> {
    Handler handler = new Handler();

    public void mvpTest() {
//        final ViewMain viewMain = getView();
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
