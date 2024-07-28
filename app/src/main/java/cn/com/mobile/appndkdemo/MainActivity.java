package cn.com.mobile.appndkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("hello_jni_wjf");
    }

    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOpt();
    }

    private void txtOpt() {
        TextView tv  = findViewById(R.id.txt);
        if (tv != null) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count ++;
//                    tv.setText(getFromJNI(count,"你好！我来自jni-Hello i am from jni!--count="+count));
                    tv.setText("ndk的jenkins制包有问题，先不测试NKD，count="+count);
                }
            });
        }
    }

    public native String getFromJNI(int count,String str);

}