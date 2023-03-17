package carritodecompras;

public class main {

    public static void main(String[] args) {
       Producto yerba = new Producto(101,"Yerba Playadito","500 gr",210);
       Producto galletita = new Producto(102,"9 de Oro","100 gr",380);
       Producto dulceDeLeche = new Producto(103,"Doña Pepa","500 gr",450);
    
       itemCarrito item1 = new itemCarrito(yerba,1);
       itemCarrito item2 = new itemCarrito(galletita,2);
       itemCarrito item3 = new itemCarrito(dulceDeLeche,3);
       
       
    }
    
}
