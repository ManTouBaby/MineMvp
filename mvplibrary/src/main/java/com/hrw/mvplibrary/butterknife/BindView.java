package com.hrw.mvplibrary.butterknife;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:MtBaby
 * @date:2017/09/22 14:56
 * @desc:
 */

//作用域为属性
@Target(ElementType.FIELD)
//运行时的有效
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    @IdRes int value();
}
