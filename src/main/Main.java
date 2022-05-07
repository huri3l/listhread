/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import br.edu.ifsul.controller.BandThread;
import br.edu.ifsul.model.Band;
import br.edu.ifsul.model.Song;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author 20201PF.CC0165
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BlockingQueue<Song> bqueue = new ArrayBlockingQueue<>(73);
        
        createBands(bqueue);
    }
    
    public static void createBands(BlockingQueue<Song> bqueue) throws IOException {  
        try {
            URL url = Main.class.getResource("bands.txt");
            Scanner scanner = new Scanner(new File(url.getPath()));
            while (scanner.hasNextLine()) {                
                String[] rawBandData = scanner.nextLine().split("-");
                String bandName = rawBandData[0].trim();

                Band current_band = new Band(bandName);
                String[] members = rawBandData[1].split(";");
                current_band.addMembers(members);

                BandThread producer = new BandThread(bqueue);
                producer.setBand(current_band);
           
                Thread pThread = new Thread(producer);
                pThread.setName(bandName);
                pThread.start();
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("O sistema nao conseguiu carregar os dados das bandas!");
        }
    }
}
