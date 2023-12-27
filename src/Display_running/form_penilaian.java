package Display_running;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Connection.Connection;
import java.io.InputStream;
import java.sql.*;
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

public class form_penilaian extends javax.swing.JFrame {
    
    private DefaultTableModel model;
    private java.sql.Connection conn = new Connection().connect();
    
    public form_penilaian() {
        String Display_running = javax.swing.UIManager.getSystemLookAndFeelClassName();
        try{
            javax.swing.UIManager.setLookAndFeel(Display_running);
        }catch(Exception e){}
        
        initComponents();
        Locale locale = new Locale("ID","id");
        Locale.setDefault(locale);
        
        tabel_karyawan();
        delete.setEnabled(false);
    }
    
    protected void tabel_karyawan(){
        Object[] baris = {"NIP","Nama","Jenis Kelamin","Pangkat/Golongan","Jabatan"};
        model = new DefaultTableModel(null, baris){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        tabel_karyawan.setModel(model);
        
        String sql = "SELECT * FROM db_karyawan ORDER BY nama_lengkap ASC";
        
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            
            while(hasil.next()){
                String a = hasil.getString("nip");
                String b = hasil.getString("nama_lengkap");
                String c = hasil.getString("jenis_kelamin");
                String d = hasil.getString("pangkat");
                String e = hasil.getString("jabatan");
                
                String[] data = {a,b,c,d,e};
                model.addRow(data);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + e);
        }
    }
    
    protected void tabel_penilaian(){
        Object[] baris = {"NIP","Nama","Usia","Pangkat/Golongan","Kinerja","Disiplin","Pengalaman"};
        model = new DefaultTableModel(null, baris){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        tabel_penilaian.setModel(model);
        
        String sql = "SELECT * FROM db_penilaian ORDER BY nama ASC";
        
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
    
    private void normalisasi(){
        String sql = "SELECT * FROM db_penilaian";
        String maxmin = "SELECT MAX(usia) AS max_usia, MAX(pangkat) AS max_pangkat, MAX(kinerja) AS max_kinerja, MAX(disiplin) AS max_disiplin, MAX(pengalaman) AS max_pengalaman, MIN(usia) AS min_usia, MIN(pangkat) AS min_pangkat, MIN(kinerja) AS min_kinerja, MIN(disiplin) AS min_disiplin, MIN(pengalaman) AS min_pengalaman FROM db_penilaian";
        String sql2 = "INSERT INTO db_normalisasi VALUES (?,?,?,?,?,?,?)";
        
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet ambil = stat.executeQuery(sql);
            
            while(ambil.next()){
                java.sql.Statement statMaxmin = conn.createStatement();
                ResultSet ambilMaxmin = statMaxmin.executeQuery(maxmin);
                
                while(ambilMaxmin.next()){
                    String nip = ambil.getString("nip");
                    String nama = ambil.getString("nama");
                    double norUsia = (ambil.getDouble("usia") - ambilMaxmin.getDouble("min_usia"))/(ambilMaxmin.getDouble("max_usia") - ambilMaxmin.getDouble("min_usia"));
                    double norPangkat = (ambil.getDouble("pangkat") - ambilMaxmin.getDouble("min_pangkat"))/(ambilMaxmin.getDouble("max_pangkat") - ambilMaxmin.getDouble("min_pangkat"));
                    double norKinerja = (ambil.getDouble("kinerja") - ambilMaxmin.getDouble("min_kinerja"))/(ambilMaxmin.getDouble("max_kinerja") - ambilMaxmin.getDouble("min_kinerja"));
                    double norDisiplin = (ambil.getDouble("disiplin") - ambilMaxmin.getDouble("min_disiplin"))/(ambilMaxmin.getDouble("max_disiplin") - ambilMaxmin.getDouble("min_disiplin"));
                    double norPengalaman = (ambil.getDouble("pengalaman") - ambilMaxmin.getDouble("min_pengalaman"))/(ambilMaxmin.getDouble("max_pengalaman") - ambilMaxmin.getDouble("min_pengalaman"));
                    
                    PreparedStatement stat2 = conn.prepareStatement(sql2);
                    stat2.setString(1, nip);
                    stat2.setString(2, nama);
                    stat2.setDouble(3, norUsia);
                    stat2.setDouble(4, norPangkat);
                    stat2.setDouble(5, norKinerja);
                    stat2.setDouble(6, norDisiplin);
                    stat2.setDouble(7, norPengalaman);
                    stat2.executeUpdate();   
                }
            }
            JOptionPane.showMessageDialog(null, "Data Normalisasi Berhasil Dibuat\nSilahkan Buka Menu Keputusan Mutasi");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Data Andah Sudah Ternomalisasi\nSilahkan Buka Menu Keputusan Mutasi","Terjadi Kesalahan!",JOptionPane.ERROR_MESSAGE);
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

        exit = new javax.swing.JLabel();
        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        title6 = new javax.swing.JLabel();
        title4 = new javax.swing.JLabel();
        content_panel = new javax.swing.JPanel();
        back_button = new javax.swing.JLabel();
        main_panel = new javax.swing.JPanel();
        data_karyawan = new javax.swing.JPanel();
        content_3 = new javax.swing.JPanel();
        title7 = new javax.swing.JLabel();
        area_search2 = new javax.swing.JPanel();
        search_data_karyawan = new javax.swing.JTextField();
        b_search_karyawan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_karyawan = new javax.swing.JTable();
        refresh1 = new javax.swing.JButton();
        data_penilaian = new javax.swing.JPanel();
        content_1 = new javax.swing.JPanel();
        title5 = new javax.swing.JLabel();
        area_search1 = new javax.swing.JPanel();
        search_data = new javax.swing.JTextField();
        b_search_data = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_penilaian = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        b_normalisasi = new javax.swing.JButton();
        delete_all = new javax.swing.JButton();
        button_data_karyawan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        button_data_penilaian = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Penilaian Karyawan");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/exit_button.png"))); // NOI18N
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, -1, -1));

        background.setBackground(new java.awt.Color(79, 14, 14));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/logo_kemhan_small.png"))); // NOI18N

        title6.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        title6.setForeground(new java.awt.Color(255, 241, 241));
        title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title6.setText("BALITBANG KEMHAN RI");

        title4.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        title4.setForeground(new java.awt.Color(255, 241, 241));
        title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title4.setText("Penilaian Karyawan.");

        content_panel.setBackground(new java.awt.Color(255, 241, 241));
        content_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_button.setBackground(new java.awt.Color(255, 255, 255));
        back_button.setForeground(new java.awt.Color(255, 255, 255));
        back_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/back_30px.png"))); // NOI18N
        back_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back_buttonMouseEntered(evt);
            }
        });
        content_panel.add(back_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        main_panel.setBackground(new java.awt.Color(187, 135, 96));
        main_panel.setLayout(new java.awt.CardLayout());

        data_karyawan.setBackground(new java.awt.Color(187, 135, 96));

        content_3.setBackground(new java.awt.Color(255, 241, 241));

        title7.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        title7.setForeground(new java.awt.Color(79, 14, 14));
        title7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title7.setText("Data Karyawan.");

        area_search2.setBackground(new java.awt.Color(255, 241, 241));

        search_data_karyawan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        search_data_karyawan.setForeground(new java.awt.Color(102, 102, 102));
        search_data_karyawan.setText("Cari");
        search_data_karyawan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                search_data_karyawanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                search_data_karyawanFocusLost(evt);
            }
        });
        search_data_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_data_karyawanActionPerformed(evt);
            }
        });
        search_data_karyawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_data_karyawanKeyPressed(evt);
            }
        });

        b_search_karyawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/search_20px.png"))); // NOI18N
        b_search_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_search_karyawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout area_search2Layout = new javax.swing.GroupLayout(area_search2);
        area_search2.setLayout(area_search2Layout);
        area_search2Layout.setHorizontalGroup(
            area_search2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, area_search2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search_data_karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_search_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        area_search2Layout.setVerticalGroup(
            area_search2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, area_search2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(area_search2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_search_karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_data_karyawan)))
        );

        tabel_karyawan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tabel_karyawan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabel_karyawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NIP", "Nama", "Jenis Kelamin", "Pangkat/Golongan", "Jabatan"
            }
        ));
        tabel_karyawan.setRowHeight(25);
        tabel_karyawan.getTableHeader().setResizingAllowed(false);
        tabel_karyawan.getTableHeader().setReorderingAllowed(false);
        tabel_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_karyawanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabel_karyawanMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_karyawan);

        refresh1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        refresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/refresh_20px.png"))); // NOI18N
        refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout content_3Layout = new javax.swing.GroupLayout(content_3);
        content_3.setLayout(content_3Layout);
        content_3Layout.setHorizontalGroup(
            content_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, content_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(content_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addComponent(title7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, content_3Layout.createSequentialGroup()
                        .addComponent(refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(area_search2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        content_3Layout.setVerticalGroup(
            content_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(content_3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(content_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(area_search2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout data_karyawanLayout = new javax.swing.GroupLayout(data_karyawan);
        data_karyawan.setLayout(data_karyawanLayout);
        data_karyawanLayout.setHorizontalGroup(
            data_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_karyawanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        data_karyawanLayout.setVerticalGroup(
            data_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_karyawanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        main_panel.add(data_karyawan, "card2");

        data_penilaian.setBackground(new java.awt.Color(187, 135, 96));

        content_1.setBackground(new java.awt.Color(255, 241, 241));

        title5.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        title5.setForeground(new java.awt.Color(79, 14, 14));
        title5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title5.setText("Data Penilaian Karyawan.");

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

        tabel_penilaian.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tabel_penilaian.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabel_penilaian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NIP", "Nama", "Usia", "Pangkat/Golongan", "Kinerja", "Disiplin", "Pengalaman"
            }
        ));
        tabel_penilaian.setRowHeight(25);
        tabel_penilaian.getTableHeader().setResizingAllowed(false);
        tabel_penilaian.getTableHeader().setReorderingAllowed(false);
        tabel_penilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_penilaianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabel_penilaianMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_penilaian);
        if (tabel_penilaian.getColumnModel().getColumnCount() > 0) {
            tabel_penilaian.getColumnModel().getColumn(4).setHeaderValue("Kinerja");
            tabel_penilaian.getColumnModel().getColumn(5).setHeaderValue("Disiplin");
            tabel_penilaian.getColumnModel().getColumn(6).setHeaderValue("Pengalaman");
        }

        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/print_25px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        refresh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/refresh_20px.png"))); // NOI18N
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/delete_20px.png"))); // NOI18N
        delete.setText("Hapus Data");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        b_normalisasi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        b_normalisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/normalisasi_20px_black.png"))); // NOI18N
        b_normalisasi.setText("Normalisasikan");
        b_normalisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_normalisasiActionPerformed(evt);
            }
        });

        delete_all.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        delete_all.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/delete_20px.png"))); // NOI18N
        delete_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_allActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout content_1Layout = new javax.swing.GroupLayout(content_1);
        content_1.setLayout(content_1Layout);
        content_1Layout.setHorizontalGroup(
            content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(content_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addComponent(title5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(content_1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete_all, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(area_search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, content_1Layout.createSequentialGroup()
                        .addComponent(b_normalisasi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete)))
                .addContainerGap())
        );
        content_1Layout.setVerticalGroup(
            content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(content_1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(area_search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_all, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(b_normalisasi))
                .addContainerGap())
        );

        javax.swing.GroupLayout data_penilaianLayout = new javax.swing.GroupLayout(data_penilaian);
        data_penilaian.setLayout(data_penilaianLayout);
        data_penilaianLayout.setHorizontalGroup(
            data_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_penilaianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        data_penilaianLayout.setVerticalGroup(
            data_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_penilaianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        main_panel.add(data_penilaian, "card2");

        content_panel.add(main_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 800, 450));

        button_data_karyawan.setBackground(new java.awt.Color(187, 135, 96));
        button_data_karyawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_data_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_data_karyawanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_data_karyawanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button_data_karyawanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button_data_karyawanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button_data_karyawanMouseReleased(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/add_20px.png"))); // NOI18N
        jLabel4.setText("Data Karyawan");

        javax.swing.GroupLayout button_data_karyawanLayout = new javax.swing.GroupLayout(button_data_karyawan);
        button_data_karyawan.setLayout(button_data_karyawanLayout);
        button_data_karyawanLayout.setHorizontalGroup(
            button_data_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, button_data_karyawanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );
        button_data_karyawanLayout.setVerticalGroup(
            button_data_karyawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_data_karyawanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        content_panel.add(button_data_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, -1));

        button_data_penilaian.setBackground(new java.awt.Color(187, 135, 96));
        button_data_penilaian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_data_penilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_data_penilaianMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_data_penilaianMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button_data_penilaianMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button_data_penilaianMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button_data_penilaianMouseReleased(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/show_20px.png"))); // NOI18N
        jLabel2.setText("Data Penilaian");

        javax.swing.GroupLayout button_data_penilaianLayout = new javax.swing.GroupLayout(button_data_penilaian);
        button_data_penilaian.setLayout(button_data_penilaianLayout);
        button_data_penilaianLayout.setHorizontalGroup(
            button_data_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, button_data_penilaianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );
        button_data_penilaianLayout.setVerticalGroup(
            button_data_penilaianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_data_penilaianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        content_panel.add(button_data_penilaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 190, -1));

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title6)
                    .addComponent(title4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(content_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(title6)
                        .addGap(5, 5, 5)
                        .addComponent(title4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void back_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_buttonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_back_buttonMouseEntered

    private void button_data_penilaianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_penilaianMouseEntered
        // TODO add your handling code here:
        button_data_penilaian.setBackground(new Color(162,110,71));
    }//GEN-LAST:event_button_data_penilaianMouseEntered

    private void button_data_penilaianMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_penilaianMouseExited
        // TODO add your handling code here:
        button_data_penilaian.setBackground(new Color(187,135,96));
    }//GEN-LAST:event_button_data_penilaianMouseExited

    private void button_data_penilaianMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_penilaianMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_button_data_penilaianMousePressed

    private void button_data_penilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_penilaianMouseClicked
        // TODO add your handling code here:
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        
        main_panel.add(data_penilaian);
        main_panel.repaint();
        main_panel.revalidate();
        tabel_penilaian();
    }//GEN-LAST:event_button_data_penilaianMouseClicked

    private void button_data_penilaianMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_penilaianMouseReleased
        // TODO add your handling code here:
        button_data_penilaian.setBackground(new Color(162,110,71));
    }//GEN-LAST:event_button_data_penilaianMouseReleased

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String realPath = "Image_assets/";
        HashMap hash = new HashMap();
        try {
            hash.put("realPath", realPath);
            InputStream ambil = getClass().getResourceAsStream("/Display_running/report_penilaian_karyawan.jasper") ;
            JasperPrint print = JasperFillManager.fillReport(ambil, hash, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Data Gagal Dicetak" + e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void search_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_dataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_dataActionPerformed

    private void tabel_penilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_penilaianMouseClicked
        // TODO add your handling code here:
        delete.setEnabled(true);
    }//GEN-LAST:event_tabel_penilaianMouseClicked

    private void tabel_penilaianMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_penilaianMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_penilaianMouseEntered

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        tabel_penilaian();
        search_data.setText("Cari");
        delete.setEnabled(false);
    }//GEN-LAST:event_refreshActionPerformed

    private void b_search_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_search_dataActionPerformed
        // TODO add your handling code here:
        if(search_data.getText().equals("Cari") || search_data.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan Kata Kunci");
            tabel_karyawan();
        }else{
            Object[] baris = {"NIP","Nama","Usia","Pangkat/Golongan","Kinerja","Disiplin","Pengalaman"};
            model = new DefaultTableModel(null, baris){
                @Override
                public boolean isCellEditable(int row, int column) {
                return false;
            }};
            tabel_penilaian.setModel(model);
            String sql = "SELECT * FROM db_penilaian WHERE nip like '%"+search_data.getText()+"%' OR nama like '%"+search_data.getText()+"%' OR usia like '%"+search_data.getText()+"%' ORDER BY nama ASC";
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
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemulan " + e);
                }
            }
    }//GEN-LAST:event_b_search_dataActionPerformed

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

    private void button_data_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_karyawanMouseClicked
        // TODO add your handling code here:
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();
        
        main_panel.add(data_karyawan);
        main_panel.repaint();
        main_panel.revalidate();
        tabel_karyawan();
    }//GEN-LAST:event_button_data_karyawanMouseClicked

    private void button_data_karyawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_karyawanMouseEntered
        // TODO add your handling code here:
        button_data_karyawan.setBackground(new Color(162,110,71));
    }//GEN-LAST:event_button_data_karyawanMouseEntered

    private void button_data_karyawanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_karyawanMouseExited
        // TODO add your handling code here:
        button_data_karyawan.setBackground(new Color(187,135,96));
    }//GEN-LAST:event_button_data_karyawanMouseExited

    private void button_data_karyawanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_karyawanMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_data_karyawanMousePressed

    private void button_data_karyawanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_data_karyawanMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_button_data_karyawanMouseReleased

    private void search_data_karyawanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_data_karyawanFocusGained
        // TODO add your handling code here:
        String a = "Cari";
        if(search_data_karyawan.getText().equals(a)){
            search_data_karyawan.setText("");
        }
    }//GEN-LAST:event_search_data_karyawanFocusGained

    private void search_data_karyawanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_data_karyawanFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_search_data_karyawanFocusLost

    private void search_data_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_data_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_data_karyawanActionPerformed

    private void search_data_karyawanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_data_karyawanKeyPressed
        // TODO add your handling code here:
        String a = search_data_karyawan.getText();
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(a.equals("Cari")||a.equals("")){
                JOptionPane.showMessageDialog(null, "Silahkan Masukan Pencarian Anda.","Terjadi Kesalahan!",JOptionPane.ERROR_MESSAGE);
                search_data_karyawan.setText("Cari");
            }else{
                b_search_karyawanActionPerformed(null);
            }
        }
    }//GEN-LAST:event_search_data_karyawanKeyPressed

    private void b_search_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_search_karyawanActionPerformed
        // TODO add your handling code here:
        if(search_data_karyawan.getText().equals("Cari") || search_data_karyawan.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan Kata Kunci");
            tabel_karyawan();
        }else{
            Object[] baris = {"NIP","Nama","Jenis Kelamin","Pangkat/Golongan","Jabatan"};
            model = new DefaultTableModel(null, baris){
                @Override
                public boolean isCellEditable(int row, int column) {
                return false;
            }};
            tabel_karyawan.setModel(model);
            String sql = "SELECT * FROM db_karyawan WHERE nip like '%"+search_data_karyawan.getText()+"%' OR nama_lengkap like '%"+search_data_karyawan.getText()+"%' OR pangkat like '%"+search_data_karyawan.getText()+"%' OR jabatan like '%"+search_data_karyawan.getText()+"%' ORDER BY nama_lengkap ASC";
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);

                while(hasil.next()){
                String a = hasil.getString("nip");
                String b = hasil.getString("nama_lengkap");
                String c = hasil.getString("jenis_kelamin");
                String d = hasil.getString("pangkat");
                String e = hasil.getString("jabatan");
                
                String[] data = {a,b,c,d,e};
                model.addRow(data);
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemulan " + e);
                }
            }
    }//GEN-LAST:event_b_search_karyawanActionPerformed

    private void tabel_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_karyawanMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            input_penilaian fr = new input_penilaian();
            
            int bar = tabel_karyawan.getSelectedRow();
            String nip = model.getValueAt(bar, 0).toString();
            String nama = model.getValueAt(bar, 1).toString();
            fr.nip.setText(nip);
            fr.nama.setText(nama);
            
            String sql = "SELECT * FROM db_karyawan WHERE nip = '"+nip+"'";
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while(hasil.next()){
                    String usia = hasil.getString("usia");
                    fr.usia.setText(usia);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + e);
            }
            
            String pangkat = model.getValueAt(bar, 3).toString();
            fr.pangkat.setText(pangkat);
            
            fr.setVisible(true);
            fr.pack();
            fr.setDefaultCloseOperation(form_data_karyawan.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_tabel_karyawanMouseClicked

    private void tabel_karyawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_karyawanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_karyawanMouseEntered

    private void refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh1ActionPerformed
        // TODO add your handling code here:
        tabel_karyawan();
        search_data_karyawan.setText("Cari");
    }//GEN-LAST:event_refresh1ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Hapus Data Penilaian Terpilih?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            int bar = tabel_penilaian.getSelectedRow();
            String nip = model.getValueAt(bar, 0).toString();
            
            String sql = "DELETE FROM db_penilaian WHERE nip = '"+nip+"'";
            String sql2 = "DELETE FROM db_normalisasi WHERE nip = '"+nip+"'";
            
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                PreparedStatement stat2 = conn.prepareStatement(sql2);
                stat2.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                tabel_penilaian();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus" + e);
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void b_normalisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_normalisasiActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Menormalisasikan Data?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            normalisasi();
        }
        
        
    }//GEN-LAST:event_b_normalisasiActionPerformed

    private void delete_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_allActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Hapus Semua Data Penilaian?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            String sql = "DELETE FROM db_penilaian";
            String sql2 = "DELETE FROM db_normalisasi";
            
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                PreparedStatement stat2 = conn.prepareStatement(sql2);
                stat2.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                tabel_penilaian();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus" + e);
            }
        }
    }//GEN-LAST:event_delete_allActionPerformed

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
            java.util.logging.Logger.getLogger(form_penilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_penilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_penilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_penilaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_penilaian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area_search1;
    private javax.swing.JPanel area_search2;
    public javax.swing.JButton b_normalisasi;
    private javax.swing.JButton b_search_data;
    private javax.swing.JButton b_search_karyawan;
    private javax.swing.JLabel back_button;
    private javax.swing.JPanel background;
    private javax.swing.JPanel button_data_karyawan;
    private javax.swing.JPanel button_data_penilaian;
    private javax.swing.JPanel content_1;
    private javax.swing.JPanel content_3;
    private javax.swing.JPanel content_panel;
    private javax.swing.JPanel data_karyawan;
    private javax.swing.JPanel data_penilaian;
    public javax.swing.JButton delete;
    public javax.swing.JButton delete_all;
    private javax.swing.JLabel exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel main_panel;
    public javax.swing.JButton refresh;
    public javax.swing.JButton refresh1;
    private javax.swing.JTextField search_data;
    private javax.swing.JTextField search_data_karyawan;
    private javax.swing.JTable tabel_karyawan;
    private javax.swing.JTable tabel_penilaian;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel title5;
    private javax.swing.JLabel title6;
    private javax.swing.JLabel title7;
    // End of variables declaration//GEN-END:variables

    void refreshMouseClicked(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
