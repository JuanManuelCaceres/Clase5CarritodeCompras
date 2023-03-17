package carritodecompras;

public class Producto {
    private String nombre, descripcion;
    private int codigo;
    protected double precio;
    
    public Producto(int codigo, String nombre, String descripcion, double precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        System.out.println("El producto se ha cargado");
    }
    public int getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public double getPrecio(){
        return precio;
    }
}
