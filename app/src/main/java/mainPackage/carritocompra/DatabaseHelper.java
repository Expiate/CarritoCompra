package mainPackage.carritocompra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Initialize Database Name and Table name
    private static final String DATABASE_NAME = "database_name";
    private static final String TABLE_NAME = "listas";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creo las tablas
        String createTable = "create table " + TABLE_NAME +
                "(id INTEGER PRIMARY KEY, TituloLista TEXT, descLista TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Dropea la anterior tabla si existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addLista(objetosListasDeCompra objeto) {
        // Inicializo una instancia en modo escritura de la bd
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // Creo un ContentValues para insertar datos
        ContentValues contentValues = new ContentValues();
        contentValues.put("TituloLista", objeto.getTitulo());
        contentValues.put("descLista", objeto.getDesc());
        // Inserto los datos en la bd
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return true;
    }

    public ArrayList<objetosListasDeCompra> getAllListas() {
        // Inicializo una instancia en modo lectura de la bd
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<objetosListasDeCompra> listas = new ArrayList<objetosListasDeCompra>();
        // Creo un cursos para recorrer la consulta
        Cursor cursor = sqLiteDatabase.rawQuery("select Titulolista, descLista FROM "
                        + TABLE_NAME, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String tituloLista = cursor.getString(cursor.getColumnIndex("TituloLista"));
            String descLista = cursor.getString(cursor.getColumnIndex("descLista"));
            listas.add(new objetosListasDeCompra(tituloLista, descLista));

            cursor.moveToNext();
        }

        return listas;
    }

    public boolean eliminarLista(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int i = sqLiteDatabase.delete(TABLE_NAME, "id" + "=?",
                new String[]{String.valueOf(id)});

        if(i > 0) {
            return true;
        } else {
            return false;
        }
    }
}
