import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection();

        if(con == null){
            System.out.println("Database connection failed!");
            return;
        }

        while(true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch(choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    try {
                        String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, name);
                        pst.setString(2, email);
                        pst.setString(3, password);
                        pst.executeUpdate();
                        System.out.println("Registration successful!");
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.print("Email: ");
                    String loginEmail = sc.nextLine();
                    System.out.print("Password: ");
                    String loginPass = sc.nextLine();

                    try {
                        String sql = "SELECT * FROM users WHERE email=? AND password=?";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, loginEmail);
                        pst.setString(2, loginPass);
                        ResultSet rs = pst.executeQuery();

                        if(rs.next()) {
                            System.out.println("Login successful! Welcome " + rs.getString("name"));
                        } else {
                            System.out.println("Invalid credentials!");
                        }
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
