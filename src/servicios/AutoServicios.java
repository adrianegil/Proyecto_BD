package servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Auto;

public class AutoServicios {

   private Auto recuperar(ResultSet result) {
      Auto aux = null;
      try {
         aux = new Auto();
         aux.setNumPlaca(result.getString(1));
         aux.setCodAuto(result.getInt(2));
         aux.setCantkm(result.getInt(3));
         aux.setColor(result.getString(4));
         aux.setTarifa(LocalizacionServicios.getServicios().tarifaServicios.buscarTarifa(result.getInt(5)));
         aux.setModelo(LocalizacionServicios.getServicios().modeloServicios.buscarModelo(result.getInt(6)));
         aux.setSituacion(LocalizacionServicios.getServicios().situacionServicios.buscarSituacion(result.getInt(7)));
      } catch (Exception e) {
         System.out.println(e);
      }
      return aux;
   }

   public Auto buscarAuto(String numPlaca) {
      Auto auto = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT * FROM auto WHERE	auto.placauto = '" + numPlaca + "';";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            auto = new Auto(result.getInt(2), result.getString(1), result.getInt(3), result.getString(4), LocalizacionServicios.getServicios().tarifaServicios.buscarTarifa(result.getInt(5))
                    , LocalizacionServicios.getServicios().modeloServicios.buscarModelo(result.getInt(6)),
                    LocalizacionServicios.getServicios().situacionServicios.buscarSituacion(result.getInt(7)));
         //auto=recuperar(result);
         consult.close();
         result.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return auto;
   }

   public Auto buscarAuto(int id_auto) {
      Auto auto = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT  * FROM 	auto WHERE	auto.codauto = " + id_auto + ";";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            auto = new Auto(result.getInt(2), result.getString(1), result.getInt(3), result.getString(4), LocalizacionServicios.getServicios().tarifaServicios.buscarTarifa(result.getInt(5))
                    , LocalizacionServicios.getServicios().modeloServicios.buscarModelo(result.getInt(6)),
                    LocalizacionServicios.getServicios().situacionServicios.buscarSituacion(result.getInt(7)));

         //	auto = recuperar(result);
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return auto;
   }

   public void insertarAuto(Auto auto) {
      try {
         String sqlSentenc = "SELECT auto_insertar(?,?,?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setString(1, auto.getNumPlaca());
         preparCons.setInt(2, auto.getCantkm());
         preparCons.setString(3, auto.getColor());
         preparCons.setInt(4, auto.getTarifa().getCodTarifa());
         preparCons.setInt(5, auto.getModelo().getCodModelo());
         preparCons.setInt(6, auto.getSituacion().getCodSituacion());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void modificarAuto(Auto auto) {
      try {
         String sqlSentenc = "SELECT auto_modificar(?,?,?,?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setInt(1, auto.getCodAuto());
         preparCons.setString(2, auto.getNumPlaca());
         preparCons.setInt(3, auto.getCantkm());
         preparCons.setString(4, auto.getColor());
         preparCons.setInt(5, auto.getTarifa().getCodTarifa());
         preparCons.setInt(6, auto.getModelo().getCodModelo());
         preparCons.setInt(7, auto.getSituacion().getCodSituacion());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void eliminarAuto(Auto auto) {

      try {
         String sqlSentenc = "SELECT auto_eliminar (?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setInt(1, auto.getCodAuto());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public LinkedList<Auto> listAutos() {
      LinkedList<Auto> List = new LinkedList<Auto>();
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = " SELECT * "
                 + " FROM auto ; ";
         ResultSet result = consult.executeQuery(sqlSentenc);
         while (result.next()) {
            //Auto aux = recuperar(result);
            Auto aux = new Auto(result.getInt(2), result.getString(1), result.getInt(3), result.getString(4), LocalizacionServicios.getServicios().tarifaServicios.buscarTarifa(result.getInt(5))
                    , LocalizacionServicios.getServicios().modeloServicios.buscarModelo(result.getInt(6)),
                    LocalizacionServicios.getServicios().situacionServicios.buscarSituacion(result.getInt(7)));
				/*
				aux.setNumPlaca(result.getString(1));
				aux.setCodAuto(result.getInt(2));	
				aux.setCantkm(result.getInt(3));
				aux.setColor(result.getString(4));
				aux.setTarifa(LocalizacionServicios.getServicios().tarifaServicios.buscarTarifa(result.getInt(5)));
			    aux.setModelo(LocalizacionServicios.getServicios().modeloServicios.buscarModelo(result.getInt(6)));
				aux.setSituacion(LocalizacionServicios.getServicios().situacionServicios.buscarSituacion(result.getInt(7)));
				 */
            List.add(aux);
         }
         consult.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return List;
   }
}
