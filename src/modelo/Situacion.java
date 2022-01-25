package modelo;

public class Situacion {

   private int codSituacion;
   private String nombSituacion;

   public String toString() {
      return nombSituacion;
   }

   public Situacion() {

      super();
   }

   public Situacion(int codSituacion, String nombSituacion) {
      super();
      this.codSituacion = codSituacion;
      this.nombSituacion = nombSituacion;
   }

   public int getCodSituacion() {
      return codSituacion;
   }

   public void setCodSituacion(int codSituacion) {
      this.codSituacion = codSituacion;
   }

   public String getNombSituacion() {
      return nombSituacion;
   }

   public void setNombSituacion(String nombSituacion) {
      this.nombSituacion = nombSituacion;
   }
}
