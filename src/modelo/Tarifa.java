package modelo;

public class Tarifa {

   private int codTarifa;
   private double ingreso;
   private double ingresoEspecial;

   public Tarifa() {
      super();
   }

   public Tarifa(int codTarifa, double ingreso, double ingresoEspecial) {
      super();
      this.codTarifa = codTarifa;
      this.ingreso = ingreso;
      this.ingresoEspecial = ingresoEspecial;
   }

   public int getCodTarifa() {
      return codTarifa;
   }

   public void setCodTarifa(int codTarifa) {
      this.codTarifa = codTarifa;
   }

   public double getIngreso() {
      return ingreso;
   }

   public void setIngreso(double ingreso) {
      this.ingreso = ingreso;
   }

   public double getIngresoEspecial() {
      return ingresoEspecial;
   }

   public void setIngresoEspecial(double ingresoEspecial) {
      this.ingresoEspecial = ingresoEspecial;
   }
}
