package academy.learnprogramming;

public class DecimalComparator {

    public static boolean areEqualByThreeDecimalPlaces(double decOne, double decTwo) {

        Double double1 = new Double(decOne);
        Double double2 = new Double(decTwo);

        String strD1 = double1.toString();
        String strD2 = double2.toString();

        String[] strArr1 = strD1.split("\\.", 2);
        String[] strArr2 = strD2.split("\\.", 2);

        strD1 = strArr1[0];
        strD2 = strArr2[0];

        if(!strD1.equals(strD2)) {
            return false;
        }

        strD1 = strArr1[1];
        strD2 = strArr2[1];

        if(strD1.length() > 3) {
            strD1 = strD1.substring(0, 3);
        }

        if(strD2.length() > 3) {
            strD2 = strD2.substring(0, 3);
        }

        double1 = Double.valueOf(strD1);
        double2 = Double.valueOf(strD2);

        if(Double.compare(double1, double2) == 0) {
            return true;
        }

        return false;
    }
}
