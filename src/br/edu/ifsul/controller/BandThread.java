/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controller;

import br.edu.ifsul.model.Band;
import br.edu.ifsul.model.Musician;
import br.edu.ifsul.model.Song;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import main.Main;

/**
 *
 * @author 20201PF.CC0165
 */
public class BandThread implements Runnable {
    private Band band;
    private Song[] songList;
    private final BlockingQueue<Song> songs;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy "
            + "HH:mm:ss.SSSSSS"
    );
    
    public BandThread(BlockingQueue<Song> songs) {
        this.songs = songs;
    }
    
    @Override
    public void run() {
        try {
            // createSongs();
        } catch (Exception e) {
            System.out.println("A banda '" + 
                    getBand().getName() + 
                    "' nao conseguiu produzir a musica!"
            );
            e.printStackTrace();
        }
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Song[] getSongList() {
        return songList;
    }

    public void setSongList(Song[] songList) {
        this.songList = songList;
    }
    
    
}
