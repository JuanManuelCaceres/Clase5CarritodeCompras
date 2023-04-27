package carritodecompras;

public class DescuentoPorcentaje extends Descuento{

    @Override
    public double aplicarDescuento(double valorInicial) {
        return valorInicial*(1-this.getDesceunto());
    }
    
}
