//
// Created by Asim Williams on 9/1/18.
//

#include "AudioEngine.h"
#include <android/log.h>
#include <mutex>
#include <thread>

using namespace std;

// According to google.
// "Double-buffering offers a good tradeoff between latency and protection against glitches."
constexpr int32_t kBufferSizeInBursts = 2;

aaudio_data_callback_result_t dataCallback(
        AAudioStream *stream,
        void *userData,
        void *audioData,
        int32_t numFrames) {

    ((SineOscillator *) (userData))->render(static_cast<float *>(audioData), numFrames);
    return AAUDIO_CALLBACK_RESULT_CONTINUE;
}

void errorCallback(AAudioStream *stream,
                   void *userData,
                   aaudio_result_t error){
    if (error == AAUDIO_ERROR_DISCONNECTED){
        __bind<void (AudioEngine::*)(), AudioEngine *> restartFunction = bind(&AudioEngine::restartStream,
                                                                              static_cast<AudioEngine *>(userData));
        new thread(restartFunction);
    }
}

// 1)Create a builder object to configure stream
// 2)Open Stream
// 3)Create stream, set sample rate and buffer size
// 4)Start stream
bool AudioEngine::startStream() {
    // Creating and configuring stream builder
    AAudioStreamBuilder *builder;
    AAudio_createStreamBuilder(&builder);
    AAudioStreamBuilder_setFormat(builder, AAUDIO_FORMAT_PCM_FLOAT);
    AAudioStreamBuilder_setChannelCount(builder, 1);
    AAudioStreamBuilder_setPerformanceMode(builder, AAUDIO_PERFORMANCE_MODE_LOW_LATENCY);

    // Callback
    AAudioStreamBuilder_setDataCallback(builder, ::dataCallback, &sineOscillator);

    // Error Callback
    AAudioStreamBuilder_setErrorCallback(builder, ::errorCallback, this);

    // Open the stream. Degbugging
    aaudio_result_t result = AAudioStreamBuilder_openStream(builder, &stream);
    if (result != AAUDIO_OK) {
        __android_log_print(ANDROID_LOG_ERROR, "AudioEngine", "Error opening stream %s",
                            AAudio_convertResultToText(result));
        return false;
    }

    // Set sample rate for Oscillator
    int32_t sampleRate = AAudioStream_getSampleRate(stream);
    sineOscillator.setSampleRate(sampleRate);

    // Set the buffer size.
    AAudioStream_setBufferSizeInFrames(
            stream, AAudioStream_getFramesPerBurst(stream) * kBufferSizeInBursts);

    // Starts the stream. Degbugging
    result = AAudioStream_requestStart(stream);
    if (result != AAUDIO_OK) {
        __android_log_print(ANDROID_LOG_ERROR, "AudioEngine", "Error starting stream %s",
                            AAudio_convertResultToText(result));
        return false;
    }

    AAudioStreamBuilder_delete(builder);
    return true;
}

// Pretty simple just closer the stream
void AudioEngine::stopStream() {
    if (stream != nullptr) {
        AAudioStream_requestStop(stream);
        AAudioStream_close(stream);
    }
}

// Not familiar with "mutex" gotta look into that.
void AudioEngine::restartStream() {
    static mutex restartingLock;
    if (restartingLock.try_lock()){
        stopStream();
        startStream();
        restartingLock.unlock();
    }
}

// Used in Jni bridge to set tone on in response to touch event
void AudioEngine::turnOscillatorToneOn(bool toneIsOn, float frequency) {
    sineOscillator.setWaveOn(toneIsOn, frequency);
}
