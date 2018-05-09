package com.hrw.minemvp;

import android.view.View;
import android.widget.Button;

import com.hrw.mvplibrary.butterknife.BindView;
import com.hrw.mvplibrary.mvp.BaseActivity;
import com.hrw.mvplibrary.mvp.BasePresenter;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bt_click_down_app)
    Button btnShow;

    @Override
    protected void initData() {
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
//                DownloadHelper.instance(this, 2, "下载测试", "http://apk.gfan.net.cn/index.php?c=api&m=down&src=wap&apk=21000k")
//                        .setShowProgress(true)
//                        .start();
                break;
        }
    }
}
