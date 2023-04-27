package carritodecompras;

public class itemOrden {
    private int codigo;
    private int cantidad;
    
    public itemOrden(String[] item){
        this.codigo = Integer.parseInt(item[0]);
        this.cantidad = Integer.parseInt(item[1]);
    }
    public itemOrden(){
        this.codigo = 0;
        this.cantidad = 0;
    }
    public int getCodigo(){
        return codigo;
    }
    public int getCantidad(){
        return cantidad;
    }
}
