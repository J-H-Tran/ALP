package academy.learnprogramming;

public class Toyota extends Car {

    private int roadServiceMonths;

    public Toyota(int roadServiceMonths) {
        super("Toyota", "4WD", 5, 5, 6, false);
        this.roadServiceMonths = roadServiceMonths;
    }

    public void accelerate(int rate) {

        int newSpeed = getCurrentSpeed() + rate;

        if(newSpeed == 0) {
            stop();
            changeGear(1);
        } else if(newSpeed > 0 && newSpeed <= 10) {
            changeGear(1);
        } else if(newSpeed > 10 && newSpeed <= 20) {
            changeGear(2);
        } else {
            changeGear(3);
        }

        if(newSpeed > 0) {
            changeSpeed(newSpeed, getCurrentDirection());
        }
    }
}
