
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 6
 */
public class TextAnalyzer {
    static boolean keepReading;
    static Passage passage;
    static String userInput;
    static Scanner keyBoard;
    static ArrayList<Passage> passages;
    /**
     * Start  program.
     */
    public static void main(String[] args){
        passages = new ArrayList<>();
        keepReading = true;
        while (keepReading) {
            System.out.println("Enter the directory of a folder of text files: ");
            keyBoard = new Scanner(System.in);
            userInput = keyBoard.nextLine();
            try {
                File folder = new File(userInput);
                File[] directoryOfFiles = folder.listFiles();
                for (File i : directoryOfFiles) {
                    passage = new Passage(i.getName(), i);
                    passages.add(passage);
                }
                System.out.println("Text (title)                                                 |Similarities (%)");
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                FrequencyTable.buildTable(passages);
                keepReading = false;

            } catch (NullPointerException o) {
                keepReading = true;
                System.out.println("Could not find the directory.");
            }

        }
    }

}
