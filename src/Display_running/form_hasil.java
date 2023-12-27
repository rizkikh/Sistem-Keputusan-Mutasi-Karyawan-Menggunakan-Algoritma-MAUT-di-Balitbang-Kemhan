package Display_running;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Connection.Connection;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Khairul Rizki
 */
public class form_hasil extends javax.swing.JFrame {

    private DefaultTableModel model;
    private java.sql.Connection conn = new Connection().connect();
    
    public form_hasil() {
        String Display_running = javax.swing.UIManager.getSystemLookAndFeelClassName();
        try{
            javax.swing.UIManager.setLookAndFeel(Display_running);
        }catch(Exception e){}
        
        initComponents();
        Locale locale = new Locale("ID","id");
        Locale.setDefault(locale);
        
        tabel_hasil();
    }
    
    protected void updateHasil(){
    
    }
    
    protected void tabel_hasil(){
        Object[] baris = {"Ranking","NIP","Nama","Pangkat","Jabatan","Nilai Akhir","Keterangan"};
        model = new DefaultTableModel(null, baris){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        tabel_hasil.setModel(model);
        
        try{    
                String sql = "SELECT nip, nama, pangkat, jabatan, nilai, keterangan, DENSE_RANK() OVER (ORDER BY nilai DESC) as rangking FROM db_hasil;";
                String sql2 = "UPDATE db_hasil SET keterangan = ? WHERE nip = ?";
                java.sql.Statement stat2 = conn.createStatement();
                ResultSet hasil = stat2.executeQuery(sql);
                
                while(hasil.next()){
                    String a = hasil.getString("rangking");
                    String b = hasil.getString("nip");
                    String c = hasil.getString("nama");
                    String d = hasil.getString("pangkat");
                    String e = hasil.getString("jabatan");
                    String f = hasil.getString("nilai");
                    
                    int status = Integer.parseInt(a);
                    if(status <= 1){
                        PreparedStatement stat = conn.prepareStatement(sql2);
                            stat.setString(1, "Layak Dimutasi");
                            stat.setString(2, b);
                        stat.executeUpdate();
                        
                        String g = hasil.getString("keterangan");
                        String[] data = {a,b,c,d,e,f,g};
                        model.addRow(data);
                    }else{
                        PreparedStatement stat = conn.prepareStatement(sql2);
                            stat.setString(1, "--");
                            stat.setString(2, b);
                        stat.executeUpdate();
                        
                        String g = hasil.getString("keterangan");
                        String[] data = {a,b,c,d,e,f,g};
                        model.addRow(data);
                    }   
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + e);
        }
    }
    
    protected void search(){
        if(search_data.getText().equals("Cari") || search_data.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan Kata Kunci");
            tabel_hasil();
        }else{
            Object[] baris = {"Ranking","NIP","Nama","Pangkat","Jabatan","Nilai Akhir","Keterangan"};
            model = new DefaultTableModel(null, baris){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }};
                tabel_hasil.setModel(model);
                
                String sql = "SELECT nip, nama, pangkat, jabatan, nilai, keterangan, RANK() OVER (ORDER BY nilai DESC) as rangking FROM db_hasil WHERE nip LIKE '%"+search_data.getText()+"%' OR nama LIKE '%"+search_data.getText()+"%' OR pangkat LIKE '%"+search_data.getText()+"%' OR jabatan LIKE '%"+search_data.getText()+"%' OR nilai LIKE '%"+search_data.getText()+"%' OR keterangan LIKE '%"+search_data.getText()+"%'";
                try{
                    java.sql.Statement stat = conn.createStatement();
                    ResultSet hasil = stat.executeQuery(sql);

                    while(hasil.next()){
                        String a = hasil.getString("rangking");
                        String b = hasil.getString("nip");
                        String c = hasil.getString("nama");
                        String d = hasil.getString("pangkat");
                        String e = hasil.getString("jabatan");
                        String f = hasil.getString("nilai");
                        String g = hasil.getString("keterangan");
                        
                        String[] data = {a,b,c,d,e,f,g};
                        model.addRow(data);
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Data Tidak Ditemulan " + e);
                }
            }
    }
    
    protected void delete_all(){
        int ok = JOptionPane.showConfirmDialog(null, "Hapus Semua Data?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            String sql = "DELETE FROM db_hasil";
            
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                tabel_hasil();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus" + e);
            }
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
        main_panel = new javax.swing.JPanel();
        back_button = new javax.swing.JLabel();
        second_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_hasil = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        button_delete = new javax.swing.JButton();
        area_search1 = new javax.swing.JPanel();
        search_data = new javax.swing.JTextField();
        b_search_data = new javax.swing.JButton();
        button_cetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        title4.setText("Hasil Keputusan Mutasi.");

        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/exit_button.png"))); // NOI18N
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        main_panel.setBackground(new java.awt.Color(255, 241, 241));

        back_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/back_30px.png"))); // NOI18N
        back_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_buttonMouseClicked(evt);
            }
        });

        second_panel.setBackground(new java.awt.Color(187, 135, 96));

        jPanel1.setBackground(new java.awt.Color(255, 241, 241));

        tabel_hasil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Ranking", "NIP", "Nama", "Pangkat", "Jabatan", "Nilai Akhir", "Keterangan"
            }
        ));
        tabel_hasil.setRowHeight(30);
        tabel_hasil.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tabel_hasil);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tabel Perangkingan Karyawan");

        button_delete.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/delete_25px.png"))); // NOI18N
        button_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_deleteActionPerformed(evt);
            }
        });

        area_search1.setBackground(new java.awt.Color(255, 241, 241));

        search_data.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        search_data.setForeground(new java.awt.Color(102, 102, 102));
        search_data.setText("Cari");
        search_data.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                search_dataFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                search_dataFocusLost(evt);
            }
        });
        search_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_dataActionPerformed(evt);
            }
        });
        search_data.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_dataKeyPressed(evt);
            }
        });

        b_search_data.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/search_20px.png"))); // NOI18N
        b_search_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_search_dataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout area_search1Layout = new javax.swing.GroupLayout(area_search1);
        area_search1.setLayout(area_search1Layout);
        area_search1Layout.setHorizontalGroup(
            area_search1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, area_search1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search_data, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_search_data, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        area_search1Layout.setVerticalGroup(
            area_search1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, area_search1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(area_search1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_search_data, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_data)))
        );

        refresh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/refresh_20px.png"))); // NOI18N
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        button_cetak.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button_cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/print_25px.png"))); // NOI18N
        button_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(button_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(area_search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(area_search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout second_panelLayout = new javax.swing.GroupLayout(second_panel);
        second_panel.setLayout(second_panelLayout);
        second_panelLayout.setHorizontalGroup(
            second_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(second_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        second_panelLayout.setVerticalGroup(
            second_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(second_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addComponent(back_button)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(second_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(second_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                .addComponent(exit)
                .addGap(36, 36, 36))
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(title6)
                        .addGap(5, 5, 5)
                        .addComponent(title4))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(exit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void back_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_buttonMouseClicked
        // TODO add your handling code here:
        new main_menu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back_buttonMouseClicked

    private void button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_deleteActionPerformed
        // TODO add your handling code here:
        delete_all();
    }//GEN-LAST:event_button_deleteActionPerformed

    private void search_dataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_dataFocusGained
        // TODO add your handling code here:
        String a = "Cari";
        if(search_data.getText().equals(a)){
            search_data.setText("");
        }

    }//GEN-LAST:event_search_dataFocusGained

    private void search_dataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_dataFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_search_dataFocusLost

    private void search_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_dataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_dataActionPerformed

    private void search_dataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_dataKeyPressed
        // TODO add your handling code here:
        String a = search_data.getText();

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(a.equals("Cari")||a.equals("")){
                JOptionPane.showMessageDialog(null, "Silahkan Masukan Pencarian Anda.","Terjadi Kesalahan!",JOptionPane.ERROR_MESSAGE);
                search_data.setText("Cari");
            }else{
                b_search_dataActionPerformed(null);
            }
        }
    }//GEN-LAST:event_search_dataKeyPressed

    private void b_search_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_search_dataActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_b_search_dataActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        tabel_hasil();
        search_data.setText("Cari");
    }//GEN-LAST:event_refreshActionPerformed

    private void button_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cetakActionPerformed
        // TODO add your handling code here:
        String realPath = "Image_assets/";
        HashMap hash = new HashMap();
        try {
            hash.put("realPath", realPath);
            InputStream ambil = getClass().getResourceAsStream("/Display_running/report_hasil_mutasi.jasper") ;
            JasperPrint print = JasperFillManager.fillReport(ambil, hash, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Data Gagal Dicetak" + e);
        }
    }//GEN-LAST:event_button_cetakActionPerformed

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
            java.util.logging.Logger.getLogger(form_hasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_hasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_hasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_hasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_hasil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area_search1;
    private javax.swing.JButton b_search_data;
    private javax.swing.JLabel back_button;
    private javax.swing.JPanel background;
    private javax.swing.JButton button_cetak;
    private javax.swing.JButton button_delete;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main_panel;
    public static final javax.swing.JButton refresh = new javax.swing.JButton();
    private javax.swing.JTextField search_data;
    private javax.swing.JPanel second_panel;
    private javax.swing.JTable tabel_hasil;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel title6;
    // End of variables declaration//GEN-END:variables
}
