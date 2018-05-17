package com.hrw.minemvp;

import android.view.View;
import android.widget.Button;

import com.hrw.mvplibrary.butterknife.BindView;
import com.hrw.mvplibrary.mvp.BaseActivity;

public class ACFirst extends BaseActivity<ViewMain, PMain> implements ViewMain, View.OnClickListener {

    @BindView(R.id.bt_click_down_app)
    Button btnShow;

    @Override
    protected void initData() {
        btnShow.setText("ACFirst");
        btnShow.setOnClickListener(this);
        mPresenter.mvpTest();
    }

    @Override
    protected PMain createPresenter() {
        return new PMain();
    }

    @Override
    protected int createContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_click_down_app:
                goToActivity(ACSecond.class);
                break;
        }
    }
}
