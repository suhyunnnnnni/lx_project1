package project_008;
public class DeptSalaryExtremeStat {
    public int id;
    public String name;
    public int maxSalary;
    public String maxEmpName;
    public int minSalary;
    public String minEmpName;

    @Override
    public String toString() {
        return "부서ID: " + id + ", 부서명: " + name + ", 최고연봉: " + maxSalary + "(" + maxEmpName + ")" + ", 최저연봉: " + minSalary + "(" + minEmpName + ")";
    }
} 