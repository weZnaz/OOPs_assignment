public class Student {

    private int id;
    private String departmentName;
    private String studentNumber;

    public Student() {}

    public Student(int id, String departmentName, String studentNumber) {
        this.id = id;
        this.departmentName = departmentName;
        this.studentNumber = studentNumber;
    }

    public int getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
}
