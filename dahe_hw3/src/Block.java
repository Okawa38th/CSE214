import java.util.ArrayList;


/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 3
 */
public class Block {
    /**
     * Constructor
     * Return an instance of Block with input Variable
     * Block is a node.
     * Postcondition:
     *  An Block object with Variable object input is created.
     *
     */

    private Block next;
    public ArrayList<Variable> variables;

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public Block(){
        variables = new ArrayList<>();
    }

    public Block getNext() {
        return next;
    }

    public void setNext(Block next) {
        this.next = next;
    }
    public int getSize(){
        return variables.size();
    }

}
