package mainPackage.carritocompra.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import mainPackage.carritocompra.ObjetoListaDeCompra;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Initialize Database Name and Table name
    private static final String DATABASE_NAME = "database_name";
    private static final String TABLE_NAME1 = "listas";
    private static final String TABLE_NAME2 = "productos";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,
                1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creo las tablas
        String createTable = "create table " + TABLE_NAME1 +
                "(id INTEGER PRIMARY KEY, TituloLista TEXT, descLista TEXT, idProductos TEXT)";
        String createTable2 = "create table " + TABLE_NAME2 +
                "(id INTEGER PRIMARY KEY, nombre TEXT, precio DOUBLE)";
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

    public boolean addLista(ObjetoListaDeCompra objeto) {
        // Inicializo una instancia en modo escritura de la bd
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // Creo un ContentValues para insertar datos
        ContentValues contentValues = new ContentValues();
        contentValues.put("TituloLista", objeto.getTitulo());
        contentValues.put("descLista", objeto.getDesc());
        // Inserto los datos en la bd
        sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);

        return true;
    }

    public boolean añadirProductosIniciales() {
        // Inicializo una instancia en modo escritura de la bd
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // Creo un ContentValues para insertar datos
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", "Pan");
        contentValues.put("precio", 1.10);
        return true;
    }

    public ArrayList<ObjetoListaDeCompra> getAllListas() {
        // Inicializo una instancia en modo lectura de la bd
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<ObjetoListaDeCompra> listas = new ArrayList<ObjetoListaDeCompra>();
        // Creo un cursos para recorrer la consulta
        Cursor cursor = sqLiteDatabase.rawQuery("select id, Titulolista, descLista FROM "
                        + TABLE_NAME1, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String tituloLista = cursor.getString(cursor.getColumnIndex("TituloLista"));
            String descLista = cursor.getString(cursor.getColumnIndex("descLista"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            listas.add(new ObjetoListaDeCompra(tituloLista, descLista, id));

            cursor.moveToNext();
        }

        return listas;
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
}
