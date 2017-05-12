package br.com.a2luglios.radiodroid.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by ettoreluglio on 08/05/17.
 */

public class Speaker {

    private static final String LOG_TAG = "Audio_Speaker";

    private Context ctx;
    private boolean playing;
    private MediaPlayer mPlayer = null;
    private static String mFileName;

    public Speaker(Context ctx) {
        this.ctx = ctx;
    }

    public void startPlaying() {
        mPlayer = new MediaPlayer();
        mPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                Log.i(LOG_TAG, "" + mp.getCurrentPosition());
                return false;
            }
        });
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            Log.i(LOG_TAG, "Tocando...");
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    public void finish () {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public static String getmFileName() {
        return mFileName;
    }

    public static void setmFileName(String mFileName) {
        Speaker.mFileName = mFileName;
    }
}
