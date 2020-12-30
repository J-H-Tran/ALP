package academy.learnprogramming;

public class SecondsAndMinutes {

    public static String getDurationString(int minutes, int seconds) {

        if ((minutes < 0) || (seconds < 0) || (seconds > 59)) {
            return "Invalid Value";
        }

        int hoursInMinutes = Math.round((float) minutes / 60);
        int remainingMinutes = minutes % 60;

        return hoursInMinutes + "h " + remainingMinutes + "m " + seconds + "s";
    }

    public static String getDurationString(int seconds) {

        if(seconds < 0) {
            return "Invalid Value";
        }

        int totalMinutesFromSeconds = Math.round((float) seconds / 60);
        int remainingSeconds = seconds % 60;

        return getDurationString(totalMinutesFromSeconds, remainingSeconds);
    }
}