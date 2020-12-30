package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

//        System.out.println(SumOdd.sumOdd(1, 100));
//        System.out.println(SumOdd.sumOdd(-1, 100));
//        System.out.println(SumOdd.sumOdd(100, 100));
//        System.out.println(SumOdd.sumOdd(13, 13));
//        System.out.println(SumOdd.sumOdd(100, -100));
//        System.out.println(SumOdd.sumOdd(100, 1000));

        int i = 0;
        int end = 20;
        int cnt = 0;

        while(i <= end) {

            i++;
            if(!isEvenNumber(i)) {
                continue;
            }

            cnt++;
            System.out.println("Even number " + i);
            if(cnt == 5) {
                break;
            }
        }
        System.out.println("Total count of even numbers: " + cnt);

    }

    private static boolean isEvenNumber(int number) {

        if(number < 0) {
            return false;
        }

        if(number % 2 ==0) {
            return true;
        }

        return false;

    }
}
