
import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 7
 */
public class FollowGraphDriver {

    static String userInput;
    static boolean keepGoing;
    static Scanner keyBoard;
    static FollowGraph followGraph;
    static boolean printLoop;

    /**
     * Start  program.
     */
    public static void main(String[] args) {
        userInput = "";
        keepGoing = true;
        keyBoard = new Scanner(System.in);
        loadGraph();
        while(keepGoing) {
            runMainMenu();
            processMainMenuInput();
        }


    }
    /**
     * RunMainMenu and  123 display the selections and get
     * them.
     */
    public static void runMainMenu(){
        System.out.println("(U) Add User");
        System.out.println("(C) Add Connection ");
        System.out.println("(AU) Load all users");
        System.out.println("(AC) Load all connections");
        System.out.println("(P) Print all Users");
        System.out.println("(L) Print all Loops");
        System.out.println("(RU) Remove User ");
        System.out.println("(RC) Remove Connection");
        System.out.println("(SP) Find Shortest Path ");
        System.out.println("(AP) Find All Paths ");
        System.out.println("(Q) Quit");

    }
    /**
     * Process  the feedback
     */
    public static void processMainMenuInput(){
        printLoop = true;
        System.out.println("Enter a selection:");
        userInput = keyBoard.nextLine();
        if (userInput.toUpperCase().equals("U")){
            System.out.println("Please enter the name of the user: ");
            userInput = keyBoard.nextLine();
            followGraph.addUser(userInput);
        }else if (userInput.toUpperCase().equals("C")){
            String a,b;
            System.out.println("Please enter the source of the connection to add: ");
            a = keyBoard.nextLine();
            System.out.println("Please enter the dest of the connection to add: ");
            b = keyBoard.nextLine();
            followGraph.addConnection(a,b);
        }else if (userInput.toUpperCase().equals("AU")){
            System.out.println("Enter the file name:(File extension needed)");
            userInput = keyBoard.nextLine();
            followGraph.loadAllUsers(userInput);
        }else if (userInput.toUpperCase().equals("AC")){
            System.out.println("Enter the file name:(File extension needed)");
            userInput = keyBoard.nextLine();
            followGraph.loadAllConnections(userInput);
        }else if (userInput.toUpperCase().equals("P")){
            while (printLoop){
                System.out.println("(SA) Sort Users by Name");
                System.out.println("(SB) Sort Users by Number of Followers");
                System.out.println("(SC) Sort Users by Number of Following ");
                System.out.println("(Q) Quit");
                System.out.println("Enter a selection:");
                userInput = keyBoard.nextLine();
                if (userInput.toUpperCase().equals("Q")){
                    printLoop = false;
                    System.out.println();
                }else{
                    followGraph.printAllUsers(userInput);
                    System.out.println();
                }
            }
        }else if (userInput.toUpperCase().equals("L")){
            int a = followGraph.findAllLoops().size();
            List<String> b = followGraph.findAllLoops();
            if (a < 1){
                System.out.println("There is no loop.");
            }else{
                System.out.println("There is " + a + " loop(s)");
                for (int i =0; i < a;i++){
                    System.out.println(b.get(i));
                }
            }
        }else if (userInput.toUpperCase().equals("RU")){
            System.out.println("Please enter the user to remove: ");
            userInput = keyBoard.nextLine();
            followGraph.removeUser(userInput);
        }else if (userInput.toUpperCase().equals("RC")){
            String a,b;
            System.out.println("Please enter the source of the connection to remove: ");
            a = keyBoard.nextLine();
            System.out.println("Please enter the dest of the connection to remove: ");
            b = keyBoard.nextLine();
            followGraph.removeConnection(a,b);
        }else if (userInput.toUpperCase().equals("SP")){
            String a,b;
            System.out.println("Please enter the desired source: ");
            a = keyBoard.nextLine();
            System.out.println("Please enter the desired destination: ");
            b = keyBoard.nextLine();
            System.out.println(followGraph.shortestPath(a,b));
        }else if (userInput.toUpperCase().equals("AP")){
            String a,b;
            System.out.println("Please enter the desired source: ");
            a = keyBoard.nextLine();
            System.out.println("Please enter the desired destination: ");
            b = keyBoard.nextLine();
            int c = followGraph.allPaths(a,b).size();
            List<String> d = followGraph.allPaths(a,b);
            if (c < 1){
                System.out.println("There is no way that "+a+" to reach "+b);
            }else{
                System.out.println("There is " + c + " path(s)");
                for (int i =0; i < c;i++){
                    System.out.println(d.get(i));
                }
            }
        }else if (userInput.toUpperCase().equals("Q")){
           saveGraph();
        }
    }

    /**
     * Detect if the system has saved a follow graph, if so, using the previous follow graph.
     */
    private static void loadGraph(){
        try{
            FileInputStream file = new FileInputStream("followGraph.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            followGraph = (FollowGraph) inStream.readObject();
            System.out.println("A previous FollowGraph file has found ");
            System.out.println();
        }catch (FileNotFoundException e){
            System.out.println("There is no saved data has been found.");
            System.out.println("Creating a new FollowGraph now!");
            followGraph = new FollowGraph();
        }catch (IOException o){
            System.out.println("UNKNOWN PROBLEM");
        }catch (ClassNotFoundException a){
            System.out.println("Class not FOUND?");
        }

    }

    /**
     * Save the current follow graph and terminate the system.
     */
    private static void saveGraph(){
        try{
            FileOutputStream file = new FileOutputStream("followGraph.obj");
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            //FollowGraph followGraph1 = new FollowGraph(followGraph.getUsers(),followGraph.getAdjList(),followGraph.getConnections());
            outStream.writeObject(followGraph);
            System.out.println("Object has been serialized");
            System.out.println("~Bye");
            keepGoing = false;
            outStream.close();
            file.close();
        }catch (FileNotFoundException e){
            System.out.println("In some how we can't save it.");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("I DONT KNOW HOW THE FUK I WRONG");
        }
    }
}
