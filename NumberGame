import java.util.Scanner;
import java.util.Random;

public class NumberGame {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        final int MAX_ATTEMPTS = 7;
        boolean keepPlaying = true;
        int round = 1;
        int totalScore = 0;

        System.out.println("===================================");
        System.out.println("    Number Guessing Game");
        System.out.println("===================================");

        while (keepPlaying) {
            int target = random.nextInt(100) + 1;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrect = false;

            System.out.println("\nRound " + round + " - Guess the number (1 to 100):");

            while (attemptsLeft > 0) {
                System.out.print("Your guess: ");
                int guess;

                // Check for valid input
                if (input.hasNextInt()) {
                    guess = input.nextInt();
                } else {
                    System.out.println("Please enter a valid number.");
                    input.next(); // clear invalid input
                    continue;
                }

                // Range validation
                if (guess < 1 || guess > 100) {
                    System.out.println("Number must be between 1 and 100.");
                    continue;
                }

                // Check guess
                if (guess == target) {
                    System.out.println("Correct! You guessed it in " + (MAX_ATTEMPTS - attemptsLeft + 1) + " tries.");
                    guessedCorrect = true;
                    totalScore += attemptsLeft * 10;
                    break;
                } else if (guess < target) {
                    System.out.println("Too low.");
                } else {
                    System.out.println("Too high.");
                }

                attemptsLeft--;
                if (attemptsLeft > 0) {
                    System.out.println("Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrect) {
                System.out.println("You've used all attempts. The number was: " + target);
            }

            // Ask if user wants to play another round
            System.out.print("\nPlay another round? (yes/no): ");
            String response = input.next();
            keepPlaying = response.equalsIgnoreCase("yes");
            round++;
        }

        System.out.println("\nGame Over! Total Rounds: " + (round - 1));
        System.out.println("🏆 Final Score: " + totalScore);
        System.out.println("Thanks for playing!");
        input.close();
    }
}
