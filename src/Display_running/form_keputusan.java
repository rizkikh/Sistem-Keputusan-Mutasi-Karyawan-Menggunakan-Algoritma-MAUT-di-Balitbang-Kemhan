package Display_running;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Connection.Connection;

/**
 *
 * @author Khairul Rizki
 */
public class form_keputusan extends javax.swing.JFrame {

    private DefaultTableModel model;
    private java.sql.Connection conn = new Connection().connect();
    
    public form_keputusan() {
        String Display_running = javax.swing.UIManager.getSystemLookAndFeelClassName();
        try{
            javax.swing.UIManager.setLookAndFeel(Display_running);
        }catch(Exception e){}
        
        initComponents();
        tabel_normalisasi();
        tabel_bobot();
    }
    
    protected void tabel_normalisasi(){
        Object[] baris = {"NIP","Nama","Usia","Golongan","Kinerja","Disiplin","Pengalaman"};
        model = new DefaultTableModel(null, baris){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        tabel_normalisasi.setModel(model);
        
        String sql = "SELECT * FROM db_normalisasi ORDER BY nama ASC";
        
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            
            while(hasil.next()){
                String a = hasil.getString("nip");
                String b = hasil.getString("nama");
                String c = hasil.getString("usia");
                String d = hasil.getString("pangkat");
                String e = hasil.getString("kinerja");
                String f = hasil.getString("disiplin");
                String g = hasil.getString("pengalaman");
                
                String[] data = {a,b,c,d,e,f,g};
                model.addRow(data);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + e);
        }
    }
    
    protected void tabel_bobot(){
        Object[] baris = {"ID Bobot","Usia","Kinerja","Golongan","Disiplin","Pengalaman"};
        model = new DefaultTableModel(null, baris){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        tabel_bobot.setModel(model);
        
        String sql = "SELECT * FROM db_bobot ORDER BY id_bobot ASC";
        
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            
            while(hasil.next()){
                String a = hasil.getString("id_bobot");
                String b = hasil.getString("usia");
                String c = hasil.getString("kinerja");
                String d = hasil.getString("pangkat");
                String e = hasil.getString("disiplin");
                String f = hasil.getString("pengalaman");
                
                String[] data = {a,b,c,d,e,f};
                model.addRow(data);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + e);
        }
    }

    protected void hitungHasil(){
        try{
            String muncul_id = id_show.getText();
            String dataKaryawan = "SELECT * FROM db_karyawan";
            String bobot = "SELECT * FROM db_bobot WHERE id_bobot = '"+muncul_id+"'";
            String inputHasil = "INSERT INTO db_hasil VALUES (?,?,?,?,?,'--')";
                
            java.sql.Statement stat1 = conn.createStatement();
            ResultSet getDataKaryawan = stat1.executeQuery(dataKaryawan);
            while(getDataKaryawan.next()){                                      
                String nip = getDataKaryawan.getString("nip");
                String nama = getDataKaryawan.getString("nama_lengkap");
                String pangkat = getDataKaryawan.getString("pangkat");
                String jabatan = getDataKaryawan.getString("jabatan");
                    
                String normalisasi = "SELECT * FROM db_normalisasi WHERE nip = '"+nip+"'";
                java.sql.Statement stat2 = conn.createStatement();
                ResultSet getNormalisasi = stat2.executeQuery(normalisasi);
                while(getNormalisasi.next()){                                   
                    double usia = getNormalisasi.getDouble("usia");
                    double golongan = getNormalisasi.getDouble("pangkat");
                    double kinerja = getNormalisasi.getDouble("kinerja");
                    double disiplin = getNormalisasi.getDouble("disiplin");
                    double pengalaman = getNormalisasi.getDouble("pengalaman");
                        
                    java.sql.Statement stat3 = conn.createStatement();
                    ResultSet getBobot = stat3.executeQuery(bobot);
                    while(getBobot.next()){                                     
                        double b_usia = getBobot.getDouble("usia");
                        double b_kinerja = getBobot.getDouble("kinerja");
                        double b_pangkat = getBobot.getDouble("pangkat");
                        double b_disiplin = getBobot.getDouble("disiplin");
                        double b_pengalaman = getBobot.getDouble("pengalaman");
                            
                        double hasil = (usia * b_usia)+(golongan * b_pangkat)+(kinerja * b_kinerja)+(disiplin * b_disiplin)+(pengalaman * b_pengalaman);
                            
                        PreparedStatement stat4 = conn.prepareStatement(inputHasil);    
                            stat4.setString(1, nip);
                            stat4.setString(2, nama);
                            stat4.setString(3, pangkat);
                            stat4.setString(4, jabatan);
                            stat4.setDouble(5, hasil);
                        stat4.executeUpdate();
                        
                        new form_hasil().tabel_hasil();
                    }
                }
            }
                JOptionPane.showMessageDialog(null, "Data Berhasil Dibuat, Silahkan Buka Menu Hasil Mutasi");
        }catch(Exception err){
            JOptionPane.showMessageDialog(null, "Silahkan Hapus Data Hasil Mutasi Terlebih Dahulu. ","Data Sudah Di Proses!",JOptionPane.ERROR_MESSAGE);
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

        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        title6 = new javax.swing.JLabel();
        title4 = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        main_content = new javax.swing.JPanel();
        panel_bobot = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_normalisasi = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_bobot = new javax.swing.JTable();
        b_hitung = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        id_show = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proses Keputusan Mutasi");
        setUndecorated(true);

        background.setBackground(new java.awt.Color(79, 14, 14));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/logo_kemhan_small.png"))); // NOI18N

        title6.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        title6.setForeground(new java.awt.Color(255, 241, 241));
        title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title6.setText("BALITBANG KEMHAN RI");

        title4.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        title4.setForeground(new java.awt.Color(255, 241, 241));
        title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title4.setText("Proses Keputusan Mutasi.");

        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/exit_button.png"))); // NOI18N
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        main_content.setBackground(new java.awt.Color(255, 241, 241));

        panel_bobot.setBackground(new java.awt.Color(187, 135, 96));

        jPanel1.setBackground(new java.awt.Color(255, 241, 241));

        jLabel4.setBackground(new java.awt.Color(187, 135, 96));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("• Data Karyawan Yang Sudah di Normalisasi");

        tabel_normalisasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NIP", "Nama", "Usia", "Pangkat", "Kinerja", "Disiplin", "Pengalaman"
            }
        ));
        tabel_normalisasi.setEnabled(false);
        tabel_normalisasi.setRowHeight(20);
        tabel_normalisasi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tabel_normalisasi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 241, 241));

        jLabel3.setBackground(new java.awt.Color(187, 135, 96));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("• Silahkan Pilih Bobot Kriteria Yang Ingin Ditentukan");

        tabel_bobot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Bobot", "Usia", "Kinerja", "Golongan", "Disiplin", "Pengalaman"
            }
        ));
        tabel_bobot.setRowHeight(20);
        tabel_bobot.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabel_bobot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_bobotMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_bobot);

        b_hitung.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        b_hitung.setText("Hitung Hasil");
        b_hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_hitungActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(187, 135, 96));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Bobot Terpilih :");

        id_show.setEditable(false);
        id_show.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        id_show.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_show, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_hitung)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_hitung, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(id_show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_bobotLayout = new javax.swing.GroupLayout(panel_bobot);
        panel_bobot.setLayout(panel_bobotLayout);
        panel_bobotLayout.setHorizontalGroup(
            panel_bobotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bobotLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_bobotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_bobotLayout.setVerticalGroup(
            panel_bobotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bobotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/back_30px.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout main_contentLayout = new javax.swing.GroupLayout(main_content);
        main_content.setLayout(main_contentLayout);
        main_contentLayout.setHorizontalGroup(
            main_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_contentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_bobot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(main_contentLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        main_contentLayout.setVerticalGroup(
            main_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_contentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_bobot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title4)
                    .addComponent(title6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit)
                .addGap(38, 38, 38))
            .addComponent(main_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(title6)
                                .addGap(5, 5, 5)
                                .addComponent(title4))))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(exit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_exitMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        new main_menu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tabel_bobotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_bobotMouseClicked
        // TODO add your handling code here:
        int bar = tabel_bobot.getSelectedRow();
        String id = model.getValueAt(bar, 0).toString();
        id_show.setText(id);
    }//GEN-LAST:event_tabel_bobotMouseClicked

    private void b_hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hitungActionPerformed
        // TODO add your handling code here:
        
        if(id_show.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Nilai Bobot Terlebih Dahulu","Terjadi Kesalahan!",JOptionPane.ERROR_MESSAGE);
        }else{
            hitungHasil();
        }
        
    }//GEN-LAST:event_b_hitungActionPerformed

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
            java.util.logging.Logger.getLogger(form_keputusan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_keputusan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_keputusan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_keputusan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_keputusan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_hitung;
    private javax.swing.JPanel background;
    private javax.swing.JLabel exit;
    private javax.swing.JTextField id_show;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel main_content;
    private javax.swing.JPanel panel_bobot;
    private javax.swing.JTable tabel_bobot;
    private javax.swing.JTable tabel_normalisasi;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel title6;
    // End of variables declaration//GEN-END:variables
}
