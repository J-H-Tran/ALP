package academy.learnprogramming;

public class SpeedConverter {

    public static long toMilesPerHour(double kph) {

        if (kph < 0) {
            return -1;
        } else {
            return Math.round(kph / 1.609);
        }
    }

    public static void printConversion(double kph) {

        if (kph < 0) {
            System.out.println("Invalid Value for kph");
        } else {
            long mph = toMilesPerHour(kph);
            System.out.println(kph + " km/h = " + mph + " mi/h");
        }
    }
}
