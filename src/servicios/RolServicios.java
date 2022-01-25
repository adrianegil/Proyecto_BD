package servicios;

import java.sql.ResultSet;
import java.sql.Statement;

import modelo.Rol;


public class RolServicios {

   public Rol buscarRol(int codRol) {
      Rol rol = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT *  FROM rol WHERE codrol = " + codRol + ";";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            rol = recuperar(result);
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return rol;
   }

   private Rol recuperar(ResultSet result) {
      Rol rol = null;
      try {
         rol = new Rol();
         rol.setCodRol(result.getInt(1));
         rol.setNombRol(result.getString(2));
      } catch (Exception e) {
         System.out.println(e);
      }
      return rol;
   }
}
