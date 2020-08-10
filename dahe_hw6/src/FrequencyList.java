import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 6
 */
public class FrequencyList {
    String word;
    ArrayList<Double> frequencies;
    Hashtable<String,Integer> passageIndices;
    ArrayList<Passage> passages;

    /**
     * Constructor
     * Brief: Create a FrequencyList
     * @param word
     *  the String word
     * @param passages
     *  a collection containing the Passage objects to be parsed through
     *  Postcondition:
     *      *  A FrequencyList object with a word is created.
     */
    public FrequencyList(String word, ArrayList<Passage> passages){
        this.word = word;
        this.passages = passages;
        passageIndices = new Hashtable<>();
        frequencies = new ArrayList<>();
        for (int i =0; i<passages.size();i++){
            if (passages.get(i).wordsOcc.containsKey(word)){
                frequencies.add(i,passages.get(i).getWordFrequency(word));
                double a = frequencies.get(i);
            }else{
                frequencies.add(i,0.0);
            }
            passageIndices.put(passages.get(i).getTitle(),i);


        }

    }

    /**
     * Brief:
     *  Adds a Passage to this FrequencyList
     *  Postconditions:
     *  passageIndices now contains p’s title which maps to the next available index in this ArrayList
     *  The ArrayList now contains an additional index containing the frequency of “word” in the Passage
     * @param p
     *  Passage need to be add
     */
    public void addPassage(Passage p){
        passages.add(p);
        frequencies.add(p.getWordFrequency(word));

    }

    /**
     *
     * @param p
     * Passage object
     * @return
     *  the frequency of “word” in the given Passage, 0 if the Passage is not contained in this FrequencyList
     */
    public double getFrequency(Passage p){
        return frequencies.get(passages.indexOf(p));
    }
}
