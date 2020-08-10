
import java.util.ArrayList;

public class Try {
    public static void main(String[] args) {
        System.out.print(String.format("%-29s","a"));
        System.out.print(String.format("%-28s", "b"));
        System.out.print(String.format("%-28s","c"));
        ArrayList<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.remove(0);
        System.out.println(a.size());
    }

}
