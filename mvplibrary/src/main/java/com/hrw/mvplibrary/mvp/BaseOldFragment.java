package com.hrw.mvplibrary.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public abstract class BaseOldFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements BaseView {
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


    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.mine_base_old_layout, null);

        flContent = view.findViewById(R.id.fl_base_show_content);

        rvLoadingError = view.findViewById(R.id.rl_base_show_error);
        llErrorClick = view.findViewById(R.id.ll_base_loading_error_bt);
        llErrorClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorPageClick();
            }


        });

        rvLoadingDialog = view.findViewById(R.id.rl_base_loading_dialog);
        animView = view.findViewById(R.id.bav_base_loading_anim);
        loadingLabel = view.findViewById(R.id.tv_base_loading_label);

        if (createContentView() != -1) {
            View content = View.inflate(getContext(), createContentView(), null);
            flContent.addView(content);
        }

        ButterKnife.initBindView(this, view);
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }

        return view;

    }

    protected void onErrorPageClick() {

    }


    protected void goToActivity(Class<?> aClass) {
        Intent intent = new Intent(getActivity(), aClass);
        startActivity(intent);
    }

    protected void goToActivity(Class<?> aClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void goToActivityForResult(Class<?> aClass, int requestCode) {
        Intent intent = new Intent(getActivity(), aClass);
        startActivityForResult(intent, requestCode);
    }

    protected void goToActivityForResult(Class<?> aClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), aClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void toastLong(String msg) {
        T.showLong(getContext(), msg);
    }

    @Override
    public void toastShort(String msg) {
        T.showShort(getContext(), msg);
    }

    public BaseOldFragment getThis() {
        return BaseOldFragment.this;
    }

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
        handler.post(new Runnable() {
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
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (rvLoadingError != null) rvLoadingError.setVisibility(View.VISIBLE);
                if (rvLoadingDialog != null) rvLoadingDialog.setVisibility(View.GONE);
                if (animView != null) animView.stopAnimator();
            }
        });

    }


    protected abstract int createContentView();

    protected abstract P createPresenter();


}
