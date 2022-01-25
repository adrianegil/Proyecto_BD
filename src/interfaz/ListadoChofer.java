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
import java.util.LinkedList;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Chofer;
import servicios.LocalizacionServicios;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListadoChofer extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JTable table;
   private DefaultTableModel defaultTableModel = null;
   private LinkedList<Chofer> listChofer = null;
   int posicion = -1;
   JButton btnModif = null;
   JButton btnElim = null;

   public ListadoChofer() {

      setTitle("Listado de Choferes");
      setBounds(100, 100, 588, 468);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      JPanel panel = new JPanel();
      JPanel panel_1 = new JPanel();
      panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Choferes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
      GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
      gl_contentPanel.setHorizontalGroup(
              gl_contentPanel.createParallelGroup(Alignment.LEADING)
                      .addComponent(panel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                      .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
      );
      gl_contentPanel.setVerticalGroup(
              gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                      .addGroup(gl_contentPanel.createSequentialGroup()
                              .addContainerGap()
                              .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                              .addPreferredGap(ComponentPlacement.UNRELATED)
                              .addComponent(panel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
      );

      JScrollPane scrollPane = new JScrollPane();
      GroupLayout gl_panel_1 = new GroupLayout(panel_1);
      gl_panel_1.setHorizontalGroup(
              gl_panel_1.createParallelGroup(Alignment.LEADING)
                      .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
      );
      gl_panel_1.setVerticalGroup(
              gl_panel_1.createParallelGroup(Alignment.LEADING)
                      .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
      );

      table = new JTable();
      table.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {

            posicion = table.getSelectedRow();
            if (posicion != -1) {
               btnModif.setEnabled(true);
               btnElim.setEnabled(true);
            }
         }

      });
      scrollPane.setViewportView(table);
      panel_1.setLayout(gl_panel_1);
      panel.setLayout(new GridLayout(0, 4, 0, 0));
      {
         JButton btnNewButton = new JButton("Insertar");
         btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Chofer chof = new Chofer();
               InterfazChofer dialog = new InterfazChofer(chof, 1);
               ListadoChofer.this.dispose();
               dialog.setLocationRelativeTo(null);
               dialog.setVisible(true);
            }
         });
         panel.add(btnNewButton);
      }
      {
         btnModif = new JButton("Modificar");
         btnModif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String position = (String) defaultTableModel.getValueAt(table.getSelectedRow(), 0);
               Chofer chofer = LocalizacionServicios.getServicios().choferServicios.buscarChofer(position);
               ListadoChofer.this.dispose();
               InterfazChofer dialog = new InterfazChofer(chofer, 2);
               dialog.setLocationRelativeTo(null);
               dialog.setModal(true);
               dialog.setVisible(true);
            }
         });
         btnModif.setEnabled(false);

         panel.add(btnModif);
      }
      {
         btnElim = new JButton("Eliminar");
         btnElim.setEnabled(false);

         btnElim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

               String position = (String) defaultTableModel.getValueAt(table.getSelectedRow(), 0);
               Chofer chofer = LocalizacionServicios.getServicios().choferServicios.buscarChofer(position);

               if (chofer.isDisponible()) {
                  int resp = JOptionPane.showConfirmDialog(ListadoChofer.this, "Realmente desea eliminar este chofer", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
                  if (resp == JOptionPane.YES_OPTION) {
                     LocalizacionServicios.getServicios().choferServicios.eliminarChofer(chofer);
                     actualizarTabla();
                     btnModif.setEnabled(false);
                     btnElim.setEnabled(false);
                  }
               } else {
                  JOptionPane.showMessageDialog(ListadoChofer.this, "No se puede eliminar este chofer porque está de contrato");
                  btnElim.setEnabled(false);
               }
            }
         });
         panel.add(btnElim);
      }
      {
         JButton btnNewButton_3 = new JButton("Cancelar");
         btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
         });
         panel.add(btnNewButton_3);
      }
      contentPanel.setLayout(gl_contentPanel);

      actualizarTabla();
   }


   public void actualizarTabla() {

      Object[] aux = new Object[]{"ID del Chofer", "Nombre", "Apellidos", "Dirección", "Categoria", "Disponibilidad"};
      defaultTableModel = new DefaultTableModel(aux, 0);

      try {
         listChofer = LocalizacionServicios.getServicios().choferServicios.listChoferes();
      } catch (Exception e1) {
         JOptionPane.showMessageDialog(this, "Error en la Base de Datos");
         dispose();
      }
      for (Chofer chofer : listChofer) {
         defaultTableModel.addRow(new Object[]{chofer.getIdChof(), chofer.getNombChof(), chofer.getApellChof(), chofer.getDireccChof(), chofer.getCatChof(), chofer.isDisponible()});
      }
      table.setModel(defaultTableModel);
   }
}
