import java.util.Arrays;


/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 4
 */

public class KeyTable {
    private char[][]key;
    private static String letter;
    private static KeyTable keyTable;

    /**
     * Constructor
     * Return an instance of keyTable with a char matrix 5x5
     * Postcondition:
     *  A keyTable object with no input is created.
     *
     */
    public KeyTable(){
        key =new char[5][5];
        letter = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    }

    /**
     *   Builds a new KeyTable object from the provided string and returns it
     *  Preconditions:
     *  keyphrase is not null
     *
     * @param keyphrase
     *  The String to use as the key
     * @return
     *  The new KeyTable object.
     * @throws IllegalArgumentException
     *  Thrown if keyphrase is null.
     */

    public static KeyTable buildFromString(String keyphrase)throws IllegalArgumentException {
        if (keyphrase.replaceAll(" +","").length()==0){
                throw new IllegalArgumentException("it's empty");
        }else{
            keyTable = new KeyTable();
            keyphrase = keyphrase.toUpperCase().replaceAll(" +","").replaceAll("\\p{Punct}","");
            keyphrase = keyphrase.replaceAll("J","I");
            keyphrase = keyphrase.toUpperCase().replaceAll("\\d+","").replaceAll("\\p{Punct}","");
            for (int i =0; i<5;i++){
                for (int j = 0; j <5;j++){
                    if(keyphrase.length()>1){
                        keyTable.key[i][j]=keyphrase.charAt(0);
                        deleteChar(keyphrase);
                        keyphrase = keyphrase.substring(1);
                        keyphrase = keyphrase.replaceAll(String.valueOf(keyTable.key[i][j]),"");
                    }else if(keyphrase.length()==1){
                        keyTable.key[i][j]=keyphrase.charAt(0);
                        deleteChar(keyphrase);
                        keyphrase = "";
                    }else{
                        keyTable.key[i][j]=letter.charAt(0);
                        letter = letter.substring(1);
                    }

                }
            }
            return keyTable;
        }

    }

    /**
     *  Delete the same letters in the Alphabet
     * @param keys
     * The String to fill up the rest key
     */
    private static void deleteChar(String keys){
        if(!String.valueOf(keys.charAt(0)).equals("J")){
            letter = letter.replaceAll(String.valueOf(keys.charAt(0)),"");
        }
    }

    /**
     *  Returns the key matrix.
     * @return
     * The key matrix contained within this KeyTable.
     */
    public char[][] getKeyTable(){
        return key;
    }

    /**
     *  Returns the key matrix.
     * @param c
     *  The character to locate within the key matrix
     * @return
     *  The index of the row in which c occurs.
     * @throws IllegalArgumentException
     *  Thrown if c is not a valid letter in the key matrix.
     */
    public int findRow(char c)throws IllegalArgumentException{
        for (int i =0;i<5;i++) {
            for (int j = 0; j < 5; j++) {
                if (key[i][j] == c) {
                    return j;
                }
                if (i ==4 & j ==4){
                    throw new IllegalArgumentException("It is not a valid letter in the key matrix.");
                }
            }
        }
        return 0;
    }

    /**
     *  Returns the column in which c occurs.
     * @param c
     * The character to locate within the key matrix
     * @return
     * The index of the column in which c occurs.
     * @throws IllegalArgumentException
     * Thrown if c is not a valid letter in the key matrix.
     */
    public int findCol(char c)throws IllegalArgumentException{
        for (int i =0;i<5;i++) {
            for (int j = 0; j < 5; j++) {
                if (key[i][j] == c) {
                    return j;
                }
                if (i ==4 & j ==4){
                    throw new IllegalArgumentException("It is not a valid letter in the key matrix.");
                }
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "KeyTable{" +
                "key=" + Arrays.toString(key) +
                '}';
    }
}
