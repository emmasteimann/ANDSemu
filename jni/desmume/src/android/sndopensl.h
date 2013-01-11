#ifndef _SNDOPENSL_H
#define _SNDOPENSL_H

#define SNDCORE_OPENSL 1

extern SoundInterface_struct SNDOpenSL;

int SNDOpenSLInit(int buffersize);
void SNDOpenSLDeInit();
void SNDOpenSLUpdateAudio(s16 *buffer, u32 num_samples);
u32 SNDOpenSLGetAudioSpace();
void SNDOpenSLMuteAudio();
void SNDOpenSLUnMuteAudio();
void SNDOpenSLSetVolume(int volume);
void SNDOpenSLPaused(bool paused);

#endif