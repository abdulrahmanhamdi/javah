package com.zetcode;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import java.awt.EventQueue;
import java.io.File;
import java.util.Scanner;

public class Breakout extends JFrame {

    public Breakout() {
        
        initUI();
    }
    
    private void initUI() {

        Scanner scan = new Scanner(System.in);
        int level;



        do{
            System.out.println("level 1 (easy)");
            System.out.println("level 2 (medium)");
            System.out.println("level 3 (hard)");

            System.out.println("enter the level number to choose the level : ");
            level = scan.nextInt();
        }while(level < 1 || level > 3);


         if(level == 1){
             add(new Level1());
         }else if (level == 2){
             add(new Level2());
         }else {
             add(new Level3());
         }

        setTitle("Breakout");

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        pack();
    }



    public static void main(String[] args) {


        EventQueue.invokeLater(() -> {

            var game = new Breakout();
            game.setVisible(true);

            musicPlayer.continuesMusic();
        });


    }


}


class musicPlayer{

    public static void continuesMusic(){

        String musicFile = "src/resources/skulls_adventure_1.wav";

        try{
            File audioFile = new File(musicFile);

            AudioInputStream audioStream =  AudioSystem.getAudioInputStream(audioFile);

            Clip clip =  AudioSystem.getClip();
            clip.open(audioStream);

            // Loop the music continuously
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        }catch (Exception e){
            e.printStackTrace();
        }


    }











}
