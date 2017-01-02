package com.example.federicomarchesi.bottegadelcaffe;

import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by federico.marchesi on 02/01/2017.
 */

public class Speaker implements TextToSpeech.OnInitListener {

    Context mContext;
    private TextToSpeech tts;
    private boolean ready = false;
    private boolean allowed = false;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int i) {
                    switch (i) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
//                            startMediaPlayer();
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            destroy();
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            stopTTS();
                            break;
                    }

                }
            };


    public Speaker(Context context) {
        tts = new TextToSpeech(context, this);
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mContext = context;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Change this to match your
            // locale
            tts.setLanguage(Locale.ITALIAN);
            ready = true;
        } else {
            ready = false;
        }
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void allow(boolean allowed) {
        this.allowed = allowed;
    }

    public void speak(String text) {

        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                // Speaking started.

            }

            @Override
            public void onDone(String utteranceId) {
                // Speaking stopped.
                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

            }


            @Override
            public void onError(String utteranceId) {
                // There was an error.
            }
        });

        // Speak only if the TTS is ready
        // and the user has allowed speech
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (ready && allowed && result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            HashMap<String, String> hash = new HashMap<>();
            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                    String.valueOf(AudioManager.STREAM_NOTIFICATION));
//            tts.speak(text, TextToSpeech.QUEUE_ADD, hash);
            tts.speak(text, TextToSpeech.QUEUE_ADD, null, "Bottega");
        }
    }

    public void pause(int duration) {
        tts.playSilence(duration, TextToSpeech.QUEUE_ADD, null);
//        tts.playSilentUtterance(duration, TextToSpeech.QUEUE_ADD, "1");
    }

    void stopTTS() {
        tts.stop();
    }

    // Free up resources
    public void destroy() {
        tts.shutdown();
    }
}

