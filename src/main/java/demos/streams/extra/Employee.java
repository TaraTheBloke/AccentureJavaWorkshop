package demos.streams.extra;

import java.util.Arrays;
import java.util.List;

public class Employee {

    private final String name;
    private final int salaryBand;
    private final Department department;
    private final List<Address> addresses;

    public Employee(String name, int salaryBand, Department department, Address... addresses) {
        this.name = name;
        this.salaryBand = salaryBand;
        this.department = department;
        this.addresses = Arrays.asList(addresses);
    }

    public String getName() {
        return name;
    }

    public int getSalaryBand() {
        return salaryBand;
    }

    public Department getDepartment() {
        return department;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return name;
    }
}
