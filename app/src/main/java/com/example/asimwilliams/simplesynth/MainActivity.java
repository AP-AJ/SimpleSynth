package com.example.asimwilliams.simplesynth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * This class provides the UI functionality of the keyboard to the user.
 */
public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    private float frequency;
    private Button cKey;
    private Button cSharp;
    private Button dKey;
    private Button dSharp;
    private Button eKey;
    private Button fKey;
    private Button fSharp;
    private Button gKey;
    private Button gSharp;
    private Button aKey;
    private Button aSharp;
    private Button bKey;
    private Button cTwoKey;
    private Button cSharpTwo;
    private Button dTwoKey;
    private Button dSharpTwo;
    private Button eTwoKey;
    private Button fTwoKey;
    private Button fSharpTwo;
    private Button gTwoKey;
    private Button gSharpTwo;
    private Button aTwoKey;



    private native void touchEvent(int action, float frequency);

    private native void startEngine();

    private native void stopEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(view);
        setupKeys(view);
        startEngine();
    }

    @SuppressLint("ClickableViewAccessibility")
    void setupKeys(View v) {
        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            Boolean pressedDown = true;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    pressedDown = true;
                    changeKeyBackground(v, pressedDown);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    pressedDown = false;
                    changeKeyBackground(v, pressedDown);
                }
                matchKeyToFrequency(v);
                touchEvent(event.getAction(), frequency);
                return true;
            }
        };
        cKey = v.findViewById(R.id.c_key);
        cKey.setOnTouchListener(onTouchListener);
        cSharp = v.findViewById(R.id.c_sharp);
        cSharp.setOnTouchListener(onTouchListener);
        dKey = v.findViewById(R.id.d_key);
        dKey.setOnTouchListener(onTouchListener);
        dSharp = v.findViewById(R.id.d_sharp);
        dSharp.setOnTouchListener(onTouchListener);
        eKey = v.findViewById(R.id.e_key);
        eKey.setOnTouchListener(onTouchListener);
        fKey = v.findViewById(R.id.f_key);
        fKey.setOnTouchListener(onTouchListener);
        fSharp = v.findViewById(R.id.f_sharp);
        fSharp.setOnTouchListener(onTouchListener);
        gKey = v.findViewById(R.id.g_key);
        gKey.setOnTouchListener(onTouchListener);
        gSharp = v.findViewById(R.id.g_sharp);
        gSharp.setOnTouchListener(onTouchListener);
        aKey = v.findViewById(R.id.a_key);
        aKey.setOnTouchListener(onTouchListener);
        aSharp = v.findViewById(R.id.a_sharp);
        aSharp.setOnTouchListener(onTouchListener);
        bKey = v.findViewById(R.id.b_key);
        bKey.setOnTouchListener(onTouchListener);
        cTwoKey = v.findViewById(R.id.c_two_key);
        cTwoKey.setOnTouchListener(onTouchListener);
        cSharpTwo = v.findViewById(R.id.c_sharp_two);
        cSharpTwo.setOnTouchListener(onTouchListener);
        dTwoKey = v.findViewById(R.id.d_two_key);
        dTwoKey.setOnTouchListener(onTouchListener);
        dSharpTwo = v.findViewById(R.id.d_sharp_two);
        dSharpTwo.setOnTouchListener(onTouchListener);
        eTwoKey = v.findViewById(R.id.e_two_key);
        eTwoKey.setOnTouchListener(onTouchListener);
        fTwoKey = v.findViewById(R.id.f_two_key);
        fTwoKey.setOnTouchListener(onTouchListener);
        fSharpTwo = v.findViewById(R.id.f_sharp_two);
        fSharpTwo.setOnTouchListener(onTouchListener);
        gTwoKey = v.findViewById(R.id.g_two_key);
        gTwoKey.setOnTouchListener(onTouchListener);
        gSharpTwo = v.findViewById(R.id.g_sharp_two);
        gSharpTwo.setOnTouchListener(onTouchListener);
        aTwoKey = v.findViewById(R.id.a_two_key);
        aTwoKey.setOnTouchListener(onTouchListener);
    }

    void matchKeyToFrequency(View v) {
        int id = v.getId();
        if (id == cKey.getId()) {
            frequency = 261;
        } else if (id == cSharp.getId()) {
            frequency = 277;
        } else if (id == dKey.getId()) {
            frequency = 293;
        } else if (id == dSharp.getId()) {
            frequency = 311;
        } else if (id == eKey.getId()) {
            frequency = 329;
        } else if (id == fKey.getId()) {
            frequency = 349;
        } else if (id == fSharp.getId()) {
            frequency = 369;
        } else if (id == gKey.getId()) {
            frequency = 391;
        } else if (id == gSharp.getId()) {
            frequency = 415;
        } else if (id == aKey.getId()) {
            frequency = 440;
        } else if (id == aSharp.getId()) {
            frequency = 466;
        } else if (id == bKey.getId()) {
            frequency = 493;
        } else if (id == cTwoKey.getId()) {
            frequency = 523;
        } else if (id == cSharpTwo.getId()) {
            frequency = 554;
        } else if (id == dTwoKey.getId()) {
            frequency = 587;
        } else if (id == dSharpTwo.getId()) {
            frequency = 622;
        } else if (id == eTwoKey.getId()) {
            frequency = 659;
        } else if (id == fTwoKey.getId()) {
            frequency = 698;
        } else if (id == fSharpTwo.getId()) {
            frequency = 739;
        } else if (id == gTwoKey.getId()) {
            frequency = 783;
        } else if (id == gSharpTwo.getId()) {
            frequency = 830;
        } else if (id == aTwoKey.getId()) {
            frequency = 880;
        }
    }

    void changeKeyBackground(View v, Boolean pressedDown) {
        int id = v.getId();
        if (id == cKey.getId()) {
            if (pressedDown) {
                cKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                cKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == cSharp.getId()) {
            if (pressedDown) {
                cSharp.setBackground(getDrawable(R.drawable.black_key_down));
            } else {
                cSharp.setBackground(getDrawable(R.drawable.black_key_up));
            }
        } else if (id == dKey.getId()) {
            if (pressedDown) {
                dKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                dKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == dSharp.getId()) {
            if (pressedDown) {
                dSharp.setBackground(getDrawable(R.drawable.black_key_down));
            } else {
                dSharp.setBackground(getDrawable(R.drawable.black_key_up));
            }
        } else if (id == eKey.getId()) {
            if (pressedDown) {
                eKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                eKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == fKey.getId()) {
            if (pressedDown) {
                fKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                fKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == fSharp.getId()) {
            if (pressedDown) {
                fSharp.setBackground(getDrawable(R.drawable.black_key_down));
            } else {
                fSharp.setBackground(getDrawable(R.drawable.black_key_up));
            }
        } else if (id == gKey.getId()) {
            if (pressedDown) {
                gKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                gKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == gSharp.getId()) {
            if (pressedDown) {
                gSharp.setBackground(getDrawable(R.drawable.black_key_down));
            } else {
                gSharp.setBackground(getDrawable(R.drawable.black_key_up));
            }
        } else if (id == aKey.getId()) {
            if (pressedDown) {
                aKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                aKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == aSharp.getId()) {
            if (pressedDown) {
                aSharp.setBackground(getDrawable(R.drawable.black_key_down));
            } else {
                aSharp.setBackground(getDrawable(R.drawable.black_key_up));
            }
        } else if (id == bKey.getId()) {
            if (pressedDown) {
                bKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                bKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == cTwoKey.getId()) {
            if (pressedDown) {
                cTwoKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                cTwoKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == cSharpTwo.getId()) {
            if (pressedDown) {
                cSharpTwo.setBackground(getDrawable(R.drawable.black_key_down));
            } else {
                cSharpTwo.setBackground(getDrawable(R.drawable.black_key_up));
            }
        } else if (id == dTwoKey.getId()) {
            if (pressedDown) {
                dTwoKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                dTwoKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == dSharpTwo.getId()) {
            if (pressedDown) {
                dSharpTwo.setBackground(getDrawable(R.drawable.black_key_down));
            } else {
                dSharpTwo.setBackground(getDrawable(R.drawable.black_key_up));
            }
        } else if (id == eTwoKey.getId()) {
            if (pressedDown) {
                eTwoKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                eTwoKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == fTwoKey.getId()) {
            if (pressedDown) {
                fTwoKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                fTwoKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == fSharpTwo.getId()) {
            if (pressedDown) {
                fSharpTwo.setBackground(getDrawable(R.drawable.black_key_down));
            } else {
                fSharpTwo.setBackground(getDrawable(R.drawable.black_key_up));
            }
        } else if (id == gTwoKey.getId()) {
            if (pressedDown) {
                gTwoKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                gTwoKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        } else if (id == gSharpTwo.getId()) {
            if (pressedDown) {
                gSharpTwo.setBackground(getDrawable(R.drawable.black_key_down));
            } else {
                gSharpTwo.setBackground(getDrawable(R.drawable.black_key_up));
            }
        } else if (id == aTwoKey.getId()) {
            if (pressedDown) {
                aTwoKey.setBackground(getDrawable(R.drawable.white_key_down));
            } else {
                aTwoKey.setBackground(getDrawable(R.drawable.white_key_up));
            }
        }
    }

    @Override
    public void onDestroy() {
        stopEngine();
        super.onDestroy();
    }
}
