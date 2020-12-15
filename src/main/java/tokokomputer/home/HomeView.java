/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tokokomputer.home;

import tokokomputer.barang.BarangView;
import tokokomputer.pelanggan.PelangganView;
import tokokomputer.transaksi.TransaksiView;

/**
 *
 * @author FAJAR SYA'BANIE
 */
public class HomeView extends javax.swing.JFrame {

    /** Creates new form HomeView */
    public HomeView() {
        initComponents();
    }

    public HomeView(String status){
     initComponents();
     if(status.equals("kasir")){
         menuData.setVisible(false);
         menuBarangMasuk.setVisible(false);
     }else if(status.equals("gudang")){
         menuData.setVisible(false);
         menuPenjualan.setVisible(false);
     }
     this.setExtendedState(MAXIMIZED_BOTH);//mengatur homeview full screen
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        panelMain = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAplikasi = new javax.swing.JMenu();
        menuKeluar = new javax.swing.JMenuItem();
        menuData = new javax.swing.JMenu();
        menuBarang = new javax.swing.JMenuItem();
        menuPelanggan = new javax.swing.JMenuItem();
        menuTransaksi = new javax.swing.JMenu();
        menuBarangMasuk = new javax.swing.JMenuItem();
        menuPenjualan = new javax.swing.JMenuItem();
        menuLaporan = new javax.swing.JMenu();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        getContentPane().add(panelMain, java.awt.BorderLayout.CENTER);

        menuAplikasi.setText("Aplikasi");

        menuKeluar.setText("Keluar");
        menuKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKeluarActionPerformed(evt);
            }
        });
        menuAplikasi.add(menuKeluar);

        jMenuBar1.add(menuAplikasi);

        menuData.setText("Data");

        menuBarang.setText("Barang");
        menuBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBarangActionPerformed(evt);
            }
        });
        menuData.add(menuBarang);

        menuPelanggan.setText("Pelanggan");
        menuPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPelangganActionPerformed(evt);
            }
        });
        menuData.add(menuPelanggan);

        jMenuBar1.add(menuData);

        menuTransaksi.setText("Transaksi");

        menuBarangMasuk.setText("Barang Masuk");
        menuTransaksi.add(menuBarangMasuk);

        menuPenjualan.setText("Penjualan");
        menuPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPenjualanActionPerformed(evt);
            }
        });
        menuTransaksi.add(menuPenjualan);

        jMenuBar1.add(menuTransaksi);

        menuLaporan.setText("Laporan");
        jMenuBar1.add(menuLaporan);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBarangActionPerformed
        BarangView bv = new BarangView();
        panelMain.add(bv);
        bv.setVisible(true);
    }//GEN-LAST:event_menuBarangActionPerformed

    private void menuKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKeluarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuKeluarActionPerformed

    private void menuPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPelangganActionPerformed
        PelangganView pv = new PelangganView();
        panelMain.add(pv);
        pv.setVisible(true);
    }//GEN-LAST:event_menuPelangganActionPerformed

    private void menuPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPenjualanActionPerformed
        TransaksiView tv = new TransaksiView();
        panelMain.add(tv);
        tv.setVisible(true);
    }//GEN-LAST:event_menuPenjualanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuAplikasi;
    private javax.swing.JMenuItem menuBarang;
    private javax.swing.JMenuItem menuBarangMasuk;
    private javax.swing.JMenu menuData;
    private javax.swing.JMenuItem menuKeluar;
    private javax.swing.JMenu menuLaporan;
    private javax.swing.JMenuItem menuPelanggan;
    private javax.swing.JMenuItem menuPenjualan;
    private javax.swing.JMenu menuTransaksi;
    private javax.swing.JDesktopPane panelMain;
    // End of variables declaration//GEN-END:variables

}
