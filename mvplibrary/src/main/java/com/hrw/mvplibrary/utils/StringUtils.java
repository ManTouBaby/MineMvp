package com.hrw.mvplibrary.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.hrw.mvplibrary.log.Log.d;
import static com.hrw.mvplibrary.log.Log.e;

/**
 * @author:MtBaby
 * @date:2018/01/25 18:47
 * @desc:
 */

public class StringUtils {
    public static String json(String json) {
        if (TextUtils.isEmpty(json)) {
            d("Empty/Null json content");
            return null;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(2);
//                d(message);
                return message;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(2);
//                d(message);
                return message;
            }
            e("Invalid Json");
        } catch (JSONException e) {
            e("Invalid Json");
        }
        return json;
    }
}
