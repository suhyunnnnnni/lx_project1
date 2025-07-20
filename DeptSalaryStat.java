package project_008;

public class DeptSalaryStat {
    public int id;
    public String name;
    public double avgSalary;

    @Override
    public String toString() {
        return "Department ID: " + id + ", Department Name: " + name + ", Average Salary: " + String.format("%.2f", avgSalary);
    }
} 