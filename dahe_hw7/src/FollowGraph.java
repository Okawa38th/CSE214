
import java.io.*;
import java.util.*;
/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 7
 */


public class FollowGraph implements Serializable {
     public ArrayList<User> users;
     public static final int MAX_USERS = 100;
     public boolean[][] connections;
     private int[][] dis;
     private User[][] next;
     private ArrayList<ArrayList<User>>adjList;
     private List<String> tempList;
     public ArrayList<User> tempUsers;
    /**
     * Constructor
     * Brief: Create a FollowGraph object
     * Postcondition:
     *  A new FollowGraph object has been created.
     *
     */
     public FollowGraph(){
         users = new ArrayList<>();
         adjList = new ArrayList<>();
         connections = new boolean[MAX_USERS][MAX_USERS];
    }

    /**
     * Brief:
     *  adds a new user if not already exist
     * @param userName
     *  the user will be add to list
     */

    public void addUser(String userName) {
        if (users.isEmpty()) {
            users.add(new User(userName));
            adjList.add(new ArrayList<>());
        } else {
           if (checkIndexBox(userName)==-1){
               users.add(new User(userName));
               adjList.add(new ArrayList<>());
           }else{
               System.out.println("This user is already exist.");
           }
        }
    }

    /**
     * Brief:
     *  let the user follow the other one if appropriate users are given.
     * @param userFrom
     * the follower
     * @param userTo
     *  to follow this kid
     */
    public void addConnection(String userFrom, String userTo){
        if (users.isEmpty()){
            System.out.println("There is no use yet.");
        }else if( users.size()==1){
            System.out.println("There is only one user.");
        }else if(checkIndexBox(userFrom)==-1|checkIndexBox(userTo)==-1){
            System.out.println("One or all of user(s) does not exist.");
        } else{
            if (checkIndexBox(userFrom)==checkIndexBox(userTo)){
                System.out.println("You wanna follow yourself??");
            }else{
                if (!adjList.get(checkIndexBox(userFrom)).contains(users.get(checkIndexBox(userTo)))){
                    connections[checkIndexBox(userFrom)][checkIndexBox(userTo)]=true;
                    adjList.get(checkIndexBox(userFrom)).add(users.get(checkIndexBox(userTo)));
                }else{
                    System.out.println("You can't follow the same kid twice :)");
                }
            }
        }
    }

    /**
     * Brief:
     * let the first no longer to follow the second one if they both exist.
     * @param userFrom
     * the follower
     * @param userTo
     * the kid that would not be followed anymore
     */
    public void removeConnection(String userFrom, String userTo){
        if (users.isEmpty()){
            System.out.println("There is no user yet.");
        }else if( users.size()==1){
            System.out.println("There is only one user.");
        }else{
            if (checkIndexBox(userFrom)==-1|checkIndexBox(userTo)==-1){
                System.out.println("One of this user does not exist.");
            }else{
                connections[checkIndexBox(userFrom)][checkIndexBox(userTo)]=false;
                if (adjList.get(checkIndexBox(userFrom)).contains(users.get(checkIndexBox(userTo)))){
                    adjList.get(checkIndexBox(userFrom)).remove(users.get(checkIndexBox(userTo)));
                }else{
                    System.out.println("These kids has not follow each other yet.");
                }
            }
        }
    }

    /**
     * Brief:
     *  Find the shortest follow path between first user to second user.
     * @param userFrom
     * the first user
     * @param userTo
     * the second user
     * @return
     * the String list of shortestPath
     */
    public String shortestPath(String userFrom, String userTo){
        FloydWarshallWithPathReconstruction();
        return userFrom + printShortest(checkIndexBox(userFrom),checkIndexBox(userTo));
    }

    /**
     * Brief:
     * Find all path between first user and second user.
     * @param userFrom
     * the first user
     * @param userTo
     * the second user
     * @return
     * the String list of allPath
     */
    public List<String> allPaths(String userFrom, String userTo){
         boolean[] isVisted = new boolean[users.size()];
         ArrayList<User> pathList = new ArrayList<>();
         tempList  = new ArrayList<>();
         pathList.add(users.get(checkIndexBox(userFrom)));
         printAllPath(users.get(checkIndexBox(userFrom)),users.get(checkIndexBox(userTo)),isVisted,pathList);
         return tempList;
    }

    /**
     * A support  recursive method to help find the path between two user
     * @param from
     * the first user
     * @param to
     * the second user
     * @param isVisted
     * a boolean array to see if each user has been visited
     * @param local
     * A User list for drawing the path
     */
    private void printAllPath(User from, User to, boolean[] isVisted, List<User>local){
        isVisted[users.indexOf(from)]=true;
        if (from == to) {
            isVisted[users.indexOf(from)] = false;
            String a = "";
            for (User i : local) {
                a = a + (" -> " + i.toString());
            }
            tempList.add(a.substring(3));


        }else{
            for (User i : adjList.get(users.indexOf(from))){
                if (!isVisted[users.indexOf(i)]){
                    local.add(i);
                    printAllPath(i,to,isVisted,local);
                    local.remove(i);
                }
            }
            isVisted[users.indexOf(from)]=false;
        }

    }

    /**
     * Brief:
     *  Print all users in the way you want.
     * @param comp
     * The user's name
     */
        public void printAllUsers(String comp){
         tempUsers = new ArrayList<>(users);

        if (comp.toUpperCase().equals("SA")){
            Collections.sort(tempUsers, new NameComparator());
            printAllList();
        }else if(comp.toUpperCase().equals("SB")){
            Collections.sort(tempUsers, new FollowersComparator() );
            printAllList();
        }else if(comp.toUpperCase().equals("SC")){
            Collections.sort(tempUsers, new FollowingComparator());
            printAllList();
        }else{
            System.out.println("TRY AGAIN.");
        }

    }

    /**
     * Brief:
     * print all the followers of this user
     * @param userName
     * the user's name
     */
    public void printAllFollowers(String userName){
         System.out.println("The followers of " + userName +" are ");
         String a = "";
        for (int i = 0; i < adjList.size(); i++) {
            if (adjList.get(i).contains(tempUsers.get(i))) {
                a += users.get(i).getUser() + ",";
            }
        }
        System.out.println(a);


    }
    /**
     * Brief:
     * print all the following of this user
     * @param userName
     * the user's name
     */
    public void printAllFollowing(String userName){
        System.out.println("The following of " + userName +" are ");
        String a = "";
        for (int i = 0; i < adjList.get(checkIndexBox(userName)).size(); i++) {
            a += adjList.get(checkIndexBox(userName)).get(i);
        }
        System.out.println(a);

    }

    /**
     * Brief:
     * find all loops in this graph
     * @return
     * A String list of loops.
     */
    public List<String> findAllLoops(){
        List<String> tempLoop = new ArrayList<>();
        for (User i: users){
            tempLoop.addAll(printAllLoop(i));
        }
        return tempLoop;

    }

    /**
     * A support method for print all loops
     * @param loop
     * the current user loop
     * @return
     * the loop of the given user
     */
    private List<String> printAllLoop(User loop){
        List<String> tempLoop = new ArrayList<>();
        for (User i : adjList.get(users.indexOf(loop))){
            int c = allPaths(i.getUser(),loop.getUser()).size();
            String a= loop.getUser() + " ->";
            if (c>0){
                List<String> temp = new ArrayList<>(allPaths(i.getUser(),loop.getUser()));
                for (int j=0;j<temp.size();j++){
                    temp.set(j,a+temp.get(j));
                }
                tempLoop.addAll(temp);
            }
        }
        return tempLoop;
    }

    /**
     * Brief:
     *  load all users from a file
     * @param fileName
     * file name
     */
    public void loadAllUsers(String fileName){
        FileInputStream fis;
        InputStreamReader inStream;
        BufferedReader stdin;
        String data;
        try{
            fis = new FileInputStream(fileName);
            inStream = new InputStreamReader(fis);
            stdin = new BufferedReader(inStream);
            while((data=stdin.readLine())!=null){
                addUser(data);
            }
            stdin.close();
            inStream.close();
        }catch(IOException e){
            System.out.println("File has not Found.");

        }


    }

    /**
     * Brief:
     *  load all users connection from a file
     * @param fileName
     * file name
     */
    public void loadAllConnections(String fileName){
        FileInputStream fis;
        InputStreamReader inStream;
        BufferedReader stdin;
        String data;
        String[] a;
        try{
            fis = new FileInputStream(fileName);
            inStream = new InputStreamReader(fis);
            stdin = new BufferedReader(inStream);
            while((data=stdin.readLine())!=null){
               a = data.split(", ");
               if (checkIndexBox(a[0])!=-1&&checkIndexBox(a[1])!=-1){
                   connections[checkIndexBox(a[0])][checkIndexBox(a[1])] = true;
                   adjList.get(checkIndexBox(a[0])).add(users.get(checkIndexBox(a[1])));
               }
            }
            stdin.close();
            inStream.close();
        }catch(IOException e){
            System.out.println("File has not Found.");

        }
    }

    /**
     * A support method to find the index of this user by his String name.
     * @param userName
     * String user name
     * @return
     * the current position in the users list.
     */
    private int checkIndexBox(String userName){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getUser().equals(userName)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Special algorithm to find the shortest length from all nodes.
     */
    private void FloydWarshallWithPathReconstruction(){
         dis = new int[users.size()][users.size()];
         next = new User [users.size()][users.size()];
         int a = (int) Double.POSITIVE_INFINITY;
         for (int i =0; i<users.size();i++){
             for (int j =0; j < users.size(); j++){
                 if (i == j){
                     dis [i][j] = 0;
                     next[i][j] = users.get(j);
                 }else if (connections[i][j]){
                     dis [i][j] = 1;
                     next[i][j] = users.get(i);
                 }else{
                     dis [i][j] = a;
                     next[i][j] = null;
                 }
             }
         }
         findShortest();

    }

    /**
     * A support method to find the shortest path.
     */
    private void findShortest(){
        int a = (int) Double.POSITIVE_INFINITY;
        for (int k = 0; k < users.size();k++){
            for (int i= 0; i< users.size();i++){
                for (int j = 0; j< users.size(); j++){
                    if (dis[i][j]>(dis[i][k]+dis[k][j])&& dis[i][k]!= a&&dis[k][j] != a){
                        dis[i][j] = dis[i][k]+dis[k][j];
                        next[i][j] = next[k][j];
                    }
                }
            }
        }
    }

    /**
     * An other support recursive method to find the shortest between 2 users.
     * @param i
     * the first user's index.
     * @param j
     * the second user's index
     * @return
     * The shortest path between two users.
     */
    private String printShortest(int i, int j){
         String a = "";
         if (next[i][j]!=null){
             if (next[i][j].equals(users.get(i))){
                 a += " -> " + users.get(j).getUser();
             }else{
                 a += printShortest(i,users.indexOf(next[i][j]));
                 a += printShortest(users.indexOf(next[i][j]),j);
             }
         }
         return a;

    }

    /**
     * Remove a user from user list.
     * @param userName
     * the user's name
     */

    public void removeUser(String userName){
         if (checkIndexBox(userName)!=-1){
             adjList.remove(checkIndexBox(userName));
             for (ArrayList<User> i: adjList){
                 i.remove(users.get(checkIndexBox(userName)));
             }
             users.remove(checkIndexBox(userName));
             boolean[][] tempConnection = new boolean[MAX_USERS][MAX_USERS];
             for (int i =0; i<adjList.size();i++){
                 for ( int j =0;j<adjList.get(i).size();j++){
                     tempConnection[i][users.indexOf(adjList.get(i).get(j))]=true;
                 }
             }
             connections = tempConnection;
         }else{
             System.out.println("This user does not exist.");
         }
    }

    /**
     * A support method to print all users
     */
    private void printAllList(){
        System.out.print(String.format("%-29s","UserName"));
        System.out.print(String.format("%-28s", "Number of Followers"));
        System.out.print(String.format("%-28s", "Number Following"));
        System.out.println();
        for (int i = 0; i < tempUsers.size(); i++) {
            int a = 0;
            int b = 0;
            for (int j = 0; j < users.size(); j++) {
                if (tempUsers.get(i).equals(users.get(j))) {
                    a = adjList.get(j).size();
                }else if (adjList.get(j).contains(tempUsers.get(i))){
                    b+=1;
                }
            }
            System.out.print(String.format("%-36s",tempUsers.get(i).getUser()));
            System.out.print(String.format("%-29s", b));//followers
            System.out.print(String.format("%-32s", a));//followings
            System.out.println();
        }
    }

    /**
     * Custom comparator for users by their follower numbers
     */
    public class FollowersComparator implements Comparator<User>{
        @Override
        public int compare(User o1, User o2) {
            int a =0,b=0;
            for (int i =0; i < adjList.size(); i++){
                if (adjList.get(i).contains(o1)){
                    a += 1;
                }
                if (adjList.get(i).contains(o2)){
                    b += 1;
                }
            }
            if (a==b){
                return 0;
            }else if(a>b){
                return -1;
            }else{
                return 1;
            }

        }
    }
    /**
     * Custom comparator for users by their following numbers
     */
    public class FollowingComparator implements Comparator<User>{
        @Override
        public int compare(User o1, User o2) {
            int a = adjList.get(users.indexOf(o1)).size();
            int b = adjList.get(users.indexOf(o2)).size();
            if (a==b){
                return 0;
            }else if(a>b){
                return -1;
            }else{
                return 1;
            }
        }

    }
    /**
     * Custom comparator for users by their names.
     */
    public class NameComparator implements Comparator<User>{
        @Override
        public int compare(User o1, User o2) {
            return o1.getUser().compareTo(o2.getUser());
        }
    }

}

