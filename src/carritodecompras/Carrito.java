//https://github.com/JuanManuelCaceres/Clase5CarritodeCompras
package carritodecompras;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Carrito {
    private double precio;
    private float documento;
    private itemCarrito item;
    private ArrayList <itemCarrito> carrito;
    private String nombre;
    private conexionMySQL con;
    
    public Carrito() throws SQLException{
       carrito = new ArrayList<>();
       this.item = new itemCarrito();
       this.nombre ="";
       this.documento=0;
       con = new conexionMySQL();
       
       
    }
    
    public void agregarItem(itemCarrito item){
        this.carrito.add(item);  
    }
    
    
    public void agregarItem() throws IOException, SQLException{
        
        String[] vecProd;
        String[] vecCod;
        int cantidad=0; 
        Producto prod;
        itemCarrito itemCar;
        String codigo;
        Scanner in = new Scanner(System.in);
        boolean agregar = true;
        boolean carritoLleno=false;
        
        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
           
        }
        
        System.out.print("Ingrese codigo: ");
        codigo = in.next().toLowerCase();
        if (codigo.equals("fin")) {
            agregar = false;
        }
        if (carritoLleno) {
            agregar = false;
            System.out.println("El carrito esta lleno");
        }
        
        while (agregar) {

            //Creamos el vector comando
            if (codigo.contains("+") || codigo.contains("*")) {
                vecCod = codigo.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
            } else {
                codigo = codigo.concat("+1");
                vecCod = codigo.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
            }
            System.out.println(codigo);
            //Determinamos la cantidad en funcion del vecCod[]
            cantidad = Integer.parseInt(vecCod[2]);
            
            //Creamos el itemCarrito
            vecProd = con.buscarProducto(vecCod[0]);
            
            prod = new Producto(vecProd);
            System.out.println("Producto creado");
            
            itemCar = new itemCarrito(prod, cantidad);
            System.out.println("item carrito creado");
            if (this.contieneItem(String.valueOf(itemCar.getCodigo()))) {
                cantidad += this.carrito.get(this.posicionItem(vecCod[0])).getCantidad();
                this.carrito.get(this.posicionItem(vecCod[0])).setCantidad(cantidad);
            } else {
                this.carrito.add(itemCar);
            }
            
            
            System.out.print("Ingrese codigo: ");
            codigo = in.next();
            
            if (codigo.equals("fin")) {
                agregar = false;
            }
            
        }
        con.cerrarConexion();
    }
    /*
    public void agregarItem() throws IOException{
        String rutaProd = "C:\\Users\\Juan\\Desktop\\programacion\\CURSO JAVA INICIAL UTN\\Clase 5\\CarritoDeCompras\\src\\carritodecompras\\Productos.txt";
        Path productos = Paths.get(rutaProd);
        String[] vecProd;
        String[] vecCod;
        int posicion=0;
        int cantidad=0;
        Producto prod = new Producto();
        itemCarrito itemCar;
        String codigo;
        Scanner in = new Scanner(System.in);
        boolean agregar = true;
        boolean carritoLleno=false;
        
        
        System.out.print("Ingrese codigo: ");
        codigo = in.next().toLowerCase();
        if (codigo.equals("fin")) {
            agregar = false;
        }
        if (carritoLleno) {
            agregar = false;
            System.out.println("El carrito esta lleno");
        }
        
        while (agregar) {

            //Creamos el vector comando
            if (codigo.contains("+") || codigo.contains("*")) {
                vecCod = codigo.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
            } else {
                codigo = codigo.concat("+1");
                vecCod = codigo.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
            }

            //Determinamos la cantidad en funcion del vecCod[]
            cantidad = Integer.parseInt(vecCod[2]);

            //Creamos el itemCarrito
            for (String lineas : Files.readAllLines(productos)) {
                vecProd = lineas.split(";");
                if (vecProd[0].equals(vecCod[0])) {
                    prod = new Producto(vecProd);
                } else {
                    
                }
            }
            
            itemCar = new itemCarrito(prod, cantidad);
            
            if (this.carrito.size() < 10) {
                if (this.contieneItem(String.valueOf(itemCar.getCodigo()))) {
                    cantidad += this.carrito.get(this.posicionItem(vecCod[0])).getCantidad();
                    this.carrito.get(this.posicionItem(vecCod[0])).setCantidad(cantidad);
                } else {
                    this.carrito.add(itemCar);
                }
            } else {
                System.out.println("El carrito esta lleno");
                carritoLleno = true;
                agregar = false;
            }
            System.out.print("Ingrese codigo: ");
            codigo = in.next();
            
            if (codigo.equals("fin")) {
                agregar = false;
            }
        }
    }
    */
    public itemCarrito getItem(int e){
        return carrito.get(e);
    }
    
    public double precio(){
        precio=0;
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
    public void setDocumento(float documento){
        this.documento = documento;
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
            vec = lineas.split(";");
            prod = new Producto(vec);
            for (int i = 0; i < orden.tamaño(); i++) {
                if (prod.getCodigo()==orden.getItemOrden(i).getCodigo()) {
                    itemCar = new itemCarrito(prod,orden.getItemOrden(i).getCantidad());
                    carrito.add(itemCar);
                }    
            }
        }
        
        
    }
    
    public void mostrarCarrito(){
        System.out.println("\n"+"Codigo\t\t\tDescripcion\t\tPrecio\tCantidad");
        
        for (int i = 0; i < carrito.size(); i++) {
            if ((carrito.get(i).getProducto().getNombre().length()+carrito.get(i).getProducto().getDescripcion().length())<22) {
                System.out.println(carrito.get(i).getProducto().getCodigo()+
                            "\t  "+carrito.get(i).getProducto().getNombre()+
                                   carrito.get(i).getProducto().getDescripcion()+
                        "\t\t\t "+carrito.get(i).getProducto().getPrecio()+
                            " \t   "+carrito.get(i).getCantidad());
            } else if((carrito.get(i).getProducto().getNombre().length()+carrito.get(i).getProducto().getDescripcion().length())<26){
                System.out.println(carrito.get(i).getProducto().getCodigo()+
                            "\t  "+carrito.get(i).getProducto().getNombre()+
                                   carrito.get(i).getProducto().getDescripcion()+
                         "\t\t "+carrito.get(i).getProducto().getPrecio()+
                            " \t   "+carrito.get(i).getCantidad());
                
            }else{
                System.out.println(carrito.get(i).getProducto().getCodigo()+
                             "\t  "+carrito.get(i).getProducto().getNombre()+
                                    carrito.get(i).getProducto().getDescripcion()+
                             "\t "+carrito.get(i).getProducto().getPrecio()+
                             " \t   "+carrito.get(i).getCantidad());
            }
        }
        
        System.out.println("\t\t\t\t\t Total\t"+this.precio());
    }
    public void imprimirPrecio(){
         
        
        System.out.println("\n"+"Codigo\t\t\tDescripcion\t\tPrecio\tCantidad");
        
        for (int i = 0; i < carrito.size(); i++) {
            if ((carrito.get(i).getProducto().getNombre().length()+carrito.get(i).getProducto().getDescripcion().length())<22) {
                System.out.println(carrito.get(i).getProducto().getCodigo()+
                            "\t  "+carrito.get(i).getProducto().getNombre()+
                                   carrito.get(i).getProducto().getDescripcion()+
                        "\t\t\t "+carrito.get(i).getProducto().getPrecio()+
                            " \t   "+carrito.get(i).getCantidad());
            } else if((carrito.get(i).getProducto().getNombre().length()+carrito.get(i).getProducto().getDescripcion().length())<26){
                System.out.println(carrito.get(i).getProducto().getCodigo()+
                            "\t  "+carrito.get(i).getProducto().getNombre()+
                                   carrito.get(i).getProducto().getDescripcion()+
                         "\t\t "+carrito.get(i).getProducto().getPrecio()+
                            " \t   "+carrito.get(i).getCantidad());
                
            }else{
                System.out.println(carrito.get(i).getProducto().getCodigo()+
                             "\t  "+carrito.get(i).getProducto().getNombre()+
                                    carrito.get(i).getProducto().getDescripcion()+
                             "\t "+carrito.get(i).getProducto().getPrecio()+
                             " \t   "+carrito.get(i).getCantidad());
            }
        }
        
        System.out.println("\t\t\t\t\t Total\t"+this.precio());
    }
    
    public void subirDB(Double descuento,String cuilCl) throws SQLException{
        String precioCo = String.valueOf(this.precio());
        String descuentoCo = String.valueOf(descuento);
        LocalDate fecha = null;
        

        String consulta = "insert into supermercado.compra(idCo,cuilCl,precioCo,descuentoCo,fecha) "
            + "values (idCo,"+cuilCl+","+precioCo+","+descuentoCo+","+fecha+")";
        
        
        //Conectamos el metodo a la base de datos
        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
           
        }
        
        //Hacemos la consulta
        con.ejecutarActualizacion(consulta);
        
        //Cerramos conexion
        con.cerrarConexion();
    }
    
    public void nuevoCarrito(){
        this.carrito.clear();
    }
    
    public boolean contieneItem(String codigo){
        for(itemCarrito item : this.carrito){
            if(item.getCodigo()== Integer.parseInt(codigo)){
                return true;
            }
        }
        return false;
    }
    
    public void limpiarCarrito(){
        for (itemCarrito item : this.carrito) {
            if(item.getCantidad()<=0){
                this.carrito.remove(item);
            }
        }
    }
    public int posicionItem(String codigo){
        
        for (int i = 0; i < this.carrito.size(); i++) {
            if (this.carrito.get(i).getCodigo()==Integer.parseInt(codigo)) {
                return i;
            }
        }
        
        return -1;
    }
}
