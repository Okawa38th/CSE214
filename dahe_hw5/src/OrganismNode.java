/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 5
 */


public class OrganismNode {
    private String name;
    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;
    private OrganismNode left;
    private OrganismNode middle;
    private OrganismNode right;
        /**
         * Constructor
         * Brief: Create an OrganismNode
         * Postcondition:
         *  An OrganismNode object with no input is created.
         *
         */
    public OrganismNode(){
        this.left=null;
        this.middle=null;
        this.right=null;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPlant(boolean plant) {
        isPlant = plant;
    }

    public void setHerbivore(boolean herbivore) {
        isHerbivore = herbivore;
    }

    public void setCarnivore(boolean carnivore) {
        isCarnivore = carnivore;
    }



    public String getName() {
        return name;
    }

    public boolean isPlant() {
        return isPlant;
    }

    public boolean isHerbivore() {
        return isHerbivore;
    }

    public boolean isCarnivore() {
        return isCarnivore;
    }

    public OrganismNode getLeft() {
        return left;
    }

    public OrganismNode getMiddle() {
        return middle;
    }

    public OrganismNode getRight() {
        return right;
    }

    /**
     * Brief:
     *      Adds preyNode as prey to this node.
     *      Left filled first, Middle filled  then, Right filled last.
     * @param preyNode
     *      An OrganismNode to be added as prey of this organism.
     * @throws PositionNotAvailableException
     *      Thrown if there is no available child position for preyNode to be added
     * @throws IsPlantException
     *      Thrown if this node is a plant node
     * @throws DietMismatchException
     *      Thrown if preyNode does not correctly correspond to the diet of this animal.
     */


    public void addPrey(OrganismNode preyNode) throws PositionNotAvailableException, IsPlantException, DietMismatchException {
         if (preyNode.isPlant){
             throw new IsPlantException("This is a plant, it doesn't consume anything here");
         }else {
             if (preyNode.isCarnivore && !preyNode.isHerbivore && this.isPlant) {
                 throw new DietMismatchException("Carnivore doesn't eat plant, dude");
             } else if (!preyNode.isCarnivore && preyNode.isHerbivore && !this.isPlant) {
                 throw new DietMismatchException("Herbivore only eat plant, dude");
             } else {
                 if (this.left == null) {
                     this.left = preyNode;
                 } else if (this.middle == null) {
                     this.middle = preyNode;
                 } else if (this.right == null) {
                     this.right = preyNode;
                 } else {
                     throw new PositionNotAvailableException("There is no more space for this creature to consume.");
                 }
             }
         }
    }

    public void setLeft(OrganismNode left) {
        this.left = left;
    }

    public void setMiddle(OrganismNode middle) {
        this.middle = middle;
    }

    public void setRight(OrganismNode right) {
        this.right = right;
    }

    class DietMismatchException extends Exception {
        DietMismatchException(String s){
            super(s);
        }
    }
}
