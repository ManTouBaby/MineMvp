package com.hrw.minemvp;

import android.view.View;
import android.widget.Button;

import com.hrw.mvplibrary.butterknife.BindView;
import com.hrw.mvplibrary.mvp.BaseActivity;
import com.hrw.mvplibrary.mvp.BasePresenter;

/**
 * @desc:
 * @author:Hrw
 * @date:2018/05/17 上午 9:23
 * @version:1.0.0
 */
public class ACMain extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.bt_click_down_app)
    Button btnShow;

    @Override
    protected void initData() {
        btnShow.setText("ACMain");
        btnShow.setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int createContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_click_down_app:
                goToActivity(ACFirst.class);
                break;
        }
    }


}
