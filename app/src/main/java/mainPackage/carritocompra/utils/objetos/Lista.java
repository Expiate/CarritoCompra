package mainPackage.carritocompra.utils.objetos;

import android.widget.Button;

public class Lista {
    private int id = -1;
    private String titulo;
    private String desc;
    private Button editButton;
    private String idProductos;

    public Lista(String titulo, String desc, int id, String idProductos) {
        this.id = id;
        this.titulo = titulo;
        this.desc = desc;
        this.idProductos = idProductos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setButton(Button button) {
        this.editButton = button;
    }

    public Button getButton() {
        return this.editButton;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDesc() {
        return desc;
    }

    public String getIdProductos() {
        return idProductos;
    }

    @Override
    public String toString() {
        return "Lista{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", desc='" + desc + '\'' +
                ", editButton=" + editButton +
                '}';
    }
}
