
import java.io.*;
import java.util.Scanner;


/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 3
 */
public class BlockTracer {

    static String fileName;
    static Scanner keyBoard;
    static String data;
    static FileInputStream fis;
    static InputStreamReader inStream;
    static BufferedReader stdin;
    static boolean keepReading;
    static boolean keepFinding;
    static Block block;
    static Stack stack;
    static Stack tempStack;
    static Variable variable;
    static String[] strArray;
    static String[] intStrArray;
    static String[] spiltLine;
    static String currentLine;
    static int tempInt;
    static String str;

    /**
     * Start  program.
     */

    public static void main(String[] args){
        stack = new Stack();
        keepReading = true;
        keyBoard = new Scanner(System.in);
        System.out.println("Type your file name(Don't need to type (.text))");
        while(keepReading){
            readLines();
        }


    }

    /**
     * Input the following lines
     */

    private static void readLines(){
        fileName = keyBoard.nextLine();
        try{
            fis = new FileInputStream(fileName+".text");
            inStream = new InputStreamReader(fis);
            stdin = new BufferedReader(inStream);
            while((data=stdin.readLine())!=null){
                processThisLine(data);
            }
            stdin.close();
            inStream.close();
            keepReading = false;
        }catch(IOException e){
            System.out.println("File has not Found.");
            keepReading = false;

        }

    }

    /**
     * This part will check the current line is either
     * "int","{","}" or "print".
     * @param line
     *  Current line which read by the text file.
     */

    public static void processThisLine(String line){
        if(line.contains(";")&& line.contains("/")){
            spiltLine = line.split(";");
            for (int i = 0; i<spiltLine.length; i++){
                processThisLine(spiltLine[i]);
            }
        }else{
            if(line.contains("{")){
                block = new Block();
                stack.push(block);
            }if(line.contains("int ")&&!line.contains("/*$print ")){
                currentLine = line;
                addInt();
            }if(line.contains("/*$print ")){
                keepFinding = true;
                currentLine = line;
                printInt();
            }if(line.contains("}")){
                stack.pop();
            }
        }

    }

    /**
     * Recognize the int variable and put it in to Block.
     * First String array(strArray) separate the lines by ",".
     * second String array (intArray) separate each variables by "=".
     */
    public static  void addInt(){
        variable = new Variable();
        strArray = currentLine.replace("{","").split(",");
        for(int i = 0 ; i<strArray.length;i++){
            if(i==0) {
                str = strArray[i].replaceAll("\\s*","").substring(3).replace(";","");
            }else {
                str = strArray[i].replaceAll("\\s*", "").replace(";","");
            }
            if(!str.contains("=")){
                variable = new Variable(str,0);
            }else{
                intStrArray = str.split("=");
                tempInt = Integer.parseInt(intStrArray[1]);
                variable = new Variable(intStrArray[0],tempInt);
            }
            block.variables.add(variable);
        }
    }

    /**
     * Print all variable in the block or the special Variable from all block.
     */

    public static void printInt() {
        strArray = currentLine.split("/");
        intStrArray = strArray[1].split(" ");
        if (intStrArray[1].contains("LOCAL*")) {
            block = stack.peek();
            if (block.getSize()!=0) {
                System.out.printf("%-26s%-21s", "Variable Name", "Initial Value");
                System.out.println();
                for (int i = 0; i < block.getSize(); i++) {
                    System.out.println(block.variables.get(i));
                }
                System.out.println();
            } else {
                System.out.println("No local variables to print.  ");
                System.out.println();
            }

        }else {
            tempStack = new Stack();
            str = intStrArray[1].replace("*", "");
            while(stack.getSize()>0){
                block = stack.peek();
                for (int i=0;i<block.getSize();i++){
                    if (block.variables.get(i).getName().equals(str)){
                        System.out.printf("%-26s%-"+(21*3)+"s", "Variable Name", "Initial Value");
                        System.out.println();
                        System.out.println(block.variables.get(i).toString()+'\n');
                        while(!stack.isEmpty()){
                            tempStack.push(stack.pop());
                        }
                    }
                }if(!stack.isEmpty()){
                    tempStack.push(stack.pop());
                }
            }
            while (!tempStack.isEmpty()){
                stack.push(tempStack.pop());
            }
        }

    }
}
