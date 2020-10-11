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
        // Create Table
        String createTable = "create table " + TABLE_NAME +
                "(id INTEGER PRIMARY KEY, TituloLista TEXT, descLista TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addLista(objetosListasDeCompra objeto) {
        // Get WriteAble Database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // Create Content Values
        ContentValues contentValues = new ContentValues();
        contentValues.put("TituloLista", objeto.getTitulo());
        contentValues.put("descLista", objeto.getDesc());
        // Add Values into Database
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<objetosListasDeCompra> getAllListas() {
        // Get ReadAble Database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<objetosListasDeCompra> listas = new ArrayList<objetosListasDeCompra>();
        // Create Cursor to Select all Values
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
}
