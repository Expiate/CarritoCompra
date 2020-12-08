package mainPackage.carritocompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ListHolder> {
    private ArrayList<ObjetosListasDeCompra> items;
    private Context context;

    public CustomAdapter(ArrayList<ObjetosListasDeCompra> itemList, Context context) {
        this.items = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);

        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ListHolder holder, int position) {
        holder.setObj(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        private ObjetosListasDeCompra obj;
        private TextView tituloLista;
        private TextView descLista;
        private Button button;
        private MyDialog.ExampleDialogListener listener;

        public ListHolder(@NonNull View itemView) {
            super(itemView);

            tituloLista = itemView.findViewById(R.id.tituloTextoVerad);
            descLista = itemView.findViewById(R.id.descText);
            button = itemView.findViewById(R.id.borrarButton);

            try {
                listener = (MyDialog.ExampleDialogListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + "must implement Example" +
                        "DialogListener");
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.borrarLista(obj.getId());
                }
            });
        }

        public void setObj(ObjetosListasDeCompra obj) {
            this.obj = obj;
            this.tituloLista.setText(obj.getTitulo());
            this.descLista.setText(obj.getDesc());
        }

        public ObjetosListasDeCompra getObj() {
            return obj;
        }

    }
}

