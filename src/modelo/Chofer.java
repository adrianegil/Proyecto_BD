package modelo;

public class Chofer {

   private int codChof;
   private String idChof;
   private String nombChof;
   private String apellChof;
   private String direccChof;
   private String catChof;
   private boolean disponible;

   @Override
   public String toString() {
      return nombChof + " " + apellChof + " " + " Catg. " + catChof;
   }

   public Chofer() {
      super();
   }

   public Chofer(int codChof, String idChof, String nombChof, String apellChof, String direccChof, String catChof) {
      super();
      this.codChof = codChof;
      this.idChof = idChof;
      this.nombChof = nombChof;
      this.apellChof = apellChof;
      this.direccChof = direccChof;
      this.catChof = catChof;
      this.disponible = true;
   }


   public int getCodChof() {
      return codChof;
   }

   public void setCodChof(int codChof) {
      this.codChof = codChof;
   }

   public String getIdChof() {
      return idChof;
   }

   public void setIdChof(String idChof) {
      this.idChof = idChof;
   }

   public String getNombChof() {
      return nombChof;
   }

   public void setNombChof(String nombChof) {
      this.nombChof = nombChof;
   }

   public String getApellChof() {
      return apellChof;
   }

   public void setApellChof(String apellChof) {
      this.apellChof = apellChof;
   }

   public String getDireccChof() {
      return direccChof;
   }

   public void setDireccChof(String direccChof) {
      this.direccChof = direccChof;
   }

   public String getCatChof() {
      return catChof;
   }

   public void setCatChof(String catChof) {
      this.catChof = catChof;
   }

   public boolean isDisponible() {
      return disponible;
   }

   public void setDisponible(boolean disponible) {
      this.disponible = disponible;
   }
}
