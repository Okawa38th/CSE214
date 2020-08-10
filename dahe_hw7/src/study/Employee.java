package study;

public class Employee implements Comparable {
    private String name;
    private int salary;
    private int id;
    public Employee(String initName, int initSal, int initId) {
        id     = initId;
        name = initName;
        salary = initSal;
    }
    public String getName(){ return name; }
    public int getSalary() { return salary; }
    public int getId(){ return id; }
    public void setSalary(int newSalary) {
        salary = newSalary;
    }
    public int compareTo(Object o) {
        Employee otherEmp = (Employee)o;
        if (this.salary == otherEmp.salary)
            return 0;
        else if (this.salary > otherEmp.salary)
            return 1;
        else
            return -1;
    }
    public String toString() {
        return name + ", $" + salary + ", "+ id;
    }
}