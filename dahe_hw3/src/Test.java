import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    static String data;
    static String line;
    public static void main(String[] args){
        String a = "  {     *int i = 0*k = 5; ";
        String[] b;

        b = a.replace("{","").split("\\*");
        for(int i=0; i <b.length;i++){
                if (b[i].contains("int ")){
                    System.out.println(b[i].replace(" ","").substring(3).replace(";",""));
                }else{
                    System.out.println(b[i].replace(" ","").replace(";",""));
                }
            }

        try{
            FileInputStream fis = new FileInputStream("sample2.text");
            InputStreamReader inStream = new InputStreamReader(fis);
            BufferedReader stdin = new BufferedReader(inStream);

            while((data = stdin.readLine())!=null){
                line = data;
                if(line.contains("}")){
                    System.out.println("!11111111");
                }
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println("asdasd");

        }




    }


}
