package br.com.a2luglios.radiodroid.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import br.com.a2luglios.radiodroid.dao.LocalDao;
import br.com.a2luglios.radiodroid.dao.Managed;
import br.com.a2luglios.radiodroid.dao.UsuarioDao;

/**
 * Created by ettoreluglio on 08/05/17.
 */

public abstract class BancoUtil extends SQLiteOpenHelper {

    private static final String DATABASE = "RadioDroid";
    private static final int VERSION = 1;

    public BancoUtil(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalDao.CREATE_QUERY);
        db.execSQL(UsuarioDao.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(LocalDao.UPDATE_QUERY);
        db.execSQL(UsuarioDao.UPDATE_QUERY);
    }

    public void insertOrUpdate(Managed managed) {
        String tabela = managed.getClass().getSimpleName();
        if ( managed.getId() == null ) {
            long id = getWritableDatabase().insert(tabela, null, managed.getContentValues());
            managed.setId(id);
        } else {
            String[] id = new String[]{managed.getId().toString()};
            getWritableDatabase().update(tabela, managed.getContentValues(), "id=?", id);
        }
    }

    public void delete(Managed managed) {
        String tabela = managed.getClass().getSimpleName();
        getWritableDatabase().delete(tabela, "id=?", new String[]{managed.getId().toString()});
    }

    protected abstract <T extends Managed> T getObjectFromCursor(Cursor c);

    public abstract List<? extends Managed> getList();

    public abstract <T extends Managed> T getObjectPorID (Managed managed);

}
