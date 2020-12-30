package academy.learnprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        List<String> serialNumbers = new ArrayList<>();

        serialNumbers.add("AVG190420T");
        serialNumbers.add("RTF20001000Z");
        serialNumbers.add("QWER201850G");   // fail
        serialNumbers.add("AFA199620E");    // fail
        serialNumbers.add("ERT1947200T");
        serialNumbers.add("RTY20202004");   // fail
        serialNumbers.add("DRV1984500Y");
        serialNumbers.add("ETB2010400G");   // fail
        serialNumbers.add("DRV19845006");

        // go through entire list of serial numbers
        System.out.println("Sum is: " + countCounterfeit(serialNumbers));

    }

    public static int countCounterfeit(List<String> serialNumber) {

        int sum = 0;

        for (String serial : serialNumber) {
            LOGGER.log(Level.INFO, "\n\n" + serial);
            if(isValidLength(serial)) {
                if(isFirstThreeUppercase(serial)) {
                    if(isFirstThreeDistinct(serial)) {
                        if(isValidYear(serial)) {

                            sum += getValidDenomination(serial);
                        }
                    }
                }
            }
        }
        return sum;
    }

    public static boolean isValidLength(String serialNo) {
        if (serialNo.length() >= 10 && serialNo.length() <= 12) {
            LOGGER.log(Level.INFO, "Passes check for isValidLength");
            return true;
        }

        LOGGER.log(Level.INFO, "Does not pass check for isValidLength");
        return false;
    }

    public static boolean isFirstThreeUppercase(String serialNo) {
        String tempStr = serialNo.substring(0, 3);
        char[] chars = tempStr.toCharArray();

        for (char ch : chars) {
            if (!Character.isUpperCase(ch)) {
                LOGGER.log(Level.INFO, "Does not pass check for isFirstThreeUppercase");
                return false; // continue to next serial number
            }
        }

        LOGGER.log(Level.INFO, "Passes check for isFirstThreeUppercase");
        return true;
    }

    public static boolean isFirstThreeDistinct(String serialNo) {
        String tempStr = serialNo.substring(0, 3);
        char[] chars = tempStr.toCharArray();

        for (int i = 0; i < tempStr.length(); i++) {
            for (int j = i + 1; j < tempStr.length(); j++) {
                if (chars[i] == chars[j]) {
                    LOGGER.log(Level.INFO, "Does not pass check for isFirstThreeDistinct");
                    return false;
                }
            }
        }

        LOGGER.log(Level.INFO, "Passes check for isFirstThreeDistinct");
        return true;
    }

    public static boolean isValidYear(String serialNo) {
        String tempStr = serialNo.substring(3, 7);

        int year = 0;
        if(Character.isDigit(tempStr.charAt(0))) {
            year = Integer.parseInt(tempStr);

            if (year >= 1900 && year <= 2019) {

                LOGGER.log(Level.INFO, "Passes check for isValidYear");
                return true;
            }
        }
        LOGGER.log(Level.INFO, "Does not pass check for isValidYear");
        return false;
    }

    public static int getValidDenomination(String serialNo) {
        String tempStr = serialNo.substring(7);
        char tempChar = tempStr.charAt(tempStr.length() - 1);
        
        if (!Character.isUpperCase(tempChar)) {
            LOGGER.log(Level.INFO, "Not a valid final character");
            return 0;
        }

        int denomination = 0;

        if (tempStr.length() == 3) {
            tempStr = tempStr.substring(0, tempStr.length() - 1);
            denomination = Integer.parseInt(tempStr);

            if (denomination == 10 || denomination == 20 || denomination == 50) {
                LOGGER.log(Level.INFO, "Is a valid denomination value");
                return denomination;
            }
        } else if (tempStr.length() == 4) {
            tempStr = tempStr.substring(0, tempStr.length() - 1);
            denomination = Integer.parseInt(tempStr);
            
            if (denomination == 100 || denomination == 200 || denomination == 500) {
                LOGGER.log(Level.INFO, "Is a valid denomination value");
                return denomination;
            }
        } else if (tempStr.length() == 5) {
            tempStr = tempStr.substring(0, tempStr.length() - 1);
            denomination = Integer.parseInt(tempStr);
            
            if (denomination == 1000) {
                LOGGER.log(Level.INFO, "Is a valid denomination value");
                return denomination;
            }
        }
        LOGGER.log(Level.INFO, "Not a valid denomination value");
        return 0;
    }

//    public static boolean is


}
