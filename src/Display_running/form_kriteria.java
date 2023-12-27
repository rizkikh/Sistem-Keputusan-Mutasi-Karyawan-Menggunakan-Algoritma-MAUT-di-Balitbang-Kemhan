package Display_running;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Connection.Connection;
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
public class form_kriteria extends javax.swing.JFrame {

    private DefaultTableModel model, model2;
    private java.sql.Connection conn = new Connection().connect();
    
    public form_kriteria() {
        String Display_running = javax.swing.UIManager.getSystemLookAndFeelClassName();
        try{
            javax.swing.UIManager.setLookAndFeel(Display_running);
        }catch(Exception e){}
        initComponents();
        Locale locale = new Locale("ID","id");
        Locale.setDefault(locale);
        datatabel();
        datatabel_2();
        id_oto();
    }

    protected void reset(){
//        id_bobot.setText("");
        kriteria_1.setText("");
        kriteria_2.setText("");
        kriteria_3.setText("");
        kriteria_4.setText("");
        kriteria_5.setText("");
        id_oto();
    }
    
    protected void datatabel(){
        Object[] baris = {"ID Bobot","Usia","Kinerja","Golongan","Disiplin","Pengalaman"};
        model = new DefaultTableModel(null, baris){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        table_data.setModel(model);
        
        String sql = "SELECT * FROM db_kriteria ORDER BY id_bobot ASC";
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
    
    protected void datatabel_2(){
        Object[] baris = {"ID Bobot","Usia","Kinerja","Golongan","Disiplin","Pengalaman"};
        model2 = new DefaultTableModel(null, baris){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
        }};
        tabel_bobot.setModel(model2);
        
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
                model2.addRow(data);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + e);
        }
    }
    
    protected void hitung_bobot(){    
        String sql = "SELECT * FROM db_kriteria WHERE id_bobot = '"+id.getText()+"'";
        String sql2 = "INSERT INTO db_bobot VALUES (?,?,?,?,?,?)";
        
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            while(rs.next()){
                double usia = Double.parseDouble(rs.getString("usia"));
                double kinerja = Double.parseDouble(rs.getString("kinerja"));
                double golongan = Double.parseDouble(rs.getString("pangkat"));
                double disiplin = Double.parseDouble(rs.getString("disiplin"));
                double pengalaman = Double.parseDouble(rs.getString("pengalaman"));
                
                double total = usia + kinerja + golongan + disiplin + pengalaman;
                
                double b_usia = usia / total;
                double b_kinerja = kinerja / total;
                double b_golongan = golongan / total;
                double b_disiplin = disiplin / total;
                double b_pengalaman = pengalaman / total;
                
                
                PreparedStatement stat2 = conn.prepareStatement(sql2);
                stat2.setString(1, id.getText());
                stat2.setDouble(2, b_usia);
                stat2.setDouble(3, b_kinerja);
                stat2.setDouble(4, b_golongan);
                stat2.setDouble(5, b_disiplin);
                stat2.setDouble(6, b_pengalaman);
                stat2.executeUpdate();
            }
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihitung");
            datatabel_2();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Data Yang Anda Hitung Sudah Ada.","Terjadi Kesalahan!",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected void id_oto(){
        try{
            String sql = "SELECT * FROM db_kriteria ORDER BY id_bobot DESC";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            
            if(rs.next()){
                String nofak = rs.getString("id_bobot").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";
                String hr = "B";
                if(AN.length() == 1){
                    Nol = "00";
                }else if(AN.length() == 2){
                    Nol = "0";
                }else if(AN.length() == 3){
                    Nol = "";
                }
                
                id_bobot.setText(hr + Nol + AN);
            }else{
                id_bobot.setText("B001");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        title4 = new javax.swing.JLabel();
        title6 = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        content_panel = new javax.swing.JPanel();
        back_button = new javax.swing.JLabel();
        main_panel = new javax.swing.JPanel();
        tampil_data = new javax.swing.JPanel();
        content_1 = new javax.swing.JPanel();
        title5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_data = new javax.swing.JTable();
        b_print = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        button_hitung = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_bobot = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        kelola_data = new javax.swing.JPanel();
        content_2 = new javax.swing.JPanel();
        title7 = new javax.swing.JLabel();
        area_form = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        id_bobot = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        kriteria_4 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        kriteria_2 = new javax.swing.JTextField();
        t_simpan = new javax.swing.JButton();
        t_reset = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        kriteria_1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        kriteria_3 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        kriteria_5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        button_tampil_data = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        button_kelola_data = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Kriteria");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setBackground(new java.awt.Color(79, 14, 14));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/logo_kemhan_small.png"))); // NOI18N

        title4.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        title4.setForeground(new java.awt.Color(255, 241, 241));
        title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title4.setText("Kriteria Mutasi.");

        title6.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        title6.setForeground(new java.awt.Color(255, 241, 241));
        title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title6.setText("BALITBANG KEMHAN RI");

        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/exit_button.png"))); // NOI18N
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

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

        tampil_data.setBackground(new java.awt.Color(187, 135, 96));
        tampil_data.setPreferredSize(new java.awt.Dimension(800, 450));

        content_1.setBackground(new java.awt.Color(255, 241, 241));

        title5.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        title5.setForeground(new java.awt.Color(79, 14, 14));
        title5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title5.setText("Data Kriteria.");

        table_data.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        table_data.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        table_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Bobot", "Usia", "Kinerja", "Pangkat/Golongan", "Disiplin", "Pengalaman"
            }
        ));
        table_data.setRowHeight(25);
        table_data.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table_data.getTableHeader().setResizingAllowed(false);
        table_data.getTableHeader().setReorderingAllowed(false);
        table_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_dataMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                table_dataMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(table_data);

        b_print.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        b_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/print_25px.png"))); // NOI18N
        b_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_printActionPerformed(evt);
            }
        });

        refresh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/refresh_20px.png"))); // NOI18N
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        button_hitung.setBackground(new java.awt.Color(187, 135, 96));
        button_hitung.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_hitung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_hitungMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_hitungMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button_hitungMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/hitung_25px.png"))); // NOI18N
        jLabel6.setText(" Hitung Bobot Kriteria");

        javax.swing.GroupLayout button_hitungLayout = new javax.swing.GroupLayout(button_hitung);
        button_hitung.setLayout(button_hitungLayout);
        button_hitungLayout.setHorizontalGroup(
            button_hitungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
            .addGroup(button_hitungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(button_hitungLayout.createSequentialGroup()
                    .addGap(0, 7, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addGap(0, 7, Short.MAX_VALUE)))
        );
        button_hitungLayout.setVerticalGroup(
            button_hitungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
            .addGroup(button_hitungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(button_hitungLayout.createSequentialGroup()
                    .addGap(0, 6, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addGap(0, 6, Short.MAX_VALUE)))
        );

        tabel_bobot.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tabel_bobot.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabel_bobot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Bobot", "Usia", "Kinerja", "Golongan", "Disiplin", "Pengalaman"
            }
        ));
        tabel_bobot.setEnabled(false);
        tabel_bobot.setRowHeight(25);
        tabel_bobot.getTableHeader().setResizingAllowed(false);
        tabel_bobot.getTableHeader().setReorderingAllowed(false);
        tabel_bobot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_bobotMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabel_bobotMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_bobot);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(79, 14, 14));
        jLabel12.setText("ID Bobot Terpilih");

        id.setEditable(false);
        id.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        id.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout content_1Layout = new javax.swing.GroupLayout(content_1);
        content_1.setLayout(content_1Layout);
        content_1Layout.setHorizontalGroup(
            content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(content_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addComponent(title5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addGroup(content_1Layout.createSequentialGroup()
                        .addComponent(b_print, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(content_1Layout.createSequentialGroup()
                        .addGroup(content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_hitung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        content_1Layout.setVerticalGroup(
            content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(content_1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_print, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(content_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_hitung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(content_1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );

        javax.swing.GroupLayout tampil_dataLayout = new javax.swing.GroupLayout(tampil_data);
        tampil_data.setLayout(tampil_dataLayout);
        tampil_dataLayout.setHorizontalGroup(
            tampil_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampil_dataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tampil_dataLayout.setVerticalGroup(
            tampil_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tampil_dataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content_1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        main_panel.add(tampil_data, "card2");

        kelola_data.setBackground(new java.awt.Color(187, 135, 96));

        content_2.setBackground(new java.awt.Color(255, 241, 241));

        title7.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        title7.setForeground(new java.awt.Color(79, 14, 14));
        title7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title7.setText("Tambah Data Kriteria.");

        area_form.setBackground(new java.awt.Color(255, 241, 241));

        jPanel1.setBackground(new java.awt.Color(255, 241, 241));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 14, 14));
        jLabel4.setText("ID Bobot");

        id_bobot.setEditable(false);
        id_bobot.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id_bobot)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id_bobot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 241, 241));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(79, 14, 14));
        jLabel5.setText("Disiplin");

        kriteria_4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kriteria_4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kriteria_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 241, 241));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(79, 14, 14));
        jLabel7.setText("Kinerja");

        kriteria_2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kriteria_2)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kriteria_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        t_simpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        t_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/save_25px.png"))); // NOI18N
        t_simpan.setText("Simpan");
        t_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_simpanActionPerformed(evt);
            }
        });

        t_reset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        t_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/reset_20px.png"))); // NOI18N
        t_reset.setText("Reset");
        t_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_resetActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 241, 241));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(79, 14, 14));
        jLabel8.setText("Usia");

        kriteria_1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kriteria_1)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kriteria_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 241, 241));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(79, 14, 14));
        jLabel9.setText("Golongan");

        kriteria_3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kriteria_3)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kriteria_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 241, 241));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(79, 14, 14));
        jLabel11.setText("Pengalaman");

        kriteria_5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kriteria_5)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kriteria_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout area_formLayout = new javax.swing.GroupLayout(area_form);
        area_form.setLayout(area_formLayout);
        area_formLayout.setHorizontalGroup(
            area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(t_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(t_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        area_formLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel4});

        area_formLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel3, jPanel5});

        area_formLayout.setVerticalGroup(
            area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, area_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(area_formLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(area_formLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(area_formLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(area_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_reset))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(79, 14, 14));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Masukan data kriteria dibawah dengan lengkap");

        jPanel7.setBackground(new java.awt.Color(79, 14, 14));
        jPanel7.setPreferredSize(new java.awt.Dimension(100, 1));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout content_2Layout = new javax.swing.GroupLayout(content_2);
        content_2.setLayout(content_2Layout);
        content_2Layout.setHorizontalGroup(
            content_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(content_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(content_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(title7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, content_2Layout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addGroup(content_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(area_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(141, 141, 141))
        );
        content_2Layout.setVerticalGroup(
            content_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(content_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(area_form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout kelola_dataLayout = new javax.swing.GroupLayout(kelola_data);
        kelola_data.setLayout(kelola_dataLayout);
        kelola_dataLayout.setHorizontalGroup(
            kelola_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kelola_dataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kelola_dataLayout.setVerticalGroup(
            kelola_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kelola_dataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        main_panel.add(kelola_data, "card2");

        content_panel.add(main_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 800, 450));

        button_tampil_data.setBackground(new java.awt.Color(187, 135, 96));
        button_tampil_data.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_tampil_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_tampil_dataMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_tampil_dataMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button_tampil_dataMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button_tampil_dataMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button_tampil_dataMouseReleased(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/show_20px.png"))); // NOI18N
        jLabel2.setText("Tampilkan Data");

        javax.swing.GroupLayout button_tampil_dataLayout = new javax.swing.GroupLayout(button_tampil_data);
        button_tampil_data.setLayout(button_tampil_dataLayout);
        button_tampil_dataLayout.setHorizontalGroup(
            button_tampil_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_tampil_dataLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );
        button_tampil_dataLayout.setVerticalGroup(
            button_tampil_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_tampil_dataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        content_panel.add(button_tampil_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, -1));

        button_kelola_data.setBackground(new java.awt.Color(187, 135, 96));
        button_kelola_data.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_kelola_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_kelola_dataMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_kelola_dataMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button_kelola_dataMouseExited(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image_assets/add_20px.png"))); // NOI18N
        jLabel3.setText("Tambahkan Data");

        javax.swing.GroupLayout button_kelola_dataLayout = new javax.swing.GroupLayout(button_kelola_data);
        button_kelola_data.setLayout(button_kelola_dataLayout);
        button_kelola_dataLayout.setHorizontalGroup(
            button_kelola_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_kelola_dataLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );
        button_kelola_dataLayout.setVerticalGroup(
            button_kelola_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, button_kelola_dataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        content_panel.add(button_kelola_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 190, -1));

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
                .addGap(35, 35, 35))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(title4))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(exit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 570));

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

    private void table_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_dataMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            edit_kriteria fr = new edit_kriteria();
        
            int bar = table_data.getSelectedRow();
            String id_bobot = model.getValueAt(bar, 0).toString();
            String usia = model.getValueAt(bar, 1).toString();
            String kinerja = model.getValueAt(bar, 2).toString();
            String golongan = model.getValueAt(bar, 3).toString();
            String disiplin = model.getValueAt(bar, 4).toString();
            String pengalaman = model.getValueAt(bar, 5).toString();

            fr.id_bobot.setText(id_bobot);
            fr.kriteria_1.setText(usia);
            fr.kriteria_2.setText(kinerja);
            fr.kriteria_3.setText(golongan);
            fr.kriteria_4.setText(disiplin);
            fr.kriteria_5.setText(pengalaman);

            fr.setVisible(true);
            fr.pack();
            fr.setDefaultCloseOperation(form_kriteria.DISPOSE_ON_CLOSE);
        }else{
            int bar = table_data.getSelectedRow();
            String id_bobot = model.getValueAt(bar, 0).toString();
            id.setText(id_bobot);
        }
    }//GEN-LAST:event_table_dataMouseClicked

    private void table_dataMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_dataMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_table_dataMouseEntered

    private void b_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_printActionPerformed
        // TODO add your handling code here:
        String realPath = "Image_assets/";
        HashMap hash = new HashMap();
        try {
            hash.put("realPath", realPath);
            InputStream ambil = getClass().getResourceAsStream("/Display_running/report_kriteria_mutasi.jasper") ;
           
            JasperPrint print = JasperFillManager.fillReport(ambil, hash, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Data Gagal Dicetak" + e);
        }
    }//GEN-LAST:event_b_printActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        datatabel_2();
        datatabel();
        id.setText("");
    }//GEN-LAST:event_refreshActionPerformed

    private void t_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_simpanActionPerformed
        // TODO add your handling code here:
        String sql = "INSERT INTO db_kriteria VALUES (?,?,?,?,?,?)";

        String a = id_bobot.getText();
        String b = kriteria_1.getText();
        String c = kriteria_2.getText();
        String d = kriteria_3.getText();
        String e = kriteria_4.getText();
        String f = kriteria_5.getText();

        if(a.equals("")||b.equals("")||c.equals("")||d.equals("")||e.equals("")||f.equals("")){
            JOptionPane.showMessageDialog(null, "Silahkan Lengkapi Data Anda.","Terjadi Kesalahan!",JOptionPane.ERROR_MESSAGE);
            reset();
            kriteria_1.requestFocus();
        }else{
            try{
                PreparedStatement stat = conn.prepareStatement(sql);

                stat.setString(1, id_bobot.getText());
                stat.setString(2, kriteria_1.getText());
                stat.setString(3, kriteria_2.getText());
                stat.setString(4, kriteria_3.getText());
                stat.setString(5, kriteria_4.getText());
                stat.setString(6, kriteria_5.getText());
                stat.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan");
                reset();
                kriteria_1.requestFocus();
            }catch(Exception g){
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + g);
                reset();
                id_bobot.requestFocus();
            }
        }
    }//GEN-LAST:event_t_simpanActionPerformed

    private void t_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_resetActionPerformed
        // TODO add your handling code here:
        reset();
        id_bobot.requestFocus();
    }//GEN-LAST:event_t_resetActionPerformed

    private void button_tampil_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_tampil_dataMouseClicked
        // TODO add your handling code here:
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();

        main_panel.add(tampil_data);
        main_panel.repaint();
        main_panel.revalidate();
        datatabel();
    }//GEN-LAST:event_button_tampil_dataMouseClicked

    private void button_tampil_dataMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_tampil_dataMouseEntered
        // TODO add your handling code here:
        button_tampil_data.setBackground(new Color(162,110,71));
    }//GEN-LAST:event_button_tampil_dataMouseEntered

    private void button_tampil_dataMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_tampil_dataMouseExited
        // TODO add your handling code here:
        button_tampil_data.setBackground(new Color(187,135,96));
    }//GEN-LAST:event_button_tampil_dataMouseExited

    private void button_tampil_dataMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_tampil_dataMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_button_tampil_dataMousePressed

    private void button_tampil_dataMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_tampil_dataMouseReleased
        // TODO add your handling code here:
        button_tampil_data.setBackground(new Color(162,110,71));
    }//GEN-LAST:event_button_tampil_dataMouseReleased

    private void button_kelola_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_kelola_dataMouseClicked
        // TODO add your handling code here:
        main_panel.removeAll();
        main_panel.repaint();
        main_panel.revalidate();

        main_panel.add(kelola_data);
        main_panel.repaint();
        main_panel.revalidate();
        id_oto();
    }//GEN-LAST:event_button_kelola_dataMouseClicked

    private void button_kelola_dataMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_kelola_dataMouseEntered
        // TODO add your handling code here:
        button_kelola_data.setBackground(new Color(162,110,71));
    }//GEN-LAST:event_button_kelola_dataMouseEntered

    private void button_kelola_dataMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_kelola_dataMouseExited
        // TODO add your handling code here:
        button_kelola_data.setBackground(new Color(187,135,96));
    }//GEN-LAST:event_button_kelola_dataMouseExited

    private void button_hitungMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_hitungMouseEntered
        // TODO add your handling code here:
        button_hitung.setBackground(new Color(162,110,71));
    }//GEN-LAST:event_button_hitungMouseEntered

    private void button_hitungMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_hitungMouseExited
        // TODO add your handling code here:
        button_hitung.setBackground(new Color(187,135,96));
    }//GEN-LAST:event_button_hitungMouseExited

    private void tabel_bobotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_bobotMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_bobotMouseClicked

    private void tabel_bobotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_bobotMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_bobotMouseEntered

    private void button_hitungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_hitungMouseClicked
        // TODO add your handling code here:
        String a = id.getText();
        if(a.equals("")){
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Data Yang Ingin Dihitung.","Terjadi Kesalahan!",JOptionPane.ERROR_MESSAGE);
        }else{
            hitung_bobot();
        }
        
    }//GEN-LAST:event_button_hitungMouseClicked

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
            java.util.logging.Logger.getLogger(form_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_kriteria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area_form;
    private javax.swing.JButton b_print;
    private javax.swing.JLabel back_button;
    private javax.swing.JPanel background;
    private javax.swing.JPanel button_hitung;
    private javax.swing.JPanel button_kelola_data;
    private javax.swing.JPanel button_tampil_data;
    private javax.swing.JPanel content_1;
    private javax.swing.JPanel content_2;
    private javax.swing.JPanel content_panel;
    private javax.swing.JLabel exit;
    private javax.swing.JTextField id;
    private javax.swing.JTextField id_bobot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel kelola_data;
    private javax.swing.JTextField kriteria_1;
    private javax.swing.JTextField kriteria_2;
    private javax.swing.JTextField kriteria_3;
    private javax.swing.JTextField kriteria_4;
    private javax.swing.JTextField kriteria_5;
    private javax.swing.JPanel main_panel;
    public javax.swing.JButton refresh;
    private javax.swing.JButton t_reset;
    private javax.swing.JButton t_simpan;
    private javax.swing.JTable tabel_bobot;
    private javax.swing.JTable table_data;
    private javax.swing.JPanel tampil_data;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel title5;
    private javax.swing.JLabel title6;
    private javax.swing.JLabel title7;
    // End of variables declaration//GEN-END:variables

}
