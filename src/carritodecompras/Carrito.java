package carritodecompras;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Carrito {
    private double precio;
    private itemCarrito item;
    ArrayList <itemCarrito> carrito;
    
    public Carrito(){
       carrito = new ArrayList<>();
       this.item = new itemCarrito();
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
}
