
package blackmp3;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Conection {

    Connection cone;
    UserG userG;
    ArrayList resultadoL;

    Conection(){

        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            cone=DriverManager.getConnection("jdbc:mysql://localhost:3306/playlist","root","123");
            
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(null, "Error Conection");

        }
        

    }


    public void savePlaylist(ArrayList data,ArrayList data2){

        
            
            String namePlaylist="";
        try {
            int statement=0;
            Statement query = cone.createStatement();

            namePlaylist=JOptionPane.showInputDialog(null,"Name Playlist");
            System.out.println(data.size());

   
            if(namePlaylist.length()!=0){
                if(data.isEmpty()==false){
                    for(int i=0;i<data.size();i++){
                        System.out.println("Entro al for");

    //                    System.out.println("insert into plailist(nameplay,dirsong,namesong,nunsong) "
    //                        + "values('"+ namePlaylist+"',"
    //                        + "'"+data.get(i)+"',"
    //                        + "'"+data2.get(i)+"',"
    //                        + ""+(i+1)+")");
                    System.out.println("insert into plailist(nameplay,dirsong,namesong,nunsong) "
                            + "values('"+ namePlaylist+"',"
                            + "'"+data.get(i)+"',"
                            + "'"+data2.get(i)+"',"
                            + ""+(i+1)+")");

                    statement=query.executeUpdate("insert into plailist(nameplay,dirsong,namesong,nunsong) "
                            + "values('"+ namePlaylist+"',"
                            + "'"+data.get(i)+"',"
                            + "'"+data2.get(i)+"',"
                            + ""+(i+1)+")");
    //                        JOptionPane.showMessageDialog(null, "Bacano "+i);

                    }
                System.out.println("ok");}
                else{
                    JOptionPane.showMessageDialog(null, "List Empty");
                }
            }else{JOptionPane.showMessageDialog(null, "Witchout Name");}

                
            } catch (SQLException ex) {
                
            JOptionPane.showMessageDialog(null, "Error Statement");
            }
            

       
//            JOptionPane.showMessageDialog(null, "Error Statement");

    }

    public ArrayList openPlayList(){
        resultadoL=new ArrayList();


        try {
            Statement query = cone.createStatement();

            ResultSet resultado=query.executeQuery("select DISTINCT nameplay from plailist");
            ResultSetMetaData data=resultado.getMetaData();

            while(resultado.next()){

//                for(int i=0;i<data.getColumnCount();i++)
//                resultadoL.add(resultado.getString(i));
//
//                System.out.println(resultadoL.get(0));
//                System.out.println("Aki ok");

//                System.out.println(resultado.getString(1));
                System.out.println(resultado.getString(1));
                resultadoL.add(resultado.getString(1));

            }
            
            } catch (SQLException ex) {
        
        }
        return resultadoL;

    }

    public ResultSet loadList(String select){
        ResultSet resultado=null;
        try {
            Statement query = cone.createStatement();
            System.out.println("Antes del execute");
            System.out.println("select * from plailist where nameplay='"+select+"' order by nunsong asc");

            resultado=query.executeQuery("select * from plailist where nameplay='"+select+"' order by nunsong asc");

            System.out.println("Ok con el resulset");
            
        } catch (SQLException ex){
            System.out.println("Error con El ResultSet");
        }

        System.out.println("ok End");
        return resultado;

    }

}
