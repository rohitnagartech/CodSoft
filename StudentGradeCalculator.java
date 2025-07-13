import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Student Grade Calculator 🎓");
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Validate subject count
        if (numSubjects <= 0) {
            System.out.println("Invalid number of subjects.");
            return;
        }

        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        // Input marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
                int mark = scanner.nextInt();

                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                    totalMarks += mark;
                    break;
                } else {
                    System.out.println("Please enter valid marks between 0 and 100.");
                }
            }
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Determine grade
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Display results
        System.out.println("\n========== RESULT ==========");
        System.out.println("Total Marks     : " + totalMarks);
        System.out.printf("Average Percent : %.2f%%\n", averagePercentage);
        System.out.println("Grade           : " + grade);
        System.out.println("============================");

        scanner.close();
    }
}

