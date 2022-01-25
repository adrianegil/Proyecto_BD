package servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Chofer;

public class ChoferServicios {

   public LinkedList<Chofer> listChoferes() {

      LinkedList<Chofer> List = new LinkedList<Chofer>();
      try {

         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = " SELECT * "
                 + " FROM chofer ; ";
         ResultSet result = consult.executeQuery(sqlSentenc);
         while (result.next()) {
            Chofer chofer = new Chofer(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
            chofer.setDisponible(result.getBoolean(7));
            List.add(chofer);
         }
         consult.close();
         result.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return List;
   }

   public Chofer buscarChofer(String id) {
      Chofer chofer = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT 	* FROM 	chofer WHERE chofer.idchof = '" + id + "';";

         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            chofer = new Chofer(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
         chofer.setDisponible(result.getBoolean(7));
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return chofer;
   }

   public Chofer buscarChofer(int cod_chofer) {
      Chofer chofer = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT  * FROM chofer	WHERE	chofer.codchof = " + cod_chofer + ";";

         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            chofer = new Chofer(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return chofer;
   }

   public void eliminarChofer(Chofer chofer) {

      try {
         String sqlSentenc = "SELECT chofer_eliminar(?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setInt(1, chofer.getCodChof());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void insertarChofer(Chofer chofer) {
      try {
         String sqlSentenc = "SELECT chofer_insertar(?,?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setString(1, chofer.getIdChof());
         preparCons.setString(2, chofer.getNombChof());
         preparCons.setString(3, chofer.getApellChof());
         preparCons.setString(4, chofer.getDireccChof());
         preparCons.setString(5, chofer.getCatChof());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void modificarChofer(Chofer chofer) {

      try {
         String sqlSentenc = "SELECT chofer_modificar(?,?,?,?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setInt(1, chofer.getCodChof());
         preparCons.setString(2, chofer.getIdChof());
         preparCons.setString(3, chofer.getNombChof());
         preparCons.setString(4, chofer.getApellChof());
         preparCons.setString(5, chofer.getDireccChof());
         preparCons.setString(6, chofer.getCatChof());
         preparCons.setBoolean(7, chofer.isDisponible());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }
}
