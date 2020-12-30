package academy.learnprogramming;

import java.util.ArrayList;

public class GearBox {

    private ArrayList<Gear> gears;
    private int maxGears;
    private int currentGear = 0;
    private boolean clutchIsIn;

    public GearBox(int maxGears) {
        this.maxGears = maxGears;
        this.gears = new ArrayList<>();
        Gear neutral = new Gear(0,0.0);
        this.gears.add(neutral);

        for(int i = 0; i < maxGears; i++) {
            addGear(i, i * 5.3);
        }
    }

    /*
    * Gear innerclass probably isn't useful as a standalone class
    * it is currently associated with class GearBox.
    * In this case it doesn't make sense to refer to Gear unless within the context of GearBox
    *
    * class Gear has access to all members of GearBox even the ones declared as private
    * class Gear's this keyword will refer to it's own
    *
    * when using same variable name, as in the case of gearNumber, class Gear's variable 'shadows' that of GearBox
    * avoid this by renaming. Could cause issues
    *
    * class Gear is actually a member of GearBox
    * */

    public void operateClutch(boolean in) {
        this.clutchIsIn = in;
    }

    /*
    * This method is actually part of building the GearBox and not actually being used like a method
    * therefore, we can consider that it is better to be put in the constructor of the GearBox class*/
    public void addGear(int number, double ratio) {
        if((number > 0) && (number <= maxGears)) {
            this.gears.add(new Gear(number, ratio));
        }
    }

    public void changeGear(int newGear) {
        if((newGear >= 0) && (newGear < this.gears.size()) && this.clutchIsIn) {
            this.currentGear = newGear;
            System.out.println("Gear " + newGear + " selected");
        } else {
            System.out.println("Grind!");
            this.currentGear = 0;
        }
    }

    public double wheelSpeed(int revs) {
        if(clutchIsIn) {
            System.out.println("Scream!");
            return 0.0;
        }
        return revs * gears.get(currentGear).getRatio();
    }

    /*
    * Can be a good way to improve encapsulation with this private inner class
    * Objects only know about other objects that they need to know about
    * Nothing other than the GearBox class needs to have any details about Gear
    * so, we can hide the class completely by making it a private inner class
    * We cannot access Gear class at all unless when using GearBox class
    * */
    private class Gear {
        private int gearNumber;
        private double ratio;

        public Gear(int gearNumber, double ratio) {
            this.gearNumber = gearNumber;
            this.ratio = ratio;
        }

        public double getRatio() {
            return ratio;
        }

        public double driveSpeed(int revs) {
            return revs * (this.ratio);
        }
    }
}
