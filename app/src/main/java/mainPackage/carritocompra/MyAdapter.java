package mainPackage.carritocompra;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<ObjetosListasDeCompra> items;
    private Context context;
    private MyDialog.ExampleDialogListener listener;
    private TextView tituloTexto;
    private TextView descText;
    private Button editButton;


    public MyAdapter(ArrayList<ObjetosListasDeCompra> items, Context context) {
        this.items = items;
        this.context = context;

        try {
            listener = (MyDialog.ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement Example" +
                    "DialogListener");
        }
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
        ObjetosListasDeCompra ldc = (ObjetosListasDeCompra) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
        tituloTexto = convertView.findViewById(R.id.tituloTextoVerad);
        descText = convertView.findViewById(R.id.descText);
        editButton = convertView.findViewById(R.id.borrarButton);

        tituloTexto.setText(ldc.getTitulo());
        descText.setText(ldc.getDesc());


        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("dev", String.valueOf(tituloTexto.getText()));
                String sda = tituloTexto.getText().toString();
                // int i = ldc.getId();
                //listener.borrarLista(ldc.getId());
                listener.recogerListas();
            }
        });
        return convertView;
    }
}
