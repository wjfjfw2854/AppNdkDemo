# include <jni.h>
# include <stdio.h>
# include <iostream>
# include <android/log.h>
//#define LOGD(...)__android_log_print(ANDROID_LOG_DEBUG,"keymatch",__VA_ARGS__)
#define LOGI(...)__android_log_print(ANDROID_LOG_INFO,"keymatch",__VA_ARGS__)

extern "C"
{
    JNIEXPORT jstring JNICALL Java_cn_com_mobile_appndkdemo_MainActivity_getFromJNI(JNIEnv *env,jobject obj,jint count,jstring str){
//        char buf[64];
//        sprintf(buf,"%d",count);
//        return env -> NewStringUTF("你好！我来自jni-Hello i am from jni!开心开心的count="+buf);
//        return env -> NewStringUTF(buf);


    //-----------GetStringUTFChars方式需要ReleaseStringUTFChars来释放-------start-------
//    const char* strInput;
//    jboolean isCopy = false;
//    char outPutBuf[256];
//    strInput = env -> GetStringUTFChars(str,&isCopy);
//    sprintf(outPutBuf,"thanks %s",strInput);
//    env -> ReleaseStringUTFChars(str,strInput);
//    return env -> NewStringUTF(outPutBuf);
    //-----------GetStringUTFChars方式需要ReleaseStringUTFChars来释放-------end-------

    //-----------GetStringUTFRegion方式-------start-------
    int len = env -> GetStringLength(str);
    LOGI("[%s]len:%d","本地方法调用",len);

    char outputBuf[len+20];
    char inputBuf[len+20];
    env -> GetStringUTFRegion(str,0,len,inputBuf);
    sprintf(outputBuf,"看到就对头 %s",inputBuf);
    LOGI("[%s]inputBuf:%s","本地方法调用",inputBuf);
    LOGI("[%s]outputBuf:%s","本地方法调用",outputBuf);

    return env -> NewStringUTF(outputBuf);
    //-----------GetStringUTFRegion方式-------end-------
    }
}