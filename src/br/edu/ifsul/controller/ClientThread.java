/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controller;

import br.edu.ifsul.model.Client;
import br.edu.ifsul.model.Song;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Main;

/**
 *
 * @author 20201PF.CC0165
 */
public class ClientThread implements Runnable {
    private BlockingQueue<Song> songs;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy "
            + "HH:mm:ss.SSSSSS"
    );
    
    public ClientThread(BlockingQueue<Song> songs) {
        this.songs = songs;
    }
    
    @Override
    public void run() {
        try {
            int menuOption = 0;
            int songIdx = 1;
            int menuIdx = 0;
            List<String> songsName = new ArrayList<>();
            
            do {
                System.out.println("Listhread - Selecione uma das opcoes abaixo: ");
                System.out.println("0 - Sair");
                
                URL url = Main.class.getResource("songs.txt");
                Scanner scanner = new Scanner(new File(url.getPath()));
                Scanner sc = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String song = scanner.nextLine().split("-")[0];
                    System.out.println(songIdx + " - Ouvir " + song);
                    
                    if(menuIdx == 0) {
                        songsName.add(song.trim());
                    }
                    
                    songIdx ++;
                }
                songIdx = 1;
                System.out.println("Sua escolha: ");
                menuOption = sc.nextInt();
                
                if(menuOption == 0) {
                    System.out.println(
                            "Menu fechado com sucesso! Voce ainda pode conferir"
                            + " as notificacoes das musicas que estao sendo "
                            + "lancadas se deixar essa janela aberta."
                    );
                }
                    
                else {
                    String currentSong = songsName.get(menuOption - 1);
                    
                    Object[] untypedSongsArray = songs.toArray();
                    String songNameWithoutBand = currentSong.split(":")[1].trim();
                    Song songInBqueue = findByName(
                            untypedSongsArray, songNameWithoutBand
                    );
                    
                    if(songInBqueue != null) {
                        System.out.println("Ouvindo a musica '" + 
                            songInBqueue.getBand().getName() + " - " + 
                            songInBqueue.getName() + "'"
                        );
                        System.out.println("Letra:");
                        System.out.println(songInBqueue.getLyrics().replaceAll("(\\\\r)?\\\\n", System.getProperty("line.separator")));
                    } else {
                        System.out.println("A musica ainda nao foi lancada! "
                                + "Nossa equipe ira notificar quando a banda "
                                + "disponibilizar a musica!"
                        );
                    }
                    
                    System.out.println(
                            "Pressione ENTER para ouvir outra " + 
                            "musica ou sair do programa!"
                    );
                    System.in.read();
                }
                
                menuIdx ++;
            } while (menuOption != 0);
        } catch (IOException ex) {
            System.out.println(
                "O sistema nao conseguiu processar as informacoes!"
            );
        }
    }
    
    public static Song findByName(Object arr[], String t) {
        if (arr == null)
            return null;
 
        int len = arr.length;
        int i = 0;
 
        while (i < len) {
            Song currentSongSearch = (Song) arr[i];
            if (currentSongSearch.getName().trim().equalsIgnoreCase(t.trim()))
                return currentSongSearch;
            else
                i = i + 1;
        }
        return null;
    }
    
}
