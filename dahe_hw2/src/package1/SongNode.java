package package1;


/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 2
 */
public class SongNode {
    private SongNode prev;
    private SongNode next;
    private Song data;

    /**
     * Constructor
     * Return an instance of SongNode with input Song
     * Postcondition:
     *  An SongNode object with Song object input is created.
     *
     */
    public SongNode(Song song){
        data = song;
    }


    public SongNode getPrev() {
        return prev;
    }

    public void setPrev(SongNode prev) {
        this.prev = prev;
    }

    public SongNode getNext() {
        return next;
    }

    public void setNext(SongNode next) {
        this.next = next;
    }

    public Song getData() {
        return data;
    }

    public void setData(Song data) {
        this.data = data;
    }
}

