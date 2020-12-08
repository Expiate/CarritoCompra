package mainPackage.carritocompra.utils.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import mainPackage.carritocompra.R;
import mainPackage.carritocompra.utils.BorrarListaDialogListener;
import mainPackage.carritocompra.utils.rc.CustomAdapter;

public class BorrarListaDialog extends AppCompatDialogFragment {
    private BorrarListaDialogListener listener;
    private CustomAdapter.ListHolder listHolder;

    public BorrarListaDialog(CustomAdapter.ListHolder listHolder) {
        super();
        this.listHolder = listHolder;
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
            listener = (BorrarListaDialogListener) listHolder;
        } catch (ClassCastException e) {
            throw new ClassCastException(listHolder.toString() + "must implement Example" +
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

