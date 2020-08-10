
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 6
 */
public class FrequencyTable {
    static FrequencyTable frequencyTable;
    static FrequencyList frequencyList;
    static double similarity;
    static double sumOfProduct;
    static double sqrtOfU;
    static double sqrtOfV;
    static ArrayList<Passage> thisPassages;
    static BigDecimal freq;
    static String[] names;

    /**
     * Constructor
     *  Brief:
     * Iterates through passages and constructs FrequencyLists with each Passage’s appropriate word frequencies
     *
     * Postconditions:
     *      A  new FrequencyTable object has been constructed, containing a Collection of FrequencyLists with information from all Passages
     * @param passages
     *  a collection containing the Passage objects to be parsed through
     *
     *
     * @return
     *  The FrequencyTable constructed from each Passage in passages.
     */
    public static FrequencyTable buildTable(ArrayList<Passage> passages) {
        thisPassages = passages;
        sumOfProduct = 0.0;
        sqrtOfU = 0.0;
        sqrtOfV = 0.0;
        frequencyTable = new FrequencyTable();
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
                            freq = new BigDecimal(cosineSimilarity(passages.get(i), passages.get(j)));
                        }
                        passages.get(i).similarTitles.put(passages.get(j).getTitle(),freq.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
                     //    System.out.println("Similarity of " + passages.get(i).getTitle() + " and " + passages.get(j).getTitle() + " is " +freq.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                }

            }
            System.out.println(passages.get(i).getTitle());
            Enumeration names = passages.get(i).similarTitles.keys();
            while(names.hasMoreElements()) {
                String a = (String)names.nextElement();
                System.out.printf("%125s", a +"("+(int)Math.round(passages.get(i).similarTitles.get(a)*100)+"%)"+'\n');
            }
        }
        return frequencyTable;
    }

    /**
     * Brief:
     *  Calculates the similarity between two Passage objects using the formula above and modifies their similarTitles accordingly
     * @param passage1
     *  passage 1
     * @param passage2
     *  passage 2
     * @return
     *   The similarity of two passage
     */
    public static double cosineSimilarity(Passage passage1, Passage passage2){
        sumOfProduct += (frequencyList.frequencies.get(frequencyList.passageIndices.get(passage1.getTitle())) *
               frequencyList.frequencies.get(frequencyList.passageIndices.get(passage2.getTitle()))) ;
       sqrtOfU += Math.pow( frequencyList.frequencies.get(frequencyList.passageIndices.get(passage1.getTitle())),2);
       sqrtOfV += Math.pow( frequencyList.frequencies.get(frequencyList.passageIndices.get(passage2.getTitle())),2);
        if ((Math.pow(sqrtOfU,1.0/2) * Math.pow(sqrtOfV,1.0/2)) !=0){
            return sumOfProduct / (Math.pow(sqrtOfU,1.0/2) * Math.pow(sqrtOfV,1.0/2));
        }else{
            return 0;
       }
    }

    /**
     * @param word
     * word
     * @param p
     * passage
     * @return
     * the frequency of the given word in the given Passage.
     */
    public double getFrequency(String word, Passage p){
        frequencyList = new FrequencyList(word,thisPassages);

        return  frequencyList.getFrequency(p);
    }

}
