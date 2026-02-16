import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private String url = "jdbc:mysql://localhost:3306/testdb";
    private String user = "root";
    private String password = "1234";

    public void insertStudent(String departmentName, String studentNumber) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO students (department_name, student_number) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, departmentName);
            ps.setString(2, studentNumber);

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while (rs.next()) {

                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("department_name"),
                        rs.getString("student_number")
                );

                list.add(s);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
