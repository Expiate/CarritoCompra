package mainPackage.carritocompra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import mainPackage.carritocompra.utils.rc.CustomAdapter;

public class InteriorListaActivity extends AppCompatActivity {
    private RecyclerView viewItems;
    private CustomAdapter adaptador;
    private RecyclerView.LayoutManager layoutManager;

    private Button seleccionarProductos;
    private Button añadirNuevosProductos;
    private Button eliminarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interior_lista_activity);
    }
}