package com.hrw.minemvp;

import android.view.View;
import android.widget.Button;

import com.hrw.mvplibrary.butterknife.BindView;
import com.hrw.mvplibrary.mvp.BaseActivity;

/**
 * @desc:
 * @author:Hrw
 * @date:2018/05/17 上午 9:16
 * @version:1.0.0
 */
public class ACSecond extends BaseActivity<ViewSecond, PSecond> implements ViewSecond, View.OnClickListener {
    @BindView(R.id.bt_click_down_app)
    Button btnShow;

    @Override
    protected void initData() {
        mPresenter.mvpTest();
        btnShow.setText("ACSecond");
        btnShow.setOnClickListener(this);
    }

    @Override
    protected PSecond createPresenter() {
        return new PSecond();
    }

    @Override
    protected int createContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_click_down_app:
                mPresenter.mvpTest();
                break;
        }
    }
}
