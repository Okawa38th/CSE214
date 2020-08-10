

/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 3
 */

public class Variable {
    private String name;
    private int initialValue;
    /**
     * Constructor
     * Return an instance of Variable with no input
     * Postcondition:
     *  A Variable object with no input is created.
     *
     */

    public Variable(){}
    public Variable(String name, int initialValue){
        this.name = name;
        this.initialValue = initialValue;
    }

    public String getName() {
        return name;
    }

    public int getInitialValue() {
        return initialValue;
    }

    @Override
    public String toString() {
        return String.format("%-26s",name)+
                String.format("%-21s",initialValue);
    }
}
