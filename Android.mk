LOCAL_PATH:= $(call my-dir)

# the library
# ============================================================
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)
            
LOCAL_MODULE := com.winca.tools

LOCAL_CERTIFICATE := platform

include $(BUILD_STATIC_JAVA_LIBRARY)

