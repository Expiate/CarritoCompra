package mainPackage.carritocompra.bd;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;

import mainPackage.carritocompra.utils.objetos.Lista;
import mainPackage.carritocompra.utils.objetos.Producto;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Initialize Database Name and Table name
    private static final String DATABASE_NAME = "database_name";
    private static final String TABLE_NAME1 = "listas";
    private static final String TABLE_NAME2 = "productos";

    private Resources res;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,
                3);
        res = context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creo las tablas
        String createTable = "create table " + TABLE_NAME1 +
                "(id INTEGER PRIMARY KEY, TituloLista TEXT, descLista TEXT, idProductos TEXT)";
        String createTable2 = "create table " + TABLE_NAME2 +
                "(id INTEGER PRIMARY KEY, nombre TEXT, precio TEXT, atributo TEXT)";
        db.execSQL(createTable);
        db.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Dropea la anterior tabla si existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean addLista(Lista objeto) {
        // Inicializo una instancia en modo escritura de la bd
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // Creo un ContentValues para insertar datos
        ContentValues contentValues = new ContentValues();
        contentValues.put("TituloLista", objeto.getTitulo());
        contentValues.put("descLista", objeto.getDesc());
        contentValues.put("idProductos", returnGsonArray());
        // Inserto los datos en la bd
        sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);

        return true;
    }

    public String returnGsonArray() {
        Gson gson = new Gson();
        String prueba = "1";
        ArrayList<String> arrayPrueba = new ArrayList<>();
        arrayPrueba.add(prueba);
        return gson.toJson(arrayPrueba, ArrayList.class);
    }

    public ArrayList<Lista> getAllListas() {
        // Inicializo una instancia en modo lectura de la bd
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Lista> listas = new ArrayList<Lista>();
        // Creo un cursos para recorrer la consulta
        Cursor cursor = sqLiteDatabase.rawQuery("select id, Titulolista, descLista," +
                " idProductos FROM "
                        + TABLE_NAME1, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String tituloLista = cursor.getString(cursor.getColumnIndex("TituloLista"));
            String descLista = cursor.getString(cursor.getColumnIndex("descLista"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String idProductos = cursor.getString(cursor.getColumnIndex("idProductos"));
            listas.add(new Lista(tituloLista, descLista, id, idProductos));

            cursor.moveToNext();
        }

        return listas;
    }

    public ArrayList<Producto> getProducts(ArrayList<String> array) {
        // Inicializo una instancia en modo lectura de la bd
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Producto> productos = new ArrayList<Producto>();
        // Creo un cursor para recorrer la consulta
        Cursor cursor = sqLiteDatabase.rawQuery("select id, nombre, precio, atributo FROM " +
                TABLE_NAME2, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(array.isEmpty() || array.isEmpty()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String precio = cursor.getString(cursor.getColumnIndex("precio"));
                String atributo = cursor.getString(cursor.getColumnIndex("atributo"));
                productos.add(new Producto(id, nombre, Double.valueOf(precio), atributo));
            } else {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                if(array.contains(String.valueOf(id))) {
                    String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    String precio = cursor.getString(cursor.getColumnIndex("precio"));
                    String atributo = cursor.getString(cursor.getColumnIndex("atributo"));
                    productos.add(new Producto(id, nombre, 2.2, atributo));
                }
            }

            cursor.moveToNext();
        }
        return productos;
    }

    public boolean addProducto() {
        // Inicializo una instancia en modo escritura de la bd
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // Creo un ContentValues para insertar datos
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", "EJEMPLO");
        contentValues.put("precio", "EJEMPLOPRECIO");
        contentValues.put("atributo", "EJEMPLOATRIBUTO");
        // Inserto los datos en la bd
        sqLiteDatabase.insert(TABLE_NAME2, null, contentValues);

        return true;
    }

    public boolean eliminarLista(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int i = sqLiteDatabase.delete(TABLE_NAME1, "id" + "=?",
                new String[]{String.valueOf(id)});

        if(i > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int i = sqLiteDatabase.delete(TABLE_NAME2, "id" + "=?",
                new String[]{String.valueOf(id)});

        if(i > 0) {
            return true;
        } else {
            return false;
        }
    }
}
