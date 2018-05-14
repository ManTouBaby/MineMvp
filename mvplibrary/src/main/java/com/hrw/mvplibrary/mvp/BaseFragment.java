package com.hrw.mvplibrary.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hrw.datedialoglib.loading.LoadDialogUtils;
import com.hrw.loainganimviewlibrary.ball.BallAnimView;
import com.hrw.mvplibrary.R;
import com.hrw.mvplibrary.butterknife.ButterKnife;
import com.hrw.mvplibrary.toast.T;


/**
 * @author:MtBaby
 * @date:2018/04/24 19:24
 * @desc:
 */
public abstract class BaseFragment<V extends BaseFragmentView, P extends BasePresenter<V>> extends Fragment implements BaseFragmentView {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_base_layout, null);
        flContent = view.findViewById(R.id.fl_base_show_content);
        rvLoadingError = view.findViewById(R.id.rl_base_show_error);
        llErrorClick = view.findViewById(R.id.ll_base_loading_error_bt);

        rvLoadingDialog = view.findViewById(R.id.rl_base_loading_dialog);
        animView = view.findViewById(R.id.bav_base_loading_anim);
        loadingLabel = view.findViewById(R.id.tv_base_loading_label);

        llErrorClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorPageClick();
            }


        });
        if (createContentView() != -1) {
            View contentView = inflater.inflate(createContentView(), null);
            flContent.addView(contentView);
        } else {
            new Throwable("A layout file must be returned");
        }
        ButterKnife.initBindView(this);
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
        return view;
    }


    protected abstract P createPresenter();

    protected abstract int createContentView();

    protected void onErrorPageClick() {

    }

    protected void goToActivity(Class<?> aClass) {
        Intent intent = new Intent(getContext(), aClass);
        startActivity(intent);
    }

    protected void goToActivity(Class<?> aClass, Bundle bundle) {
        Intent intent = new Intent(getContext(), aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void goToActivityForResult(Class<?> aClass, int requestCode) {
        Intent intent = new Intent(getContext(), aClass);
        startActivityForResult(intent, requestCode);
    }

    protected void goToActivityForResult(Class<?> aClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getContext(), aClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startLoading() {
        this.startLoading(null);
    }

    @Override
    public void startLoading(String msg) {
        LoadDialogUtils.instance().startLoading(getContext(), msg);
    }


    @Override
    public void stopLoading() {
        LoadDialogUtils.instance().stopLoading();
        if (rvLoadingError.getVisibility() == View.VISIBLE) rvLoadingError.setVisibility(View.GONE);
    }

    @Override
    public void startPageLoading() {
        this.startLoading(null);
    }

    @Override
    public void startPageLoading(String msg) {
        rvLoadingDialog.setVisibility(View.VISIBLE);
        animView.startAnimator();
        if (msg != null) loadingLabel.setText(msg);
    }

    @Override
    public void stopPageLoading() {
        rvLoadingDialog.setVisibility(View.GONE);
        animView.stopAnimator();
    }

    @Override
    public void showErrorPage() {
        rvLoadingError.setVisibility(View.VISIBLE);
    }

    @Override
    public void toastLong(String msg) {
        T.showLong(getContext(), msg);
    }

    @Override
    public void toastShort(String msg) {
        T.showShort(getContext(), msg);
    }

    public void finishAll() {
        ActivityManager.removeAll();
    }


}
