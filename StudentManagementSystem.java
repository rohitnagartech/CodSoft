import java.io.*;
import java.util.*;

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }

    public String toCSV() {
        return name + "," + rollNumber + "," + grade;
    }

    public static Student fromCSV(String data) {
        String[] parts = data.split(",");
        return new Student(parts[0], parts[1], parts[2]);
    }
}

class StudentManager {
    private final List<Student> studentList = new ArrayList<>();
    private final String fileName = "students.txt";

    public StudentManager() {
        loadFromFile();
    }

    public void addStudent(Student student) {
        studentList.add(student);
        System.out.println("Student added successfully.");
        saveToFile();
    }

    public void removeStudent(String rollNumber) {
        boolean removed = studentList.removeIf(s -> s.getRollNumber().equalsIgnoreCase(rollNumber));
        if (removed) {
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
        saveToFile();
    }

    public void editStudent(String rollNumber, Scanner scanner) {
        for (Student s : studentList) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                System.out.print("Enter new name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter new grade: ");
                String grade = scanner.nextLine().trim();

                if (!name.isEmpty()) s.setName(name);
                if (!grade.isEmpty()) s.setGrade(grade);

                System.out.println("Student updated.");
                saveToFile();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void searchStudent(String rollNumber) {
        for (Student s : studentList) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                System.out.println("Found: " + s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("List of Students:");
            for (Student s : studentList) {
                System.out.println(s);
            }
        }
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                studentList.add(Student.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student s : studentList) {
                writer.write(s.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        boolean running = true;

        System.out.println("🎓 Welcome to Student Management System 🎓");

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter roll number: ");
                    String roll = scanner.nextLine().trim();
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine().trim();

                    if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
                        System.out.println("All fields are required.");
                    } else {
                        manager.addStudent(new Student(name, roll, grade));
                    }
                }
                case "2" -> {
                    System.out.print("Enter roll number to edit: ");
                    String roll = scanner.nextLine().trim();
                    manager.editStudent(roll, scanner);
                }
                case "3" -> {
                    System.out.print("Enter roll number to remove: ");
                    String roll = scanner.nextLine().trim();
                    manager.removeStudent(roll);
                }
                case "4" -> {
                    System.out.print("Enter roll number to search: ");
                    String roll = scanner.nextLine().trim();
                    manager.searchStudent(roll);
                }
                case "5" -> manager.displayAllStudents();
                case "6" -> {
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
