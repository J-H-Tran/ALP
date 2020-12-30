package academy.learnprogramming;

public class Main {
//Section 4 Coding Exercises
    public static void main(String[] args) {

/*
        long val = SpeedConverter.toMilesPerHour(10.25);
        System.out.println("val: " + val);

        SpeedConverter.printConversion(25.42);
*/

        //MegaByteConverter.printMegaBytesAndKiloBytes(2500);

        //System.out.println(BarkingDog.shouldWakeUp(true, 4));

        //System.out.println(LeapYearCalculator.isLeapYear(-1600));
        //System.out.println(LeapYearCalculator.isLeapYear(1600));
        //System.out.println(LeapYearCalculator.isLeapYear(2017));
        //System.out.println(LeapYearCalculator.isLeapYear(2000));

        //System.out.println(DecimalComparator.areEqualByThreeDecimalPlaces(3.1756, 3.175));

        //int newScore = calculateScore("Tim", 500);
        //System.out.println("New score is " + newScore);

        //calcFeetAndInchesToCentimeters(157);

        System.out.println(SecondsAndMinutes.getDurationString(61, 00));
        System.out.println(SecondsAndMinutes.getDurationString(9999));
    }

    //public static int calculateScore(String playerName, int score) {
    //    System.out.println("Player " + playerName + " scored " + score + " points");
    //    return score * 1000;
    //}

    //public static int calculateScore(int score) {
    //    System.out.println("No name Player scored " + score + " points");
    //    return score * 1000;
    //}

    //METHOD OVERLOADING
    public static double calcFeetAndInchesToCentimeters(double feet, double inches) {

        //Validate that feet is >= 0 and 0 <= inches <= 12, return -1 if NOT true
        if ((feet < 0) || (inches < 0) || (inches > 12)) {
            System.out.println("Invalid values for feet and/or inches.");
            return -1;
        }

        //calculate how many cm are in 'x' ft and in.
        double centimeters = (feet * 12) * 2.54;
        centimeters += inches * 2.54;
        System.out.println(feet + " feet + " + inches + " inches = " + centimeters + " cm");
        return centimeters;
    }

    public static double calcFeetAndInchesToCentimeters(double inches) {

        //Validate that feet is >= 0 and 0 <= inches <= 12, return -1 if NOT true
        if (inches < 0) {
            return -1;
        }

        //calculate how many cm are from in.
        double inchesToFeet = (int) inches / 12;
        double remainingInches = (int) inches % 12;
        System.out.println(inches + " inches is equal to " + inchesToFeet + " feet and " + remainingInches + " inches");
        return calcFeetAndInchesToCentimeters(inchesToFeet, remainingInches);
    }

}
