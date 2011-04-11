
package blackmp3;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class MPlayer extends Thread{

    Player mPlayer;
    UserG userG;
    MPlayer m;

    public MPlayer(String dir){
        
            try {
                
            FileInputStream fileMp = null;
            
                try {

                    fileMp = new FileInputStream(dir);
                
                } catch (FileNotFoundException ex) {

                }
            
            mPlayer = new Player(fileMp);
            
        } catch (JavaLayerException ex) {

        }
    }

 
    @Override
    public void run(){
        System.out.println("Entro al run");

        while(true){

        System.out.println("Entro al run");

        userG=new UserG();

        try {
            mPlayer.play();

            if(mPlayer.isComplete()){
                System.out.println("Termino la vaina");
                userG.nunSong+=1;

                System.out.println(userG.nunSong);
            }

        } catch (JavaLayerException ex) {
        }

        if(mPlayer.isComplete()){
            m=new MPlayer((String) userG.data2.get(userG.nunSong));
        JOptionPane.showMessageDialog(null, " Error termino");}

        }

    }

    public void stoped(){
            mPlayer.close();
    }

    public void paused(){
        try {
            mPlayer.play(userG.pausePos);
        }
        catch (JavaLayerException ex){
        }
    }
}
