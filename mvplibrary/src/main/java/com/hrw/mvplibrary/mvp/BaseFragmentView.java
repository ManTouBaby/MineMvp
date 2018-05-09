package com.hrw.mvplibrary.mvp;

/**
 * @desc:
 * @author:Hrw
 * @date:2018/04/25 下午 3:26
 * @version:1.0.0
 */
public interface BaseFragmentView extends BaseView {
    void startPageLoading();

    void startPageLoading(String msg);

    void stopPageLoading();
}
