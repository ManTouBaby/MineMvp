package com.hrw.mvplibrary.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hrw.loainganimviewlibrary.ball.BallAnimView;
import com.hrw.mvplibrary.R;
import com.hrw.mvplibrary.butterknife.ButterKnife;
import com.hrw.mvplibrary.toast.T;


/**
 * @author:MtBaby
 * @date:2017/09/20 14:56
 * @desc:
 */

public abstract class BaseOldActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView {
    protected P mPresenter;
    //显示内容
    FrameLayout flContent;
    //显示错误页面
    RelativeLayout rvLoadingError;
    LinearLayout llErrorClick;
    //显示加载Dialog
    RelativeLayout rvLoadingDialog;
    BallAnimView animView;
    TextView loadingLabel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_base_old_layout);
        flContent = (FrameLayout) findViewById(R.id.fl_base_show_content);

        rvLoadingError = (RelativeLayout) findViewById(R.id.rl_base_show_error);
        llErrorClick = (LinearLayout) findViewById(R.id.ll_base_loading_error_bt);
        llErrorClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorPageClick();
            }
        });

        rvLoadingDialog = (RelativeLayout) findViewById(R.id.rl_base_loading_dialog);
        animView = (BallAnimView) findViewById(R.id.bav_base_loading_anim);
        loadingLabel = (TextView) findViewById(R.id.tv_base_loading_label);

        if (createContentView() != -1) {
            View view = View.inflate(this, createContentView(), null);
            flContent.addView(view);
        }
        ButterKnife.initBindView(this);
        ActivityManager.addActivity(this);
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
    }

    protected void onErrorPageClick() {
    }

    public Context getContent() {
        return this;
    }

    public BaseOldActivity getThis() {
        return BaseOldActivity.this;
    }

    /**
     * 抽象方法获取布局文件
     *
     * @return
     */
    protected abstract int createContentView();

    /**
     * 抽象方法获取Presenter类
     *
     * @return
     */
    protected abstract P createPresenter();


    @Override
    public void startLoading() {
        if (rvLoadingError != null) rvLoadingError.setVisibility(View.GONE);
        if (rvLoadingDialog != null) rvLoadingDialog.setVisibility(View.VISIBLE);
        if (animView != null) animView.startAnimator();
    }

    @Override
    public void startLoading(String message) {
        if (rvLoadingError != null) rvLoadingError.setVisibility(View.GONE);
        if (loadingLabel != null) loadingLabel.setText(message);
        if (rvLoadingDialog != null) rvLoadingDialog.setVisibility(View.VISIBLE);
        if (animView != null) animView.startAnimator();
    }

    @Override
    public void stopLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (rvLoadingError != null) rvLoadingError.setVisibility(View.GONE);
                if (rvLoadingDialog != null) rvLoadingDialog.setVisibility(View.GONE);
                if (animView != null) animView.stopAnimator();
            }
        });
    }

    @Override
    public void showErrorPage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (rvLoadingError != null) rvLoadingError.setVisibility(View.VISIBLE);
                if (rvLoadingDialog != null) rvLoadingDialog.setVisibility(View.GONE);
                if (animView != null) animView.stopAnimator();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (rvLoadingDialog != null && rvLoadingDialog.getVisibility() == View.VISIBLE) {
            rvLoadingDialog.setVisibility(View.GONE);
            if (animView != null) animView.stopAnimator();
        } else {
            super.onBackPressed();
        }
    }

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

}
