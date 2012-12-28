# Android ndk makefile for ds4droid

LOCAL_PATH := $(call my-dir)

MY_LOCAL_PATH := $(LOCAL_PATH)

include $(CLEAR_VARS)


LOCAL_MODULE    		:= 	libdesmumecompat
LOCAL_C_INCLUDES		:= 	$(LOCAL_PATH)/desmume/src \
							$(LOCAL_PATH)/desmume/src/android \
							$(LOCAL_PATH)/desmume/src/android/7z/CPP \
							$(LOCAL_PATH)/desmume/src/android/7z/CPP/include_windows \
							$(LOCAL_PATH)/desmume/src/android/agg/include
						   
include $(LOCAL_PATH)/desmume_core_files.mk
							
LOCAL_ARM_MODE 			:= thumb
LOCAL_ARM_NEON 			:= false
LOCAL_CFLAGS			:= -DANDROID -DHAVE_LIBAGG -DHAVE_LIBZ -fexceptions
LOCAL_STATIC_LIBRARIES 	:= aggcompat sevenzip
LOCAL_LDLIBS 			:= -llog -lz -lGLESv1_CM -lEGL -ljnigraphics -lOpenSLES -landroid

include $(BUILD_SHARED_LIBRARY)

