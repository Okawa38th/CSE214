/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 5
 */

public class OrganismTree {
    private OrganismNode root;
    private OrganismNode cursor;
    private OrganismNode tempNode;
    private String address;
    private String tempTree;
    private String allPlants;

    /**
     * Brief:
     *       Constructor that creates a new OrganismTree with apexPredator as the root.
     * @param apexPredator
     *      The apex predator of the food pyramid.
     * Postcondition:
     *      An OrganismTree object is made, with apexPredator representing the apex predator. Both root and cursor reference this node.
     */
    public OrganismTree(OrganismNode apexPredator) {
        root = apexPredator;
        cursor = apexPredator;
        address = root.getName();
    }

    /**
     * Brief:
     *      Moves the cursor back to the root of the tree.
     * Postcondition:
     *      cursor now references the root of the tree.
     */

    public void cursorReset() {
        cursor = root;
        address = root.getName();
        System.out.println("Cursor successfully reset to root!");
    }

    /**
     * Brief:
     *      Moves cursor to one of cursor’s children.
     * @param name
     *      The name of the node to be moved to.
     * Postcondition:
     *       Either an exception is thrown, or cursor now points to the node whose name is referenced by name,
     *       and cursor now points to a child of the original cursor reference.
     * @throws IllegalArgumentException
     *      Thrown if name does not reference a direct, valid child of cursor.
     */
    public void moveCursor(String name) throws IllegalArgumentException {
        if (cursor.getLeft() != null) {
            if (cursor.getLeft().getName().equals(name)) {
                cursor = cursor.getLeft();
                address += "-> " + cursor.getName();
                System.out.println();
                System.out.println("Cursor successfully moved to " + name + " !");
                System.out.println();
            } else if (cursor.getMiddle() != null) {
                if (cursor.getMiddle().getName().equals(name)) {
                    cursor = cursor.getMiddle();
                    address += "-> " + cursor.getName();
                    System.out.println();
                    System.out.println("Cursor successfully moved to " + name + " !");
                    System.out.println();
                } else if (cursor.getRight() != null) {
                    if (cursor.getRight().getName().equals(name)) {
                        cursor = cursor.getRight();
                        address += "-> " + cursor.getName();
                        System.out.println();
                        System.out.println("Cursor successfully moved to " + name + " !");
                        System.out.println();
                    }
                } else {
                    throw new IllegalArgumentException(name + " does not exist.");
                }
            } else {
                throw new IllegalArgumentException(name + " does not exist.");
            }
        } else {
            throw new IllegalArgumentException(cursor.getName() + " is Empty.");
        }
    }

    /**
     * Brief:
     *      Returns a String including the organism at cursor and all its possible prey
     * @return
     *      A String containing the name of the cursor, and all the cursor’s possible prey.
     * Postcondition:
     *      cursor has not moved.
     * @throws IsPlantException
     *      Thrown if the cursor currently references a plant object.
     */

    public String listPrey() throws IsPlantException {
        if (cursor.isPlant()) {
            throw new IsPlantException("This is plant, it does not have PREY");
        } else {
            if (cursor.getLeft() == null) {
                return cursor.getName() + " does not have any prey yet.";
            } else {
                if (cursor.getMiddle() == null) {
                    return cursor.getName() + " -> " + cursor.getLeft().getName();
                } else {
                    if (cursor.getRight() == null) {
                        return cursor.getName() + " -> " + cursor.getLeft().getName() + ", " + cursor.getMiddle().getName();
                    } else {
                        return cursor.getName() + " -> " + cursor.getLeft().getName() + ", " + cursor.getMiddle().getName() + ", " + cursor.getRight().getName();
                    }
                }
            }
        }
    }

    /**
     * Brief:
     *      Returns a String containing the path of organisms that leads from the apex predator (root) to the organism at cursor.
     * Postcondition:
     *      cursor has not moved.
     * @return
     *      A String containing the food chain from the apex predator to the cursor.
     */

    public String listFoodChain() {
        return address;
    }

    /**
     * Brief:
     *      Recursion for print the OrganismTree from the current cursor to all
     *      its children.
     * @param a
     *      OrganismNode to print
     * @param n
     *      The deeper  it goes, the more "space" it will need.
     */

    private void recursion(OrganismNode a, int n) {
        if (a.getLeft() != null) {
            if (a.getLeft().getLeft() != null) {
                tempTree += '\n' + String.format("%" + n + "s", "");
                tempTree += "|- " + a.getLeft().getName();
                recursion(a.getLeft(), n + 3);
            } else {
                tempTree += '\n' + String.format("%" + n + "s", "");
                tempTree += "- " + a.getLeft().getName();
                if (a.getLeft().isPlant()) {
                    allPlants += a.getLeft().getName() + ", ";
                }
            }
            if (a.getMiddle() != null) {
                if (a.getMiddle().getLeft() != null) {
                    tempTree += '\n' + String.format("%" + n + "s", "");
                    tempTree += "|- " + a.getMiddle().getName();
                    recursion(a.getMiddle(), n + 3);
                } else {
                    tempTree += '\n' + String.format("%" + n + "s", "");
                    tempTree += "- " + a.getMiddle().getName();
                    if (a.getMiddle().isPlant()) {
                        allPlants += a.getMiddle().getName() + ", ";
                    }
                }
                if (a.getRight() != null) {
                    if (a.getRight().getLeft() != null) {
                        tempTree += '\n' + String.format("%" + n + "s", "");
                        tempTree += "|- " + a.getRight().getName();
                        recursion(a.getRight(), n + 3);
                    } else {
                        tempTree += '\n' + String.format("%" + n + "s", "");
                        tempTree += "- " + a.getRight().getName();
                        if (a.getRight().isPlant()) {
                            allPlants += a.getRight().getName() + ", ";
                        }
                    }
                }
            }
        }
    }

    /**
     * Brief:
     *      Prints out a layered, indented tree by performing a preorder traversal starting at cursor.
     * Postconditions:
     *      cursor has not moved.
     *      root has not moved.
     */

    public void printOrganismTree() {
        tempTree = "|- " + cursor.getName();
        recursion(cursor, 4);
        System.out.println(tempTree);
    }

    /**
     * Brief:
     *      Returns a list of all plants currently at cursor and beneath cursor in the food pyramid.
     * Postconditions:
     *       cursor has not moved.
     *       root has not moved.
     * @return
     *      A String containing a list of all the plants in the food pyramid.
     */

    public String listAllPlants() {
        allPlants = "";
        recursion(cursor, 4);
        if (allPlants.length() > 1) {
            return allPlants.substring(0, allPlants.length() - 2);
        } else {
            return "There is no plant yet";
        }

    }

    /**
     * Brief:
     *      Creates a new animal node with a specific name and diet and adds it as a child of the cursor node.
     * @param name
     *      The name of the child node.
     * @param isHerbivore
     *      value depending on whether the animal consumes plants.
     * @param isCarnivore
     *      value depending on whether the animal consumes other animals.
     * Postconditions:
     *       Either an exception is thrown, or a new animal node named name is added as a child of the cursor with a specific diet.
     *       The cursor does not move.
     * @throws IllegalArgumentException
     *      Thrown if name references an exact name with one of its would-be siblings.
     *
     */

    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) throws
            IllegalArgumentException {
        tempNode = new OrganismNode();
        tempNode.setHerbivore(isHerbivore);
        tempNode.setCarnivore(isCarnivore);
        tempNode.setPlant(false);
        tempNode.setName(name);
        try {
            addNew(name);
        } catch (PositionNotAvailableException e) {
            System.out.println("There is no more space for it to consume");
        } catch (IllegalArgumentException o) {
            System.out.println("It's already exist");
        }
    }



    /**
     * Brief:
     *      Creates a new plant node with a specific name and adds it as a child of the cursor node.
     * Postconditions:
     *      Either an exception is thrown, or a new plant node named name is added as a child of the cursor.
     * @param name
     *      The name of the child node.
     */

    public void addPlantChild(String name) {
        tempNode = new OrganismNode();
        tempNode.setName(name);
        tempNode.setPlant(true);
        tempNode.setHerbivore(false);
        tempNode.setCarnivore(false);
        try {
            addNew(name);
        } catch (PositionNotAvailableException e) {
            System.out.println("There is no more space for it to consume");
        } catch (IllegalArgumentException o) {
            System.out.println("It's already exist");
        }
    }

    /**
     * Brief:
     *      If there is no available child position for a new node to be added or this child already exist,
     *    my system will ask the user which kind of animal is that anyway. Meanwhile, it would create a
     *    temporary node as the new prey. Then it will check it's either full or already exist.
     *
     * @param name
     *      The name of the child node.
     * @throws PositionNotAvailableException
     *      Thrown if there is no available child position for a new node to be added.
     *
     * @throws IllegalArgumentException
     *      Thrown if name references an exact name with one of its would-be siblings.
     */
    private void addNew(String name) throws PositionNotAvailableException, IllegalArgumentException {
        if (cursor.getLeft() != null & cursor.getMiddle() != null & cursor.getRight() != null) {
            throw new PositionNotAvailableException("There is no more space for it to consume");
        } else {
            if (cursor.getLeft() == null) {
                cursor.setLeft(tempNode);
            } else {
                if (cursor.getLeft().getName().equals(name)) {
                    throw new IllegalArgumentException("Left same");
                } else {
                    if (cursor.getMiddle() == null) {
                        cursor.setMiddle(tempNode);
                    } else {
                        if (cursor.getMiddle().getName().equals(name)) {
                            throw new IllegalArgumentException("Middle same");
                        } else {
                            if (cursor.getRight() == null) {
                                cursor.setRight(tempNode);
                            } else {
                                if (cursor.getRight().getName().equals(name)) {
                                    throw new IllegalArgumentException("Right same");
                                }
                            }
                        }
                    }
                }
            }
            System.out.println();
            System.out.println("A(n) " + tempNode.getName() + " has successfully been added as prey for the " + cursor.getName());
            System.out.println();


        }

    }

    /**
     * Brief:
     *      Removes the child node of cursor with name name, and properly shifts the deleted node’s other siblings if necessary.
     *      If the deleted node has any descendants, those nodes are deleted as well.
     * @param name
     *      The name of the node to be deleted.
     * Postconditions:
     *      The child node of cursor with name name has been removed, and all the deleted node’s descendants
     *      have been removed from the tree as well.
     *
     *      The deleted node’s siblings are shifted if necessary
     *
     *       cursor has not moved.
     * @throws IllegalArgumentException
     *      Thrown if name does not reference a direct child of the cursor.
     */
    public void removeChild(String name) throws IllegalArgumentException{
        if (cursor.getLeft()!=null){
            if (cursor.getLeft().getName().equals(name)){
                tempNode = cursor.getLeft();
                cursor.setLeft(cursor.getMiddle());
                cursor.setMiddle(cursor.getRight());
                System.out.println();
                System.out.println("A(n) "+tempNode.getName()+" has successfully been removed as prey for the "+cursor.getName());
                System.out.println();
            }else if (cursor.getMiddle()!=null) {
                if (cursor.getMiddle().getName().equals(name)){
                    tempNode = cursor.getMiddle();
                    cursor.setMiddle(cursor.getRight());
                    cursor.setRight(null);
                    System.out.println();
                    System.out.println("A(n) "+tempNode.getName()+" has successfully been removed as prey for the "+cursor.getName());
                    System.out.println();
            } else if (cursor.getRight()!=null){
                    if (cursor.getRight().getName().equals(name)){
                        tempNode = cursor.getRight();
                        cursor.setRight(null);
                        System.out.println();
                        System.out.println("A(n) "+tempNode.getName()+" has successfully been removed as prey for the "+cursor.getName());
                        System.out.println();

                    }
                }
                else {
                    throw new IllegalArgumentException(name + " does not exist.");
                }
            }
            else {
                throw new IllegalArgumentException(name + " does not exist.");
            }
        }else {
            throw new IllegalArgumentException(name + " does not exist.");
        }
    }

    /**
     * Brief:
     *      Check if the cursor animal available to eat the prey.
     * @param name
     *      The name of the node to be deleted.
     * @return
     *      Either ok or not.
     */

    public boolean checkDiet(String name){
        if (!cursor.isHerbivore()& name.toUpperCase().equals("PC")){
            return false;
        }else if (!cursor.isCarnivore()& name.toUpperCase().equals("AC")){
            return false;
        }else{
            return true;
        }
    }
}
