package carritodecompras;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        
        /*Producto yerba = new Producto(101,"Yerba Playadito","500 gr",210);
            Producto galletita = new Producto(102,"9 de Oro","100 gr",380);
            Producto dulceDeLeche = new Producto(103,"Doña Pepa","500 gr",450);
            
            itemCarrito item1 = new itemCarrito(yerba,1);
            itemCarrito item2 = new itemCarrito(galletita,2);
            itemCarrito item3 = new itemCarrito(dulceDeLeche,3);
            
            Carrito listaCompra = new Carrito();
            
            listaCompra.agregarItem(item1);
            listaCompra.agregarItem(item2);
            listaCompra.agregarItem(item3);
            
            
            
            System.out.println("Codigo: "+listaCompra.getItem(0).getProducto().getCodigo());
            
            System.out.println("Producto: "+item1.getProducto().getNombre());
            //System.out.println("El tamañano del carrito es: "+listaCompra.size());
            System.out.println("El precio de la compra es: "+listaCompra.precio());
            */
        //for(String linea : Productos){
            
        //}
        //Path rutaProd = new Path(getPat("C:\\Users\\Juan\\Desktop\\programacion\\CURSO JAVA INICIAL UTN\\Clase 5\\CarritoDeCompras\\src\\carritodecompras\\Productos.txt"));
        //File productos = new File("C:\\Users\\Juan\\Desktop\\programacion\\CURSO JAVA INICIAL UTN\\Clase 5\\CarritoDeCompras\\src\\carritodecompras\\Productos.txt");
        
        
        /*String stringProd = "C:\\Users\\Juan\\Desktop\\programacion\\CURSO JAVA INICIAL UTN\\Clase 5\\CarritoDeCompras\\src\\carritodecompras\\Productos.txt";
        File rutaProd = new File(stringProd);*/
        
        String rutaProd = "C:\\Users\\Juan\\Desktop\\programacion\\CURSO JAVA INICIAL UTN\\Clase 5\\CarritoDeCompras\\src\\carritodecompras\\Productos.txt";
        String rutaCompra = "C:\\Users\\Juan\\Desktop\\programacion\\CURSO JAVA INICIAL UTN\\Clase 5\\CarritoDeCompras\\src\\carritodecompras\\Compra.txt";
        
        Carrito carrito = new Carrito();
        carrito.crearCarrito(rutaCompra, rutaProd);
        
        System.out.println(carrito.precio());
       
    }
}
