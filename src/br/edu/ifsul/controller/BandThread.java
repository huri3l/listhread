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
            createSongs();
        } catch (Exception e) {
            System.out.println("A banda '" + 
                    getBand().getName() + 
                    "' nao conseguiu produzir a musica!"
            );
            e.printStackTrace();
        }
    }
    
     public void createSongs() {
        try {
            URL url = Main.class.getResource("songs.txt");
            Scanner scanner = new Scanner(new File(url.getPath()));
            while (scanner.hasNextLine()) {
                String[] rawSongData = scanner.nextLine().split(":");
                String bandNameData = (String) rawSongData[0];
                String bandName = (String) getBand().getName();                
                
                if(bandNameData.trim().equals(bandName.trim())) {
                    String[] songInfo = rawSongData[1].split("-");
                    String songAlbum = songInfo[1].split(";")[0].trim();
                    String songLyrics = songInfo[1].split(";")[1];
                    String songName = songInfo[0].trim();
                    
                    Song newSong = new Song(songName, songAlbum, getBand(), songLyrics);
                    Thread.currentThread().sleep((long)(Math.random() * 100000));
                    songs.put(newSong);
                    System.out.println("Banda " + newSong.getBand().getName() + " Musica: " + newSong.getName());
                    System.out.println("Novo lancamento! A banda " + 
                            Thread.currentThread().getName() + 
                            " lancou a musica: '" + newSong.getName() + 
                            "' do album '" + newSong.getAlbum() + 
                            "' no exato momento: " + 
                            sdf.format(newSong.getRelease_moment().getTime())
                    );
                }
            }
        } catch(Exception e) {
            System.out.println("O sistema nao conseguiu carregar os dados das musicas!");
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
