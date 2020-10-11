package mainPackage.carritocompra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Add Database Values to ArrayList
        items = databaseHelper.getAllListas();

        // Assign Variable
        viewItems = findViewById(R.id.list1);
        nuevaListaButton = findViewById(R.id.newListaButton);

        // Create List Button
        nuevaListaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        // Inicializo la vista de las Listas
        actualizarLista();
    }

    private void actualizarLista() {
        adaptador = new MyAdapter(items, this);
        viewItems.setAdapter(adaptador);
    }

    private void openDialog() {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),"Crear Lista");
    }

    @Override
    public void applyTexts(String titulo, String desc) {
        // Get Item from Dialog
        objeto = new objetosListasDeCompra(titulo, desc);
        items.add(objeto);
        // Update Database
        databaseHelper.addLista(objeto);
        // Refresh List
        actualizarLista();
    }


}