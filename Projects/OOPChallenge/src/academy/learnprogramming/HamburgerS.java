package academy.learnprogramming;

public class HamburgerS {

    private String name;
    private String meat;
    private double price;
    private String breadType;

    private String addonName1;
    private double addonPrice1;
    private String addonName2;
    private double addonPrice2;
    private String addonName3;
    private double addonPrice3;
    private String addonName4;
    private double addonPrice4;

    public HamburgerS(String name, String meat, double price, String breadType) {
        this.name = name;
        this.meat = meat;
        this.price = price;
        this.breadType = breadType;
    }

    public void addHamburgerAddition1(String name, double price) {
        this.addonName1 = name;
        this.addonPrice1 = price;
    }

    public void addHamburgerAddition2(String name, double price) {
        this.addonName2 = name;
        this.addonPrice2 = price;
    }

    public void addHamburgerAddition3(String name, double price) {
        this.addonName3 = name;
        this.addonPrice3 = price;
    }

    public void addHamburgerAddition4(String name, double price) {
        this.addonName4 = name;
        this.addonPrice4 = price;
    }

    public double itemizeHamburger() {
        double hamburgerPrice = this.price;
        System.out.println(this.name + " hamburger " + " on a " + this.breadType + " roll " +
                " price is " + this.price);
        if (this.addonName1 != null) {
            hamburgerPrice += this.addonPrice1;
            System.out.println("Added " + this.addonName1 + " for an extra " + this.addonPrice1);
        }
        if (this.addonName2 != null) {
            hamburgerPrice += this.addonPrice2;
            System.out.println("Added " + this.addonName2 + " for an extra " + this.addonPrice2);
        }
        if (this.addonName3 != null) {
            hamburgerPrice += this.addonPrice3;
            System.out.println("Added " + this.addonName3 + " for an extra " + this.addonPrice3);
        }
        if (this.addonName4 != null) {
            hamburgerPrice += this.addonPrice4;
            System.out.println("Added " + this.addonName4 + " for an extra " + this.addonPrice4);
        }

        return hamburgerPrice;
    }
}
