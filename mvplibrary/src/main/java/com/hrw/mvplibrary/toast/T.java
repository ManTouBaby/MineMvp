package com.hrw.mvplibrary.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * @author:MtBaby
 * @date:2017/09/25 14:48
 * @desc:
 */

public class T {
    /**
     * 长时间吐司
     *
     * @param context
     * @param label
     */
    public static void showLong(Context context, String label) {
        Toast.makeText(context, label, Toast.LENGTH_LONG).show();
    }

    /**
     * 短时间吐司
     *
     * @param context
     * @param label
     */
    public static void showShort(Context context, String label) {
        Toast.makeText(context, label, Toast.LENGTH_SHORT).show();
    }
}
