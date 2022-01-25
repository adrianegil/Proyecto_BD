package modelo;

public class Marca {

   private int codMarca;
   private String nombMarca;

   @Override
   public String toString() {
      return nombMarca;
   }

   public Marca() {
      super();
   }


   public Marca(int codMarca, String nombMarca) {
      super();
      this.codMarca = codMarca;
      this.nombMarca = nombMarca;
   }

   public int getCodMarca() {
      return codMarca;
   }

   public void setCodMarca(int codMarca) {
      this.codMarca = codMarca;
   }

   public String getNombMarca() {
      return nombMarca;
   }

   public void setNombMarca(String nombMarca) {
      this.nombMarca = nombMarca;
   }
}
