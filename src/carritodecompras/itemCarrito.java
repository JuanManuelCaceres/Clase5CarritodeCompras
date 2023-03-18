package carritodecompras;

public class itemCarrito{
    private Producto producto;
    private int cantidad;
    
    public itemCarrito(){
        this.producto = new Producto();
        this.cantidad = 1;
        System.out.println("El producto ha sido agregado correctamente");
    }
    
    public itemCarrito(Producto producto,int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
        System.out.println("El producto ha sido agregado correctamente");
    }
    
    public itemCarrito(int codigo, String nombre, String descripcion, double precio,int cantidad){
        this.producto = new Producto(codigo,nombre,descripcion,precio);
        this.cantidad = cantidad;
    }
    
    public Producto getProducto(){
        return producto;
    }
    public int getCodigo(){
        return producto.getCodigo();
    }
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
}
