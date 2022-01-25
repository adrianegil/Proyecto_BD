package servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Pais;
import modelo.Turista;

public class TuristaServicios {

   public LinkedList<Turista> listTurista() {

      LinkedList<Turista> List = new LinkedList<Turista>();
      try {

         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = " SELECT * "
                 + " FROM turista ; ";
         ResultSet result = consult.executeQuery(sqlSentenc);
         while (result.next()) {
            Pais pais = LocalizacionServicios.getServicios().paisServicios.buscarPais(result.getInt(8));
            Turista tur = new Turista(result.getInt(1), result.getLong(2), result.getString(3),
                    result.getString(4), result.getString(5), result.getInt(6), result.getLong(7), pais);
            List.add(tur);
         }
         consult.close();
         result.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return List;
   }

   public Turista buscarTurista(long num_pasaporte) {
      Turista turista = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * "
                 + "FROM turista "
                 + "WHERE numpaspturis='" + num_pasaporte + "';";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next()) {
            Pais pais = LocalizacionServicios.getServicios().paisServicios.buscarPais(result.getInt(8));
            turista = new Turista(result.getInt(1), result.getLong(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getLong(7), pais);
         }
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return turista;
   }

   public Turista buscarTurista(int id_turista) {
      Turista turista = null;

      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * "
                 + "FROM turista "
                 + "WHERE codturis =" + id_turista + ";";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next()) {
            Pais pais = LocalizacionServicios.getServicios().paisServicios.buscarPais(result.getInt(8));
            turista = new Turista(result.getInt(1), result.getLong(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getLong(7), pais);
         }
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return turista;
   }

   public void insertarTurista(Turista turista) {
      try {
         String sqlSentenc = "SELECT turista_insertar(?,?,?,?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setLong(1, turista.getNumPaspTuris());
         preparCons.setString(2, turista.getNombTuris());
         preparCons.setString(3, turista.getApellTuris());
         preparCons.setString(4, turista.getSexTuris());
         preparCons.setInt(5, turista.getEdadTuris());
         preparCons.setLong(6, turista.getContactTurist());
         preparCons.setInt(7, turista.getPaisTuris().getCodPais());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void modificarTurista(Turista turista) {
      try {
         String sqlSentenc = "SELECT turista_modificar(?,?,?,?,?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setInt(1, turista.getCodTuris());
         preparCons.setLong(2, turista.getNumPaspTuris());
         preparCons.setString(3, turista.getNombTuris());
         preparCons.setString(4, turista.getApellTuris());
         preparCons.setString(5, turista.getSexTuris());
         preparCons.setInt(6, turista.getEdadTuris());
         preparCons.setLong(7, turista.getContactTurist());
         preparCons.setInt(8, turista.getPaisTuris().getCodPais());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void eliminarTurista(Turista turista) {

      try {
         String sqlSentenc = "SELECT turista_eliminar(?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setLong(1, turista.getNumPaspTuris());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }
}
