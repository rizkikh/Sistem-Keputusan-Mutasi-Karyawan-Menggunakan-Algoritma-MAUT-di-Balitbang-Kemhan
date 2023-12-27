package spk_mutasi_balitbang;

/**
 *
 * @author Khairul_Rizki
 */

import Display_running.*;
import javax.swing.JOptionPane;
public class SPK_Mutasi_Balitbang {
    public static void main(String[] args) {        
        loading_scene loading = new loading_scene();
        loading.setVisible(true);
        try{
            for(int i = 0; i<100; i++){
                Thread.sleep(80);
                loading.loading_text.setText(Integer.toString(i)+"%");
                loading.loading_bar.setValue(i);
                
            }
            loading.dispose();
            new form_login().setVisible(true);
        }catch(Exception err){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + err);
        }
    }
}
