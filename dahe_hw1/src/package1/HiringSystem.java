package package1;

import java.util.Scanner;

/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 1
 */
public class HiringSystem {
    static String userInput;
    static boolean keepGoing;
    static Scanner keyBoard;
    static HiringTable table;
    static Applicant tempApplicant;
    static String[] tempCompany;
    static String[] tempSkills;
    static HiringTable tableBackUp;
    static HiringTable tableBackBackup;

    /**
     * Start  program.
     */

    public static void main (String[] args) throws FullTableException, ApplicantNotFoundException {
        userInput = "";
        keepGoing = true;
        keyBoard = new Scanner(System.in);
        tempCompany = new String[3];
        tempSkills = new String[3];
        table = new HiringTable();
        while(keepGoing)
        {
            runMainMenu();
            processMainMenuInput();
        }
    }

    /**
     * RunMainMenu, display the selections and get
     * them.
     */
    private static void runMainMenu(){
        System.out.println("(A)      Add Applicant");
        System.out.println("(R)      Remove Applicant");
        System.out.println("(G)      Get Applicant");
        System.out.println("(P)      Print List");
        System.out.println("(RS)     Refine Search");
        System.out.println("(S)      Size");
        System.out.println("(B)      Backup");
        System.out.println("(CB)     Compare Backup");
        System.out.println("(RB)     Revert Backup");
        System.out.println("(Q)      Quit");
        userInput = keyBoard.nextLine();

    }

    /**
     * Process the feedback
     */
    private static void processMainMenuInput() throws FullTableException, ApplicantNotFoundException {
        //A
        if(userInput.toLowerCase().equals("a")){
            addNewApplicant();
        }
        //R
        else if(userInput.toLowerCase().equals("r")){
            System.out.println("Enter applicant name: ");
            userInput = keyBoard.nextLine();
            table.removeApplicant(userInput);
            if(table.removeCorrect){
                System.out.println("Applicant " + userInput +
                        " has been successfully removed from the hiring system");
            }

        }
        //G
        else if(userInput.toLowerCase().equals("g")){
            System.out.println("Enter Applicant Name: ");
            userInput = keyBoard.nextLine();
            tempApplicant = table.getApplicant(userInput);
            if(table.getCorrect){
                System.out.println("Applicant Name: " + tempApplicant.getApplicantName());
                System.out.println("Applicant Applying From: " + tempApplicant.CompanyToString()) ;
                System.out.println("Applicant GPA: " + tempApplicant.getApplicantGPA());
                System.out.println("Applicant College: " + tempApplicant.getApplicantCollege());
                System.out.println("Applicant Skills: "+ tempApplicant.SkillsToString());
            }

        }
        //P
        else if(userInput.toLowerCase().equals("p")){
            table.printApplicantTable();
        }
        //RS
        else if(userInput.toLowerCase().equals("rs")){
            refineSearch();
        }
        //S
        else if(userInput.toLowerCase().equals("s")){
            System.out.println("There are "+ table.size()+ " applicants in the hiring system.");
        }
        //B
        else if(userInput.toLowerCase().equals("b")){
            try{
                tableBackBackup = (HiringTable) tableBackUp.clone();
            }catch(Exception e){
                System.out.println("This is the first Back up");
            }
            try{
                tableBackUp = (HiringTable) table.clone();
                System.out.println("Successfully created backup.");
            }catch (CloneNotSupportedException c){
                System.out.println("Back up has failed");
            }
        }
        //CB
        else if(userInput.toLowerCase().equals("cb")){
            if(tableBackUp.size()==table.size()){
                for(int i =0;i<tableBackUp.size();i++){
                    if(!table.getApplicantArray()[i].getApplicantName().equals(tableBackUp.getApplicantArray()[i].getApplicantName())){
                        System.out.println("Current list is NOT the same as the backup copy.");
                        break;
                    }else if(i==tableBackUp.size()-1){
                        System.out.println("Current list is the same as the backup copy.");
                    }
                }
            }else{
                System.out.println("Current list is Not the same as the backup copy.");
            }
        }
        //RB
        else if(userInput.toLowerCase().equals("rb")){
            try{
                tableBackUp = (HiringTable) tableBackBackup.clone();
                System.out.println("Successfully reverted to the backup copy.");
            }catch (CloneNotSupportedException c){
                System.out.println("Revert back up has failed");
            }
        }
        //Q
        else if(userInput.toLowerCase().equals("q")){
            keepGoing = false;
            System.out.println("Bye~");
        }
    }

    private static void refineSearch(){
        String company;
        String skill;
        String college;
        String gpa;
        if(table.size()>0) {
            System.out.println("Enter a company to filter: ");
            company = keyBoard.nextLine();
            System.out.println("Enter a skill to filter for: ");
            skill = keyBoard.nextLine();
            System.out.println("Enter a college to filter for: ");
            college = keyBoard.nextLine();
            System.out.println("Enter the minimum GPA to filter for: ");
            gpa = keyBoard.nextLine();
            if(gpa.equals("")){
                HiringTable.refineSearch(table, company, skill, college, 0);
            }else {
                try {
                    HiringTable.refineSearch(table, company, skill, college, Double.parseDouble(gpa));
                } catch (NumberFormatException e) {
                    System.out.println("This not a standard GPA" + '\n');
                }
            }

        }else{
            System.out.println("There is no Applicant."+'\n');
        }


    }



    private static void addNewApplicant() throws FullTableException {
        tempApplicant = new Applicant();
        tempCompany = new String[3];
        tempSkills = new String[3];
        System.out.println("Enter Applicant Name: ");
        userInput = keyBoard.nextLine();
        tempApplicant.setApplicantName(userInput);
        System.out.println("Enter Applicant GPA: ");
        userInput = keyBoard.nextLine();
        try{
            if(userInput.equals("")){
                tempApplicant.setApplicantGPA(0);
            }else{
                tempApplicant.setApplicantGPA(Double.parseDouble(userInput));
            }
        }catch(Exception e){
            System.out.println("This is not a Standard GPA!!!");
            tempApplicant.GPAcorrect=false;
        }
        if(tempApplicant.GPAcorrect) {
            System.out.println("Enter Applicant College: ");
            userInput = keyBoard.nextLine();
            tempApplicant.setApplicantCollege(userInput);
            System.out.println("Enter up to 3 Companies: ");
            for (int i = 0; i < 3; i++) {
                userInput = keyBoard.nextLine();
                if (!userInput.equals("")) {
                    tempCompany[i] = userInput;
                    if(i<2){
                        System.out.println("Enter up to " + (2 - i) + " Companies: ");
                   }
                }else{
                    break;
                }
            }
            tempApplicant.setCompanyName(tempCompany);
            System.out.println("Enter up to 3 Skills: ");
            for (int i = 0; i < 3; i++) {
                userInput = keyBoard.nextLine();
                if (!userInput.equals("")) {
                    tempSkills[i] = userInput;
                    if(i<2){
                        System.out.println("Enter up to " + (2 - i) + " Skills: ");
                    }
                } else {
                    break;
                }
            }
            tempApplicant.setApplicantSkills(tempSkills);
            table.addApplicant(tempApplicant);
            System.out.println("Applicant " + tempApplicant.getApplicantName() +
                    " has been successfully added to the hiring system");
        }

    }
}
