package br.com.a2luglios.radiodroid.dao;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by ettoreluglio on 08/05/17.
 */

public interface Managed extends Serializable{

    public void setId(Long id);

    public Long getId();

    public ContentValues getContentValues();

}
