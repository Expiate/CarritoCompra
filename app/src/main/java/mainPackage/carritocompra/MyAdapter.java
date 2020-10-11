package mainPackage.carritocompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<objetosListasDeCompra> items;
    private Context context;

    public MyAdapter(ArrayList<objetosListasDeCompra> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        objetosListasDeCompra ldc = (objetosListasDeCompra) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
        TextView tituloTexto = convertView.findViewById(R.id.tituloTextoVerad);
        TextView descText = convertView.findViewById(R.id.descText);

        tituloTexto.setText(ldc.getTitulo());
        descText.setText(ldc.getDesc());
        return convertView;
    }
}
