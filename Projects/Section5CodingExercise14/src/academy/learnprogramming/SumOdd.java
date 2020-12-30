package academy.learnprogramming;

public class SumOdd {

    public static boolean isOdd (int n) {

        if (n < 0) {
            return false;
        }

        if(n % 2 != 0) {
            return true;
        } else
            return false;
    }

    public static int sumOdd (int start, int end) {

        if((end < start) || start < 0) {
            return -1;
        }

        int sum = 0;

        for(int i = start; i <= end; i++) {

            if(isOdd(i)) {
                sum += i;
            }
        }

        return sum;
    }
}
