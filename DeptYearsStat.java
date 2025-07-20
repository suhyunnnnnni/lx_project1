package project_008;
public class DeptYearsStat {
    public int id;
    public String name;
    public double avgYears;

    @Override
    public String toString() {
        return "Department ID: " + id + ", Department Name: " + name + ", Average Years: " + String.format("%.2f", avgYears);
    }
} 