package package1;

import java.util.Scanner;



/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 2
 */
public class Player {
    static String userInput;
    static boolean keepGoing;
    static Scanner keyBoard;
    static SongLinkedList songLinkedList;
    static Song song;


    /**
     * Start  program.
     */
    public static void main(String[] args){
        userInput = "";
        keepGoing = true;
        keyBoard = new Scanner(System.in);
        songLinkedList = new SongLinkedList();
        while(keepGoing)
        {
            runMainMenu();
            processMainMenuInput();
        }

    }

    /**
     * RunMainMenu, display the selections and get
     * them.
     */

    private static void runMainMenu(){
        System.out.println("(A)      Add Song to Playlist");
        System.out.println("(F)      Go to Next Song");
        System.out.println("(B)      Go to Previous Song");
        System.out.println("(R)      Remove Song from Playlist");
        System.out.println("(L)      Play a Song");
        System.out.println("(C)      Clear the Playlist");
        System.out.println("(S)      Shuffle Playlist");
        System.out.println("(Z)      Random Song");
        System.out.println("(P)      Print Playlist");
        System.out.println("(T)      Get the total amount of songs in the playlist");
        System.out.println("(Q)      Exit the playlist");
        userInput = keyBoard.nextLine();

    }
    /**
     * Process the feedback
     */
    private static void processMainMenuInput(){
        if (userInput.toLowerCase().equals("a")) {
            song = new Song();
            System.out.println("Enter song title: ");
            userInput = keyBoard.nextLine();
            song.setSongName(userInput);
            System.out.println("Enter artist(s) of the song: ");
            userInput = keyBoard.nextLine();
            song.setArtisit(userInput);
            System.out.println("Enter album: ");
            userInput = keyBoard.nextLine();
            song.setAlbum(userInput);
            System.out.println("Enter length (in seconds): ");
            userInput = keyBoard.nextLine();
            try{
                song.setLength(Integer.parseInt(userInput));
                songLinkedList.insertAfterCursor(song);
                System.out.println("'"+song.getSongName()+"' by "+song.getArtisit()+" is added to your playlist.");
            }catch(NumberFormatException e){
                System.out.println("THIS IS NOT A NUMBER DUDE");
            }

        } else if(userInput.toLowerCase().equals("f")){
            songLinkedList.cursorForwards();
        }else if(userInput.toLowerCase().equals("b")){
            songLinkedList.cursorBackwards();
        }else if(userInput.toLowerCase().equals("r")){
            song = songLinkedList.removeCursor();
            if(song!=null){
                System.out.println("'"+song.getSongName()+"'"+ " by "+song.getArtisit()+" was removed from the playlist.");
            }
        }else if(userInput.toLowerCase().equals("l")){
            System.out.println("Enter name of song to play: ");
            userInput = keyBoard.nextLine();
            songLinkedList.play(userInput);
        }else if(userInput.toLowerCase().equals("c")){
            songLinkedList.deleteAll();
        }else if(userInput.toLowerCase().equals("s")){
            songLinkedList.shuffle();
        }else if(userInput.toLowerCase().equals("z")){
            song = songLinkedList.random();
            if(song!=null){
                System.out.println("Playing a random song...\n"+"'"+song.getSongName()+"'"+" by "+song.getArtisit()+" is now playing.");
                songLinkedList.play(song.getSongName());
            }
        }else if(userInput.toLowerCase().equals("p")){
            songLinkedList.printPlaylist();
            System.out.println();
        }else if(userInput.toLowerCase().equals("t")){
            System.out.println("Your playlist contains "+songLinkedList.getSize()+" songs.");
        }else if ((userInput.toLowerCase().equals("q"))){
            keepGoing = false;
            System.out.println("Bye~");
        }

    }
}
