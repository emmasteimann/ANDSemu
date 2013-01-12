#ifndef _MAIN_H
#define _MAIN_H

#include <jni.h>
#include <android/log.h>

unsigned int GetPrivateProfileInt(JNIEnv* env, const char* lpAppName, const char* lpKeyName, int nDefault, const char* lpFileName);
unsigned int GetTickCount();

#ifdef __cplusplus
bool GetPrivateProfileBool(JNIEnv* env, const char* lpAppName, const char* lpKeyName, bool bDefault, const char* lpFileName);

extern "C" {
#endif

#define APPNAME "ANDSemu"

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, APPNAME, __VA_ARGS__))
#define LOGW(...) ((void)__android_log_print(ANDROID_LOG_WARN, APPNAME, __VA_ARGS__))
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, APPNAME, __VA_ARGS__))


//JNI callbacks go here

#ifdef __cplusplus
} //end extern "C"
#endif

extern unsigned int frameCount;

#endif
