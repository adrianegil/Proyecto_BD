package modelo;

public class Pais {

   private int codPais;
   private String nombPais;

   public Pais() {
      super();
   }

   @Override
   public String toString() {
      return nombPais;
   }

   public Pais(int codPais, String nombPais) {
      super();
      this.codPais = codPais;
      this.nombPais = nombPais;
   }

   public int getCodPais() {
      return codPais;
   }

   public void setCodPais(int codPais) {
      this.codPais = codPais;
   }

   public String getNombPais() {
      return nombPais;
   }

   public void setNombPais(String nombPais) {
      this.nombPais = nombPais;
   }
}
