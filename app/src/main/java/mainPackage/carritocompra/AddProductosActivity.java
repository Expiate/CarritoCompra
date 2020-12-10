package mainPackage.carritocompra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import mainPackage.carritocompra.bd.DatabaseHelper;
import mainPackage.carritocompra.utils.rc.ListAdapter;

public class AddProductosActivity extends AppCompatActivity {
    private RecyclerView viewItems;
    private ListAdapter adaptador;
    private RecyclerView.LayoutManager layoutManager;

    private Button seleccionarProductos;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
    }
}