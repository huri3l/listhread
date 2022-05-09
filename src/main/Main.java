/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import br.edu.ifsul.controller.BandThread;
import br.edu.ifsul.controller.ClientThread;
import br.edu.ifsul.model.Band;
import br.edu.ifsul.model.Client;
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
        
        createClient(bqueue);
        
        createBands(bqueue);
    }
    
    public static void createClient(BlockingQueue<Song> bqueue) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Ola! Seja bem-vindo ao Listhread, o servico mais "
                + "rapido de entrega de musica!"
        );
        System.out.println("Para que possa desfrutar do "
                + "nosso servico, precisamos de alguns dados. " +
                "Esses dados serao usados para lhe notificar sobre "
                + "o lancamento de novas musicas.");
        System.out.println("Por favor, nos informe seu nome: ");
        String name = sc.nextLine();
        System.out.println("Maravilha " + name + "! Agora, nos informe "
                + "seu e-mail (Exemplo: email@gmail.com): "
        );
        String email = sc.nextLine();
        
        Client client = new Client(name, email);
        System.out.println("Cadastro efetuado com sucesso!");
        
        ClientThread consumer = new ClientThread(bqueue);
        
        Thread cThread = new Thread(consumer);
        cThread.setName(name);
        cThread.start();
    }
    
    public static void createBands(BlockingQueue<Song> bqueue) {  
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
