package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

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
        //DRY
    }
}
