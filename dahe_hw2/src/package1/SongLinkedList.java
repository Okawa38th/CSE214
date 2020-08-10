package package1;


import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;
import java.io.File;


/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 2
 */
public class SongLinkedList {
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size;
    private SongNode songNode;
    private SongNode shuffleNode;
    /**
     * Return an instance of songLinkedList
     * Postcondition:
     *   This SongLinkedList has been initialized to an empty linked list.
     */
    public SongLinkedList(){
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    /**
     *  Preconditions:
     *      The name must match an actual song name in the playlist and there must be a file associated with it.
     *  Postconditions:
     *      The song is now playing.
     * @param name
     *  The name provided should be the name of the song, not the name of the file.
     * @throws IllegalArgumentException
     *  indicates that the provided name does not correspond to a song in the playlist, or that the wav file could not be found.
     */
    public void play(String name)throws IllegalArgumentException{
        try{
            AudioInputStream AIS = AudioSystem.getAudioInputStream(
                    new File(name +".wav"));
            Clip c = AudioSystem.getClip();
            c.open(AIS);
            c.start();
            System.out.println("'"+name+"' is now playing. \n");
        }catch (Exception ex) {
            System.out.println("'"+name +"' is not found.");
        }
    }

    /**
     * Preconditions:
     *      The list is not empty (cursor is not null).
     * Postconditions:
     *      The cursor has been advanced to the next SongNode, or has remained at the tail of the list.
     */
    public void cursorForwards(){
        try {
            if (size == 0) {
                throw new Exception("There is no song in the list.");
            }else if(cursor.getNext()==null){
                System.out.println("This is your last song.");
            }else{
                songNode = cursor.getNext();
                cursor = songNode;
                System.out.println("Cursor moved to next song. ");
            }
        }catch(Exception e){
            System.out.println("You don't have any songs yet.");
        }
    }

    /**
     * Preconditions:
     *       The list is not empty (cursor is not null).
     * Postconditions:
     *     The cursor has been moved back to the previous SongNode, or has remained at the head of the list.
     *
     *
     */
    public void cursorBackwards(){
        try {
            if (size == 0) {
                throw new Exception("There is no song in the list\n.");
            }else if(cursor.getPrev()==null){
                System.out.println("This is your first song.\n" );
            }else{
                songNode = cursor.getPrev();
                cursor = songNode;
                System.out.println("Cursor moved to the previous song.\n");
            }
        }catch(Exception e){
            System.out.println("You don't have any songs yet.\n");
        }
    }

    /**
     *
     * @param newSong
     *  the Song to be inserted into the playlist.
     *  Preconditions:
     *      The newSong object has been instantiated.
     *  Postconditions:
     *      The new Song is inserted into the playlist after the position of the cursor.
     *      All Song objects previously in the playlist are still in the playlist, and the order of the playlist is preserved.
     *      The cursor now points to the inserted node.
     */

    public void insertAfterCursor(Song newSong){
        songNode = new SongNode(newSong);
        if(size == 0){
            head = songNode;
            head.setPrev(null);
            cursor = head;
            tail = head;
            tail.setNext(null);
            size++;
        } else if(size == 1){
            songNode.setPrev(head);
            songNode.getPrev().setNext(songNode);
            songNode.setPrev(head);
            songNode.setNext(null);
            tail = songNode;
            cursor = songNode;
            size++;
        } else if(cursor==tail) {
            songNode.setPrev(cursor);
            songNode.setNext(null);
            cursor.setNext(songNode);
            tail = songNode;
            cursor = songNode;
            size++;
        }else if(cursor ==head){
            songNode.setNext(head.getNext());
            songNode.setPrev(head);
            songNode.getPrev().setNext(songNode);
            songNode.getNext().setPrev(songNode);
            cursor = songNode;
            size++;
        }
        else{
            songNode.setPrev(cursor);
            songNode.setNext(cursor.getNext());
            songNode.getNext().setPrev(songNode);
            songNode.getPrev().setNext(songNode);
            cursor = songNode;
            size++;
        }
    }

    /**
     * Preconditions:
     *      The cursor is not null.
     * Postconditions:
     *      The SongNode referenced by the cursor has been removed from the playlist.
     *      The cursor now references the next node, or the previous node if no next node exists.
     * @return
     *      The Song contained within the removed SongNode.
     */

    public Song removeCursor(){
        SongNode tempSongNode = cursor;
        try{
            if(size==0){
                throw new Exception();
            }
            else{
                removeInside(cursor);
                return tempSongNode.getData();
            }
        }catch(Exception e){
            System.out.println("You don't have any song yet.");
            return null;
        }


    }

    /**
     * It's a method to help removeCursor method.
     * @param songNode1
     *  The SongNode object need to be removed.
     */
    private void removeInside(SongNode songNode1){
        if (songNode1==tail&&size!=1) {
            songNode1 = tail.getPrev();
            tail.getPrev().setNext(null);
            tail = songNode1;
            cursor = songNode1;
            size--;
        }else if(songNode1 ==head&&size!=1 ){
            songNode1 = head.getNext();
            head.getNext().setPrev(null);
            head = songNode1;
            cursor = songNode1;
            size--;
        }else if(size==1){
            head = null;
            tail = null;
            cursor =null;
            size--;
        }else{
            songNode1.getPrev().setNext(songNode1.getNext());
            songNode1.getNext().setPrev(songNode1.getPrev());
            songNode1.setPrev(null);
            songNode1.setNext(null);
            songNode1 = songNode1.getNext();
            cursor = songNode1;
            size--;
        }
    }

    /**
     *  Postconditions:
     *      The randomly selected song is now playing.
     *
     * @return
     *      The Song which was randomly selected.
     */

    public Song random(){
       int a = (int)(Math.random()*(size));
       try {
           if (size == 0) {
               throw new Exception();
           } else {
               songNode = head;
               for (int i = 0; i < a; i++) {
                   songNode = songNode.getNext();
               }
               cursor = songNode;
               return songNode.getData();
           }
       }catch(Exception e){
           System.out.println("There is no song in your list.");
           return null;
       }
    }

    /**
     *  Postconditions:
     *      The playlist is randomly reordered.
     *      cursor should reference the SongNode which contains the same Song as when this method was entered.
     */
    public void shuffle(){
        SongNode tempSongNode;
        int a = (int)(Math.random()*3);
        try{
            if(size ==0){
                throw new Exception();
            }else{
                Song tempCursor = cursor.getData();
                for(int i = 0; i <a;i++){
                    int b = (int)(Math.random()*(size));
                    shuffleNode = head;
                    for(int j = 0; j<b;j++){
                        shuffleNode = shuffleNode.getNext();
                    }
                    tempSongNode = shuffleNode;

                    removeInside(shuffleNode);
                    cursor = head;
                    insertAfterCursor(tempSongNode.getData());
                }
                songNode = head;
                for (int k =0;k<size;k++){
                    if (songNode.getData()==tempCursor){
                        cursor = songNode;
                    }else{
                        songNode = songNode.getNext();
                    }
                }
            }
            System.out.println("Playlist shuffled. ");
        }catch(Exception e){
            System.out.println("There is No song in your list.");
        }
    }

    /**
     * Prints the playlist in a neatly-formatted table.
     */
    public void printPlaylist(){
        System.out.println(toString());
        if(size >0){
            songNode = head;
            for(int i =0;i<size;i++){
                if(songNode == cursor){
                    System.out.println(songNode.getData().toString() + "<--");
                    songNode = songNode.getNext();
                }else{
                    System.out.println(songNode.getData().toString());
                    songNode = songNode.getNext();
                }

            }


        }

    }

    /**
     * This will simply delete all of the songs from the playlist.
     * Postconditions:
     *      All songs have been removed.
     */
    public void deleteAll(){
        head=null;
        tail=null;
        cursor=null;
        size =0;
        System.out.println("Playlist cleared. \n");

    }

    /**
     *  Returns a neatly formatted String representation of the playlist. See the Sample I/O for layout.
     * @return
     *  A neatly formatted String representing the playlist in tabular form.
     */
    public String toString(){
        return "Player List:" +'\n' +String.format("%-29s","Song")+
                String.format("%-28s","| Artist") +
                String.format("%-28s","| Album") +
                String.format("%-28s","| Length (s)") + '\n' +
                "--------------------------------------------------" +
                "--------------------------------------------------" ;
    }


    public SongNode getHead() {
        return head;
    }

    public void setHead(SongNode head) {
        this.head = head;
    }

    public SongNode getTail() {
        return tail;
    }

    public void setTail(SongNode tail) {
        this.tail = tail;
    }

    public SongNode getCursor() {
        return cursor;
    }

    public void setCursor(SongNode cursor) {
        this.cursor = cursor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
