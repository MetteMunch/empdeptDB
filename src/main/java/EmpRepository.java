import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpRepository {
    Connection con;
    List<Employee> searchList;

    public EmpRepository() {
        getConnection();
        searchList = new ArrayList<>();

    }

    public void getConnection() {
        String database = "jdbc:mysql://localhost:3306/emp_dept";
        String username = "mmh";
        String password = "mmh1974";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(database, username, password);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    //En metode, der udskriver medarbejdernummer og -navn på alle medarbejdere.
    public List<Employee> empNameNumber() {
        searchList.clear();

        try {
            String SQL = "SELECT ename, empno FROM emp";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Employee emp = new Employee(rs.getString("ENAME"), rs.getInt("EMPNO"));
                searchList.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return searchList;

    }
// En metode, der udskriver medarbejdernummer og -navn på medarbejdere, der starter med ”S”.

    public List<Employee> empNameNumberStartsWithS() {
        searchList.clear();
        try {
            String SQL = "SELECT ename, empno FROM emp WHERE ename LIKE 'S%' ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Employee emp = new Employee(rs.getString("ENAME"), rs.getInt("EMPNO"));
                searchList.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return searchList;

    }

    //En metode, der udskriver den samlede lønudgift for alle medarbejdere.
    public int totalSalary() {
        int result = 0;
        try {
            String SQL = "SELECT SUM(sal) AS totalSal FROM emp";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            if (rs.next()) { //Når der kun returneres et resultat på forespørgslen, så kun nødvendigt med if
                result = rs.getInt("totalSal");
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return result;
    }

    //En metode der udskriver navnet på den højst lønnede medarbejder
    public Employee empWithHighestSal() {
        Employee emp = null;
        try {
            String SQL = "SELECT ename, empno, sal FROM emp ORDER BY sal DESC LIMIT 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            if (rs.next()) {
                emp = new Employee(rs.getString("ENAME"), rs.getInt("EMPNO"));
                emp.setSal(rs.getInt("SAL"));
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return emp;

    }

    //En metode, der udskriver antal medarbejdere som tjener mere end gennemsnittet
    public int empWhoEarnMoreThanAverage() {
        int result = 0;
        try {
            String SQL = "SELECT COUNT(ENAME) AS 'Count employees' FROM emp WHERE sal > (SELECT AVG(sal) FROM emp);";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            if (rs.next()) {
                result = rs.getInt("Count employees");
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return result;
    }

    //En metode, der udskriver navnet på medarbejdere med en given manager (managers navn gives som parameter til metoden)
    public List<Employee> empNames(String managerName) {
        searchList.clear();
        try {
            String SQL = "SELECT ename, empno FROM emp WHERE mgr = (SELECT empno FROM emp WHERE ename = ?)";
            PreparedStatement ps = con.prepareStatement(SQL); //når vi i en forespørgsel / metode kræver et argument.
            // Dette sikrer at variablen "managerName" indsættes korrekt i SQL forespørgslen, og for at undgå SQL-injektion.
            ps.setString(1, managerName.toUpperCase()); //Denne set erstatter pladsholder (?) med værdien af variablen
            ResultSet rs = ps.executeQuery(); //Når vi benytter PreparedStatement skal SQL koden ikke medsendes her i executeQuery

            while (rs.next()) {
                Employee emp = new Employee(rs.getString("ENAME"), rs.getInt("EMPNO"));
                emp.setManagerName(managerName);
                searchList.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return searchList;
    }

    //En metode, der udskriver afdelingsnavn på afdeling med mere end 5 medarbejdere.
    public void deptWithMoreThanFiveEmp() {
        try {
            String SQL = "SELECT count(empno) AS 'Employees', dname FROM emp \n" +
                    "INNER JOIN dept \n" +
                    "ON emp.deptno = dept.deptno\n" +
                    "GROUP BY dname\n" +
                    "HAVING Employees > 5";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println("Department with more than 5 employees: " + rs.getString("DNAME") + " Number of employees: " + rs.getInt("Employees"));
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }


    }


}
