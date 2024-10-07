public class Employee {

    private String ename;
    private int empno;
    private int sal;
    private String managerName;

    public Employee(String ename, int empno){
        this.ename = ename;
        this.empno = empno;

    }

    public String getEname() {
        return ename;
    }

        public int getEmpno() {
        return empno;
    }

    public int getSal() {
        return sal;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

        public String toString() {
        return "name: " +ename+ " empNo.: "+empno;
    }
}
