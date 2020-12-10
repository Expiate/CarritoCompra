package mainPackage.carritocompra.utils.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import mainPackage.carritocompra.utils.objetos.Producto;
import mainPackage.carritocompra.R;
import mainPackage.carritocompra.utils.BorrarListaDialogListener;
import mainPackage.carritocompra.utils.rc.ListProductAdapter;

public class BorrarProductoDialog extends AppCompatDialogFragment {
    private BorrarListaDialogListener listener;
    private ListProductAdapter.ProductHolder productHolder;
    private ArrayList<Producto> productos;

    public BorrarProductoDialog(ListProductAdapter.ProductHolder productHolder,
                                ArrayList<Producto> productos) {
        super();
        this.productHolder = productHolder;
        this.productos = productos;
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        try {
            listener = (BorrarListaDialogListener) productHolder;
        } catch (ClassCastException e) {
            throw new ClassCastException(productHolder.toString() + "must implement Example" +
                    "BorrarListaDialogListener");
        }

        builder.setMessage(R.string.borrarListaString)
                .setTitle("Borrar Lista")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.dialogRetornoPositivo();
                    }
                });

        return builder.create();
    }

}
