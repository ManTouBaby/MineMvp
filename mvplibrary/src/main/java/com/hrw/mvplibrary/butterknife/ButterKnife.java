package com.hrw.mvplibrary.butterknife;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * @author:MtBaby
 * @date:2017/09/22 14:56
 * @desc:
 */

public class ButterKnife {
    /**
     * 注解式绑定控件-方法
     * currentClass 当前的类，为Fragment
     * view 当前的类，为Fragment的根View
     */
    public static void initBindView(Object currentClass, View view) {
        // 通过反射机制获取到类的全部属性
        Field[] fields = currentClass.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                // 返回BindView类型的注解内容
                BindView bindView = field.getAnnotation(BindView.class);
                if (bindView != null) {
                    int viewId = bindView.value();
                    try {
                        field.setAccessible(true);
                        // 将currentClass类中的field赋值为sourceView.findViewById(viewId)
                        field.set(currentClass, view.findViewById(viewId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 注解式绑定控件-方法
     * currentClass 当前的类，为Activity
     */
    public static void initBindView(Object currentClass) {
        // 通过反射机制获取到类的全部属性
        Field[] fields = currentClass.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                // 返回BindView类型的注解内容
                BindView bindView = field.getAnnotation(BindView.class);
                if (bindView != null) {
                    int viewId = bindView.value();
                    try {
                        field.setAccessible(true);
                        // 将currentClass类中的field赋值为sourceView.findViewById(viewId)
                        field.set(currentClass, ((Activity) currentClass).findViewById(viewId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
