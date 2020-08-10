package package1;



/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 1
 */
public class HiringTable implements Cloneable {
    private Applicant[] applicantArray;
    private int applicantNum;
    static int MAX_APPLICANTS = 50;
    static int MAX_SKILLS = 3;
    static int MAX_COMPANIES =3;
    public boolean removeCorrect;
    public boolean getCorrect;
    public boolean refineCorrect;

    /**
     * Return an instance of HiringTable
     * Postcondition:
     *  A HiringTable object created and
     *  the default applicant array length
     *  would be 50.
     */
    public HiringTable() {
        applicantArray = new Applicant[50];
        applicantNum = 0;
    }

    /** ApplicantNum is the length of the applicants
     * which has been activated in the applicant array.
     *
     * Precondition:
     *  The HiringTable has been instantiated.
     *
     * @return The number of Applicants objects in this HiringTable.
     */

    public int size() {
        return applicantNum;
    }

    /**
     * Precondition:
     *  The HiringTable has been instantiated.
     *
     * Postcondition:
     *  Add a new applicant in to the applicant array.
     *
     * @param newApplicant
     *  An applicant object to be added.
     * @throws FullTableException
     *  There is no more room in the HiringTable for new Applicants.
     *
     */

    public void addApplicant(Applicant newApplicant) throws FullTableException {
        try {
            if(applicantNum+1>MAX_APPLICANTS){
                throw new FullTableException();
            }else{
                applicantArray[applicantNum]=newApplicant;
                applicantNum+=1;
            }

        } catch (FullTableException e) {
            System.out.println("FullTableException");
        }
    }

    /**
     * Precondition:
     *  The HiringTable has been instantiated.
     *
     * Poscondition:
     *  Search the input in the applicant as applicant Name
     *  remove this applicant object and
     *  let the rest applicants up forward one position.
     * @throws ApplicantNotFoundException
     * The applicant not found.
     */

    public void removeApplicant(String name)throws ApplicantNotFoundException{
        removeCorrect = true;
        int a = 0;
        try {
            if(applicantNum==0){
                removeCorrect = false;
                throw new ApplicantNotFoundException();
            }
            for (int i = 0; i <= applicantNum; i++) {
                if (applicantArray[i].getApplicantName().toLowerCase().equals(name.toLowerCase())) {
                    a = i;
                    break;
                } else if (i == applicantNum) {
                    throw new ApplicantNotFoundException();
                }
            }
            if (applicantNum - 1 - a >= 0)
                System.arraycopy(applicantArray, a + 1, applicantArray, a, applicantNum - 1 - a);
            applicantArray[applicantNum - 1] = null;
            applicantNum -=1;
        } catch (ApplicantNotFoundException e) {
            removeCorrect = false;
            System.out.println("Applicant has not found"+'\n');
        }
    }

    /**Preconditions:
     *  The HiringTable object has been instantiated.
     *
     * @param name
     * name of the Applicant to retrieve
     * @return
     * The applicant with the corresponding name
     * @throws ApplicantNotFoundException
     *  The applicant not found
     */

    public Applicant getApplicant(String name) throws ApplicantNotFoundException {
        getCorrect =true;
        int a = 0;
        try {
            for (int i = 0; i <= applicantNum; i++) {
                if (applicantArray[i].getApplicantName().toLowerCase().equals(name.toLowerCase())) {
                    a = i;
                    break;
                } else if (i == applicantNum) {
                    throw new ApplicantNotFoundException();
                }
            }
            return applicantArray[a];
        } catch (ApplicantNotFoundException e) {
            System.out.print("Applicant has not found" +'\n');
            getCorrect = false;
            return  null;

        }
    }

    /**
     * Prints all the Applicant objects that match the requested criteria.
     *
     * Preconditions:
     *
     * The HiringTable object has been instantiated.
     *
     * @param table
     *  The list of applicants to search in
     * @param company
     * The company must be in the Applicant's application
     * @param skill
     * The skill that must be in the Applicant's application
     * @param college
     * The college that must be in the Applicant's application
     * @param GPA
     * The minimum GPA that must be in the Applicant's application
     *
     * Postconditions:
     *  Displays a neatly formatted table of each Applicant filtered from the HiringTable.
     *
     */

    public static void refineSearch(HiringTable table, String company, String skill, String college, double GPA) {
        Applicant[] tempArray = new Applicant[MAX_APPLICANTS]; // tempArray for the company
        Applicant[] tempArray1 = new Applicant[MAX_APPLICANTS]; // tempArray1 for the skill
        Applicant[] tempArray2 = new Applicant[MAX_APPLICANTS]; // tempArray2 for the college
        Applicant[] tempArray3 = new Applicant[MAX_APPLICANTS]; // tempArray3 for the GPA
        int a = 0; //length of tempArray
        int b = 0; //length of tempArray1
        int c = 0; //length of tempArray2
        int d = 0; //length of tempArray3
        for (int i = 0; i < table.size(); i++) {
            if(table.applicantArray[i]==null){
                break;
            } else if (table.applicantArray[i].getCompanyName()[0]!=null&&table.applicantArray[i].getCompanyName()[0].toLowerCase().equals(company.toLowerCase())) {
                tempArray[a] = table.applicantArray[i];
                a++;
            } else if (table.applicantArray[i].getCompanyName()[1]!=null&&table.applicantArray[i].getCompanyName()[1].toLowerCase().equals(company.toLowerCase())) {
                tempArray[a] = table.applicantArray[i];
                a++;
            } else if (table.applicantArray[i].getCompanyName()[2]!=null&&table.applicantArray[i].getCompanyName()[2].toLowerCase().equals(company.toLowerCase())) {
                tempArray[a] = table.applicantArray[i];
                a++;
            } else if (company.equals("")) {
                a = table.size();
                System.arraycopy(table.applicantArray, 0, tempArray, 0, table.size());
                break;
            }
        }
        if (a != 0){
            for (int i = 0; i < a; i++) {
                if (tempArray[i].getApplicantSkills()[0]!=null&&tempArray[i].getApplicantSkills()[0].toLowerCase().equals(skill.toLowerCase())) {
                    tempArray1[b] = tempArray[i];
                    b++;
                } else if (tempArray[i].getApplicantSkills()[1]!=null&&tempArray[i].getApplicantSkills()[1].toLowerCase().equals(skill.toLowerCase())) {
                    tempArray1[b] = tempArray[i];
                    b++;
                } else if (tempArray[i].getApplicantSkills()[2]!=null&&tempArray[i].getApplicantSkills()[2].toLowerCase().equals(skill.toLowerCase())) {
                    tempArray1[b] = tempArray[i];
                    b++;
                } else if (skill.equals("")) {
                    b = a;
                    System.arraycopy(tempArray, 0, tempArray1, 0, a);
                    break;
                }
            }
            if (b != 0) {
                for (int i = 0; i < b; i++) {
                    if (tempArray1[i].getApplicantCollege()!=null&&tempArray1[i].getApplicantCollege().toLowerCase().equals(college.toLowerCase())) {
                        tempArray2[c] = tempArray1[i];
                        c++;
                    } else if (college.equals("")) {
                        c = b;
                        System.arraycopy(tempArray1, 0, tempArray2, 0, b);
                        break;
                    }
                }
                if (c != 0) {
                        for (int i = 0; i < c; i++) {
                            if (tempArray1[i].getApplicantGPA() > GPA) {
                                tempArray3[d] = tempArray1[i];
                                d++;
                            } else if (GPA==0) {
                                d = c;
                                System.arraycopy(tempArray2, 0, tempArray3, 0, c);
                                break;
                            }
                        }if(d!=0){
                            printHeading();
                        for (Applicant applicant : tempArray3) {
                            if (applicant != null) {
                                System.out.println(applicant.toString());
                            }
                        }
                        }else{
                            System.out.println("There is no GPA meets the requirement");
                        }
                }else{
                    System.out.println("There is no college meets the requirement");
                }
            }else{
                System.out.println("There is No Skills meets the requirement");
            }
        } else{
            System.out.println("There is No company meets the requirement");
        }
    }

    public Applicant[] getApplicantArray() {
        return applicantArray;
    }

    /**Preconditions:
     * This HiringTable has been instantiated.
     *
     * @return A copy of this HiringTable object.
     * @throws CloneNotSupportedException
     * when the object can not be clone.
     */
    public Object clone()throws CloneNotSupportedException {
        return super.clone();
    }

    /** Prints a neatly formatted table of each item in the list as shown in the sample output.
     *
     * Preconditions:
     * The HiringTable has been instantiated.
     *
     * Postconditions:
     * Displays a neatly formatted table of each Applicant from the HiringTable.
     */

    public void printApplicantTable(){
        printHeading();
        for(int i=0; i< applicantNum;i++){
            System.out.print(applicantArray[i].toString()+"\n");
        }

    }

    /**
     * It's just easier to print the heading.
     */
    private static void printHeading(){
        System.out.println();
        System.out.printf("%-26s%-21s%-21s%-21s%-21s","CompanyName","Applicant","GPA",
                "College","Skills");
        System.out.println();
        System.out.println("--------------------------------------------" +
                "--------------------------------------------------");
    }





}
