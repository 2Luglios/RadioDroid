package br.com.a2luglios.radiodroid.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import br.com.a2luglios.radiodroid.modelo.Local;
import br.com.a2luglios.radiodroid.util.BancoUtil;

/**
 * Created by ettoreluglio on 10/05/17.
 */

public class LocalDao extends BancoUtil {

    public static final String TABELA = Local.class.getSimpleName();

    public static final String CREATE_QUERY = "CREATE TABLE " + TABELA + " (" +
            "id INTEGER PRIMARY KEY" + ");";

    public static final String UPDATE_QUERY = "";

    public LocalDao(Context context) {
        super(context);
    }

    @Override
    protected Local getObjectFromCursor(Cursor c) {
        Local local = new Local();
        local.setId(c.getLong(c.getColumnIndex("id")));
        return local;
    }

    @Override
    public List<? extends Managed> getList() {
        return null;
    }

    @Override
    public <T extends Managed> T getObjectPorID(Managed managed) {
        return null;
    }
}
