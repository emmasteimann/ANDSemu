# Android ndk makefile for DSdroid

APP_STL := gnustl_static
APP_ABI := armeabi-v7a
# For releases
APP_CFLAGS := -Ofast -ftree-vectorize -fsingle-precision-constant -fprefetch-loop-arrays -fvariable-expansion-in-unroller -ffast-math -funroll-loops -fomit-frame-pointer -fno-math-errno -funsafe-math-optimizations -ffinite-math-only -fdata-sections -fbranch-target-load-optimize2 -fno-exceptions -fno-stack-protector -flto -fforce-addr -funswitch-loops -ftree-loop-im -ftree-loop-ivcanon -fivopts -Wno-psabi
APP_LDFLAGS := -flto
APP_PLATFORM := android-9