package br.com.a2luglios.radiodroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.a2luglios.radiodroid.modelo.Usuario;

/**
 * Created by ettoreluglio on 08/05/17.
 */

public class UsuarioAdapter extends BaseAdapter {

    private Context ctx;
    private List<Usuario> lista;

    public UsuarioAdapter(Context ctx, List<Usuario> lista) {
        this.ctx = ctx;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
