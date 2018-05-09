package com.hrw.mvplibrary.log;

public interface FormatStrategy {

    void log(int priority, String tag, String message);
}
