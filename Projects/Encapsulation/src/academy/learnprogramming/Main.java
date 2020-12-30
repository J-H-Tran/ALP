package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
//        Player player = new Player();
/*
        player.name = "JT";     // 2. if we change the member name in the class we have to remember to change it's reference in our main code
        player.health = 20;     // 3. we may not remember to initialize every variable needed in the class (manual) class members aren't guaranteed
                                // constructor can ensure data is valid before using class
        player.weapon = "Fists";

        int damage = 10;

        player.loseHealth(damage);
        System.out.println("Remaining health = " + player.healthRemaining());

        damage = 11;
        player.health = 200;    // 1. we don't have this class member protected from external access, since it can be accessed directly
                                // we've it can be altered without restriction

        player.loseHealth(damage);
        System.out.println("Remaining health = " + player.healthRemaining());
*/

//        EnhancedPlayer player1 = new EnhancedPlayer("JT", 100, "Fists");
//        System.out.println("Health is = " + player1.getHealth());

        /*
        * Create a class to demonstrate proper encapsulation techniques
        * this class will be called Printer
        * It will simulate a real computer printer
        * It should have fields for the toner level, number of pages printed, and
        * also whether it is a duplex printer
        * Add methods to fill up the toner (max 100%)
        * another method to simulate printing a page (should increase the number of pages printed
        * Decide on the scope whether to use constructors, and anything else you think is needed
        * */

        Printer printer = new Printer(50, false);
        System.out.println("initial page count " + printer.getNumberOfPagesPrinted());
        int pagesPrinted = printer.printPage(4);
        System.out.println("Pages printed was " + pagesPrinted + " new total print count for printer = " + printer.getNumberOfPagesPrinted());
        pagesPrinted = printer.printPage(2);
        System.out.println("Pages printed was " + pagesPrinted + " new total print count for printer = " + printer.getNumberOfPagesPrinted());
    }
}
