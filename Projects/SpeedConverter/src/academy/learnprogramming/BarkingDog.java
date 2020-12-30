package academy.learnprogramming;

public class BarkingDog {

    public static boolean shouldWakeUp(boolean isBarking, int hourOfDay) {

        if(isBarking) {

            if((hourOfDay > 23) || (hourOfDay < 0)) {
                return false;
                //System.out.println("Invalid Value");
            } else if((hourOfDay < 8) || (hourOfDay > 22)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
