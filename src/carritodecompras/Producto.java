package carritodecompras;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    }
    
    public Producto(int codigo, String nombre, String descripcion, double precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    public Producto(String[] datos){
        this.codigo = Integer.parseInt(datos[0]);
        this.nombre = datos[1];
        this.descripcion = datos[2];
        this.precio = Double.parseDouble(datos[3]);
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
    
    public void verLista(String rutaProd) throws IOException{
        Path productos = Paths.get(rutaProd);
        for(String lineas:Files.readAllLines(productos)){
            System.out.println(lineas);
        }
    }
}
