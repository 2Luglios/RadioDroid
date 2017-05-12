package br.com.a2luglios.radiodroid.audio;

import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioRecordingConfiguration;
import android.media.MediaRecorder;
import android.util.Log;
import android.util.Xml;

import java.io.IOException;
import java.util.List;

/**
 * Created by ettoreluglio on 08/05/17.
 */

public class Microfone {

    private static final String LOG_TAG = "Audio_Microfone";

    private Context ctx;
    private MediaRecorder mRecorder = null;
    private boolean isRecording;
    private static String mFileName;

    public Microfone(Context ctx) {
        this.ctx = ctx;
    }

    public void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        mRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
            @Override
            public void onInfo(MediaRecorder mr, int what, int extra) {
                Log.i(LOG_TAG, "WHAT: " + what + " EXTRA: " + extra );
            }
        });

        try {
            mRecorder.prepare();
            Log.i(LOG_TAG, "preparado o mic");
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    public void stopRecording() {
        if ( mRecorder != null) {
            try {mRecorder.stop();} catch(Exception e){e.printStackTrace();}
            mRecorder.release();
            mRecorder = null;
        }
    }

    public void finish() {
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }

    public static String getmFileName() {
        return mFileName;
    }

    public static void setmFileName(String mFileName) {
        Microfone.mFileName = mFileName;
    }

}
