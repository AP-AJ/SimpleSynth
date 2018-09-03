//
// Created by Asim Williams on 9/1/18.
//

#include "SineOscillator.h"
#include <math.h>

#define TWO_PI (2 * 3.14159)

// Obtain sampleRate of Audio Stream through Engine
void SineOscillator::setSampleRate(int32_t sampleRate) {
    this->sampleRate = sampleRate;
}

// Turn Oscillator on (send phase value that isn't 0 through engine callback) with frequency matching note
void SineOscillator::setWaveOn(bool waveIsOn, float frequency) {
    this->waveIsOn.store(waveIsOn);
    this->frequency = frequency;
}

// Turned on as soon as stream is started
// Only outputs when "waveIsOn"
void SineOscillator::render(float *audioData, int32_t numFrames) {
    // 1) Sets increment for phase
    phaseIncrement = ((TWO_PI * frequency) / sampleRate);
    // 2) If wave is not on phase is 0
    if (!waveIsOn.load()) phase = 0;
    // Because of callback in engine this is always looping as long as stream is started
    for (int sample = 0; sample < numFrames; sample++) {
        if (waveIsOn.load()) {
            audioData[sample] = sin(phase);
            phase += phaseIncrement;
            if (phase > TWO_PI) {
                phase -= TWO_PI;
            }
        } else {
            audioData[sample] = 0;
        }
    }
}
