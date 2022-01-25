package servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import auxiliar.Encriptar;
import modelo.Usuario;

public class UsuarioServicios {

   public LinkedList<Usuario> listUsuarios() {
      LinkedList<Usuario> list = new LinkedList<Usuario>();
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * FROM usuario;";
         ResultSet result = consult.executeQuery(sqlSentenc);
         while (result.next()) {
            Usuario user = recuperar(result);
            list.add(user);
         }
         consult.close();
         result.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return list;
   }

   public Usuario recuperar(ResultSet result) {
      Usuario usuario = null;
      try {
         usuario = new Usuario();
         usuario.setCodUsuario(result.getInt(1));
         usuario.setNombUsuario(result.getString(2));
         usuario.setNombyApell(result.getString(5));
         usuario.setPassword(result.getString(3));
         usuario.setRol(LocalizacionServicios.getServicios().rolServicios.buscarRol(result.getInt(4)));
      } catch (Exception e) {
         System.out.println(e);
      }
      return usuario;
   }

   public Usuario buscarUsuario(String nomusuario) {
      Usuario usuario = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT *" + "FROM usuario WHERE nombusuario='"
                 + nomusuario + "';";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            usuario = recuperar(result);
         consult.close();
         result.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return usuario;
   }

   public void insertarUsuario(Usuario usuario) {
      try {
         String sqlSentenc = "SELECT usuario_insertar(?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setString(1, usuario.getNombUsuario());
         preparCons.setString(4, usuario.getNombyApell());
         preparCons.setString(2, Encriptar.getMd5(usuario.getPassword()));
         preparCons.setInt(3, usuario.getRol().getCodRol());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void eliminarUsuario(String nombUsuario) throws SQLException, ClassNotFoundException {
      String sqlSentenc = "SELECT usuario_eliminar(?); ";
      PreparedStatement prepararCons = LocalizacionServicios.getConnection().prepareStatement(sqlSentenc);
      prepararCons.setString(1, nombUsuario);
      prepararCons.execute();
      prepararCons.close();
   }

   public void actualizarUsuario(Usuario user) {
      try {
         String sqlSentenc = "SELECT usuario_modificar(?,?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setInt(1, user.getCodUsuario());
         preparCons.setString(2, user.getNombUsuario());
         preparCons.setString(3, Encriptar.getMd5(user.getPassword()));
         preparCons.setInt(4, user.getRol().getCodRol());
         preparCons.setString(5, user.getNombyApell());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }
}
