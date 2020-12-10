package mainPackage.carritocompra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

import mainPackage.carritocompra.bd.DatabaseHelper;
import mainPackage.carritocompra.utils.ProductComunicationInterface;
import mainPackage.carritocompra.utils.objetos.Producto;
import mainPackage.carritocompra.utils.rc.ListProductAdapter;

public class InteriorListaActivity extends AppCompatActivity implements ProductComunicationInterface {
    private RecyclerView viewItems;
    private ListProductAdapter adaptador;
    private RecyclerView.LayoutManager layoutManager;

    private Button seleccionarProductos;

    private DatabaseHelper databaseHelper;
    private ArrayList<String> idProductos;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interior_lista_activity);

        // Inicializo la instancia de la base de datos
        databaseHelper = new DatabaseHelper(this);

        // Utilizar las ID's de la interfaz
        viewItems = findViewById(R.id.list2);
        seleccionarProductos = findViewById(R.id.seleccionarProductosButton);

        // Recojo la info del intent
        getIdProducts();
        actualizarLista();
    }

    public void actualizarLista() {
        ArrayList<Producto> productos;
        productos = databaseHelper.getProducts(idProductos);
        adaptador = new ListProductAdapter(productos, this, getSupportFragmentManager());
        layoutManager = new LinearLayoutManager(this);
        viewItems.setLayoutManager(layoutManager);
        viewItems.setAdapter(adaptador);
    }

    public void getIdProducts() {
        String idProductosSerializado = getIntent().getStringExtra("ID_PRODUCTOS");
        Log.i("dev", idProductosSerializado);

        if(idProductosSerializado.equals("AAA")) {
            idProductos = new ArrayList<String>();
        } else {
            gson = new Gson();
            idProductos = gson.fromJson(idProductosSerializado,
                    ArrayList.class);
        }
    }

    @Override
    public boolean borrarProductoDeLista(int id) {
        for(int i = 0; i < idProductos.size(); i++) {
            if(idProductos.get(i).equals(String.valueOf(id))) {
                idProductos.remove(i);
            }
        }
        return false;
    }

    @Override
    public boolean actualizarInterfaz() {
        actualizarLista();
        return false;
    }
}