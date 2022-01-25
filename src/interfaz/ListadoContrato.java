package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import modelo.Auto;
import modelo.Chofer;
import modelo.Contrato;
import modelo.Situacion;
import modelo.Usuario;
import servicios.LocalizacionServicios;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoContrato extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JTable table;
   private DefaultTableModel defaultTableModel_Contrato;
   private DefaultTableModel defaultTableModel_ContratoAct = new DefaultTableModel();
   private JButton btnCrearContrato;
   private JButton btnFinalizar;
   private Usuario usuario;
   private DefaultTableModel defaultTableModel_ContratoFinal = new DefaultTableModel();
   private JTable table_1;

   public ListadoContrato(Usuario usuario) {

      this.usuario = usuario;

      setBounds(100, 100, 623, 498);
      setSize(1200, 700);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      JPanel panel = new JPanel();
      panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Contratos Activos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      JPanel panel_1 = new JPanel();

      JPanel panel_2 = new JPanel();
      panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Contratos Finalizados", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
      gl_contentPanel.setHorizontalGroup(
              gl_contentPanel.createParallelGroup(Alignment.LEADING)
                      .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
                      .addComponent(panel, GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
                      .addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
      );
      gl_contentPanel.setVerticalGroup(
              gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                      .addGroup(gl_contentPanel.createSequentialGroup()
                              .addGap(19)
                              .addComponent(panel, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                              .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
                              .addGap(27)
                              .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
      );

      JScrollPane scrollPane_1 = new JScrollPane();
      GroupLayout gl_panel_2 = new GroupLayout(panel_2);
      gl_panel_2.setHorizontalGroup(
              gl_panel_2.createParallelGroup(Alignment.LEADING)
                      .addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
      );
      gl_panel_2.setVerticalGroup(
              gl_panel_2.createParallelGroup(Alignment.LEADING)
                      .addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
      );

      table_1 = new JTable();
      table_1.setEnabled(false);
      scrollPane_1.setViewportView(table_1);
      panel_2.setLayout(gl_panel_2);
      JScrollPane scrollPane = new JScrollPane();
      GroupLayout gl_panel = new GroupLayout(panel);
      gl_panel.setHorizontalGroup(
              gl_panel.createParallelGroup(Alignment.LEADING)
                      .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                              .addContainerGap()
                              .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE))
      );
      gl_panel.setVerticalGroup(
              gl_panel.createParallelGroup(Alignment.LEADING)
                      .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                              .addContainerGap()
                              .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                              .addContainerGap())
      );
      {
         table = new JTable();
         table.setUpdateSelectionOnSort(false);
         table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               int position = (int) defaultTableModel_Contrato.getValueAt(table.getSelectedRow(), 0);
               Contrato cont = LocalizacionServicios.getServicios().contratoServicios.buscarContrato(position);

               if (cont.getFechEntregAuto() == null) {
                  btnFinalizar.setEnabled(true);
               } else {
                  btnFinalizar.setEnabled(false);
               }
            }
         });
         scrollPane.setViewportView(table);
      }
      panel.setLayout(gl_panel);
      panel_1.setLayout(new GridLayout(0, 3, 0, 0));
      {
         btnFinalizar = new JButton("Finalizar Contrato");
         btnFinalizar.setEnabled(false);
         btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               int position = (int) defaultTableModel_Contrato.getValueAt(table.getSelectedRow(), 0);
               Contrato cont = LocalizacionServicios.getServicios().contratoServicios.buscarContrato(position);
               GregorianCalendar fecha = new GregorianCalendar();
               Date fechaEntreg = new Date(fecha.get(Calendar.YEAR) - 1900, fecha.get(Calendar.MONTH), fecha.get(Calendar.DATE));
               Auto at = LocalizacionServicios.getServicios().autoServicio.buscarAuto(cont.getAuto().getCodAuto());
               at.setSituacion(new Situacion(3, "Disponible"));
               LocalizacionServicios.getServicios().autoServicio.modificarAuto(at);
               Chofer chof = LocalizacionServicios.getServicios().choferServicios.buscarChofer(cont.getChofer().getCodChof());
               chof.setDisponible(true);
               LocalizacionServicios.getServicios().choferServicios.modificarChofer(chof);
               LocalizacionServicios.getServicios().contratoServicios.actualizarContrato(cont, fechaEntreg);
               actualizarTabla();
            }
         });
         panel_1.add(btnFinalizar);
      }
      {
         btnCrearContrato = new JButton("Crear nuevo Contrato");
         btnCrearContrato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               ListadoContrato.this.dispose();
               InterfazContrato dialog = new InterfazContrato(ListadoContrato.this.usuario);
               dialog.setLocationRelativeTo(null);
               dialog.setModal(true);
               dialog.setVisible(true);
            }
         });
         panel_1.add(btnCrearContrato);
      }
      {
         JButton btnCancelar = new JButton("Cancelar");
         btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
         });
         panel_1.add(btnCancelar);
      }
      contentPanel.setLayout(gl_contentPanel);

      actualizarTabla();
   }


   public void actualizarTabla() {

      LinkedList<Contrato> list = new LinkedList<>();
      Object[] aux = new Object[]{"Num. de Contrato", "Cliente", "Auto", "Fecha de Inicio", "Fecha de Fin", "Cant. Dias Pórroga", "Fecha de Entrega", "Forma de Pago", "Chofer", "Importe Total", "Contratista"};
      defaultTableModel_Contrato = new DefaultTableModel(aux, 0);
      defaultTableModel_ContratoFinal = new DefaultTableModel(aux, 0);
      try {
         list = LocalizacionServicios.getServicios().contratoServicios.listContratos();

      } catch (Exception e1) {
         JOptionPane.showMessageDialog(this, "Error en la Base de Datos");
         dispose();
      }
      for (Contrato cont : list) {
         if (cont.getFechEntregAuto() == null) {
            defaultTableModel_Contrato.addRow(new Object[]{cont.getCodContrat(), cont.getTurista(), cont.getAuto(), cont.getFechInicio(), cont.getFechFin(), cont.getCantDiasPorr(), cont.getFechEntregAuto(),
                    cont.getFormPago(), cont.getChofer(), cont.getImporteTotal(), cont.getNombContratista()});
         } else {
            defaultTableModel_ContratoFinal.addRow(new Object[]{cont.getCodContrat(), cont.getTurista(), cont.getAuto(), cont.getFechInicio(), cont.getFechFin(), cont.getCantDiasPorr(), cont.getFechEntregAuto(),
                    cont.getFormPago(), cont.getChofer(), cont.getImporteTotal(), cont.getNombContratista()});
         }
      }
      table.setModel(defaultTableModel_Contrato);
      table_1.setModel(defaultTableModel_ContratoFinal);
   }
}
