import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        UI ui = new UI();

//        ui.empNameNumber();
//        System.out.println("");
//        ui.empNameNumberStartsWithS();
//        System.out.println();
//        ui.totalSalary();
//        ui.empWithHighestSal();
//        ui.empWhoEarnMoreThanAverage();
        ui.empNames("Blake");
        ui.empNames("kING");


//        EmployeeRepository er = new EmployeeRepository();
//        er.empNameNumber();
//        er.empNameNumberStartsWithS();
//        er.totalSalary();
//        er.empWithHighestSal();
//        er.empWhoEarnMoreThanAverage();
//        er.empNames("Ford");
//        er.deptWithMoreThanFiveEmp();



    }
}
