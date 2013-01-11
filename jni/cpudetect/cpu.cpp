#include <cpu-features.h>
#include <jni.h>

#define JNI_NOARGS(X) Java_com_danieru_miraie_nds_DeSmuME_##X(JNIEnv* env, jclass* clazz)

#define CPUTYPE_COMPAT 0
#define CPUTYPE_V7 1
#define CPUTYPE_NEON 2

extern "C"
{


	jint JNI_NOARGS(getCPUType)
	{
		AndroidCpuFamily cpuFamily = android_getCpuFamily();
		uint64_t cpuFeatures = android_getCpuFeatures();
		if (cpuFamily == ANDROID_CPU_FAMILY_ARM &&
        		(cpuFeatures & ANDROID_CPU_ARM_FEATURE_NEON) != 0)
    	{
			return CPUTYPE_NEON;
    	}
		else if (cpuFamily == ANDROID_CPU_FAMILY_ARM &&
        		(cpuFeatures & ANDROID_CPU_ARM_FEATURE_ARMv7) != 0)
		{
			return CPUTYPE_V7;
		}
    	else
    	{
        	return CPUTYPE_COMPAT;
    	}
	}

}
