
/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 4
 */

public class Bigram {
    private char first;
    private char second;
    private Bigram next;
    private Bigram prev;

    /**
     * Constructor
     * Return an instance of Bigram with no input
     * Postcondition:
     *  A Bigram object with no input is created.
     *
     */
    public Bigram(){}

    public Bigram getPrev() {
        return prev;
    }

    public void setPrev(Bigram prev) {
        this.prev = prev;
    }

    public Bigram getNext() {
        return next;
    }

    public void setNext(Bigram next) {
        this.next = next;
    }

    public char getFirst() {
        return first;
    }

    public void setFirst(char first) {
        this.first = first;
    }

    public char getSecond() {
        return second;
    }

    public void setSecond(char second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "{"+getFirst()+", "+getSecond()+"}";
    }
}
