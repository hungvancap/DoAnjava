/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.BangdiemController;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Bangdiemlophoc extends javax.swing.JPanel {

    /**
     * Creates new form Bangdiemlophoc
     */
    public Bangdiemlophoc() {
        initComponents();
        
    }
    public boolean check;public String selection;
    public Bangdiemlophoc( String selection) throws SQLException {
        initComponents();
        this.selection=selection;
        BangdiemController controller=new BangdiemController(this,jTableView,this.selection);
        controller.setEvent();
    }

    

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableView = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã số sinh viên", "Họ tên", "Điểm quá trình", "Điểm giữa kì", "Điểm thực hành", "Điểm cuối kì"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableView);
        if (jTableView.getColumnModel().getColumnCount() > 0) {
            jTableView.getColumnModel().getColumn(0).setResizable(false);
            jTableView.getColumnModel().getColumn(1).setResizable(false);
            jTableView.getColumnModel().getColumn(2).setResizable(false);
            jTableView.getColumnModel().getColumn(3).setResizable(false);
            jTableView.getColumnModel().getColumn(4).setResizable(false);
            jTableView.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableView;
    // End of variables declaration//GEN-END:variables
}