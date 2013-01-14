# Android ndk makefile for DSdroid

# To enable profiling uncomment
PROFILE := 1

APP_STL := gnustl_static
APP_ABI := armeabi armeabi-v7a x86
# For releases
APP_CFLAGS := -ftree-vectorize -fsingle-precision-constant -fvariable-expansion-in-unroller -ffast-math -funroll-loops -fno-math-errno -funsafe-math-optimizations -ffinite-math-only -fdata-sections -fbranch-target-load-optimize2 -fno-exceptions -fno-stack-protector -flto -fforce-addr -funswitch-loops -ftree-loop-im -ftree-loop-ivcanon -fivopts -Wno-psabi

ifdef PROFILE
APP_CFLAGS += -fno-omit-frame-pointer
else
APP_CFLAGS += -fomit-stack-pointers
endif

APP_LDFLAGS := -flto
APP_PLATFORM := android-9

