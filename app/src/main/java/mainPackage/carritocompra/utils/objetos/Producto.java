package mainPackage.carritocompra.utils.objetos;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private String atributo;

    public Producto(int id, String nombre, double precio, String atributo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.atributo = atributo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getAtributo() {
        return atributo;
    }

    public int getId() {
        return id;
    }
}
