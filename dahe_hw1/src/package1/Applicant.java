package package1;



/**
 * @author :Dachuan He
 * SBU ID: 111443457
 * Recitation:  03
 * Homework : 1
 **/

public class Applicant implements Cloneable {
    private String[] companyName;
    private String applicantName;
    private double applicantGPA;
    private String applicantCollege;
    private String[] applicantSkills;
    public boolean GPAcorrect;




    /**
     * Constructor
     * Return an instance of Applicant with no input
     * Postcondition:
     *  An Applicant object with no input is created.
     *
     */

    public Applicant(){ }
    /**
     * Return an instance of Applicant Object with specific inputs
     * @param companyName
     * A String array of the Company names to be added
     * @param applicantName
     * A String of the Name to be added
     * @param applicantGPA
     * A Double value of the GPA to be added
     * @param applicantCollege
     * A String of the College to be added
     * @param applicantSkills
     * A String array of the skills to be added
     */

    public Applicant(String[] companyName, String applicantName, double applicantGPA, String applicantCollege, String[] applicantSkills) {
        this.companyName = companyName;
        this.applicantName = applicantName;
        this.applicantGPA = applicantGPA;
        this.applicantCollege = applicantCollege;
        this.applicantSkills = applicantSkills;
    }

    /**
     *
     * @return A value is a copy of this Applicant object.
     *  Any further changed to the copy will not effect the original and vice versa.
     * @throws CloneNotSupportedException
     *  when the object can not be clone.
     */

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * @param obj
     * @return A value of true if the object that this Applicant
     * is being compared to has the same attributes.
     */

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public double getApplicantGPA() {
        return applicantGPA;
    }

    /**
     * This method would check if the input is correct criteria
     * which is larger then 0 and less than 4.
     * @param applicantGPA
     *  Set the Applicant GPA.
     */

    public void setApplicantGPA(double applicantGPA) {
        GPAcorrect = true;
        try {
            this.applicantGPA = applicantGPA;
            if (applicantGPA < 0 || applicantGPA > 4) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("!!!This is not a standard GPA!!!");
            GPAcorrect = false;
        }

    }

    public String getApplicantCollege() {
        return applicantCollege;
    }

    public void setApplicantCollege(String applicantCollege) {
        this.applicantCollege = applicantCollege;
    }

    public String[] getApplicantSkills() {
        return applicantSkills;
    }

    public void setApplicantSkills(String[] applicantSkills) {
        this.applicantSkills = applicantSkills;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String[] getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String[] companyName) {
        this.companyName = companyName;
    }

    /** Since we are not allowed to use Array.toString
     *  so this for-loop would turn array to String
     *  which separate by comma.
     *
     * @return A String of the Company
     *   and separate by comma.
     */

    public String CompanyToString(){
        StringBuffer companyToString = new StringBuffer();
        for(int i = 0;i<companyName.length;i++){
            if(companyName[i]!=null){
                if(i!=0){
                    companyToString.append(",");
                }
                companyToString.append(companyName[i]);
            }
        }
        return companyToString.toString();

    }

    /** Same as the CompanyToString
     *
     * @return A String of the Skills
     *  and separate by comma.
     */
    public String SkillsToString(){
        StringBuffer skillsToString = new StringBuffer();
        for(int i = 0;i<applicantSkills.length;i++){
            if(applicantSkills[i]!=null){
                if(i!=0){
                    skillsToString.append(",");
                }
                skillsToString.append(applicantSkills[i]);
            }
        }
        return skillsToString.toString();
    }

    @Override
    public String toString() {
        return  String.format("%-26s",CompanyToString())+
                String.format("%-21s",applicantName) +
                String.format("%-21s",applicantGPA) +
                String.format("%-21s",applicantCollege) +
                String.format("%-21s",SkillsToString());
    }

}
