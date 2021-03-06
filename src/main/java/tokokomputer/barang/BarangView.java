/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokokomputer.barang;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import tokokomputer.pengaturan.Koneksi;
/**
 
 * @author FAJAR SYA'BANIE
 */
public class BarangView extends javax.swing.JInternalFrame {

    /**
     * Creates new form BarangView
     */
    public BarangView() {
        initComponents();
        ulang();
    }
    
    PreparedStatement pst;
    ResultSet rs;
    Connection conn = new Koneksi().getKoneksi();
    String status, sql;

    
    private void tampil_data(){
      try{
          String[]judul = {"KODE BARANG", "NAMA BARANG", "KATEGORI", "HARGA", "STOCK"};
          DefaultTableModel dtm = new DefaultTableModel(null,judul);
          tabelBarang.setModel(dtm);
          if(textCari.getText().isEmpty()){
          sql = "SELECT * FROM tb_barang";
          }else{
          sql = "SELECT * FROM tb_barang WHERE nama_barang like '%" + textCari.getText() + "%'";
      }
          pst = conn.prepareStatement(sql);
          rs = pst.executeQuery();
          while(rs.next()){
              String[]data = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
              dtm.addRow(data);
          }
    }catch(SQLException e){
          JOptionPane.showMessageDialog(null, e.toString());
      }
    }
    
    
     private void ulang(){
        textKodeBarang.setEnabled(false);
        textNamaBarang.setEnabled(false);
        textKategori.setEnabled(false);
        textHarga.setEnabled(false);
        textStock.setEnabled(false);
        buttonSimpan.setEnabled(false);
        buttonHapus.setEnabled(false);
        buttonUbah.setEnabled(false);
        buttonTambah.setEnabled(true);
        textKodeBarang.setText("");
        textNamaBarang.setText("");
        textKategori.setText("");
        textHarga.setText("");
        textStock.setText("");
        tampil_data();
    }
     
      private void id_otomatis(){
        try{
            sql = "SELECT * FROM tb_barang ORDER BY kode_barang desc limit 1";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                int kode = Integer.parseInt(rs.getString(1).substring(4)) + 1;
                textKodeBarang.setText("BRG-"+kode);
            }else{
                textKodeBarang.setText("BRG-1000");
            }
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textKodeBarang = new javax.swing.JTextField();
        textNamaBarang = new javax.swing.JTextField();
        textKategori = new javax.swing.JTextField();
        textHarga = new javax.swing.JTextField();
        textStock = new javax.swing.JTextField();
        buttonTambah = new javax.swing.JButton();
        buttonSimpan = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonUbah = new javax.swing.JButton();
        buttonUlang = new javax.swing.JButton();
        buttonKeluar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        textCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();

        setClosable(true);
        setResizable(true);
        setTitle("FORM BARANG");

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("FORM BARANG");

        jLabel2.setText("KODE BARANG");

        jLabel3.setText("NAMA BARANG");

        jLabel4.setText("KATEGORI");

        jLabel5.setText("HARGA");

        jLabel6.setText("STOCK");

        textKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textKodeBarangActionPerformed(evt);
            }
        });

        textStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textStockActionPerformed(evt);
            }
        });

        buttonTambah.setText("TAMBAH");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });

        buttonSimpan.setText("SIMPAN");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        buttonHapus.setText("HAPUS");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });

        buttonUbah.setText("UBAH");
        buttonUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUbahActionPerformed(evt);
            }
        });

        buttonUlang.setText("ULANG");
        buttonUlang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUlangActionPerformed(evt);
            }
        });

        buttonKeluar.setText("KELUAR");
        buttonKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKeluarActionPerformed(evt);
            }
        });

        jLabel7.setText("CARI BARANG");

        textCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCariActionPerformed(evt);
            }
        });
        textCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textCariKeyReleased(evt);
            }
        });

        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(288, 288, 288))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonTambah)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(buttonHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonUbah)
                        .addGap(18, 18, 18)
                        .addComponent(buttonUlang)
                        .addGap(18, 18, 18)
                        .addComponent(buttonKeluar)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textStock, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTambah)
                    .addComponent(buttonSimpan)
                    .addComponent(buttonHapus)
                    .addComponent(buttonUbah)
                    .addComponent(buttonUlang)
                    .addComponent(buttonKeluar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textKodeBarangActionPerformed

    private void textStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textStockActionPerformed

    private void textCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCariActionPerformed

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        textKodeBarang.setEnabled(false);
        textNamaBarang.setEnabled(true);
        textKategori.setEnabled(true);
        textHarga.setEnabled(true);
        textStock.setEnabled(true);
        buttonTambah.setEnabled(false);
        buttonSimpan.setEnabled(true);
        status = "tambah";
        id_otomatis();
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUbahActionPerformed
        textKodeBarang.setEnabled(false);
        textNamaBarang.setEnabled(true);
        textKategori.setEnabled(true);
        textHarga.setEnabled(true);
        textStock.setEnabled(true);
        buttonUbah.setEnabled(true);
        buttonSimpan.setEnabled(true);
        buttonHapus.setEnabled(false);
        status = "ubah";
    }//GEN-LAST:event_buttonUbahActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        if (textKodeBarang.getText().isEmpty() || textNamaBarang.getText().isEmpty() || textHarga.getText().isEmpty()) {
        } else {
            try {
                if (status.equals("tambah")) {
                    sql = "insert into tb_Barang values(?,?,?,?,?)";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, textKodeBarang.getText());
                    pst.setString(2, textNamaBarang.getText());
                    pst.setString(3, textKategori.getText());
                    pst.setString(4, textHarga.getText());
                    pst.setString(5, textStock.getText());
                } else if (status.equals("ubah")) {
                    sql = "update tb_barang set nama_barang=?,kategori=?,harga=?,stock=? where kode_barang=?";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, textNamaBarang.getText());
                    pst.setString(2, textKategori.getText());
                    pst.setString(3, textHarga.getText());
                    pst.setString(4, textStock.getText());
                    pst.setString(5, textKodeBarang.getText());
                }
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Barang berhasil ditambahkan");
                ulang();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }                                            

  static {                                            
               
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        int konf = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data barang ini?", "Hapus Data?", JOptionPane.YES_NO_OPTION);
        if (konf == 0) {
            try {
                pst = conn.prepareStatement("delete from tb_barang where kode_barang=?");
                pst.setString(1, textKodeBarang.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data barang berhasil dihapus!");
                ulang();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void buttonUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUlangActionPerformed
        ulang();
    }//GEN-LAST:event_buttonUlangActionPerformed

    private void buttonKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonKeluarActionPerformed
   

    
    private void textCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCariKeyReleased
        tampil_data();
    }//GEN-LAST:event_textCariKeyReleased

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        int baris = tabelBarang.getSelectedRow();
        String id = tabelBarang.getValueAt(baris, 0).toString();
        if(!id.isEmpty()){
            textKodeBarang.setText(id);
            textNamaBarang.setText(tabelBarang.getValueAt(baris, 1).toString());
            textKategori.setText(tabelBarang.getValueAt(baris, 2).toString());
            textHarga.setText(tabelBarang.getValueAt(baris, 3).toString());
            textStock.setText(tabelBarang.getValueAt(baris, 4).toString());
            buttonTambah.setEnabled(false);
            buttonSimpan.setEnabled(false);
            buttonHapus.setEnabled(true);
            buttonUbah.setEnabled(true);
        }
    }//GEN-LAST:event_tabelBarangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonKeluar;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JButton buttonUbah;
    private javax.swing.JButton buttonUlang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTextField textCari;
    private javax.swing.JTextField textHarga;
    private javax.swing.JTextField textKategori;
    private javax.swing.JTextField textKodeBarang;
    private javax.swing.JTextField textNamaBarang;
    private javax.swing.JTextField textStock;
    // End of variables declaration//GEN-END:variables
}
