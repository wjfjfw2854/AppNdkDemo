package cn.com.mobile.appndkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cisetech.mobile.inspectdatalibrary.helpers.InspectDataHelper;
import cisetech.mobile.inspectdatalibrary.lis.Callback;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("hello_jni_wjf");
    }

    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        createAnr();
        setContentView(R.layout.activity_main);
        txtOpt();
        InspectDataHelper.getInstance(ApplicationNdkMulti.getInstance()).initData(this);
    }

    private void txtOpt() {
        TextView tv  = findViewById(R.id.txt);
        if (tv != null) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 2024/8/5制造1个崩溃
//                    View v = null;
//                    v.setBackgroundColor(0xff888888);
                    count ++;
                    tv.setText(getFromJNI(count,"你好！我来自jni-Hello i am from jni!--count="+count));
//                    tv.setText("ndk的jenkins制包有问题，先不测试NKD，count="+count);

                    String url = "http://www.baidu.com";
                    InspectDataHelper.getInstance(ApplicationNdkMulti.getInstance()).inspectThenCallBack(new Callback<String>() {

                        @Override
                        public void onFailure(String str) {
                            Log.e("cisetech>>>>", "MainActivity onFailure: " + str);
                        }

                        @Override
                        public void onSuccess(String str) {
                            Log.e("cisetech>>>>", "MainActivity onSuccess: " + str);
                        }
                    },ApplicationNdkMulti.getInstance(),url);
                }
            });
        }
    }

    public native String getFromJNI(int count,String str);

    private void createAnr() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);//10秒
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);//6秒
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },0);
        try {
            Thread.sleep(30000);//20秒
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}