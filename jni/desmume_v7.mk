# Android ndk makefile for ds4droid

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)


LOCAL_MODULE    		:= 	libdesmumev7
LOCAL_C_INCLUDES		:= 	$(LOCAL_PATH)/desmume/src \
							$(LOCAL_PATH)/desmume/src/android \
							$(LOCAL_PATH)/desmume/src/android/7z/CPP \
							$(LOCAL_PATH)/desmume/src/android/7z/CPP/include_windows \
							$(LOCAL_PATH)/desmume/src/android/agg/include
						   
include $(LOCAL_PATH)/desmume_core_files.mk

LOCAL_ARM_NEON 			:= true
LOCAL_ARM_MODE 			:= arm
LOCAL_CFLAGS			:= -DANDROID -DHAVE_LIBAGG -DHAVE_LIBZ -fexceptions -ftree-vectorize -fsingle-precision-constant -fprefetch-loop-arrays -fvariable-expansion-in-unroller -mfloat-abi=softfp -mfpu=vfpv3-d16 -marm -march=armv7-a
LOCAL_STATIC_LIBRARIES 	:= aggcompat sevenzip 
LOCAL_LDLIBS 			:= -llog -lz -lGLESv1_CM -lEGL -ljnigraphics -lOpenSLES -landroid 

include $(BUILD_SHARED_LIBRARY)


