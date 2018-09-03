//
// Created by Asim Williams on 9/1/18.
//

#ifndef MYSYNTH_SINEOSCILLATOR_H
#define MYSYNTH_SINEOSCILLATOR_H

#include <atomic>

using namespace std;

class SineOscillator {
public:
    void setSampleRate(int32_t sampleRate);
    void render(float *audioData, int32_t numFrames);
    void setWaveOn(bool waveIsOn, float frequency);
private:
    int32_t sampleRate;
    float frequency = 440;
    float phase = 0.0;
    double phaseIncrement = 0.0;
    atomic<bool> waveIsOn = {false};
};


#endif //MYSYNTH_SINEOSCILLATOR_H
