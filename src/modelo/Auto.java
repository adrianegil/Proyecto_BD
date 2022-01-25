package modelo;

import servicios.LocalizacionServicios;

public class Auto {

   private int codAuto;
   private String numPlaca;
   private int cantkm;
   private String color;
   private Tarifa tarifa;
   private Modelo modelo;
   private Situacion situacion;
   private Marca marca;

   public Marca getMarca() {
      return marca;
   }

   public void setMarca(Marca marca) {
      this.marca = marca;
   }

   @Override
   public String toString() {
    return "[" + numPlaca + "]" + "-" + marca.getNombMarca() + "-" + modelo.getNombModelo();
   }

   public Auto(int codAuto, String numPlaca, int cantkm, String color, Tarifa tarifa, Modelo modelo, Situacion situacion) {
      super();
      this.codAuto = codAuto;
      this.numPlaca = numPlaca;
      this.cantkm = cantkm;
      this.color = color;
      this.tarifa = tarifa;
      this.modelo = modelo;
      this.situacion = situacion;
      this.marca = LocalizacionServicios.getServicios().marcaServicios.buscarMarca(modelo.getMarca().getCodMarca());
   }

   public Auto() {
      super();
      //this.marca = LocalizacionServicios.getServicios().marcaServicios.buscarMarca(modelo.getMarca().getCodMarca());
   }

   public int getCodAuto() {
      return codAuto;
   }

   public void setCodAuto(int codAuto) {
      this.codAuto = codAuto;
   }

   public String getNumPlaca() {
      return numPlaca;
   }

   public void setNumPlaca(String numPlaca) {
      this.numPlaca = numPlaca;
   }

   public int getCantkm() {
      return cantkm;
   }

   public void setCantkm(int cantkm) {
      this.cantkm = cantkm;
   }

   public String getColor() {
      return color;
   }

   public void setColor(String color) {
      this.color = color;
   }

   public Tarifa getTarifa() {
      return tarifa;
   }

   public void setTarifa(Tarifa tarifa) {
      this.tarifa = tarifa;
   }

   public Modelo getModelo() {
      return modelo;
   }

   public void setModelo(Modelo modelo) {
      this.modelo = modelo;
   }

   public Situacion getSituacion() {
      return situacion;
   }

   public void setSituacion(Situacion situacion) {
      this.situacion = situacion;
   }
}
