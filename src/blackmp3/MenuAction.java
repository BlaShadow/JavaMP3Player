

package blackmp3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuAction {

    ArrayList looksName,looksDir;
    StringTokenizer token;
    String nameLook;
    UserG userG;
    JButton ok,no,aceptar,cancelar;
    JFrame combo,selectList;
    Conection cone;
    JComboBox comboL;

    public MenuAction(UserG userG){
        this.userG = userG;
    }

    public String[] openFile(){

        String fileAd="";
        File fileDir = null;

        JFileChooser fileC=new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo MP3","mp3");
        fileC.setFileFilter(filter);
        int a=fileC.showOpenDialog(null);

        if(a==JFileChooser.APPROVE_OPTION){

            fileDir=fileC.getSelectedFile();
            fileAd=fileDir.getParent()+"/" +fileC.getName(fileDir);
            System.out.println(fileAd);
            
        }
        String[] data={fileAd,fileC.getName(fileDir)};

        return data;

    }

    public void savePlayList(ArrayList data,ArrayList data2){

        
        cone=new Conection();
        
        cone.savePlaylist(data,data2);

        

    }

    public void view(){
        

        looksName=new ArrayList();
        looksDir=new ArrayList();

        UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();

        for (int i = 0; i < laf.length; i++) {
            token=new StringTokenizer(laf[i].getClassName(),".");
//        System.out.println("Class name: " + laf[i].getClassName());
//        System.out.println("Name: " + laf[i].getName() + "\n");
            while(token.hasMoreTokens()){
                
                nameLook=token.nextToken();
                System.out.println(token);

            }

            looksName.add(laf[i].getClassName());
            looksDir.add(laf[i].getClassName());
        }

        combo=new JFrame("Set Look");
        combo.setSize(280,100);
        combo.setLocationRelativeTo(userG.mainF);
        combo.setDefaultCloseOperation(2);
        JComboBox comboBox=new JComboBox();
        

        JPanel panel=new JPanel();
        ok=new JButton("Aceptar");
        no=new JButton("Cancel");

        for(int i=0;i<looksDir.size();i++){
        comboBox.addItem(looksDir.get(i));
        }

        combo.add(comboBox,BorderLayout.CENTER);

        panel.add(ok);
        panel.add(no);
        comboBox.addItemListener(new Eventos());
        ok.addActionListener(new Eventos());
        no.addActionListener(new Eventos());
        combo.add(panel,BorderLayout.SOUTH);

        combo.setVisible(true);


    }

    public void openPlayList(){

        cone=new Conection();
        ArrayList resultado=new ArrayList();

        resultado=cone.openPlayList();

        selectList=new JFrame("Select PlayList");
        selectList.setSize(225,90);
        selectList.setLocationRelativeTo(null);
        selectList.setDefaultCloseOperation(2);
        selectList.setResizable(false);
        aceptar=new JButton("Aceptar");
        cancelar=new JButton("Cancelar");
        JPanel boton=new JPanel();

        comboL=new JComboBox();

        for(int i=0;i<resultado.size();i++){
        comboL.addItem(resultado.get(i));
        }

        selectList.add(comboL,BorderLayout.NORTH);
        
//        selectList.add(boton1,BorderLayout.WEST);
//        selectList.add(boton2,BorderLayout.EAST);
        aceptar.addActionListener(new Eventos());
        cancelar.addActionListener(new Eventos());

        boton.add(aceptar);
        boton.add(cancelar);
        selectList.add(boton,BorderLayout.SOUTH);

        selectList.setVisible(true);
        
    }


    class Eventos implements ItemListener,ActionListener{

        public void itemStateChanged(ItemEvent e) {
            try {
                UIManager.setLookAndFeel((String)e.getItem());
                combo.dispose();
//                userG.mainF.repaint();
                userG.mainF.dispose();
                userG.UserGrafic();

                System.out.println((String)e.getItem());
                
            } catch (Exception ex) {
            }

        }

        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==ok){

//                userG.mainF.UIManager.setLookAndFeel()

            }

            if(e.getSource()==no){

                combo.dispose();

            }

            if(e.getSource()==aceptar){
                selectList.dispose();
                ResultSet resultado;
                try {
                    cone = new Conection();
                    resultado = cone.loadList((String) comboL.getSelectedItem());

//                        userG.modeloList.clear();

                        userG.modeloList.clear();
                        userG.data.clear();
                        userG.data2.clear();
                    while (resultado.next()) {
                     
//                            userG.modeloList.addElement(resultado.getString(1));

                        userG.modeloList.addElement(resultado.getString(4));
                        userG.data.add(resultado.getString(3));
                        userG.data2.add(resultado.getString(4));

                    }
                    
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error Resulset");
                }

                
                

            }
            if(e.getSource()==cancelar){

                selectList.dispose();
            }
        }
    }

}
