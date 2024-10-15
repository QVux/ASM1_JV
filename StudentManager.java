import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {
    int id;
    String name;
    double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getRank() {
        if (marks < 5.0) return "Fail";
        if (marks < 6.5) return "Medium";
        if (marks < 7.5) return "Good";
        if (marks < 9.0) return "Very Good";
        return "Excellent";
    }
}

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Adds a student to the list
    public void addStudent(int id, String name, double marks) {
        students.add(new Student(id, name, marks));
        System.out.println("Student has been added successfully!");
    }

    // Displays all students with details
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("There are no students in the list yet..");
        } else {
            for (Student s : students) {
                System.out.println("ID: " + s.id + ", Name: " + s.name + ", Score: " + s.marks + ", Rating: " + s.getRank());
            }
        }
    }

    // Sorts students by marks in descending order
// Sắp xếp sinh viên theo điểm từ thấp đến cao
    public void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to arrange.");
        } else {
            students.sort(Comparator.comparingDouble(s -> s.marks)); // Sắp xếp theo điểm từ thấp đến cao
            System.out.println("List of students sorted by score (low to high).");
        }
    }

    // Edits an existing student's information
    public void editStudent(int id, String name, double marks) {
        boolean found = false;
        for (Student s : students) {
            if (s.id == id) {
                s.name = name;
                s.marks = marks;
                found = true;
                System.out.println("Students have been updated.");
                break;
            }
        }
        if (!found) {
            System.out.println("No student found with ID: " + id);
        }
    }

    // Deletes a student by ID
    public void deleteStudent(int id) {
        boolean removed = students.removeIf(s -> s.id == id);
        if (removed) {
            System.out.println("Student has been deleted.");
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }

    // Main menu to interact with the program
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nSelect an option:");
                System.out.println("1. Add students");
                System.out.println("2. Edit students");
                System.out.println("3. Delete students");
                System.out.println("4. Show students");
                System.out.println("5. Sort students by score");
                System.out.println("6. Exit");
                System.out.print("Enter your selection: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Score (0-10): ");
                        double marks = scanner.nextDouble();
                        if (marks < 0 || marks > 10) {
                            System.out.println("Invalid score. Score must be between 0 and 10.");
                        } else {
                            manager.addStudent(id, name, marks);
                        }
                        break;

                    case 2:
                        System.out.print("Enter the Student ID to be edited: ");
                        int editId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();

                        System.out.print("Enter New Points: ");
                        double newMarks = scanner.nextDouble();
                        if (newMarks < 0 || newMarks > 10) {
                            System.out.println("Invalid score. Score must be between 0 and 10.");
                        } else {
                            manager.editStudent(editId, newName, newMarks);
                        }
                        break;

                    case 3:
                        System.out.print("Enter the Student ID to delete: ");
                        int deleteId = scanner.nextInt();
                        manager.deleteStudent(deleteId);
                        break;

                    case 4:
                        System.out.println("List of Students:");
                        manager.displayStudents();
                        break;

                    case 5:
                        manager.sortStudents();
                        break;

                    case 6:
                        System.out.println("Program ends.");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid selection, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number..");
                scanner.nextLine(); // Clear buffer
            }
        }
    }
}
