package demos.collections.streams.extra;

import static demos.collections.streams.extra.Department.HR;
import static demos.collections.streams.extra.Department.IT;
import static demos.collections.streams.extra.Department.SALES;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.BaseStream;

public class Utils {

    public static void printResults(String msg, BaseStream<?, ?> results) {
        System.out.printf("%s\n\t", msg);
        Iterator<?> iter = results.iterator();
        while (iter.hasNext()) {
            System.out.printf("%s ", iter.next());
        }
        System.out.println();
    }

    public static List<Employee> buildStaff() {
        ArrayList<Employee> staff = new ArrayList<>();
        staff.add(new Employee("Dave", 1, IT, new Address(10, "Arcatia Road", "London")));
        staff.add(new Employee("Jane", 2, HR, new Address(11, "Arcatia Road", "London")));
        staff.add(new Employee("Fred", 1, SALES, new Address(12, "Arcatia Road", "London")));
        staff.add(new Employee("Mary", 2, IT, new Address(20, "University Street", "Belfast")));
        staff.add(new Employee("Pete", 1, HR, new Address(21, "University Street", "Belfast")));
        staff.add(new Employee("Evie", 2, SALES, new Address(22, "University Street", "Belfast")));
        return staff;
    }
}
