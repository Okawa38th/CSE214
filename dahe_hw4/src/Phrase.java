
/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 4
 */


public class Phrase {
    private Bigram top;
    private Bigram rear;
    private Bigram tempBigram;
    private int size;
    private static Bigram bigram;
    private static Phrase phrase;
    private static Bigram pair;
    private Bigram le1;
    private Bigram le2;
    private Phrase enPhrase;
    private Bigram enBigram;
    private int a;
    private int b;
    private int c;
    private int d;
    /**
     * Constructor
     * Return an instance of Phrase with no input
     * Postcondition:
     *  A Phrase object with no input is created.
     *
     */


    public Phrase(){
        top = null;
        rear = null;

    }

    /**
     * adds a new Bigram to the end of the Phrase
     * @param b
     * The Bigram that will add to the Phrase.
     */
    public void enqueue(Bigram b){
        if (size == 0){
            top = b;
            rear = b;
            size++;
        }
        else{
            b.setPrev(rear);
            rear.setNext(b);
            rear = b;
            size++;
        }


    }

    /**
     *  removes and returns the first Bigram in the Phrase
     * @return
     *  The Bigram that has removed
     */
    public Bigram dequeue(){
        if(top == rear){
            tempBigram = top;
            top = null;
            rear = null;
            size--;
            return tempBigram;
        }
        if (top==null){
            return null;
        }else{
            tempBigram = top;
            top.getNext().setPrev(null);
            top = top.getNext();
            size--;
            return tempBigram;
        }
    }

    /**
     *  returns (without removing) the first Bigram in the phrase
     * @return
     *  The first Bigram in the phrase
     */
    public Bigram peek(){
        return top;
    }

    /**
     *  returns the number of Bigrams in the Phrase
     * @return
     *  An integer
     */
    public int size(){
        return size;
    }

    /**
     *
     * @return
     * returns true if the queue is empty, false otherwise
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     *  Builds and returns a new Phrase object, which is a queue containing Bigram representing s
     * Switch all the "J" letter to "I"
     * @param s
     *  the String to represent as a Bigram queue.
     * @return
     * The new Phrase object which contains a queue of Bigram objects representing s.
     */

    public static Phrase buildPhraseFromStringforEnc(String s){
        s = s.toUpperCase().replaceAll("\\d+","").replaceAll("\\p{Punct}","").replaceAll("J","I");
        s = s.replaceAll(" +","");

        phrase = new Phrase();
        while(s.length()>0){
            bigram = new Bigram();
            if(s.length()==1){
                bigram.setFirst(s.charAt(0));
                bigram.setSecond('X');
                phrase.enqueue(bigram);
                break;
            }
            if (s.charAt(0)!=s.charAt(1)){
                bigram.setFirst(s.charAt(0));
                bigram.setSecond(s.charAt(1));
                s = s.substring(2);
                phrase.enqueue(bigram);
            }else{
                bigram.setFirst(s.charAt(0));
                bigram.setSecond('X');
                s = s.substring(1);
                phrase.enqueue(bigram);
            }
        }
        return phrase;
    }

    /**
     *  Encrypts this Phrase, storing the encrypted bigrams in a new Phrase queue object and returning it.
     * Preconditions:
     *  key is not null.
     * @param key
     * the KeyTable to use to encrypt this Phrase
     * @return
     *  The new Phrase object which contains a queue of Bigram objects representing the encrypted version of this Phrase.
     */
    public Phrase encrypt(KeyTable key){
        le1 = new Bigram();
        le2 = new Bigram();
        enPhrase = new Phrase();
        while(phrase.size>0){
            pair = phrase.dequeue();
            for (int i =0; i<5;i++){
                for (int j =0; j<5; j++){
                    if (key.getKeyTable()[i][j]==pair.getFirst()){
                        le1.setFirst((char)(i+'0'));
                        le1.setSecond((char)(j+'0'));
                    }
                    if (key.getKeyTable()[i][j]==pair.getSecond()){
                        le2.setFirst((char)(i+'0'));
                        le2.setSecond((char)(j+'0'));
                    }
                }
            }
            a = Character.getNumericValue(le1.getFirst());
            b = Character.getNumericValue(le1.getSecond());
            c = Character.getNumericValue(le2.getFirst());
            d = Character.getNumericValue(le2.getSecond());
            enBigram = new Bigram();
            if(le1.getFirst()==le2.getFirst()){
                if(le1.getSecond()=='4'){
                    enBigram.setFirst(key.getKeyTable()[a][0]);
                }else{
                    enBigram.setFirst(key.getKeyTable()[a][b+1]);
                }
                if (le2.getSecond()=='4'){
                    enBigram.setSecond(key.getKeyTable()[a][0]);
                }else{
                    enBigram.setSecond(key.getKeyTable()[a][d+1]);
                }
            }else if(le1.getSecond()==le2.getSecond()){
                if(le1.getFirst()=='4'){
                    enBigram.setFirst(key.getKeyTable()[0][a]);
                }else{
                    enBigram.setFirst(key.getKeyTable()[a+1][b]);
                }
                if (le2.getFirst()=='4'){
                    enBigram.setSecond(key.getKeyTable()[0][b]);
                }else{
                    enBigram.setSecond(key.getKeyTable()[c+1][d]);
                }
            }else{
                enBigram.setFirst(key.getKeyTable()[a][d]);
                enBigram.setSecond(key.getKeyTable()[c][b]);
            }
            enPhrase.enqueue(enBigram);
        }


        return  enPhrase;
    }

    /**
     *  Decrypts this Phrase, storing the decypted bigrams in a new Phrase queue object and returning it.
     * @param key
     * The KeyTable to use to decrypt this Phrase
     * @return
     * The new Phrase object which contains a queue of Bigram objects representing the decypted version of this Phrase.
     */
    public Phrase decrypt(KeyTable key){
        le1 = new Bigram();
        le2 = new Bigram();
        enPhrase = new Phrase();
        while(phrase.size>0){
            pair = phrase.dequeue();
            for (int i =0; i<5;i++){
                for (int j =0; j<5; j++){
                    if (key.getKeyTable()[i][j]==pair.getFirst()){
                        le1.setFirst((char)(i+'0'));
                        le1.setSecond((char)(j+'0'));
                    }
                    if (key.getKeyTable()[i][j]==pair.getSecond()){
                        le2.setFirst((char)(i+'0'));
                        le2.setSecond((char)(j+'0'));
                    }
                }
            }a = Character.getNumericValue(le1.getFirst());
            b = Character.getNumericValue(le1.getSecond());
            c = Character.getNumericValue(le2.getFirst());
            d = Character.getNumericValue(le2.getSecond());
            enBigram = new Bigram();
            if(le1.getFirst()==le2.getFirst()){
                if(le1.getSecond()=='0'){
                    enBigram.setFirst(key.getKeyTable()[a][4]);
                }else{
                    enBigram.setFirst(key.getKeyTable()[a][b-1]);
                }
                if (le2.getSecond()=='0'){
                    enBigram.setSecond(key.getKeyTable()[a][4]);
                }else{
                    enBigram.setSecond(key.getKeyTable()[a][d-1]);
                }
            }else if(le1.getSecond()==le2.getSecond()){
                if(le1.getFirst()=='0'){
                    enBigram.setFirst(key.getKeyTable()[4][a]);
                }else{
                    enBigram.setFirst(key.getKeyTable()[a-1][b]);
                }
                if (le2.getFirst()=='0'){
                    enBigram.setSecond(key.getKeyTable()[4][b]);
                }else{
                    enBigram.setSecond(key.getKeyTable()[c-1][d]);
                }
            }else{
                enBigram.setFirst(key.getKeyTable()[a][d]);
                enBigram.setSecond(key.getKeyTable()[c][b]);
            }
            enPhrase.enqueue(enBigram);
        }


        return  enPhrase;
    }

}
