

package blackmp3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

//import javax.

public class UserG {

    public JFrame mainF;
    public JPanel menuP,controlP,playListP,visualP;
    public JButton play,stop,backward,forward,pause;
    public JList listaM;
    public JMenu file,help,view;
    public JMenuItem openPlayList,savePlayList,exit,openFile,about,skin;
    public JMenuBar menuBar;
    public Evento event;//Clase Eventos
    public MenuAction menuAction;//Clase MenuAction
    public ArrayList data,data2;//Almacenada tanto la url como el nombre del File
    public DefaultListModel modeloList;//PlayList
    public MPlayer player;//Player
    int nunSong=0;
    public boolean status=false;
    public boolean isPause;
    public int pausePos=0;


    public void UserGrafic(){

        
        mainF=new JFrame("Black Mp3");
        mainF.setSize(400, 300);
        mainF.setDefaultCloseOperation(2);
        mainF.setLocationRelativeTo(null);
        mainF.setMinimumSize(new Dimension(400,300));
        mainF.setIconImage(Toolkit.getDefaultToolkit().getImage(UserG.class.getResource("Icon/black.png")));
        
        
        //Objetos
        event=new Evento();
        menuAction=new MenuAction(this);
        data=new ArrayList();
        data2=new ArrayList();
       

        //Paneles
        menuP=new JPanel();
        controlP=new JPanel();
        playListP=new JPanel();
        visualP=new JPanel();

        
        //Botones
        play=new JButton();
        stop=new JButton();
        forward=new JButton();
        backward=new JButton();
        pause=new JButton();

        //Jlist
        modeloList=new DefaultListModel();
        listaM=new JList();
        listaM.setModel(modeloList);
        listaM.setSelectedIndex(nunSong);
        JScrollPane scroll=new JScrollPane(listaM);
        scroll.setPreferredSize(new Dimension(150,200));
//        scroll.setViewportView();

        //SpltPane
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,visualP,scroll);
        split.setDividerSize(2);
        split.setOneTouchExpandable(true);
        split.setDividerLocation(0.1);
        split.setDividerLocation(250);
        

        //menus
        file=new JMenu("File");
        help=new JMenu("Help");
        view=new JMenu("View");

        //menuItem
        openPlayList=new JMenuItem("Open PlayList");
        savePlayList=new JMenuItem("Save PlayList");
        exit=new JMenuItem("Close");
        openFile=new JMenuItem("Open");
        about=new JMenuItem("About");
        skin=new JMenuItem("Skin Manager");

        //barra menu
        menuBar=new JMenuBar();
        

        //Llammadas
        paneles();
        botones();
        addEventos();


        //menu Add
        file.add(openFile);
        file.add(openPlayList);
        file.add(savePlayList);
        file.addSeparator();
        file.add(exit);

        help.add(about);

        view.add(skin);


        //menuBar Add
        menuBar.add(file);
        menuBar.add(view);
        menuBar.add(help);

        
        //panel Add
        controlP.add(play);
        controlP.add(pause);
        controlP.add(backward);
        controlP.add(stop);
        controlP.add(forward);
//        playListP.add(scroll);


        //Add Frame
        mainF.setJMenuBar(menuBar);
        mainF.add(menuP,BorderLayout.NORTH);
        mainF.add(controlP,BorderLayout.SOUTH);
//        mainF.add(playListP,BorderLayout.EAST);
//        mainF.add(visualP,BorderLayout.CENTER);
        mainF.add(split);

        
        mainF.setVisible(true);
    }

    public void paneles(){

        //MenuP
//        menuP.setSize(400,0);
        menuP.setBackground(Color.black);

        //ControlP
        controlP.setBackground(Color.black);

        //PlayListP
        playListP.setBackground(Color.black);

        //visualP
        visualP.setBackground(Color.cyan);
        

    }

    public void botones(){

        //play
        ImageIcon playI=new ImageIcon(UserG.class.getResource("Icon/playI.png"));
//        play.setText("Play");
        play.setIcon(playI);
        play.setBackground(Color.getHSBColor(nunSong, nunSong, nunSong));
        play.setBorderPainted(false);
//        play.setBorder(null);
        
        

        //stop
        ImageIcon stopI=new ImageIcon(UserG.class.getResource("Icon/stopI.png"));
//        stop.setText("Stop");
        stop.setIcon(stopI);
        stop.setBackground(Color.getHSBColor(nunSong, nunSong, nunSong));
        stop.setBorderPainted(false);

        //forward
        ImageIcon forwardI=new ImageIcon(UserG.class.getResource("Icon/forwardI.png"));
//        forward.setText("Next");
        forward.setIcon(forwardI);
        forward.setBackground(Color.getHSBColor(nunSong, nunSong, nunSong));
        forward.setBorderPainted(false);

        //backward
        ImageIcon backwardI=new ImageIcon(UserG.class.getResource("Icon/backwardI.png"));
//        backward.setText("Prev");
        backward.setIcon(backwardI);
        backward.setBackground(Color.getHSBColor(nunSong, nunSong, nunSong));
        backward.setBorderPainted(false);


        //pause
//        pause.setText("Pause");
        ImageIcon pauseI=new ImageIcon(UserG.class.getResource("Icon/pauseI.png"));
        pause.setIcon(pauseI);
        pause.setBackground(Color.getHSBColor(nunSong, nunSong, nunSong));
        pause.setBorderPainted(false);

        
        pause.setVisible(false);
    }

    public void addEventos(){

        //menu Listener
        exit.addActionListener(event);
        openFile.addActionListener(event);
        openPlayList.addActionListener(event);
        savePlayList.addActionListener(event);


        
        about.addActionListener(event);

        skin.addActionListener(event);

        //Control Listener
        play.addActionListener(event);
        pause.addActionListener(event);
        stop.addActionListener(event);
        forward.addActionListener(event);
        backward.addActionListener(event);


        listaM.addMouseListener(event);
        
    }

    class Evento implements ActionListener,MouseListener{

        public void actionPerformed(ActionEvent e) {

            //Exit Event
            if(e.getSource()==exit){
            System.exit(0);}

            //Open Event
            if(e.getSource()==openFile){

                String[] dato=menuAction.openFile();

                if(dato[1]!=null){
                data.add(dato[0]);//url del File
                data2.add(dato[1]);//Nombre del File
                }
                modeloList.clear();
                
                for(int i=0;i<data2.size();i++){

                    modeloList.addElement(data2.get(i));

                }
                
                mainF.repaint();

                for(int i=0;i<data2.size();i++){

                    System.out.println(data2.get(i));

                }

                
            }

            //Open PlayList Event
            if(e.getSource()==openPlayList){

                menuAction.openPlayList();
            }
            //savePlayList Event
            if(e.getSource()==savePlayList){

                
                menuAction.savePlayList(data,data2);
            }

            //view
            if(e.getSource()==skin){

                menuAction.view();

            }
            
            //play
            if(e.getSource()==play){

                if(!modeloList.isEmpty()){
                play.setVisible(false);
                pause.setVisible(true);


                player=new MPlayer((String)(data.get(nunSong)));
                listaM.setSelectedIndex(nunSong);

                player.start();
                
                    status = true;
                 if(player.mPlayer.isComplete()){
                    nunSong+=1;
                    player.start();
                 }

                }

            }
            //stop
            if(e.getSource()==stop){

                if(!modeloList.isEmpty()){
                nunSong=0;

                if(isPause=true){
                    player.resume();
                    isPause=false;}

                if(isPause==true){
                    player.resume();
                    isPause=false;}

                if(status==true)
                player.stoped();
                
                pause.setVisible(false);
                play.setVisible(true);
                }
            }
            //forward
            if(e.getSource()==forward){

                if(!modeloList.isEmpty()){

                if(isPause=true){
                    player.resume();
                    isPause=false;
                    ImageIcon pauseI3=new ImageIcon(UserG.class.getResource("Icon/pauseI.png"));
                    pause.setIcon(pauseI3);
                }
                
                if(!modeloList.isEmpty()){

                    play.setVisible(false);
                    pause.setVisible(true);

                    if(nunSong==(data.size()-1)){
                        nunSong=0;
                        System.out.println("aki 1");
                    }else{
                        System.out.println("aki 2");
                        nunSong+=1;
                    }

                if(status==true)
                player.stoped();
                    
                player=new MPlayer((String)data.get(nunSong));
                listaM.setSelectedIndex(nunSong);
                player.start();
                status=true;
//                player.
                    }

                }
            }
            //backward
            if(e.getSource()==backward){


                if(!modeloList.isEmpty()){

                if(isPause=true){
                    player.resume();
                    isPause=false;}

                if(!modeloList.isEmpty()){
                    play.setVisible(false);
                    pause.setVisible(true);
                if(nunSong==0){

                    nunSong=0;
                }
                else{nunSong-=1;}
                if(status==true)
                player.stoped();
                    
                player=new MPlayer((String)data.get(nunSong));
                player.start();
                listaM.setSelectedIndex(nunSong);
                }
                
                }
            }
            //pause Event
            if(e.getSource()==pause){

                if(!modeloList.isEmpty()){
//
                    if(isPause==false){
//                    pause.setVisible(false);
//                    play.setVisible(true);
//                    player.mPlayer.close();
//                    player
                    isPause=true;
                    player.stoped();
                    ImageIcon pauseI2=new ImageIcon(UserG.class.getResource("Icon/pausePlay.png"));
                    pause.setIcon(pauseI2);

                    System.out.println("se pauso");}
                     else{
                        ImageIcon pauseI3=new ImageIcon(UserG.class.getResource("Icon/pauseI.png"));
                        pause.setIcon(pauseI3);
                        player.resume();
                        isPause=false;
                    }
                  
                }
                
            }

        }

        public void mouseClicked(MouseEvent e) {

            int mouseClick=e.getClickCount();
            int listaSeleted=0;

            if(mouseClick>1){//Play List Double Click

                
                    //Play List Double Click
                    if (status == true) {
                        player.stoped();
                    }
                    listaSeleted = listaM.getSelectedIndex();
                    play.setVisible(false);
                    pause.setVisible(true);
                    player = new MPlayer((String) (data.get(listaSeleted)));
                    player.start();
                    status = true;
                

            }

        }

        public void mousePressed(MouseEvent e) {

        }
        public void mouseReleased(MouseEvent e) {
              }
        public void mouseEntered(MouseEvent e) {            
        }
        public void mouseExited(MouseEvent e) {
        }
    }
    
}


