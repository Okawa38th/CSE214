package package1;


/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 2
 */
public class Song {
    private String songName;
    private String artisit;
    private String album;
    private int length;

    /**
     * Constructor
     * Return an instance of song with no input
     * Postcondition:
     *  An song object with no input is created.
     *
     */
    public Song(){}

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtisit() {
        return artisit;
    }

    public void setArtisit(String artisit) {
        this.artisit = artisit;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;

    }

    @Override
    public String toString() {
        return String.format("%-29s",songName)+
                String.format("%-28s",artisit) +
                String.format("%-29s",album) +
                String.format("%-16s",length);
    }
}
