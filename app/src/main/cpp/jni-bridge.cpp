#include <jni.h>
#include <android/input.h>
#include "AudioEngine.h"

static AudioEngine *audioEngine = new AudioEngine();

extern "C" {

JNIEXPORT void JNICALL
Java_com_example_asimwilliams_simplesynth_MainActivity_touchEvent(JNIEnv *env, jobject obj, jint action, jfloat frequency) {
    switch (action) {
        case AMOTION_EVENT_ACTION_DOWN:
            audioEngine->turnOscillatorToneOn(true, frequency);
            break;
        case AMOTION_EVENT_ACTION_UP:
            audioEngine->turnOscillatorToneOn(false, frequency);
            break;
        default:
            break;
    }
}

JNIEXPORT void JNICALL
Java_com_example_asimwilliams_simplesynth_MainActivity_startEngine(JNIEnv *env, jobject /* this */) {
    audioEngine->startStream();
}

JNIEXPORT void JNICALL
Java_com_example_asimwilliams_simplesynth_MainActivity_stopEngine(JNIEnv *env, jobject /* this */) {
    audioEngine->stopStream();
}}
