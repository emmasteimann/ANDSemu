# Android ndk makefile for DSdroid

LOCAL_BUILD_PATH := $(call my-dir)

include $(CLEAR_VARS)

include $(LOCAL_BUILD_PATH)/cpudetect/cpudetect.mk

include $(LOCAL_BUILD_PATH)/desmume_neon.mk
include $(LOCAL_BUILD_PATH)/desmume_v7.mk

include $(LOCAL_BUILD_PATH)/desmume/src/android/agg/agg_compat.mk
include $(LOCAL_BUILD_PATH)/desmume/src/android/7z/7z.mk