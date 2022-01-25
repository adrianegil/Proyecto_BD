package servicios;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import modelo.Contrato;

public class ContratoServicios {

   private Contrato recuperar(ResultSet result) {
      Contrato aux = null;
      try {
         aux = new Contrato();
         aux.setAuto(LocalizacionServicios.getServicios().autoServicio.buscarAuto(result.getInt(1)));
         aux.setTurista(LocalizacionServicios.getServicios().turistaServicios.buscarTurista(result.getInt(2)));
         aux.setCodContrat(result.getInt(3));
         aux.setFechInicio(result.getDate(4));
         aux.setFechFin(result.getDate(5));
         aux.setCantDiasPorr(result.getInt(6));
         aux.setFormPago(result.getString(7));
         aux.setChofer(LocalizacionServicios.getServicios().choferServicios.buscarChofer(result.getInt(8)));
         aux.setFechEntregAuto(result.getDate(9));
         aux.setImporteTotal(result.getDouble(10));
         aux.setNombContratista(result.getString(11));
      } catch (Exception e) {
         e.printStackTrace();
      }
      return aux;
   }

   public LinkedList<Contrato> listContratos() {
      LinkedList<Contrato> List = new LinkedList<Contrato>();
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(
                 ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT  *  FROM contrato;";
         ResultSet result = consult.executeQuery(sqlSentenc);
         while (result.next()) {
            Contrato contrato = recuperar(result);
            List.add(contrato);
         }
         consult.close();
         result.close();
      } catch (Exception e) {
         System.out.println(e);
      }
      return List;
   }

   public Contrato buscarContrato(int num_contrato) {
      Contrato aux = null;
      try {
         Statement consult = LocalizacionServicios.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         String sqlSentenc = "SELECT	*  FROM 	contrato WHERE	contrato.codcontr = " + num_contrato + ";";
         ResultSet result = consult.executeQuery(sqlSentenc);
         if (result.next())
            aux = recuperar(result);
         consult.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return aux;
   }

   public void insertarContrato1(Contrato cont) throws SQLException {
      try {
         String sqlSentenc = "SELECT contrato_insertar1(?,?,?,?,?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setInt(1, cont.getAuto().getCodAuto());
         preparCons.setInt(2, cont.getTurista().getCodTuris());
         preparCons.setDate(3, new java.sql.Date(cont.getFechInicio().getTime()));
         preparCons.setDate(4, new java.sql.Date(cont.getFechFin().getTime()));
         preparCons.setString(5, cont.getFormPago());
         preparCons.setInt(6, cont.getChofer().getCodChof());
         preparCons.setDouble(7, cont.getImporteTotal());
         preparCons.setString(8, cont.getNombContratista());
         preparCons.execute();
         preparCons.close();
      } catch (SQLException e) {
         System.out.println(e);
         throw e;
      }
   }

   public void insertarContrato2(Contrato cont) throws SQLException {
      try {
         String sqlSentenc = "SELECT contrato_insertar2(?,?,?,?,?,?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setInt(1, cont.getAuto().getCodAuto());
         preparCons.setInt(2, cont.getTurista().getCodTuris());
         //java.sql.Date sql = new java.sql.Date(cont.getFechInicio().getTime());
         preparCons.setDate(3, new java.sql.Date(cont.getFechInicio().getTime()));
         preparCons.setDate(4, new java.sql.Date(cont.getFechFin().getTime()));
         preparCons.setString(5, cont.getFormPago());
         preparCons.setDouble(6, cont.getImporteTotal());
         preparCons.setString(7, cont.getNombContratista());
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public void actualizarContrato(Contrato cont, Date date) {
      try {
         String sqlSentenc = "SELECT contrato_actualizar(?,?);";
         PreparedStatement preparCons = LocalizacionServicios.getConnection()
                 .prepareStatement(sqlSentenc);
         preparCons.setInt(1, cont.getCodContrat());
         preparCons.setDate(2, date);
         preparCons.execute();
         preparCons.close();
      } catch (Exception e) {
         System.out.println(e);
      }
   }
}
