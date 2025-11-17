import java.util.Scanner;


class InvalidDepartmentException extends Exception {
    public InvalidDepartmentException(String message) {
        super(message);
    }
}

public class Main {

  
    public static void validateAge(int age) throws IllegalArgumentException {
        if (age < 18 || age > 100) {
            throw new IllegalArgumentException("Age must be between 18 and 100.");
        }
    }

   
    public static void validateDepartment(String dept) throws InvalidDepartmentException {
        if (dept == null || dept.isEmpty()) {
            throw new InvalidDepartmentException("Department cannot be null or empty.");
        }

      
        String[] validDepartments = {"HR", "Finance", "Engineering", "Marketing"};
        boolean valid = false;

        for (String validDept : validDepartments) {
            if (validDept.equalsIgnoreCase(dept)) {
                valid = true;
                break;
            }
        }

        if (!valid) {
            throw new InvalidDepartmentException("Invalid department. Valid departments are: HR, Finance, Engineering, Marketing.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
           
            System.out.print("Enter your age: ");
            int age = Integer.parseInt(scanner.nextLine());
            validateAge(age);
            System.out.println("Age is valid.");

            
            System.out.print("Enter your department: ");
            String department = scanner.nextLine();
            validateDepartment(department);
            System.out.println("Department is valid.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidDepartmentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number for age.");
        } finally {
            scanner.close();
        }
    }
}
