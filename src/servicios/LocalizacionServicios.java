package servicios;

import java.sql.SQLException;

import auxiliar.Conexion;

public class LocalizacionServicios {

   public AutoServicios autoServicio;
   public ChoferServicios choferServicios;
   public ContratoServicios contratoServicios;
   public PaisServicios paisServicios;
   public TuristaServicios turistaServicios;
   public UsuarioServicios usuarioServicios;
   public RolServicios rolServicios;
   public MarcaServicios marcaServicios;
   public ModeloServicios modeloServicios;
   public SituacionServicios situacionServicios;
   public TarifaServicios tarifaServicios;
   private static LocalizacionServicios servicios;

   public LocalizacionServicios() {
      autoServicio = new AutoServicios();
      choferServicios = new ChoferServicios();
      contratoServicios = new ContratoServicios();
      paisServicios = new PaisServicios();
      turistaServicios = new TuristaServicios();
      usuarioServicios = new UsuarioServicios();
      rolServicios = new RolServicios();
      marcaServicios = new MarcaServicios();
      modeloServicios = new ModeloServicios();
      situacionServicios = new SituacionServicios();
      tarifaServicios = new TarifaServicios();
   }

   public static LocalizacionServicios getServicios() {
      if (servicios == null)
         servicios = new LocalizacionServicios();
      return servicios;
   }

   public static java.sql.Connection getConnection() {
      Conexion connection = null;
      try {
         connection = new Conexion("localhost", "BD_Proyecto", "postgres", "1234");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return connection.getConnection();
   }
}
