package academy.learnprogramming;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static long startTime;
    public static long endTime;


    public static void main(String[] args) {

        List<String> serialNumbers = new ArrayList<>(Arrays.asList(
                "AVG190420T", "RTF20001000Z", "QWER201850G", "AFA199620E", "ERT1947200T", "RTY20202004", "DRV1984500Y",
                "ETB2010400G", "DRV19845006", "DRV1984B606Z"
        ));

        System.out.println("Sum is: " + countCounterfeit(serialNumbers));
    }

    public static int countCounterfeit(List<String> serialNumber) {
        ArrayList<Integer> validDenominationValuesArr = new ArrayList<>(Arrays.asList(10, 20, 50, 100, 200, 500, 1000));
        int sum = 0;

        startTime = System.currentTimeMillis();
        for (String serial : serialNumber) {

            //LOGGER.log(Level.INFO, "\nSerial No. " + serial);
            if((serial.length() >= 10) && (serial.length() <= 12)
                    && (Character.isUpperCase(serial.charAt(serial.length() - 1)))
                    && isFirstThreeValid(serial.substring(0, 3))
                    && isValidYear(serial)) {

                sum += getValidDenomination(serial, validDenominationValuesArr);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + "ms");
        return sum;
    }

    public static boolean isFirstThreeValid(String serialFirstThreeStr) {
        Set<Character> set = new HashSet<>();

        for (char ch : serialFirstThreeStr.toCharArray()) {
            if (!set.add(ch)) {
                return false;
            }
        }

        for (char ch : set) {
            if (!Character.isUpperCase(ch)) {
                //LOGGER.log(Level.INFO, "FAIL: First 3 characters are NOT all uppercase letters");
                return false;
            }
        }
        //LOGGER.log(Level.INFO, "PASS: First 3 characters are uppercase letters and are distinct");
        return true;
    }

    public static boolean isValidYear(String serialNo) {
        String yearStr = serialNo.substring(3, 7);
        try {
            int year = Integer.parseInt(yearStr);

            if (year >= 1900 && year <= 2019) {
                //LOGGER.log(Level.INFO, "PASS: Next 4 characters represent a valid year");
                return true;
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Serial No. "+serialNo+" - NumberFormatException: could not parseInt() for \"year\"");
        }
        //LOGGER.log(Level.INFO, "FAIL: Next 4 characters do NOT represent a valid year");
        return false;
    }

    public static int getValidDenomination(String serialNo, ArrayList<Integer> validDenominationValues) {
        String denominationStr = serialNo.substring(7, serialNo.length() - 1);
        try {
            int denominationValue = Integer.parseInt(denominationStr);

            if (validDenominationValues.contains(denominationValue)) {
                //LOGGER.log(Level.INFO, "PASS: The denomination value before end character is valid");
                return denominationValue;
            }
        } catch (NumberFormatException numberFormatException) {
            LOGGER.log(Level.WARNING, "Serial No. "+serialNo+" - NumberFormatException: could not parseInt() for \"denominationValue\"");
            return 0;
        }
        //LOGGER.log(Level.INFO, "FAIL: not valid denomination value");
        return 0;
    }

}
