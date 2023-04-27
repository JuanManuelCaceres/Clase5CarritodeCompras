//https://github.com/JuanManuelCaceres/Clase5CarritodeCompras
package carritodecompras;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
public class main {
    public static void main(String[] args) throws IOException, SQLException {
        
        boolean continuar = true;
        String comando;
        String codigo;
        Scanner in = new Scanner(System.in);
        String rutaProd = "C:\\Users\\Juan\\Desktop\\programacion\\CURSO JAVA INICIAL UTN\\Clase 5\\CarritoDeCompras\\src\\carritodecompras\\Productos.txt";
        String rutaCompra = "C:\\Users\\Juan\\Desktop\\programacion\\CURSO JAVA INICIAL UTN\\Clase 5\\CarritoDeCompras\\src\\carritodecompras\\Compra.txt";
        Carrito carrito= new Carrito();
        Producto prod = new Producto();
        DescuentoFijo desc = new DescuentoFijo();
        desc.setDescuento(800);
        int archivosCargados = 0;
        
        LocalDate fecha;
        
        // Objeto para crear la conexion con la base de datos
        conexionMySQL con = new conexionMySQL();
        try {
            con.conectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido conectar con la base de datos.\n"+"El programa finalizará inmediatamente...");
            System.exit(1);
        }
        
        System.out.println("\n"+
                           "******************************************************************"+"\n"+
                           "*                         ¡Bienvenido!                           *"+"\n"+
                           "******************************************************************"+"\n");
        
        
        //-Carga de Productos desde archivo hacia la base de datos
        
        
        
        try {
            for (String lineas : Files.readAllLines(Paths.get(rutaProd))) {
                String[] vecProd = lineas.split(";");
                String consulta = "insert ignore into producto(idP,codigoP,nombreP,descripcionP,precioP) values(idP," + vecProd[0] + ",'" + vecProd[1] + "','" + vecProd[2] + "'," + vecProd[3] + ")";                    
                archivosCargados = con.ejecutarActualizacion(consulta);
                
            }
        } catch (IOException | SQLException e) {
            
        }
        if(archivosCargados > 0){
            System.out.println("Se han cargado "+archivosCargados+" archivos exitosamente en la base de datos");
        }
        
        
        while(continuar){
            System.out.println("Ingrese un comando (Ej:AYUDA): ");
            comando = in.nextLine();
            
            
            switch(comando.toLowerCase()){
                case "ayuda":
                    System.out.println("\t-Nuevo Carrito(NC)\t\t-Cargar Carrito desde Archivo(CA)\t-Agregar Items mediante codigo(CC)\n"+
                                       "\t-Ver Lista de Productos(LP)\t-Mostrar Carrito(MC)\t\t\t-Imprimir Ticket(IT)\n"+
                                       
                                       "\t-Finalizar(fin)\n");
                    break;
                    
                case "nc":
                    carrito.nuevoCarrito();
                    System.out.println("\n"+"Nuevo carro de compra inicializado...\n"+"Ya puede comenzar a agregar Items.\n");
                    break;
                    
                case "ca":
                    carrito.crearCarrito(rutaCompra,rutaProd);
                    System.out.println("\nProcesando informacion...\n"+"Su compra se ha cargado.\n");
                    break;
                    
                case "cc":
                    carrito.agregarItem();
                    break;
                case "lp":
                    System.out.println("Codigo\tNombre y Descripcion\t\t\tPrecio");
                    ResultSet rs = con.ejecutarConsulta("Select * from supermercado.producto where codigoP = 101");
                    while(rs.next()){
                        int codigoP = rs.getInt("codigoP");
                        String nombre = rs.getString("nombreP");
                        String descripcion = rs.getString("descripcionP");
                        int precio = rs.getInt("precioP");
                        if(nombre.length()+1+descripcion.length()<24){
                            System.out.println(codigoP+"\t"+nombre+" "+descripcion+"\t\t\t "+precio);
                        } else if(nombre.length()+descripcion.length()+1<32){
                            System.out.println(codigoP+"\t"+nombre+" "+descripcion+"\t\t "+precio);
                        } else if(nombre.length()+descripcion.length()+1<40){
                            System.out.println(codigoP+"\t"+nombre+" "+descripcion+"\t "+precio);
                        }
                    }
                    break;
                    
                case "mc":
                    carrito.mostrarCarrito();
                    
                    break;
                    
                case "it":
                    if (carrito.precio()>0) {
                        if (desc.aplicarDescuento(carrito.precio())<0) {
                            System.out.println("El total del carrito no puede ser negativo. Verifique el descuento aplicado");
                            break;
                        } else {
                            System.out.print("\nNombre: ");
                            String nombreCl = in.nextLine();
                            
                            System.out.print("Documento : ");
                            String documentoCl = in.nextLine();
                            
                            carrito.imprimirPrecio();
                            desc.imprimirDescuento(carrito.getPrecio());
                            
                            double descuento = desc.getDesceunto();
                            carrito.subirDB(descuento,nombreCl,documentoCl);
                           
                            System.out.println("\n"
                                    + "******************************************************************" + "\n"
                                    + "*                  ¡Gracias por su compra!                       *" + "\n"
                                    + "******************************************************************");
                            System.out.println("\n\n");
                            break;
                        }
                    
                    } else{
                        System.out.println("No se puede aplicar descuento a un carrito de precio \"0\" o un carrito vacío.\nIntente nuevamente.");
                        break;
                    }
                    
                    
                case "fin":
                    continuar = false;
                    con.cerrarConexion();
                    break;
                    
                default:
                    System.out.println("!Atención¡ El comando ingresado no es válido. Intente Nuevamente.");
                    break;
            }
        }
   }
}
