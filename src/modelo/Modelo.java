package modelo;

public class Modelo {

   private int codModelo;
   private String nombModelo;
   private Marca marca;

   @Override
   public String toString() {
      return nombModelo;
   }

   public Modelo() {

      super();
   }

   public Modelo(int codModelo, String nombModelo, Marca marca) {
      super();
      this.codModelo = codModelo;
      this.nombModelo = nombModelo;
      this.marca = marca;
   }

   public int getCodModelo() {
      return codModelo;
   }

   public void setCodModelo(int codModelo) {
      this.codModelo = codModelo;
   }

   public String getNombModelo() {
      return nombModelo;
   }

   public void setNombModelo(String nombModelo) {
      this.nombModelo = nombModelo;
   }

   public Marca getMarca() {
      return marca;
   }

   public void setMarca(Marca marca) {
      this.marca = marca;
   }
}
