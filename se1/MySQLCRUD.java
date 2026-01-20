import java.sql.*;
import java.util.Scanner;

public class MySQLCRUD {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";       // your DB username
        String password = "password";   // your DB password

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to database!");

            // --- Input Data ---
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter age: ");
            int age = sc.nextInt();

            String insertQuery = "INSERT INTO students (name, age) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                int rows = pstmt.executeUpdate();
                System.out.println(rows + " row(s) inserted.");
            }

            // --- Output Data ---
            String selectQuery = "SELECT * FROM students";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectQuery)) {

                System.out.println("\nID\tName\tAge");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + "\t" +
                                       rs.getString("name") + "\t" +
                                       rs.getInt("age"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
