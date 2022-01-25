package modelo;

public class Rol {

   private int codRol;
   private String nombRol;

   public Rol() {
      super();
   }

   public int getCodRol() {
      return codRol;
   }

   public void setCodRol(int codRol) {
      this.codRol = codRol;
   }

   public String getNombRol() {
      return nombRol;
   }

   public void setNombRol(String nombRol) {
      this.nombRol = nombRol;
   }

   public Rol(int codRol, String nombRol) {
      super();
      this.codRol = codRol;
      this.nombRol = nombRol;
   }
}
