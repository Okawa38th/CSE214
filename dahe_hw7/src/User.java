import java.io.Serializable;

/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 7
 */
public class User implements Serializable {
    String user;
    int indexPos;
    static int userCount;
    /**
     * Constructor
     * Brief: Create a User object
     * Postcondition:
     *  A new User object has been created, the "userCount" has been accumulated.
     *
     */

    public User(String userName){
        user=  userName;
        indexPos = userCount;
        userCount +=1;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIndexPos() {
        return indexPos;
    }

    public void setIndexPos(int indexPos) {
        this.indexPos = indexPos;
    }

    public static int getUserCount() {
        return userCount;
    }

    public static void setUserCount(int userCount) {
        User.userCount = userCount;
    }

    @Override
    public String toString() {
        return user;
    }
}
