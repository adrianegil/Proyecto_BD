package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

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
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import modelo.Auto;
import modelo.Chofer;
import modelo.Turista;
import servicios.LocalizacionServicios;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoAuto extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JTable table;
   private DefaultTableModel defaultTableModel_Auto = new DefaultTableModel();
   private JButton btnModificar;
   private JButton btnEliminar;
   private int posicion;


   public ListadoAuto() {
      setTitle("Listado de Autos");
      setBounds(100, 100, 624, 507);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);

      JPanel panel = new JPanel();
      panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Autos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      JPanel panel_1 = new JPanel();
      GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
      gl_contentPanel.setHorizontalGroup(
              gl_contentPanel.createParallelGroup(Alignment.LEADING)
                      .addComponent(panel, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                      .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
      );
      gl_contentPanel.setVerticalGroup(
              gl_contentPanel.createParallelGroup(Alignment.LEADING)
                      .addGroup(gl_contentPanel.createSequentialGroup()
                              .addGap(21)
                              .addComponent(panel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                              .addGap(18)
                              .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
      );

      JScrollPane scrollPane = new JScrollPane();
      GroupLayout gl_panel = new GroupLayout(panel);
      gl_panel.setHorizontalGroup(
              gl_panel.createParallelGroup(Alignment.LEADING)
                      .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
      );
      gl_panel.setVerticalGroup(
              gl_panel.createParallelGroup(Alignment.LEADING)
                      .addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
      );

      table = new JTable();
      table.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            posicion = table.getSelectedRow();
            if (posicion != -1) {
               btnModificar.setEnabled(true);
               btnEliminar.setEnabled(true);
            }
         }
      });
      scrollPane.setViewportView(table);
      panel.setLayout(gl_panel);
      panel_1.setLayout(new GridLayout(0, 4, 0, 0));

      JButton btnInsertar = new JButton("Insertar");
      btnInsertar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ListadoAuto.this.dispose();
            InterfazAuto dialog = new InterfazAuto(new Auto(), 1);
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });
      panel_1.add(btnInsertar);

      btnModificar = new JButton("Modificar");
      btnModificar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ListadoAuto.this.dispose();
            String position = (String) defaultTableModel_Auto.getValueAt(table.getSelectedRow(), 0);
            Auto auto = LocalizacionServicios.getServicios().autoServicio.buscarAuto(position);
            InterfazAuto dialog = new InterfazAuto(auto, 2);
            dialog.setModal(true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
         }
      });
      btnModificar.setEnabled(false);
      panel_1.add(btnModificar);

      btnEliminar = new JButton("Eliminar");
      btnEliminar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            String position = (String) defaultTableModel_Auto.getValueAt(table.getSelectedRow(), 0);
            Auto auto = LocalizacionServicios.getServicios().autoServicio.buscarAuto(position);

            if (auto.getSituacion().getCodSituacion() == 2) {
               JOptionPane.showMessageDialog(ListadoAuto.this, "No puede eliminar este auto porque esta alquilado");
               btnEliminar.setEnabled(false);
            } else {
               int resp = JOptionPane.showConfirmDialog(ListadoAuto.this, "Realmente desea eliminar este auto", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);

               if (resp == JOptionPane.YES_OPTION) {
                  LocalizacionServicios.getServicios().autoServicio.eliminarAuto(auto);
                  actualizarTabla();
                  btnModificar.setEnabled(false);
                  btnEliminar.setEnabled(false);
               }
            }
         }
      });
      btnEliminar.setEnabled(false);
      panel_1.add(btnEliminar);

      JButton btnCancelar = new JButton("Cancelar");
      btnCancelar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });
      panel_1.add(btnCancelar);
      contentPanel.setLayout(gl_contentPanel);

      actualizarTabla();
   }


   public void actualizarTabla() {

      Object[] aux = new Object[]{"Num. de placa", "Kilometraje", "Marca", "Modelo", "Color", "Ingreso", "Ingreso especial", "Situación"};
      LinkedList<Auto> listAutos = new LinkedList<>();
      defaultTableModel_Auto = new DefaultTableModel(aux, 0);


      try {
         listAutos = LocalizacionServicios.getServicios().autoServicio.listAutos();
      } catch (Exception e1) {
         JOptionPane.showMessageDialog(this, "Error en la Base de Datos");
         dispose();
      }
      for (Auto at : listAutos) {
         defaultTableModel_Auto.addRow(new Object[]{at.getNumPlaca(), at.getCantkm(), at.getMarca().getNombMarca(), at.getModelo().getNombModelo(), at.getColor(), at.getTarifa().getIngreso()
                 , at.getTarifa().getIngresoEspecial(), at.getSituacion().getNombSituacion()});
      }
      table.setModel(defaultTableModel_Auto);
   }
}
