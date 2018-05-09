package com.hrw.mvplibrary.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hrw.datedialoglib.loading.LoadDialogUtils;
import com.hrw.mvplibrary.R;
import com.hrw.mvplibrary.butterknife.ButterKnife;
import com.hrw.mvplibrary.toast.T;


/**
 * @author:MtBaby
 * @date:2018/04/24 19:23
 * @desc:
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView {
    protected P mPresenter;
    //显示内容
    FrameLayout flContent;
    //显示错误页面
    RelativeLayout rvLoadingError;
    LinearLayout llErrorClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        flContent = (FrameLayout) findViewById(R.id.fl_base_show_content);
        rvLoadingError = (RelativeLayout) findViewById(R.id.rl_base_show_error);
        llErrorClick = (LinearLayout) findViewById(R.id.ll_base_loading_error_bt);
        llErrorClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorPageClick();
            }
        });

        if (createContentView() != -1) {
            View view = View.inflate(this, createContentView(), null);
            flContent.addView(view);
        } else {
            new Throwable("A layout file must be returned");
        }
        ButterKnife.initBindView(this);
        ActivityManager.addActivity(this);
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }

    }

    @Override
    protected void onResume() {
        initData();
        super.onResume();
    }

    protected abstract void initData();

    protected abstract P createPresenter();

    protected abstract int createContentView();


    protected void goToActivity(Class<?> aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }

    protected void goToActivity(Class<?> aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void goToActivityForResult(Class<?> aClass, int requestCode) {
        Intent intent = new Intent(this, aClass);
        startActivityForResult(intent, requestCode);
    }

    protected void goToActivityForResult(Class<?> aClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startLoading() {
        this.startLoading(null);
    }

    @Override
    public void startLoading(String msg) {
        LoadDialogUtils.instance().startLoading(this, msg);
    }

    @Override
    public void stopLoading() {
        LoadDialogUtils.instance().stopLoading();
        if (rvLoadingError.getVisibility() == View.VISIBLE) rvLoadingError.setVisibility(View.GONE);
    }

    @Override
    public void showErrorPage() {
        rvLoadingError.setVisibility(View.VISIBLE);
    }

    @Override
    public void toastLong(String msg) {
        T.showLong(this, msg);
    }

    @Override
    public void toastShort(String msg) {
        T.showShort(this, msg);
    }

    @Override
    public void finish() {
        ActivityManager.removeActivity(this);
        super.finish();
    }

    public void finishAll() {
        ActivityManager.removeAll();
    }

    protected void onErrorPageClick() {

    }

}
