
import java.util.Scanner;
/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 5
 */
public class FoodPyramid {
    static OrganismTree tree;
    static OrganismNode tempNode;
    static String userInput;
    static boolean keepGoing;
    static Scanner keyBoard;

    public FoodPyramid(){}
    /**
     * Start  program.
     */
    public static void main(String[] args){
        userInput = "";
        keepGoing = true;
        keyBoard = new Scanner(System.in);
        firstSet();
        while(keepGoing) {
            runMainMenu();
            processMainMenuInput();
        }
    }

    /**
     * Set the root of the tree
     */
    private static void firstSet(){
        System.out.print("What is the name of the apex predator?: ");
        userInput = keyBoard.nextLine();
        checkIt();
        tree = new OrganismTree(tempNode);
        System.out.println();
        System.out.println("Constructing food pyramid. . .");
        System.out.println();
    }

    /**
     * Check the characteristic of this creature
     */
    private static void checkIt(){
        tempNode = new OrganismNode();
        tempNode.setName(userInput);
        System.out.print("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O): ");
        userInput = keyBoard.nextLine();
        if (userInput.toUpperCase().equals("H")){
            tempNode.setHerbivore(true);
            tempNode.setCarnivore(false);
            tempNode.setPlant(false);
        }else if (userInput.toUpperCase().equals("C")){
            tempNode.setHerbivore(false);
            tempNode.setCarnivore(true);
            tempNode.setPlant(false);
        }else if (userInput.toUpperCase().equals("O")){
            tempNode.setHerbivore(true);
            tempNode.setCarnivore(true);
            tempNode.setPlant(false);
        }else{
            System.out.println("WTF is that..");
            System.out.print("What is the name of the organism?: ");
            userInput = keyBoard.nextLine();
            checkIt();
        }
    }
    /**
     * RunMainMenu, display the selections and get
     * them.
     */
    private static void runMainMenu() {
        System.out.println("(PC) -  Create New Plant Child");
        System.out.println("(AC) - Create New Animal Child");
        System.out.println("(RC) - Remove Child");
        System.out.println("(P) - Print Out Cursor’s Prey");
        System.out.println("(C) - Print Out Food Chain");
        System.out.println("(F) - Print Out Food Pyramid at Cursor");
        System.out.println("(LP) - List All Plants Supporting Cursor");
        System.out.println("(R) - Reset Cursor to Root");
        System.out.println("(M) - Move Cursor to Child");
        System.out.println("(Q) - Quit");
    }
    /**
     * Process the feedback
     */

    private static void processMainMenuInput(){
        userInput = keyBoard.nextLine();
        if (userInput.toUpperCase().equals("PC")){
            if (tree.checkDiet("PC")){
                System.out.print("What is the name of the organism?: ");
                userInput = keyBoard.nextLine();
                tree.addPlantChild(userInput);
            }else{
                System.out.println("This animal doesn't eat plant");
            }
        }else if (userInput.toUpperCase().equals("AC")){
            if (tree.checkDiet("AC")){
                System.out.print("What is the name of the organism?: ");
                userInput = keyBoard.nextLine();
                checkIt();
                tree.addAnimalChild(tempNode.getName(),tempNode.isHerbivore(),tempNode.isCarnivore());
            }else{
                System.out.println("This animal only eat plant");
            }

        }else if(userInput.toUpperCase().equals("RC")){
            System.out.print("What is the name of the organism to be removed?: ");
            userInput = keyBoard.nextLine();
            try{
                tree.removeChild(userInput);
            }catch (IllegalArgumentException e){
                System.out.println(userInput + " does not exist.");
            }

        }else if(userInput.toUpperCase().equals("P")){
            try{
                System.out.println(tree.listPrey());
            }catch (IsPlantException e){
                System.out.println("This is plant, it does not have PREY");
            }
        }else if (userInput.toUpperCase().equals("C")){
            System.out.println(tree.listFoodChain());
        }else if(userInput.toUpperCase().equals("F")){
            tree.printOrganismTree();
        }else if (userInput.toUpperCase().equals("LP")){
            System.out.println(tree.listAllPlants());
        }else if (userInput.toUpperCase().equals("R")){
            tree.cursorReset();
        }else if (userInput.toUpperCase().equals("M")){
            System.out.print("Move to?: ");
            userInput = keyBoard.nextLine();
            try{
                tree.moveCursor(userInput);
            }catch (IllegalArgumentException e){
                System.out.println(userInput + " does not reference a direct");
            }

        }else if (userInput.toUpperCase().equals("Q")){
            System.out.println("Bye");
            keepGoing = false;
        }

    }
}
