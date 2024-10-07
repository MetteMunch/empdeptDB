import java.util.List;

public class UI {


    EmpRepository empRep;

    public UI() {
        empRep = new EmpRepository();
    }


    public void empNameNumber(){
        List<Employee> employees = empRep.empNameNumber();

        for(Employee emp: employees){
            System.out.println(emp);

        }
    }

    public void empNameNumberStartsWithS(){
        List<Employee> employees = empRep.empNameNumberStartsWithS();

        for(Employee emp: employees){
            System.out.println(emp);
        }
    }

    public void totalSalary() {
        System.out.println("Total salary: " +empRep.totalSalary());
    }

    public void empWithHighestSal() {
        Employee emp = empRep.empWithHighestSal();
        System.out.println("Employee Name: " +emp.getEname()+ " Salary: "+emp.getSal());
    }

    public void empWhoEarnMoreThanAverage() {
        System.out.println("Number of employees who earn more than average salary: " +empRep.empWhoEarnMoreThanAverage());
    }

    public void empNames(String managerName){
        List<Employee> employees = empRep.empNames(managerName);

        for(Employee emp: employees){
            System.out.println(emp + " has manager: " +emp.getManagerName());
        }
    }
}
