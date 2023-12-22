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


        add(new Board());
        setTitle("Breakout");

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        pack();
    }



    public static void main(String[] args) {

            var game = new Breakout();
            game.setVisible(true);

            musicPlayer.continuesMusic();

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
