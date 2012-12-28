# Android ndk makefile for ds4droid

LOCAL_PATH := $(call my-dir)

MY_LOCAL_PATH := $(LOCAL_PATH)

include $(CLEAR_VARS)


LOCAL_MODULE    		:= 	libdesmumeneon
LOCAL_C_INCLUDES		:= 	$(LOCAL_PATH)/desmume/src \
							$(LOCAL_PATH)/desmume/src/android \
							$(LOCAL_PATH)/desmume/src/android/7z/CPP \
							$(LOCAL_PATH)/desmume/src/android/7z/CPP/include_windows \
							$(LOCAL_PATH)/desmume/src/android/agg/include
						   
include $(LOCAL_PATH)/desmume_core_files.mk
LOCAL_SRC_FILES         +=  desmume/src/android/neontest.cpp
							
LOCAL_ARM_NEON 			:= true
LOCAL_ARM_MODE 			:= arm
LOCAL_CFLAGS			:= -DANDROID -DHAVE_LIBAGG -DHAVE_LIBZ -fexceptions -ftree-vectorize -fsingle-precision-constant -fprefetch-loop-arrays -fvariable-expansion-in-unroller -DHAVE_NEON=1 -mfloat-abi=softfp -mfpu=neon -marm -march=armv7-a -mtune=cortex-a9
LOCAL_STATIC_LIBRARIES 	:= aggneon mathneon sevenzip 
LOCAL_LDLIBS 			:= -llog -lz -lGLESv1_CM -lEGL -ljnigraphics -lOpenSLES -landroid 

include $(BUILD_SHARED_LIBRARY)

include $(MY_LOCAL_PATH)/desmume/src/android/agg/agg_neon.mk
include $(MY_LOCAL_PATH)/desmume/src/android/math-neon/Android.mk

