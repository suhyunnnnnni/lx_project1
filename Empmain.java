package project_008;

import java.util.List;
import java.util.Scanner;




public class Empmain {
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
            System.out.println("==== 직원 관리 시스템 ====");
            System.out.println("1. 부서 번호로 검색");
            System.out.println("2. 직원 이름으로 검색");
            System.out.println("3. 입사 년도로 검색");
            System.out.println("4. 직무로 검색");
            System.out.println("5. 도시 이름으로 검색");
            System.out.println("6. 통계자료");
            System.out.println("7. 부서장 성으로 부서원 검색");
            System.out.println("8. 나라 이름으로 근무하는 직원 검색");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택: ");
            String menuInput = sc.nextLine();
            int menu;
            try {
            	menu = Integer.parseInt(menuInput);
            } catch(NumberFormatException e) {
            	System.out.println("숫자를입력하세요");
            	continue;
            }
            switch (menu) {
            	case 1: 
            		while (true) {
            			System.out.println("부서 번호를 입력하세요");
            			try {
            				String input = sc.nextLine();
            				if (input.trim().isEmpty()) { //trim()은 앞뒤 공백 제거해주는 건데 이거 없으니가 ("")값은 잡고, (" ")값은 못잡길래 추가했음!
            					System.out.println("공백을 입력하여 오류가 발생했습니다. 부서 번호는 정수로 입력해야 합니다.");
            					continue;
            				}
            				int deptNo = Integer.parseInt(input);
            				List<Emp> result = EmpSearch.getEmpListByDeptNo(deptNo);
                            if (result.isEmpty()) {
                                System.out.println("해당하는 부서가 없습니다.");
                            } else {
                                for (Emp emp : result) {
                                    System.out.println(emp);
                                }
                            }
            				break; // 정상 입력 시 반복 종료
            			} catch (NumberFormatException e) {
            				System.out.println("정수가 아닌 값을 입력하여 오류가 발생했습니다. 부서 번호는 정수로 입력해야 합니다.");
            			} catch (Exception e) {
            				System.out.println("알 수 없는 오류가 발생했습니다: " + e.getMessage());
            			}
            		}
            		break;
            	case 2:
            		while (true) {
            			System.out.println("직원 이름을 입력하시오");
            			try {
            				String empName = sc.nextLine();
            				if (empName.trim().isEmpty()) {
            					System.out.println("공백을 입력하여 오류가 발생했습니다. 직원 이름을 입력해야 합니다.");
            					continue;
            				}
            				List<Emp> result = EmpSearch.getEmpListByName(empName);
                            if (result.isEmpty()) {
                                System.out.println("해당하는 직원이 없습니다.");
                            } else {
                                for (Emp emp : result) {
                                    System.out.println(emp);
                                }
                            }
            				break;
            			} catch (Exception e) {
            				System.out.println("오류가 발생했습니다: " + e.getMessage());
            			}
            		}
            		break;
            		
            	case 3:
            		while (true) {
            			System.out.println("입사 년도를 입력하시오");
            			try {
            				String input = sc.nextLine();
            				if (input.trim().isEmpty()) {
            					System.out.println("공백을 입력하여 오류가 발생했습니다. 입사 년도는 정수로 입력해야 합니다.");
            					continue;
            				}
            				int hireYear = Integer.parseInt(input);
            				List<Emp> result = EmpSearch.getEmpListByHireYear(hireYear);
                            if (result.isEmpty()) {
                                System.out.println("해당하는 입사 년도가 없습니다.");
                            } else {
                                for (Emp emp : result) {
                                    System.out.println(emp);
                                }
                            }
            				break;
            			} catch (NumberFormatException e) {
            				System.out.println("정수가 아닌 값을 입력하여 오류가 발생했습니다. 입사 년도는 정수로 입력해야 합니다.");
            			} catch (Exception e) {
            				System.out.println("오류가 발생했습니다: " + e.getMessage());
            			}
            		}
            		break;
            		
            	case 4:
            		while (true) {
            			System.out.println("직무 번호를 입력하세요");
            			try {
            				String jobId = sc.nextLine();
            				if (jobId.trim().isEmpty()) {
            					System.out.println("공백을 입력하여 오류가 발생했습니다. 직무 번호를 입력해야 합니다.");
            					continue;
            				}
            				List<Emp> result = EmpSearch.getEmpListByjobId(jobId);
                            if (result.isEmpty()) {
                                System.out.println("해당하는 직무가 없습니다.");
                            } else {
                                for (Emp emp : result) {
                                    System.out.println(emp);
                                }
                            }
            				break;
            			} catch (Exception e) {
            				System.out.println("오류가 발생했습니다: " + e.getMessage());
            			}
            		}
            		break;
            		
            	case 5:
            		while (true) {
            			System.out.println("도시 이름을 입력하세요.");
            			try {
            				String cityName = sc.nextLine();
            				if (cityName.trim().isEmpty()) {
            					System.out.println("공백을 입력하여 오류가 발생했습니다. 도시 이름을 입력해야 합니다.");
            					continue;
            				}
            				List<Emp> result = EmpSearch.getEmpListBycityName(cityName);
                            if (result.isEmpty()) {
                                System.out.println("해당하는 도시가 없습니다.");
                            } else {
                                for (Emp emp : result) {
                                    System.out.println(emp);
                                }
                            }
            				break;
            			} catch (Exception e) {
            				System.out.println("오류가 발생했습니다: " + e.getMessage());
            			}
            		}
            		break;
            		
            	case 6:
                EmpStatistics.showStatisticsMenu();
                break;
            		
            	case 7:
            		while (true) {
            			System.out.println("부서장 성을 입력하세요.");
            			try {
            				String lastName = sc.nextLine();
            				if (lastName.trim().isEmpty()) {
            					System.out.println("공백을 입력하여 오류가 발생했습니다. 부서장 성을 입력해야 합니다.");
            					continue;
            				}
            				List<Emp> result = EmpSearch.getEmpListByManagerLastName(lastName);
                            if (result.isEmpty()) {
                                System.out.println("해당하는 부서장이 없습니다.");
                            } else {
                                for (Emp emp : result) {
                                    System.out.println(emp);
                                }
                            }
            				break;
            			} catch (Exception e) {
            				System.out.println("오류가 발생했습니다: " + e.getMessage());
            			}
            		}
            		break;
            	case 8:
            		while (true) {
            			System.out.println("나라 이름을 입력하세요.");
            			try {
            				String countryName = sc.nextLine();
            				if (countryName.trim().isEmpty()) {
            					System.out.println("공백을 입력하여 오류가 발생했습니다. 나라 이름을 입력해야 합니다.");
            					continue;
            				}
            				List<Emp> result = EmpSearch.getEmpListBycountryName(countryName);
                            if (result.isEmpty()) {
                                System.out.println("해당하는 나라가 없습니다.");
                            } else {
                                for (Emp emp : result) {
                                    System.out.println(emp);
                                }
                            }
            				break;
            			} catch (Exception e) {
            				System.out.println("오류가 발생했습니다: " + e.getMessage());
            			}
            		}
            		break;
            		
            	case 0:
            		 System.out.println("프로그램을 종료합니다.");
            		 return;
            		 
            	default:
            	 System.out.println("잘못된 입력입니다.");
            }
            
            }
	}
}
