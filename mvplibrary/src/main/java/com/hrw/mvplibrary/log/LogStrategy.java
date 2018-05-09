package com.hrw.mvplibrary.log;

public interface LogStrategy {

    void log(int priority, String tag, String message);
}
