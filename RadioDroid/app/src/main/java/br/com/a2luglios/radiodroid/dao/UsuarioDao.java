package br.com.a2luglios.radiodroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.a2luglios.radiodroid.modelo.Usuario;
import br.com.a2luglios.radiodroid.util.BancoUtil;

/**
 * Created by ettoreluglio on 08/05/17.
 */

public class UsuarioDao extends BancoUtil {

    public static final String TABELA = Usuario.class.getSimpleName();

    public static final String CREATE_QUERY = "CREATE TABLE " + TABELA + " (" +
            "id INTEGER PRIMARY KEY" + ");";

    public static final String UPDATE_QUERY = "";

    public UsuarioDao(Context context) {
        super(context);
    }

    @Override
    protected Usuario getObjectFromCursor(Cursor c) {
        Usuario usuario = new Usuario();
        usuario.setId(c.getLong(c.getColumnIndex("id")));

        return usuario;
    }

    public List<Usuario> getList() {
        List<Usuario> lista = new ArrayList<>();
        Cursor c = getWritableDatabase().query(TABELA, null, null, null, null, null, null);
        while (c.moveToNext()) lista.add(getObjectFromCursor(c));
        return lista;
    }

    @Override
    public Usuario getObjectPorID(Managed managed) {
        Cursor c = getWritableDatabase().query(TABELA, null, "id=?",
                new String[]{managed.getId().toString()}, null, null, null);
        if ( c.moveToNext() ) return getObjectFromCursor(c);
        else return null;
    }

}
