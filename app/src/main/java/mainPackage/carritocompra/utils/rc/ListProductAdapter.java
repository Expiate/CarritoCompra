package mainPackage.carritocompra.utils.rc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mainPackage.carritocompra.utils.objetos.Producto;
import mainPackage.carritocompra.R;
import mainPackage.carritocompra.utils.BorrarProductoDialogListener;
import mainPackage.carritocompra.utils.ProductComunicationInterface;
import mainPackage.carritocompra.utils.dialog.BorrarProductoDialog;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ProductHolder> {
    private ArrayList<Producto> productos;
    private Context context;
    private FragmentManager fragmentManager;

    public ListProductAdapter(ArrayList<Producto> itemList, Context context,
                       FragmentManager fragmentManager) {
        super();
        this.productos = itemList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ListProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_item, parent, false);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductAdapter.ProductHolder holder, int position) {
        holder.setObj(productos.get(position));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements BorrarProductoDialogListener {
        private Producto obj;
        private TextView nombre;
        private TextView precio;
        private Button button;
        private ProductComunicationInterface listener;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.tituloTextoVerad);
            precio = itemView.findViewById(R.id.descText);
            button = itemView.findViewById(R.id.borrarButton);

            try {
                    listener = (ProductComunicationInterface) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + "must implement Example" +
                        "ProductComunicationInterface");
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BorrarProductoDialog borrarListaDialog = new BorrarProductoDialog(getProductHolder(),
                            productos);
                    borrarListaDialog.show(fragmentManager, "Borrar Producto");
                }
            });

        }

        public void setObj(Producto obj) {
            this.obj = obj;
            this.nombre.setText(obj.getNombre());
            this.precio.setText(String.valueOf(obj.getPrecio()));
        }

        public void dialogRetornoPositivo() {
            listener.borrarProductoDeLista(obj.getId());
            listener.actualizarInterfaz();
        }

        public Producto getObj() {
            return obj;
        }

        public ListProductAdapter.ProductHolder getProductHolder() {
            return this;
        }
    }
}

