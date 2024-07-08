LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := hello_jni_wjf
LOCAL_SRC_FILES := test.cpp

LOCAL_LDLIBS := -lm -llog
LOCAL_SHARED_LIBRARIES := liblog libcutils
include $(BUILD_SHARED_LIBRARY)