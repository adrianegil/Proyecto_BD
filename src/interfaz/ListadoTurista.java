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
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import modelo.Turista;
import servicios.LocalizacionServicios;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoTurista extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JTable table;
   private DefaultTableModel defaultTableModel = new DefaultTableModel();
   private JButton btnModificar;
   private JButton btnEliminar;
   int posicion = -1;

   public ListadoTurista() {
      setTitle("Listados de Turistas");
      setBounds(100, 100, 537, 475);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      JPanel panel = new JPanel();
      panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Turistas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

      JPanel panel_1 = new JPanel();
      GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
      gl_contentPanel.setHorizontalGroup(
              gl_contentPanel.createParallelGroup(Alignment.LEADING)
                      .addComponent(panel, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                      .addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
      );
      gl_contentPanel.setVerticalGroup(
              gl_contentPanel.createParallelGroup(Alignment.LEADING)
                      .addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
                              .addGap(20)
                              .addComponent(panel, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                              .addGap(18)
                              .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
      );
      JScrollPane scrollPane = new JScrollPane();
      GroupLayout gl_panel = new GroupLayout(panel);
      gl_panel.setHorizontalGroup(
              gl_panel.createParallelGroup(Alignment.LEADING)
                      .addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
      );
      gl_panel.setVerticalGroup(
              gl_panel.createParallelGroup(Alignment.LEADING)
                      .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
      );
      {
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
      }
      panel.setLayout(gl_panel);
      panel_1.setLayout(new GridLayout(0, 4, 0, 0));
      {
         JButton btnAceptar = new JButton("Insertar");
         btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               ListadoTurista.this.dispose();
               Turista t = new Turista();
               InterfazTurista dialog = new InterfazTurista(t, 1);
               dialog.setLocationRelativeTo(null);
               dialog.setModal(true);
               dialog.setVisible(true);
            }
         });
         panel_1.add(btnAceptar);
      }
      {
         btnModificar = new JButton("Modificar");
         btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               long position = (long) defaultTableModel.getValueAt(table.getSelectedRow(), 0);
               Turista ver = LocalizacionServicios.getServicios().turistaServicios.buscarTurista(position);
               ListadoTurista.this.dispose();
               InterfazTurista dialog = new InterfazTurista(ver, 2);
               dialog.setLocationRelativeTo(null);
               dialog.setModal(true);
               dialog.setVisible(true);
            }
         });
         btnModificar.setEnabled(false);
         panel_1.add(btnModificar);
      }
      {
         btnEliminar = new JButton("Eliminar");
         btnEliminar.setEnabled(false);
         btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               long position = (long) defaultTableModel.getValueAt(table.getSelectedRow(), 0);
               Turista ver = LocalizacionServicios.getServicios().turistaServicios.buscarTurista(position);
               int resp = JOptionPane.showConfirmDialog(ListadoTurista.this, "Realmente desea eliminar este turista", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
               if (resp == JOptionPane.YES_OPTION) {
                  LocalizacionServicios.getServicios().turistaServicios.eliminarTurista(ver);
                  actualizarTabla();
                  btnModificar.setEnabled(false);
                  btnEliminar.setEnabled(false);
               }
            }
         });
         panel_1.add(btnEliminar);
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

      Object[] aux = new Object[]{"Num. de Pasaporte", "Nombre", "Apellidos", "Sexo", "Edad", "Num. de Contacto", "Pais"};
      LinkedList<Turista> listTuris = new LinkedList<>();
      defaultTableModel = new DefaultTableModel(aux, 0);


      try {
         listTuris = LocalizacionServicios.getServicios().turistaServicios.listTurista();
      } catch (Exception e1) {
         JOptionPane.showMessageDialog(this, "Error en la Base de Datos");
         dispose();
      }
      for (Turista tur : listTuris) {
         defaultTableModel.addRow(new Object[]{tur.getNumPaspTuris(), tur.getNombTuris(), tur.getApellTuris(), tur.getSexTuris(), tur.getEdadTuris(), tur.getContactTurist(), tur.getPaisTuris().getNombPais()});
      }
      table.setModel(defaultTableModel);
   }
}
