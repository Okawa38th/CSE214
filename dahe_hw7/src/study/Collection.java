package study;

import java.util.ArrayList;
import java.util.Collections;


public class Collection {

        public static void main(String[] args) {
            ArrayList staff = new ArrayList();

            staff.add(new Employee("Joe",100000, 177700010));
            staff.add(new Employee("Jane",200000, 111100010));
            staff.add(new Employee("Bob",66666, 1999000010));
            staff.add(new Employee("Andy",77777, 188800010));

            Collections.sort(staff);                                      // Sort by salary
            System.out.println("Lowest paid employee: "+staff.get(0));    // Prints Bob

           // Collections.sort(staff, new NameComparator());                // Sort by aplahabetical order
            System.out.println("First employee in list: "+staff.get(0));  // Prints Andy

            Collections.sort(staff, new IdComparator());                  // Sort by ID number
            System.out.println("Employee with lowest ID: "+staff.get(0)); // Prints Jane
        }

}
