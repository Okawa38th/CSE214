import java.util.Scanner;


/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 4
 */
public class PlayfairEncryptionEngine {
    static String userInput;
    static boolean keepGoing;
    static boolean notEmpty;
    static Scanner keyBoard;
    static String key;
    static KeyTable keyTable;
    static Phrase phrase;

    /**
     * Start  program.
     */

    public static void main(String[] args){
        userInput = "";
        keepGoing = true;
        keyBoard = new Scanner(System.in);
        makeAKey();
        while(keepGoing) {
            runMainMenu();
            processMainMenuInput();
        }
    }

    /**
     *  Enter a key string until it works.
     */
    private static void makeAKey(){
        notEmpty = true;
        keyTable = new KeyTable();
        while(notEmpty){
            System.out.println("Enter key phrase: ");
            key = keyBoard.nextLine();
            try{
                keyTable = KeyTable.buildFromString(key);
                notEmpty = false;
                System.out.println("Generation success!");
            }catch(IllegalArgumentException e){
                System.out.println("Enter Something!!");
            }
        }
    }

    /**
     * RunMainMenu, display the selections and get
     * them.
     */
    private static void runMainMenu() {
        System.out.println("Menu:");
        System.out.println("(CK) - Change key");
        System.out.println("(PK) - Print key");
        System.out.println("(EN) - Encrypt");
        System.out.println("(DE) - Decrypt");
        System.out.println("(Q)  - Quit");
        System.out.print("Please select an option: ");
        userInput = keyBoard.nextLine();
    }
    /**
     * Process the feedback
     */
    private static void processMainMenuInput(){
        if (userInput.toUpperCase().equals("CK")){
            makeAKey();
        }else if(userInput.toUpperCase().equals("PK")){
            printAKey();
        }else if(userInput.toUpperCase().equals("EN")){
            encrypt();
        }else if(userInput.toUpperCase().equals("DE")){
            decrypt();
        }else if(userInput.toUpperCase().equals("Q")){
            System.out.print("~Bye");
            keepGoing = false;
        }

    }

    /**
     * Print the key table
     */
    private static void printAKey(){
        System.out.println();
        for (int i = 0; i <5;i++){
            for (int j =0; j<5;j++){
                System.out.print(keyTable.getKeyTable()[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * A method to process encrypt
     */

    private static void encrypt(){
        System.out.print("Please enter a phrase to encrypt:");
        userInput = keyBoard.nextLine();
        phrase = Phrase.buildPhraseFromStringforEnc(userInput);
        phrase = phrase.encrypt(keyTable);
        printThis(phrase);

    }
    /**
     * A method to process decrypt
     */
    private static void decrypt(){
        System.out.print("Please enter a phrase to decrypt:");
        userInput = keyBoard.nextLine();
        phrase = Phrase.buildPhraseFromStringforEnc(userInput);
        phrase = phrase.decrypt(keyTable);
        printThis(phrase);
    }

    /**
     * Print the phrase
     * @param phrase1
     * The phrase object need to print
     */
    private static void printThis(Phrase phrase1){
        while (phrase1.size()>0){
            Bigram a = phrase1.dequeue();
            System.out.print(a.getFirst());
            System.out.print(a.getSecond());
        }
        System.out.println();
    }

}
