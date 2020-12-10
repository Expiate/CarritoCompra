package mainPackage.carritocompra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import mainPackage.carritocompra.bd.DatabaseHelper;
import mainPackage.carritocompra.utils.ComunicationInterface;
import mainPackage.carritocompra.utils.objetos.Lista;
import mainPackage.carritocompra.utils.rc.ListAdapter;
import mainPackage.carritocompra.utils.dialog.NuevaListaDialog;

public class MainActivity extends AppCompatActivity implements ComunicationInterface {
    private RecyclerView viewItems;
    private ListAdapter adaptador;
    private RecyclerView.LayoutManager layoutManager;

    private Button nuevaListaButton;
    private Lista objeto = null;
    private ArrayList<Lista> items = new ArrayList<Lista>();

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializo la instancia de la base de datos
        databaseHelper = new DatabaseHelper(this);

        // Asigno las ID's de la interfaz
        viewItems = findViewById(R.id.list3);
        viewItems.setHasFixedSize(true);
        nuevaListaButton = findViewById(R.id.seleccionarProductosButton);
        cambiarEstadoNuevaListaButton();

        // Listeners
        nuevaListaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        // Recogo los datos de las listas de la bd
        recogerListas();
    }

    public void recogerListas() {
        items = databaseHelper.getAllListas();

        // corregirIds();
        actualizarLista();
    }

    public void corregirIds() {
        for (int i = 0; i < items.size(); i++) {
            objeto = items.get(i);

            boolean seHaCambiadoSuId = false;
            if (objeto.getId() == -1) {
                boolean flag = borrarLista(objeto.getId());
                objeto.setId(calcularIdLibre());
                if (flag) {
                    databaseHelper.addLista(objeto);
                    seHaCambiadoSuId = flag;
                }
            }
            if (seHaCambiadoSuId) {
                items = databaseHelper.getAllListas();
            }
        }
    }

    public void actualizarLista() {
        adaptador = new ListAdapter(items, this, getSupportFragmentManager());
        layoutManager = new LinearLayoutManager(this);
        viewItems.setLayoutManager(layoutManager);
        viewItems.setAdapter(adaptador);
    }

    public void openDialog() {
        NuevaListaDialog dialog = new NuevaListaDialog();
        dialog.show(getSupportFragmentManager(), "Crear Lista");
    }

    @Override
    public void applyTexts(String titulo, String desc) {
        // Nuevo objeto obtenido del Dialog
        objeto = new Lista(titulo, desc, calcularIdLibre(), "NULL");
        items.add(objeto);
        // Actualizo la DB
        databaseHelper.addLista(objeto);
        // ELIMINAR ESTO AL TERMINAR
        databaseHelper.addProducto();
        // Refresco el adaptador
        actualizarLista();
    }

    public int calcularIdLibre() {
        int max = 1;
        for (int i = 0; i < items.size(); i++) {
            if (max < items.get(i).getId()) {
                max = items.get(i).getId();
            }
        }
        return max + 1;
    }


    public void cambiarEstadoNuevaListaButton() {
        if (sePuedenCrearNuevasListas()) {
            nuevaListaButton.setEnabled(true);
        } else {
            nuevaListaButton.setEnabled(false);
        }
    }

    public boolean sePuedenCrearNuevasListas() {
        if (items.size() >= 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean borrarLista(int id) {
        return databaseHelper.eliminarLista(id);
    }

}