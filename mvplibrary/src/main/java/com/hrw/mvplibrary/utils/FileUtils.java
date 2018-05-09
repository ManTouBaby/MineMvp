package com.hrw.mvplibrary.utils;

import java.io.File;

/**
 * @author:MtBaby
 * @date:2018/01/30 22:43
 * @desc:
 */

public class FileUtils {
    /**
     * 判断文件是否存在
     * @param path 文件的路径
     * @return
     */
    public static boolean isExists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
