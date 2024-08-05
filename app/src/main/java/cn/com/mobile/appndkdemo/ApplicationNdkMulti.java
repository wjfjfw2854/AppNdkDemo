package cn.com.mobile.appndkdemo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import xcrash.ICrashCallback;
import xcrash.XCrash;

/**
 * Created by wujunfeng on 2024/8/5
 * Describe:
 */
public class ApplicationNdkMulti extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        initXCrash();
    }

    private void initXCrash() {
        /**
         * java,native,anr通用回调
         * */
        ICrashCallback callback = new ICrashCallback() {
            @Override
            public void onCrash(String logPath, String emergency) throws Exception {
                Log.e("wjf>>>>", "initXCrash onCrash --logPath= "+logPath+"--emergency= "+emergency);
                if (!TextUtils.isEmpty(emergency)) {
                    // TODO: 2024/8/2 上传到服务器后删除日志
                } else {

                }
            }
        };
        XCrash.InitParameters pts = new XCrash.InitParameters();
        pts.setAppVersion("1.0.0")
                .setJavaRethrow(true)
                .setJavaLogCountMax(10)
                .setJavaDumpAllThreadsWhiteList(new String[]{"^main$", "^Binder:.*", ".*Finalizer.*"})
                .setJavaDumpAllThreadsCountMax(10)
                .setJavaCallback(callback)
                .setNativeRethrow(false)
                .setNativeLogCountMax(10)
                .setNativeDumpAllThreadsWhiteList(new String[]{"^xcrash\\.sample$", "^Signal Catcher$", "^Jit thread pool$", ".*(R|r)ender.*", ".*Chrome.*"})
                .setNativeDumpAllThreadsCountMax(10)
        .setNativeCallback(callback)
                .setAnrRethrow(false)
                .setAnrLogCountMax(10)
//        .setAnrCallback(callback)
                .setPlaceholderCountMax(3)
                .setPlaceholderSizeKb(512)
                .setLogFileMaintainDelayMs(400);
        xcrash.XCrash.init(this,pts);
    }
}
