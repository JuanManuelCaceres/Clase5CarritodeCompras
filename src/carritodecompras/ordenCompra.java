package carritodecompras;

import java.util.ArrayList;


public class ordenCompra {
    private ArrayList<itemOrden> orden;
    private itemOrden item;
    
    public ordenCompra(int index){
        orden = new ArrayList<>(index);
        item = new itemOrden();
    }
    public ordenCompra(){
        orden = new ArrayList<>();
        item = new itemOrden();
    }
    public void agregarItem(itemOrden item){
        this.orden.add(item);
    }
    
    public itemOrden getItemOrden(int index){
        return orden.get(index);
    }
    
    public int tamaño(){
        return orden.size();
    }
}
