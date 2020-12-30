package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
        // methods can help prevent code duplication and improve maintainability of code.
/*
        boolean isGameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;


        if (isGameOver) {
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("Your final score is: " + finalScore);
        }

        score = 10000;
        levelCompleted = 8;
        bonus = 200;

        if (isGameOver) {
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("Your final score is: " + finalScore);
        }
*/
        int highScore = scoreCalculation(true, 800, 5, 100);
        System.out.println("Your final score is: " + highScore);

        highScore = scoreCalculation(true, 10000, 8, 200);
        System.out.println("Your final score is: " + highScore);

        int highScorePosition = calculateHighScorePosition(1500);
        displayHighScorePosition("J", highScorePosition);

        highScorePosition = calculateHighScorePosition(900);
        displayHighScorePosition("R", highScorePosition);

        highScorePosition = calculateHighScorePosition(400);
        displayHighScorePosition("A", highScorePosition);

        highScorePosition = calculateHighScorePosition(50);
        displayHighScorePosition("K", highScorePosition);
    }

    public static int scoreCalculation(boolean isGameOver, int score, int levelCompleted, int bonus) {

        if (isGameOver) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 5000;
            return finalScore;
        }

        return -1; // conventional uses to determine an error, if the return statement is not present then there is an error
        // the error is there because the method has a return type but int he case that the if condition is not met nothing gets returned when something is expected
        // to cover all the paths of execution either put an else or outside the code block so that no matter what something gets returned.
    }

    public static void displayHighScorePosition(String playerName, int position) {
        System.out.println("Player " + playerName
                + " is in place no. " + position
                + " on the high score rankings");
    }

    public static int calculateHighScorePosition(int playerScore) {

        if(playerScore >= 1000) {
            return 1;
        } else if (playerScore >= 500) {
            return 2;
        } else if (playerScore >= 100) {
            return 3;
        } else {
            return 4;
        }
    }
}
