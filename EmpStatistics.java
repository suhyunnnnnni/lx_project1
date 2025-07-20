package project_008;

import java.util.List;
import java.util.Scanner;

public class EmpStatistics {
    public static void showStatisticsMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==== 통계 자료 ====");
            System.out.println("1. 부서별 평균 연봉");
            System.out.println("2. 부서별 평균 근속연수");
            System.out.println("3. 국가별 평균 연봉");
            System.out.println("4. 국가별 직원수");
            System.out.println("5. 부서별 최고/최저 연봉 직원 정보");
            System.out.println("6. 근속연수 Top 5 직원 랭킹");
            System.out.println("0. 이전 메뉴로");
            System.out.print("메뉴 선택: ");
            try {
                int menu = sc.nextInt();
                switch (menu) {
                    case 1:
                        printDeptAvgSalary();
                        break;
                    case 2:
                        printDeptAvgYears();
                        break;
                    case 3:
                        printCountryAvgSalary();
                        break;
                    case 4:
                        printCountryPeopleCount();
                        break;
                    case 5:
                        printDeptSalaryExtremes();
                        break;
                    case 6:
                        printTop5LongestEmployees();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("정수를 입력하세요.");
                sc.nextLine(); //여기서 이걸 한번 더 안해주니까 초기로 돌아갔을 때 계속 기존 값이 남아서 오류 문한루프가 되더라고..
                               //즉 abc를 입력하면 catch절에서 정수를 입력하시오가 뜨고 while 문 처음으로 돌아갔을 때 abc가 남은채로 있게됨됨
            }
        }
    }

    private static void printDeptAvgSalary() {
        try {
            List<DeptSalaryStat> stats = EmpSearch.getDeptAvgSalaryStats();
            System.out.println("[부서별 평균 연봉]");
            for (DeptSalaryStat stat : stats) {
                System.out.println(stat);
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }

    private static void printDeptAvgYears() {
        try {
            List<DeptYearsStat> stats = EmpSearch.getDeptAvgYearsStats();
            System.out.println("[부서별 평균 근속연수]");
            for (DeptYearsStat stat : stats) {
                System.out.println(stat);
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }

    private static void printCountryAvgSalary() {
        try {
            List<CountrySalaryStat> stats = EmpSearch.getCountrySalaryStats();
            System.out.println("[국가별 평균 연봉]");
            for (CountrySalaryStat stat : stats) {
                System.out.println(stat);
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }

    private static void printCountryPeopleCount() {
        try {
            List<CountryPeopleCountStat> stats = EmpSearch.getCountryPeopleCount();
            System.out.println("[국가별 직원수]");
            for (CountryPeopleCountStat stat : stats) {
                System.out.println(stat);
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }

    // 부서별 최고/최저 연봉 직원 정보
    private static void printDeptSalaryExtremes() {
        try {
            List<DeptSalaryExtremeStat> stats = EmpSearch.getDeptSalaryExtremeStats();
            System.out.println("[부서별 최고/최저 연봉 직원 정보]");
            for (DeptSalaryExtremeStat stat : stats) {
                System.out.println(stat);
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }

    // 근속연수 Top 5 직원 랭킹
    private static void printTop5LongestEmployees() {
        try {
            List<Emp> stats = EmpSearch.getTop5LongestEmployees();
            System.out.println("[근속연수 Top 5 직원 랭킹]");
            int rank = 1;
            for (Emp emp : stats) {
                System.out.println(rank + "위: " + emp);
                rank++;
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }
} 