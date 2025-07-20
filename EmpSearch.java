package project_008;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.FileReader;

public class EmpSearch {
    private static Properties props = new Properties();
    static {
        try {
            props.load(new FileReader("db-info.properties"));
        } catch (Exception e) {
            throw new RuntimeException("db-info.properties 파일을 읽는 중 오류 발생", e);
        }
    }
	// 1
	public static List<Emp> getEmpListByDeptNo(int deptNo) throws Exception {
		List<Emp> result = new ArrayList<Emp>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "select e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.salary, d.department_name, e.commission_pct "
				+ "from employees e join departments d " + "on e.department_id = d.department_id "
				+ "where d.department_id = ?";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, deptNo);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					result.add(Emp.fromResultSet(rs));
				}
			}
		}
		return result;
	}

	// 2
	public static List<Emp> getEmpListByName(String empName) throws Exception {
		List<Emp> result = new ArrayList<Emp>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "select e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.salary, d.department_name, e.commission_pct "
				+ "from employees e join departments d " + "on e.department_id = d.department_id "
				+ "where e.first_name = ?";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, empName);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					result.add(Emp.fromResultSet(rs));
				}
			}
		}
		return result;
	}

	// 3
	public static List<Emp> getEmpListByHireYear(int hireYear) throws Exception {
		List<Emp> result = new ArrayList<Emp>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "select e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.salary, d.department_name, e.commission_pct "
				+ "from employees e join departments d " + "on e.department_id = d.department_id "
				+ "where year(e.hire_date) = ?";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, hireYear);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					result.add(Emp.fromResultSet(rs));
				}
			}
		}
		return result;
	}

	// 4
	public static List<Emp> getEmpListByjobId(String jobId) throws Exception {
		List<Emp> result = new ArrayList<Emp>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "select e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.salary, d.department_name, e.commission_pct "
				+ "from employees e join departments d on e.department_id = d.department_id " + "where e.job_id = ?";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, jobId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					result.add(Emp.fromResultSet(rs));
				}
			}
		}
		return result;
	}

	// 5
	public static List<Emp> getEmpListBycityName(String cityName) throws Exception {
		List<Emp> result = new ArrayList<Emp>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "select e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.salary, d.department_name, e.commission_pct "
				+ "from employees e join departments d on e.department_id = d.department_id "
				+ "join locations l on d.location_id = l.location_id " + "where l.city = ?";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, cityName);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					result.add(Emp.fromResultSet(rs));
				}
			}
		}
		return result;
	}

	// 6
	// 부서별 평균 연봉 통계
	public static List<DeptSalaryStat> getDeptAvgSalaryStats() throws Exception {
		List<DeptSalaryStat> result = new ArrayList<>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "SELECT d.department_id, d.department_name, AVG(e.salary) AS avg_salary "
				+ "FROM employees e JOIN departments d ON e.department_id = d.department_id "
				+ "GROUP BY d.department_id, d.department_name";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				DeptSalaryStat stat = new DeptSalaryStat();
				stat.id = rs.getInt("department_id");
				stat.name = rs.getString("department_name");
				stat.avgSalary = rs.getDouble("avg_salary");
				result.add(stat);
			}
		}
		return result;
	}

	// 부서별 평균 근속연수 통계
	public static List<DeptYearsStat> getDeptAvgYearsStats() throws Exception {
		List<DeptYearsStat> result = new ArrayList<>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "SELECT d.department_id, d.department_name, AVG(TIMESTAMPDIFF(YEAR, e.hire_date, NOW())) AS avg_years "
				+ "FROM employees e JOIN departments d ON e.department_id = d.department_id "
				+ "GROUP BY d.department_id, d.department_name";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				DeptYearsStat stat = new DeptYearsStat();
				stat.id = rs.getInt("department_id");
				stat.name = rs.getString("department_name");
				stat.avgYears = rs.getDouble("avg_years");
				result.add(stat);
			}
		}
		return result;
	}

	// 7 - 부서장 성으로 부서원 검색
	public static List<Emp> getEmpListByManagerLastName(String lastName) throws Exception {
		List<Emp> result = new ArrayList<Emp>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "select e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.salary, d.department_name, e.commission_pct "
				+ "from employees e join departments d on e.department_id = d.department_id "
				+ "join employees m on d.manager_id = m.employee_id " + "where m.last_name = ?";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, lastName);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					result.add(Emp.fromResultSet(rs));
				}
			}
		}
		return result;
	}

	// 8 나라 이름으로 그 나라에 근무하는 직원 검색
	public static List<Emp> getEmpListBycountryName(String countryName) throws Exception {
		List<Emp> result = new ArrayList<Emp>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "select e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.salary, d.department_name, e.commission_pct "
				+ "from employees e join departments d on e.department_id = d.department_id "
				+ "join locations l on d.location_id = l.location_id "
				+ "join countries c on l.country_id = c.country_id " + "where c.country_name = ?";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, countryName);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					result.add(Emp.fromResultSet(rs));
				}
			}
		}
		return result;
	}

	// 국가별 평균 연봉 통계
	public static List<CountrySalaryStat> getCountrySalaryStats() throws Exception {
		List<CountrySalaryStat> result = new ArrayList<>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "select country_id, round(AVG(e.salary), 2) AS avg_salary "+
				"from employees e join departments d on e.department_id = d.department_id join locations l on d.location_id = l.location_id " +
				"group by l.country_id";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				CountrySalaryStat stat = new CountrySalaryStat();
				stat.name = rs.getString("country_id");
				stat.avgSalary = rs.getDouble("avg_salary");
				result.add(stat);
			}
		}
		return result;
	}

	// 국가별 직원수 통계
	public static List<CountryPeopleCountStat> getCountryPeopleCount() throws Exception {
		List<CountryPeopleCountStat> result = new ArrayList<>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "select country_id, count(e.employee_id) AS count " +
				"from employees e join departments d on e.department_id = d.department_id join locations l on d.location_id = l.location_id " +
				"group by l.country_id";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				CountryPeopleCountStat stat = new CountryPeopleCountStat();
				stat.name = rs.getString("country_id");
				stat.peopleCount = rs.getInt("count");
				result.add(stat);
			}
		}
		return result;
	}

	// 부서별 최고/최저 연봉 직원 정보
	public static List<DeptSalaryExtremeStat> getDeptSalaryExtremeStats() throws Exception {
		List<DeptSalaryExtremeStat> result = new ArrayList<>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "SELECT d.department_id, d.department_name, " +
				"MAX(e.salary) AS max_salary, " +
				"MIN(e.salary) AS min_salary, " +
				"(SELECT CONCAT(first_name, ' ', last_name) FROM employees WHERE salary = MAX(e.salary) AND department_id = d.department_id LIMIT 1) AS max_emp_name, " +
				"(SELECT CONCAT(first_name, ' ', last_name) FROM employees WHERE salary = MIN(e.salary) AND department_id = d.department_id LIMIT 1) AS min_emp_name " +
				"FROM employees e JOIN departments d ON e.department_id = d.department_id " +
				"GROUP BY d.department_id, d.department_name";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				DeptSalaryExtremeStat stat = new DeptSalaryExtremeStat();
				stat.id = rs.getInt("department_id");
				stat.name = rs.getString("department_name");
				stat.maxSalary = rs.getInt("max_salary");
				stat.maxEmpName = rs.getString("max_emp_name");
				stat.minSalary = rs.getInt("min_salary");
				stat.minEmpName = rs.getString("min_emp_name");
				result.add(stat);
			}
		}
		return result;
	}

	// 근속연수 Top 5 직원 랭킹
	public static List<Emp> getTop5LongestEmployees() throws Exception {
		List<Emp> result = new ArrayList<>();
		Class.forName(props.getProperty("driverClassName"));
		String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, e.hire_date, e.salary, d.department_name, e.commission_pct, TIMESTAMPDIFF(YEAR, e.hire_date, NOW()) as years " +
				"FROM employees e JOIN departments d ON e.department_id = d.department_id " +
				"ORDER BY years DESC LIMIT 5";
		try (Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("userName"), props.getProperty("password"));
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				result.add(Emp.fromResultSet(rs, true));
			}
		}
		return result;
	}
}
