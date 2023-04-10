//https://github.com/JuanManuelCaceres/Clase5CarritodeCompras
package carritodecompras;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Carrito {
    private double precio;
    private itemCarrito item;
    private ArrayList <itemCarrito> carrito;
    private String nombre;
    
    public Carrito(){
       carrito = new ArrayList<>();
       this.item = new itemCarrito();
       this.nombre ="";
    }
    
    public void agregarItem(itemCarrito item){
        this.carrito.add(item);  
    }
    
    public itemCarrito getItem(int e){
        return carrito.get(e);
    }
    
    
    
    public double precio(){
        for (int i = 0; i < carrito.size(); i++) {
           precio += carrito.get(i).getProducto().getPrecio()*carrito.get(i).getCantidad();
        }
        return precio;
    }
    
    public double getPrecio(){
        return precio;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void crearCarrito(String rutaCompra, String rutaProd) throws FileNotFoundException, IOException{
        
        Path compra = Paths.get(rutaCompra);
        Path productos = Paths.get(rutaProd);
        
        ordenCompra orden = new ordenCompra();
        itemOrden item;
        String[] vec;
        
        Producto prod;
        itemCarrito itemCar;
        
        //Extraer datos del archivo a un array tipo String[]
        for(String lineas:Files.readAllLines(compra)){
            vec = lineas.split("\t");
            item = new itemOrden(vec);
            orden.agregarItem(item);
        }
        
        //Filtro de productos
        for(String lineas:Files.readAllLines(productos)){
            vec = lineas.split("\t");
            prod = new Producto(vec);
            for (int i = 0; i < orden.tamaño(); i++) {
                if (prod.getCodigo()==orden.getItemOrden(i).getCodigo()) {
                    itemCar = new itemCarrito(prod,orden.getItemOrden(i).getCantidad());
                    carrito.add(itemCar);
                }    
            }
        }
        
        
    }
    public void imprimirPrecio(){
        Scanner in = new Scanner(System.in);
        System.out.print("Nombre: ");
        in.nextLine();
        System.out.print("Documento : ");
        in.nextInt();
        
        System.out.println("\n"+"Codigo\t\t\tDescripcion\t\tPrecio\tCantidad");
        
        for (int i = 0; i < carrito.size(); i++) {
            if ((carrito.get(i).getProducto().getNombre().length()+carrito.get(i).getProducto().getDescripcion().length())<30) {
                System.out.println(carrito.get(i).getProducto().getCodigo()+
                            "\t  "+carrito.get(i).getProducto().getNombre()+
                                   carrito.get(i).getProducto().getDescripcion()+
                        "\t\t\t "+carrito.get(i).getProducto().getPrecio()+
                            " \t   "+carrito.get(i).getCantidad());
            } else{
                System.out.println(carrito.get(i).getProducto().getCodigo()+
                             "\t  "+carrito.get(i).getProducto().getNombre()+
                                    carrito.get(i).getProducto().getDescripcion()+
                             "\t "+carrito.get(i).getProducto().getPrecio()+
                             " \t   "+carrito.get(i).getCantidad());
            }
        }
        
        System.out.println("\t\t\t\t\t Total\t"+this.precio());
    }
}
