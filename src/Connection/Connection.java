package Connection;
import java.sql.*;
public class Connection{
    private java.sql.Connection conn;
    public java.sql.Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("Berhasil Koneksi");
        }catch(ClassNotFoundException ex){
            System.out.println("Gagal Koneksi " + ex);
        }
        
        String url = "jdbc:mysql://localhost/db_mutasi_balitbang";
       
        try{
            conn = DriverManager.getConnection(url, "root","");
//            System.out.println("Berhasil Koneksi Database");
        }catch (SQLException ex){
            System.out.println("Gagal Koneksi Database " + ex);
        }
        return conn;
    }
}
