package carritodecompras;

public class itemCarrito{
    private Producto producto;
    private int cantidad;
    
    public itemCarrito(Producto producto,int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
        System.out.println("El producto ha sido agregado correctamente");
    }
    
    public Producto getProducto(){
        return producto;
    }
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}
