package mainPackage.carritocompra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class MainActivity extends AppCompatActivity implements MyDialog.ExampleDialogListener {
    private ListView viewItems;
    private MyAdapter adaptador;
    private Button nuevaListaButton;
    private objetosListasDeCompra objeto = null;
    private ArrayList<objetosListasDeCompra> items = new ArrayList<objetosListasDeCompra>();
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializo la instancia de la base de datos
        databaseHelper = new DatabaseHelper(this);



        // Asigno las ID's de la interfaz
        viewItems = findViewById(R.id.list1);

        nuevaListaButton = findViewById(R.id.newListaButton);
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
        Log.i("dev", items.get(0).getTitulo());
        // corregirIds();
        actualizarLista();
    }

    public void corregirIds() {
        for(int i = 0; i < items.size(); i++) {
            objeto = items.get(i);

            boolean flag2 = false;
            if(objeto.getId() == -1) {
                boolean flag = borrarLista(objeto.getId());
                objeto.setId(calcularIdLibre());
                if(flag) {
                    databaseHelper.addLista(objeto);
                    flag2 = flag;
                }
            }
            if(flag2) {
                items = databaseHelper.getAllListas();
            }
        }
    }

    public void actualizarLista() {
        adaptador = new MyAdapter(items, this);
        viewItems.setAdapter(adaptador);
    }

    public void openDialog() {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),"Crear Lista");
    }

    @Override
    public void applyTexts(String titulo, String desc) {
        // Nuevo objeto obtenido del Dialog
        objeto = new objetosListasDeCompra(titulo, desc, calcularIdLibre());
        items.add(objeto);
        // Actualizo la DB
        databaseHelper.addLista(objeto);
        // Refresco el adaptador
        actualizarLista();
    }

    public int calcularIdLibre() {
        int max = 1;
        for(int i = 0; i < items.size(); i++) {
            if(max < items.get(i).getId()) {
                max = items.get(i).getId();
            }
        }
        return max + 1;
    }



    public void cambiarEstadoNuevaListaButton() {
        if(sePuedenCrearNuevasListas()) {
            nuevaListaButton.setEnabled(true);
        } else {
            nuevaListaButton.setEnabled(false);
        }
    }

    public boolean sePuedenCrearNuevasListas() {
        if(items.size() >= 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean borrarLista(int id) {
        return databaseHelper.eliminarLista(id);
    }

}