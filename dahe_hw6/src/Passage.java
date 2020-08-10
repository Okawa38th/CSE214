import java.io.*;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 6
 */

public class Passage {
    String title;
    int wordCount;
    String data;
    Hashtable<String, Double> similarTitles;
    Hashtable<String, Integer>stopWords;
    Hashtable<String, Integer>wordsOcc;
    String[] stringBuffer;
    static FrequencyList frequencyList;
    static FrequencyTable frequencyTable;
    FileInputStream fis;
    InputStreamReader inStream;
    BufferedReader stdin;

    /**
     * Constructor
     * Brief: Create a Passage
     * Postcondition:
     *  A Passage object with no input is created.
     *
     */
    public Passage(String title, File file){
        this.title = title;
        stopWords = new Hashtable<>();
        wordsOcc = new Hashtable<>();
        similarTitles = new Hashtable<>();
        try{
            fis = new FileInputStream("StopWords.txt");
            inStream = new InputStreamReader(fis);
            stdin = new BufferedReader(inStream);
            while((data=stdin.readLine())!=null){
                setStopWords();
            }
            parseFile(file);
        }catch (FileNotFoundException e){
            System.out.println("did not find this file.");
        }catch (IOException  o){
            System.out.println("Something wrong..");
        }


    }

    /**
     * Set up these "stop words" in to a Hashtable.
     */

    public void setStopWords(){
        stopWords.put(data,0);
    }

    /**
     * Brief
     *      Reads the given text file and counts each word’s occurrence,
     *      excluding stop words, and inserts them into the table
     * @param file
     *      File Object
     */

    public void parseFile(File file){
        try{
            fis = new FileInputStream(file);
            inStream = new InputStreamReader(fis);
            stdin = new BufferedReader(inStream);
            while((data=stdin.readLine())!=null){
                if (data.length()!=0) {
                    processThisLine();
                }
            }
            stdin.close();
            inStream.close();

        }catch (IOException e){
            System.out.println("SomeThing wrong with the file part.");
        }


    }

    /**
     * Read each line from the file and split them word by word
     * then put them in to the hashtable.
     */
    public  void processThisLine(){
        data = data.replaceAll("\'","");
        data = data.replaceAll("\\d+"," ");
        data = data.replaceAll("[\\pP\\p{Punct}]"," ");
        data = data.replaceAll(" +"," ").trim();
        data = data.toLowerCase();
        stringBuffer = data.split(" ");
        wordCount += stringBuffer.length;
        for (int i =0; i<stringBuffer.length;i++){
            if (!stopWords.containsKey(stringBuffer[i])){
                if (wordsOcc.containsKey(stringBuffer[i])){
                    int j = wordsOcc.get(stringBuffer[i])+1;
                    wordsOcc.put(stringBuffer[i],j);
                }else{
                    wordsOcc.put(stringBuffer[i],1);
                }
            }
        }




    }

    /**
     *
     * @param word
     *  the word
     * @return
     * the relative frequency of the given word in this Passage (occurrences/wordCount)
     */

    public double getWordFrequency(String word){
        if (!wordsOcc.containsKey(word)){
            System.out.println("You seem don't have this word in the passage.");
            return 0.0;
        }else{
            double d = wordsOcc.get(word)/(double)wordCount;
            int k = wordsOcc.get(word);
            BigDecimal h = new BigDecimal(d);
            double bd = h.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
            return   bd;
        }
    }

    /**
     *
     * @return
     *  a Set containing all of the words in this Passage
     */
    public Set<String> getWords(){
        Enumeration names = wordsOcc.keys();
        Set<String> set = new HashSet<>();
        while(names.hasMoreElements()){
            set.add((String)names.nextElement());
        }
        return set;

    }

    /**
     *
     * @return
     *  string of similar titles and their percentage similarity
     */

    @Override
    public String toString() {
        return "Passage{" +
                "title='" + title + '\'' +
                ", wordCount=" + wordCount +
                ", similarTitles=" + similarTitles +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public Hashtable<String, Double> getSimilarTitles() {
        return similarTitles;
    }

    public void setSimilarTitles(Hashtable<String, Double> similarTitles) {
        this.similarTitles = similarTitles;
    }
}
