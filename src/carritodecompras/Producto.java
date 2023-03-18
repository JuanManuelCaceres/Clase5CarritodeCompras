package carritodecompras;

import java.nio.file.Path;

public class Producto {
    private String nombre, descripcion;
    private int codigo;
    protected double precio;
    //private Scanner lectura = new Scanner();
    
    public void registrarProductos(Path p){
        
    }
    
        public Producto(){
        this.codigo = 0;
        this.nombre = "";
        this.descripcion = "";
        this.precio = 0.00;
        System.out.println("El producto se ha cargado");
    }
    
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
    
    public void setCodigo(int e){
        this.codigo = e;
    }
    public void setNombre(String e){
        this.nombre = e;
    }
    public void setDescripcion(String e){
        this.descripcion = e;
    }
    public void setPrecio(int e){
        this.precio = e;
    }
}
