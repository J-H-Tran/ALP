package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

//        int i = 125;
//        int value = 1 % 10;
//        System.out.println(value);
//
//        value = 1 / 10;
//        System.out.println(value);

        System.out.println(sumDigits(125));

    }

    private static long sumDigits(int number) {

        if((number < 10) || (number < 0)) {
            return -1;
        }

        int limit = number;
        int modTen = 0;
        int sum = 0;

//        for(int i = 0; i < limit; i ++) {
//
//            modTen = number % 10; // assigns number at the end
//            sum += modTen;
//            number = number / 10; // removes the number at the end
//
//            if(number == 0) {
//                break; // reached the end of the number
//            }
//        }
        while(number > 0) {

            modTen = number % 10; // assigns number at the end
            sum += modTen;
            number = number / 10; // removes the number at the end
        }

        return sum;

    }
}
