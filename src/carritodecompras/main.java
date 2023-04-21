//https://github.com/JuanManuelCaceres/Clase5CarritodeCompras
package carritodecompras;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        
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
        
        
        System.out.println("\n"+
                           "******************************************************************"+"\n"+
                           "*                         ¡Bienvenido!                           *"+"\n"+
                           "******************************************************************"+"\n");
        
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
                    prod.verLista(rutaProd);
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
                            carrito.imprimirPrecio();
                            desc.imprimirDescuento(carrito.getPrecio());
                            System.out.println("\n"
                                    + "******************************************************************" + "\n"
                                    + "*                  ¡Gracias por su compra!                       *" + "\n"
                                    + "******************************************************************");
                            System.out.println("\n\n\n");
                            break;
                        }
 
                    } else{
                        System.out.println("No se puede aplicar descuento a un carrito de precio \"0\" o un carrito vacío.\nIntente nuevamente.");
                        break;
                    }
                    
                    
                case "fin":
                    continuar = false;
                    break;
                    
                default:
                    System.out.println("!Atención¡ El comando ingresado no es válido. Intente Nuevamente.");
                    break;
            }
        }
   }
}
