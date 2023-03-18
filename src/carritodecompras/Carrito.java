package carritodecompras;
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
    
    /*public double precio(){
        carrito.forEach(item -> {precio+=item.getProducto().getPrecio()*item.getCantidad();});
        return precio;
    }*/
}
