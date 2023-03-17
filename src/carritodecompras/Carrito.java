package carritodecompras;
import java.util.ArrayList;

public class Carrito {
    private double precio;
    private itemCarrito item;
    ArrayList <itemCarrito> carrito = new ArrayList<>();
    
    public void agrearItem(itemCarrito item){
        this.carrito.add(item);
    }
    
    public itemCarrito getItem(){
        return item;
    }
    /*public double precio(){
        for (int i = 0; i < 3; i++) {
           precio += carrito.get(i).getProducto().getPrecio()*carrito.get(i).getCantidad();
        }
        return precio;
    }*/
    
    public double precio(){
        carrito.forEach(item -> {precio+=item.getProducto().getPrecio()*item.getCantidad();});
        return precio;
    }
}
