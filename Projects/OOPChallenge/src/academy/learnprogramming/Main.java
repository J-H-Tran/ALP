package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
        // The purpose of the application is to help a fictitious company called Bills Burgers to manage
        // their process of selling hamburgers.
        // Our application will help Bill to select types of burgers, some of the additional items (additions) to
        // be added to the burgers and pricing.
        // We want to create a base hamburger, but also two other types of hamburgers that are popular ones in Bills store.
        // The basic hamburger should have the following items.
        // Bread roll type, meat and up to 4 additional additions (things like lettuce, tomato, carrot, etc) that
        // the customer can select to be added to the burger.
        // Each one of these items gets charged an additional price so you need some way to track how many items got added
        // and to calculate the final price (base burger with all the additions).
        // This burger has a base price and the additions are all separately priced (up to 4 additions, see above).
        // Create a Hamburger class to deal with all the above.
        // The constructor should only include the roll type, meat and price, can also include name of burger or you
        // can use a setter.
        // Also create two extra varieties of Hamburgers (subclasses) to cater for
        // a) Healthy burger (on a brown rye bread roll), plus two addition items that can be added.
        // The healthy burger can have 6 items (Additions) in total.
        // hint:  you probably want to process the two additional items in this new class (subclass of Hamburger),
        // not the base class (Hamburger), since the two additions are only appropriate for this new class
        // (in other words new burger type).
        // b) Deluxe hamburger - comes with chips and drinks as additions, but no extra additions are allowed.
        // hint:  You have to find a way to automatically add these new additions at the time the deluxe burger
        // object is created, and then prevent other additions being made.
        //  All 3 classes should have a method that can be called anytime to show the base price of the hamburger
        // plus all additionals, each showing the addition name, and addition price, and a grand/final total for the
        // burger (base price + all additions)
        // For the two additional classes this may require you to be looking at the base class for pricing and then
        // adding totals to final price.

        Hamburger hamburger = new Hamburger("White Bread", "Beef Meat", "Regular Burger", 1.50);
        HealthyBurger healthyBurger = new HealthyBurger();
        DeluxeBurger deluxeBurger = new DeluxeBurger();

        hamburger.includeAddons("Cheese", "Onions");
        System.out.printf("$%.2f is the grand total for your order of " + hamburger.getClass().getSimpleName() +
                " which comes with" + hamburger.printAddons() + ".\n", hamburger.subtotalPrice());

        healthyBurger.includeAddons("Onions", "Falafel", "Olives", "Tomatoes");
        System.out.printf("$%.2f is the grand total for your order of " + healthyBurger.getClass().getSimpleName() +
                " which comes with" + healthyBurger.printAddons() + ".\n", healthyBurger.subtotalPrice());

        deluxeBurger.includeAddons();
        System.out.printf("$%.2f is the grand total for your order of " + deluxeBurger.getClass().getSimpleName() +
        " which comes with" + deluxeBurger.printAddons() + ".\n", deluxeBurger.subtotalPrice());

//        HamburgerS hamburgerS = new HamburgerS("Basic", "Sausage", 3.50, "White Bread");
//        double price = hamburgerS.itemizeHamburger();
    }
}

class Hamburger {

    protected String breadType;
    protected String meatType;
    protected String name;
    protected double grandTotal;
    protected double basePrice;
    protected String addons[];
    protected String finalList;
    protected int anInt;

    public Hamburger(String breadType, String meatType, String name, double basePrice) {
        this.breadType = breadType;
        this.meatType = meatType;
        this.name = name;
        this.basePrice = basePrice;
        this.grandTotal += this.basePrice;

    }

    public void includeAddons(String... addonsRegular) {

        this.addons = new String[addonsRegular.length];
        anInt = 0;
        for(String s:addonsRegular) {
            this.addons[anInt] = s;
            if (s == "Cheese") {
                this.grandTotal += .5;
            } else if (s == "Tomatoes") {
                this.grandTotal += .6;
            } else if (s == "Avocado") {
                this.grandTotal += .8;
            } else if (s == "Onions") {
                this.grandTotal += .3;
            }
            anInt++;
        }

    }

     public double subtotalPrice() {
        return this.grandTotal;
     }

     public String printAddons() {

        this.finalList = "";
        for(String s: this.addons) {
            this.finalList += " ";
            if (s == "Cheese") {
                this.finalList += s + " at $0.50";
            } else if (s == "Tomatoes") {
                this.finalList += s + " at $0.60";
            } else if (s == "Avocado") {
                this.finalList += s + " at $0.80";
            } else if (s == "Onions") {
                this.finalList += s + " at $0.30";
            }
        }
        return this.finalList;
     }
}

class HealthyBurger extends Hamburger {

    public HealthyBurger() {
        super("Rye Bread", "Tofu", "Healthy Burger", 1.50);
        super.grandTotal += super.basePrice;
    }

    @Override
    public void includeAddons(String... addonsHealthy) {

        super.addons = new String[addonsHealthy.length];
        super.anInt = 0;
        for(String s:addonsHealthy) {
            super.addons[anInt] = s;
            if (s == "Cheese") {
                super.grandTotal += .5;
            } else if (s == "Tomatoes") {
                super.grandTotal += .6;
            } else if (s == "Avocado") {
                super.grandTotal += .8;
            } else if (s == "Onions") {
                super.grandTotal += .3;
            } else if (s == "Falafel") {
                super.grandTotal += .8;
            } else if (s == "Olives") {
                super.grandTotal += .2;
            }
            super.anInt++;
        }
    }

    @Override
    public String printAddons() {

        super.finalList = "";
        for(String s: this.addons) {
            super.finalList += " ";
            if (s == "Cheese") {
                super.finalList += s + " at $0.50";
            } else if (s == "Tomatoes") {
                super.finalList += s + " at $0.60";
            } else if (s == "Avocado") {
                super.finalList += s + " at $0.80";
            } else if (s == "Onions") {
                super.finalList += s + " at $0.30";
            } else if (s == "Falafel") {
                super.finalList += s + " at $0.80";
            } else if (s == "Olives") {
                super.finalList += s + " at $0.20";
            }
        }
        return super.finalList;
    }

}

class DeluxeBurger extends Hamburger {

    public DeluxeBurger() {
        super("White Bread", "Beef Meat", "Regular Burger", 1.50);
        super.grandTotal += super.basePrice;
    }

    public void includeAddons() {

        super.addons = new String[2];
        super.addons[0] = "Chips";
        super.grandTotal += .5;
        super.addons[1] = "Drink";
        super.grandTotal += .5;
    }

    @Override
    public String printAddons() {

        super.finalList = "";
        for(String s: super.addons) {
            super.finalList += " ";
            if (s == "Chips") {
                this.finalList += s + " at $0.50";
            } else if (s == "Drink") {
                this.finalList += s + " at $0.50";
            }
        }
        return this.finalList;
    }

}