import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test {
    static FrequencyTable frequencyTable;
    static Passage passage;
    static String userInput;
    static Scanner keyBoard;
    static ArrayList<Passage> passages;
    static FrequencyList frequencyList;
    static String[] names;

    public static void main(String[] args){
        System.out.println("Enter the directory of a folder of text files: ");
        keyBoard = new Scanner(System.in);
        userInput = keyBoard.nextLine();
        passages = new ArrayList<>();
        try {

            File folder = new File(userInput);
            File[] directoryOfFiles = folder.listFiles();
            for (File i : directoryOfFiles) {
                passage = new Passage(i.getName(), i);
                passages.add(passage);
            }
           // FrequencyTable.buildTable(passages);
            for (int i = 0; i < passages.size(); i++) {
                names = new String[passages.get(i).getWords().size()];//which passage is comparing to others
                passages.get(i).getWords().toArray(names);//the words which is being compare
                for (int j = 0; j < passages.size(); j++) {
                    if (i == passages.size() - 1 && j == passages.size() - 1) {
                        break;
                    }else{
                        if (i != j) {
                            for (int k = 0; k < names.length; k++) {
                                frequencyList = new FrequencyList(names[k], passages);
                                System.out.println(frequencyList.getFrequency(passages.get(i)));
                            }

                        }
                    }
                }
            }

        }catch (NullPointerException o) {
            System.out.println("Could not find the directory.");
        }


    }
}
