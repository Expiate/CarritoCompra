package mainPackage.carritocompra;

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

public class MyDialog extends AppCompatDialogFragment {
    private EditText nombreLista;
    private EditText descLista;
    private ExampleDialogListener listener;

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.newitemdialog, null);

        builder.setView(view)
                .setTitle("Crear nueva Lista")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String titulo = nombreLista.getText().toString();
                        String desc = descLista.getText().toString();
                        listener.applyTexts(titulo, desc);
                    }
                });

        nombreLista = view.findViewById(R.id.username);
        descLista = view.findViewById(R.id.password);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement Example" +
                    "DialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyTexts(String titulo, String desc);
    }
}
