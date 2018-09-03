//
// Created by Asim Williams on 9/1/18.
//

#ifndef MYSYNTH_AUDIOENGINE_H
#define MYSYNTH_AUDIOENGINE_H

#include "SineOscillator.h"
#include <aaudio/AAudio.h>

class AudioEngine {
public:
    bool startStream();
    void stopStream();
    void restartStream();
    void turnOscillatorToneOn(bool toneIsOn, float frequency);
private:
    SineOscillator sineOscillator;
    AAudioStream *stream;
};


#endif //MYSYNTH_AUDIOENGINE_H
