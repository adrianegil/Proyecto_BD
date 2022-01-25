package modelo;

public class Usuario {

   private int codUsuario;
   private String nombUsuario;
   private String NombyApell;
   private String password;
   private Rol rol;

   public Usuario() {
      super();
   }

   public Usuario(int codUsuario, String nombUsuario, String nombyApell, String password, Rol rol) {
      super();
      this.codUsuario = codUsuario;
      this.nombUsuario = nombUsuario;
      NombyApell = nombyApell;
      this.password = password;
      this.rol = rol;
   }

   public String getNombyApell() {
      return NombyApell;
   }

   public void setNombyApell(String nombyApell) {
      NombyApell = nombyApell;
   }

   public int getCodUsuario() {
      return codUsuario;
   }

   public void setCodUsuario(int codUsuario) {
      this.codUsuario = codUsuario;
   }

   public String getNombUsuario() {
      return nombUsuario;
   }

   public void setNombUsuario(String nombUsuario) {
      this.nombUsuario = nombUsuario;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Rol getRol() {
      return rol;
   }

   public void setRol(Rol rol) {
      this.rol = rol;
   }
}
