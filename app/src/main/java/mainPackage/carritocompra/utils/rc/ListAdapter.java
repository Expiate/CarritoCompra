package mainPackage.carritocompra.utils.rc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mainPackage.carritocompra.InteriorListaActivity;
import mainPackage.carritocompra.utils.objetos.Lista;
import mainPackage.carritocompra.R;
import mainPackage.carritocompra.utils.BorrarListaDialogListener;
import mainPackage.carritocompra.utils.ComunicationInterface;
import mainPackage.carritocompra.utils.dialog.BorrarListaDialog;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    private ArrayList<Lista> items;
    private Context context;
    private FragmentManager fragmentManager;

    public ListAdapter(ArrayList<Lista> itemList, Context context,
                       FragmentManager fragmentManager) {
        super();
        this.items = itemList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ListAdapter.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_item, parent, false);

        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListHolder holder, int position) {
        holder.setObj(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder implements BorrarListaDialogListener {
        private Lista obj;
        private TextView tituloLista;
        private TextView descLista;
        private Button button;
        private ComunicationInterface listener;

        public ListHolder(@NonNull View itemView) {
            super(itemView);

            tituloLista = itemView.findViewById(R.id.tituloTextoVerad);
            descLista = itemView.findViewById(R.id.descText);
            button = itemView.findViewById(R.id.borrarButton);

            try {
                listener = (ComunicationInterface) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + "must implement Example" +
                        "ComunicationInterface");
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BorrarListaDialog dialog = new BorrarListaDialog(getListHolder());
                    dialog.show(fragmentManager, "Borrar Lista");
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentToInteriorListaActivity = new Intent(context,
                            InteriorListaActivity.class);
                    intentToInteriorListaActivity.putExtra("ID_PRODUCTOS",
                            obj.getIdProductos());
                    context.startActivity(intentToInteriorListaActivity);
                }
            });
        }

        public void setObj(Lista obj) {
            this.obj = obj;
            this.tituloLista.setText(obj.getTitulo());
            this.descLista.setText(obj.getDesc());
        }

        public void dialogRetornoPositivo() {
            listener.borrarLista(obj.getId());
            listener.recogerListas();
        }

        public Lista getObj() {
            return obj;
        }

        public ListHolder getListHolder() {
            return this;
        }
    }
}

