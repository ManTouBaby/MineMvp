package com.hrw.mvplibrary.mvp;

/**
 * @author:MtBaby
 * @date:2017/09/20 14:56
 * @desc:
 */

public interface BaseView {
    void startLoading();

    void startLoading(String msg);

    void stopLoading();

    void showErrorPage();

    void toastLong(String msg);

    void toastShort(String msg);
}
