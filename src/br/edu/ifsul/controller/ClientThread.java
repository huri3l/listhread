package br.edu.ifsul.controller;

import br.edu.ifsul.model.Client;
import br.edu.ifsul.model.Song;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import main.Main;

/**
 * ClientThread is where the Client (Consumer) will be able to listen (consume)
 * the songs produced by the Bands. These songs are not taken from the
 * BlockingQueue, since the client might wish to listen to that same song again
 * 
 * @see br.edu.ifsul.controller.BandThread to check how songs are created
 * @author Huriel Ferreira Lopes
 */
public class ClientThread implements Runnable {
    private final BlockingQueue<Song> songs;
    private Client client;
    
    public ClientThread(BlockingQueue<Song> songs) {
        this.songs = songs;
    }
    
    @Override
    public void run() {
        try {
            optionMenu();
        } catch (IOException ex) {
            System.out.println(
                "O sistema nao conseguiu processar as informacoes!"
            );
        }
    }
    
    /**
     * This method is responsible for the menu that the user will receive to
     * choose his actions
     * 
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @since 1.0
     */
    public void optionMenu() throws FileNotFoundException, IOException {
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

                if (menuIdx == 0) {
                    songsName.add(song.trim());
                }

                songIdx++;
            }
            songIdx = 1;
            System.out.println("Sua escolha: ");
            menuOption = sc.nextInt();

            if (menuOption == 0) {
                System.out.println(
                        "Menu fechado com sucesso! Voce ainda pode conferir"
                        + " as notificacoes das musicas que estao sendo "
                        + "lancadas se deixar essa janela aberta."
                );
            } else {
                playSong(songsName, menuOption, songs);
            }

            menuIdx++;
        } while (menuOption != 0);
    }
    
    /**
     * This method is responsible for playing the song that the user chose
     *
     * @param songsName is a list of the songs the user can listen to, launched or not
     * @param menuOption is the music that the client chose
     * @param songs is the BlockingQueue that the client will consume
     * @throws java.io.IOException
     * @since 1.1
     */
    public static void playSong(
            List<String> songsName, 
            int menuOption, 
            BlockingQueue<Song> songs
    ) throws IOException {
        String currentSong = songsName.get(menuOption - 1);

        Object[] untypedSongsArray = songs.toArray();
        String songNameWithoutBand = currentSong.split(":")[1].trim();
        Song songInBqueue = findByName(
                untypedSongsArray, songNameWithoutBand
        );

        if (songInBqueue != null) {
            System.out.println("Ouvindo a musica '"
                    + songInBqueue.getBand().getName() + " - "
                    + songInBqueue.getName() + "'"
            );
            System.out.println("Letra:");
            System.out.println(
                    songInBqueue.getLyrics()
                            .replaceAll("(\\\\r)?\\\\n",
                                    System.getProperty("line.separator")
                            ));
        } else {
            System.out.println("A musica ainda nao foi lancada! "
                    + "Nossa equipe ira notificar quando a banda "
                    + "disponibilizar a musica!"
            );
        }

        System.out.println(
                "Pressione ENTER para ouvir outra "
                + "musica ou sair do programa!"
        );
        System.in.read();
    }
    
    /**
     * This method is responsible for finding the song the user chose
     * in the BlockingQueue copy array
     *
     * @param songsCopyArray is the array containing the songs in the BlockingQueue
     * @param selectedSongName is the song that the user has selected in the menu
     * @return a Song object with all the song informations
     * @see br.edu.ifsul.model.Song to see all the informations that a song may have
     * @since 1.1
     */
    public static Song findByName(Object songsCopyArray[], String selectedSongName) {
        if (songsCopyArray == null)
            return null;
 
        int len = songsCopyArray.length;
        int i = 0;
 
        while (i < len) {
            Song currentSongSearch = (Song) songsCopyArray[i];
            if (currentSongSearch.getName().trim().equalsIgnoreCase(selectedSongName.trim()))
                return currentSongSearch;
            else
                i = i + 1;
        }
        return null;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
