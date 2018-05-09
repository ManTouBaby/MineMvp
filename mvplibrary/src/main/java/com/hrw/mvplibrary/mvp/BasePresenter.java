package com.hrw.mvplibrary.mvp;

import java.lang.ref.WeakReference;

/**
 * @author:MtBaby
 * @date:2017/09/20 14:56
 * @desc:
 */

public class BasePresenter<V extends BaseView> {
    protected WeakReference<V> mWeakReference;

    public void attachView(V v) {
        mWeakReference = new WeakReference<V>(v);
    }


    protected V getView() {
        return mWeakReference.get();
    }

    public boolean isAttached() {
        return mWeakReference != null && mWeakReference.get() != null;
    }

    public void detachView() {
        if (mWeakReference != null) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

}
