package carritodecompras;

public class DescuentoFijo extends Descuento{
   
    @Override
    public double aplicarDescuento(double valorInicial) {
        return (valorInicial - this.getDesceunto());
    }  
}
