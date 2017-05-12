package br.com.a2luglios.radiodroid.modelo;

import android.content.ContentValues;

import java.io.Serializable;

import br.com.a2luglios.radiodroid.dao.Managed;

/**
 * Created by ettoreluglio on 08/05/17.
 */

public class Usuario implements Managed {

    private Long id;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", id);
        return values;
    }
}
