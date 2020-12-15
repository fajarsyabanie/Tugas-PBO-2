/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokokomputer.transaksi;

import java.math.BigDecimal;
import tokokomputer.barang.CariBarangView;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tokokomputer.pengaturan.Koneksi;

/**
 *
 * @author FAJAR SYA'BANIE
 */
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tokokomputer.pengaturan.Koneksi;

public class TransaksiView extends javax.swing.JInternalFrame {

    /**
     * Creates new form TransaksiView
     */
    public TransaksiView() {
        initComponents();
        data_pelanggan();
        ulang();
    }
    PreparedStatement pst;
    ResultSet rs;
    Connection conn = new Koneksi().getKoneksi();
    String status, sql;
    DefaultTableModel dtm;
    
    private void data_pelanggan(){
        try {
            comboPelanggan.removeAllItems();
            comboPelanggan.addItem("Pilih Pelanggan");
            pst = conn.prepareStatement("SELECT nama_pelanggan FROM tb_pelanggan");
            rs = pst.executeQuery();
            while(rs.next()){
                comboPelanggan.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

}
        private void nota_otomatis(){
        try{
            sql = "SELECT no_nota FROM tb_penjualan ORDER BY no_nota desc limit 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                int kode = Integer.parseInt(rs.getString(1).substring(4)) + 1;
                textNota.setText("NTA-"+kode);
            }else{
                textNota.setText("NTA-1000");
            }
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    
    }
        
        private void ulang() {
        nota_otomatis();
        comboPelanggan.setSelectedIndex(0);
        textNota.setEnabled(false);
        textIDPelanggan.setEnabled(false);
        textKodeBarang.setEnabled(false);
        textNamaBarang.setEnabled(false);
        textKategori.setEnabled(false);
        textHarga.setEnabled(false);
        textStock.setEnabled(false);
        textTotal.setEnabled(false);
        textIDPelanggan.setText("");
        textKodeBarang.setText("");
        textKategori.setText("");
        textHarga.setText("");
        textStock.setText("");
        textQty.setText("");
        textTotal.setText("");
        textBayar.setText("");
        textKembali.setEnabled(false);
        dtm = (DefaultTableModel)tabelItemBelanja.getModel();
        while (dtm.getRowCount() > 0) 
        {
            dtm.removeRow(0);
        }
    }
        
        
        private void hitung_total() {
        BigDecimal total = new BigDecimal(0);
        for (int a = 0; a < tabelItemBelanja.getRowCount(); a++) {
            total = total.add(new BigDecimal(tabelItemBelanja.getValueAt(a, 5).toString()));
        }
        textTotal.setText(total.toString());
    }
        
        
        private boolean validasi() {
        boolean cek = false;
        java.util.Date tgl = textTanggal.getDate();
        if (tgl == null) {
            JOptionPane.showMessageDialog(null, "Tanggal Transaksi Belum Diisi!", null, JOptionPane.ERROR_MESSAGE);
            textTanggal.requestFocus();
        } else if (textIDPelanggan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data Pelanggan Belum Dipilih", null, JOptionPane.ERROR_MESSAGE);
            comboPelanggan.requestFocus();
        } else if (tabelItemBelanja.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Data barang Belanja Masih Kosong!", null, JOptionPane.ERROR_MESSAGE);
            buttonCariBarang.requestFocus();
        } else if (textBayar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Textbox Bayar Belum Diisi!", null, JOptionPane.ERROR_MESSAGE);
            textBayar.requestFocus();
        } else {
            cek = true;
        }
        return cek;
    }
        
        
        private void simpan_transaksi() {
        if (validasi()) {
            try {
                java.util.Date d = textTanggal.getDate();
                java.sql.Date tgl = new java.sql.Date(d.getTime());
                pst = conn.prepareStatement("insert into tb_penjualan values (?,?,?,?,?,?)");
                pst.setString(1, textNota.getText());
                pst.setString(2, tgl.toString());
                pst.setString(3, textIDPelanggan.getText());
                pst.setBigDecimal(4, new BigDecimal(textTotal.getText()));
                pst.setBigDecimal(5, new BigDecimal(textBayar.getText()));
                pst.setBigDecimal(6, new BigDecimal(textKembali.getText()));
                int isSuccess = pst.executeUpdate();
                if (isSuccess == 1){
                simpan_item_belanja();
                }
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
                ulang();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada Simpan Transaksi Details \n" + ex.toString());
            }
        }
    }
   
        private void simpan_item_belanja() {
        for (int a = 0; a <= tabelItemBelanja.getRowCount()-1;a++) {
            try {
                pst = conn.prepareStatement("insert into tb_detail_penjualan (no_nota,kode_barang,qty)values(?,?,?)");
                String kode;int jumlah;
                kode = tabelItemBelanja.getValueAt(a, 0).toString();
                jumlah = Integer.parseInt(tabelItemBelanja.getValueAt(a, 4).toString());
                pst.setString(1, textNota.getText());
                pst.setString(2, kode);
                pst.setInt(3, jumlah);
                pst.executeUpdate();
                update_stok(kode, jumlah);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Simpan Item Belanja:Details\n" + ex.toString());
            }
        }
    }
        
        private void update_stok(String kode, int jumlah) {
        try {
            sql = "update tb_barang set stok=stok-?where kode_barang=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, jumlah);
            pst.setString(2, kode);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Terjadi kesalahan pada Update Stok:" + ex.toString());
        }
    }
        
        
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textNota = new javax.swing.JTextField();
        textKodeBarang = new javax.swing.JTextField();
        textNamaBarang = new javax.swing.JTextField();
        textKategori = new javax.swing.JTextField();
        comboPelanggan = new javax.swing.JComboBox<>();
        textTanggal = new com.toedter.calendar.JDateChooser();
        textIDPelanggan = new javax.swing.JTextField();
        buttonCariBarang = new javax.swing.JButton();
        textHarga = new javax.swing.JTextField();
        textStock = new javax.swing.JTextField();
        textQty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelItemBelanja = new javax.swing.JTable();
        textTotal = new javax.swing.JTextField();
        textBayar = new javax.swing.JTextField();
        textKembali = new javax.swing.JTextField();
        buttonTambah = new javax.swing.JButton();
        buttonBatal = new javax.swing.JButton();
        buttonSimpan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        buttonHapus = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        comboPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPelangganActionPerformed(evt);
            }
        });

        buttonCariBarang.setText("CARI");
        buttonCariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariBarangActionPerformed(evt);
            }
        });

        tabelItemBelanja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Kategori", "Harga", "Qty", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tabelItemBelanja);

        textBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBayarKeyReleased(evt);
            }
        });

        textKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textKembaliActionPerformed(evt);
            }
        });

        buttonTambah.setText("TAMBAH");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });

        buttonBatal.setText("BATAL");
        buttonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBatalActionPerformed(evt);
            }
        });

        buttonSimpan.setText("SIMPAN");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("FORM TRANSAKSI");

        jLabel2.setText("NO NOTA");

        jLabel3.setText("NAMA PELANGGAN");

        jLabel4.setText("KODE BARANG");

        jLabel5.setText("NAMA BARANG");

        jLabel6.setText("KATEGORI");

        jLabel7.setText("TANGGAL TRANSAKSI");

        jLabel8.setText("HARGA");

        jLabel9.setText("STOCK");

        jLabel10.setText("JUMLAH BELI");

        jLabel11.setText("TOTAL");

        jLabel12.setText("BAYAR");

        jLabel13.setText("KEMBALI");

        buttonHapus.setText("HAPUS");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(375, 375, 375)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textTotal)
                            .addComponent(textBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buttonHapus)
                                .addGap(29, 29, 29)
                                .addComponent(buttonBatal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonSimpan))
                            .addComponent(textKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(textNota, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64)
                                        .addComponent(jLabel7))
                                    .addComponent(comboPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(textKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonCariBarang)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel10))))
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textKategori, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(213, 213, 213)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textQty, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonTambah))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textIDPelanggan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textTanggal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textStock, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                .addComponent(textHarga, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel2))
                    .addComponent(textTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(textIDPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonCariBarang)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonTambah)
                            .addComponent(jLabel10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBatal)
                    .addComponent(buttonSimpan)
                    .addComponent(jLabel12)
                    .addComponent(buttonHapus))
                .addContainerGap(226, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariBarangActionPerformed
        CariBarangView cbv = new CariBarangView(null,true);
        cbv.setVisible(true);
    }//GEN-LAST:event_buttonCariBarangActionPerformed

    private void comboPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPelangganActionPerformed
        try {
            pst = conn.prepareStatement("SELECT id_pelanggan FROM tb_pelanggan WHERE nama_pelanggan = ?");
            pst.setString(1, comboPelanggan.getSelectedItem().toString());
            rs = pst.executeQuery();
            if(rs.next()){
            textIDPelanggan.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            Logger.getLogger(TransaksiView.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_comboPelangganActionPerformed

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
         if (textKodeBarang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data barang belum dipilih");
        } else if (textQty.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Jumlah beli belum diisi");
        } else if (Integer.parseInt(textQty.getText()) > Integer.parseInt(textStock.getText())) {
            JOptionPane.showMessageDialog(null, "Stok barang tidak cukup!");
            textQty.setText("0");
            textQty.requestFocus();
        } else {
            dtm = (DefaultTableModel) tabelItemBelanja.getModel();
            ArrayList list = new ArrayList();
            list.add(textKodeBarang.getText());
            list.add(textNamaBarang.getText());
            list.add(textKategori.getText());
            list.add(textHarga.getText());
            list.add(textQty.getText());
            list.add(Integer.parseInt(textHarga.getText()) * Integer.parseInt(textQty.getText()));
            dtm.addRow(list.toArray());
            textKodeBarang.setText("");
            textNamaBarang.setText("");
            textKategori.setText("");
            textHarga.setText("");
            textStock.setText("");
            textQty.setText("");
            hitung_total();
        }
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        int row = tabelItemBelanja.getSelectedRow();
        if(row<0){
            JOptionPane.showMessageDialog(null, "Pilih dulu item yang ingin dihapus!!!");
        }else{
        dtm.removeRow(row);
        tabelItemBelanja.setModel(dtm);
        hitung_total();
        }
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void textBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBayarKeyReleased
        BigDecimal bayar = new BigDecimal(0);
        if(!textBayar.getText().equals("")){
            bayar = new BigDecimal(textTotal.getText());
        }
        BigDecimal total = new BigDecimal(textTotal.getText());
        BigDecimal kembali = bayar.subtract(total);
        textKembali.setText(kembali.toString());
    }//GEN-LAST:event_textBayarKeyReleased

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        simpan_transaksi();
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void buttonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalActionPerformed
        ulang();
    }//GEN-LAST:event_buttonBatalActionPerformed

    private void textKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textKembaliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBatal;
    private javax.swing.JButton buttonCariBarang;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JComboBox<String> comboPelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelItemBelanja;
    private javax.swing.JTextField textBayar;
    public static javax.swing.JTextField textHarga;
    private javax.swing.JTextField textIDPelanggan;
    public static javax.swing.JTextField textKategori;
    private javax.swing.JTextField textKembali;
    public static javax.swing.JTextField textKodeBarang;
    public static javax.swing.JTextField textNamaBarang;
    private javax.swing.JTextField textNota;
    private javax.swing.JTextField textQty;
    public static javax.swing.JTextField textStock;
    private com.toedter.calendar.JDateChooser textTanggal;
    private javax.swing.JTextField textTotal;
    // End of variables declaration//GEN-END:variables

    private static class DefaultTabelModel {

        public DefaultTabelModel() {
        }

        private int getRowCount() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void removeRow(int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
