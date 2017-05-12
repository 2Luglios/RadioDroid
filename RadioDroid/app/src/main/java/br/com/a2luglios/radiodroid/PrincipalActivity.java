package br.com.a2luglios.radiodroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.a2luglios.radiodroid.adapter.UsuarioAdapter;
import br.com.a2luglios.radiodroid.audio.Microfone;
import br.com.a2luglios.radiodroid.audio.Speaker;
import br.com.a2luglios.radiodroid.dao.Managed;
import br.com.a2luglios.radiodroid.dao.UsuarioDao;
import br.com.a2luglios.radiodroid.modelo.Usuario;

public class PrincipalActivity extends AppCompatActivity {

    private static final String LOG_TAG = "PrincipalActivity";
    private static String mFileName = null;

    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    private Microfone mic;
    private Speaker spk;

    private Button btnPTT;
    private ListView listaColegas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_layout);

        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";

        btnPTT = (Button) findViewById(R.id.btnPTT);
        btnPTT.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
                    Log.i(LOG_TAG, "Gravando");
                    mic.startRecording();
                } else {
                    Log.i(LOG_TAG, "Parando de gravar");
                    mic.stopRecording();

                    Log.i(LOG_TAG, "Tamanho: " + new File(mFileName).getUsableSpace());

                    Log.i(LOG_TAG, "Tocando");
                    spk.startPlaying();
                }

                return false;
            }
        });

        mic = new Microfone(this);
        mic.setmFileName(mFileName);

        spk = new Speaker(this);
        spk.setmFileName(mFileName);

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();
    }

    public void carregaLista() {
        listaColegas = (ListView) findViewById(R.id.listaColegas);

        UsuarioDao dao = new UsuarioDao(this);
        List<Usuario> lista = dao.getList();
        dao.close();

        listaColegas.setAdapter(new UsuarioAdapter(this, lista));
    }

    @Override
    public void onStop() {
        super.onStop();
        mic.finish();
        spk.finish();
    }
}
