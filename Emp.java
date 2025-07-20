package project_008;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Emp {
	
	public int id;
	public String firstName;
	public String lastName;	
	public String email;
	public String phone;
	public String hireDate;
	public int salary;
	public String deptName;
	public double commissionPct;
	public int years;
	
	@Override
	public String toString() {
		return "Emp [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone="
				+ phone + ", hireDate=" + hireDate + ", salary=" + salary + ", deptName=" + deptName + "]";
	}

    public static Emp fromResultSet(ResultSet rs) throws SQLException {
        Emp emp = new Emp();
        emp.id = rs.getInt(1);
        emp.firstName = rs.getString(2);
        emp.lastName = rs.getString(3);
        emp.email = rs.getString(4);
        emp.phone = rs.getString(5);
        emp.hireDate = rs.getString("hire_date");
        emp.salary = rs.getInt(7);
        emp.deptName = rs.getString(8);
        emp.commissionPct = rs.getDouble(9); // 추가
        return emp;
    }
    // 근속연수 Top 5 직원 랭킹 조회 시 사용(years 변수 포함됨됨)
    public static Emp fromResultSet(ResultSet rs, boolean Years) throws SQLException {
        Emp emp = fromResultSet(rs);
        if (Years) {
            emp.years = rs.getInt(10);
        }
        return emp;
    }
}
