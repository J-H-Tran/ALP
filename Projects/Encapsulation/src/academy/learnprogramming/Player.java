package academy.learnprogramming;

public class Player {

    public String fullName;
    public int hitPoints;
    public String weapon;


    public void loseHealth(int damage) {
        this.hitPoints = this.hitPoints - damage;

        if(this.hitPoints <= 0) {
            System.out.println("Player KO'd");
        }
    }

    public int healthRemaining() {
        return this.hitPoints;
    }
    int myNum = 0;
    Integer myInt = (Integer) null;
    Integer one = 1;

}
