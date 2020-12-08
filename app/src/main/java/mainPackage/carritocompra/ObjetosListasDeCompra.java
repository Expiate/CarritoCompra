package mainPackage.carritocompra;

import android.widget.Button;

public class ObjetosListasDeCompra {
    private int id = -1;
    private String titulo;
    private String desc;
    private Button editButton;

    public ObjetosListasDeCompra(String titulo, String desc, int id) {
        this.id = id;
        this.titulo = titulo;
        this.desc = desc;
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

    @Override
    public String toString() {
        return "ObjetosListasDeCompra{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", desc='" + desc + '\'' +
                ", editButton=" + editButton +
                '}';
    }
}
