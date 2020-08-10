


/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 3
 */


public class Stack {
    private Block top;
    private Block tempBlock;
    private int size;
    /**
     * Constructor
     * Return an instance of Stack with input Block
     *
     * It's a my own Stack class,but the only different between
     * mine and java.util is, my stack variable is build by block.
     *
     */

    public Stack(){
        top = null;

    }

    public void push(Block block){
        if(top == null){
           top = block;
           size++;
        }else{
            block.setNext(top);
            top = block;
            size++;
        }
    }
    public Block pop(){
        if(top==null){
            return null;
        }else{
            tempBlock = top;
            top = top.getNext();
            size--;
            return tempBlock;
        }

    }

    public Block peek(){
        return top;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int getSize() {
        return size;
    }
}
