package carritodecompras;


public abstract class  Descuento {
    private double descuento;
    
    public Descuento(){ 
    }
    
    public void setDescuento(double descuento){
        this.descuento = descuento;
    }
    public double getDesceunto(){
        return this.descuento;
    }
    public abstract double aplicarDescuento(double valorInicial);
    
    public  void imprimirDescuento(double valorInicial){
        System.out.println("\t\t\t    Descuento aplicado" + "\t-" + this.getDesceunto());
        System.out.println("\t\t  Total con descuento aplicado"+"\t"+this.aplicarDescuento(valorInicial));
    }
}
