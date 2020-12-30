package academy.learnprogramming;

import java.util.*;

public class Main {

    public static long startTime;
    public static long endTime;

    public static void main(String[] args) {
	    ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(6, 3, 4, 5));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(13, 10, 21, 20));
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(8, 5, 11, 4, 6));
        ArrayList<Integer> a4 = new ArrayList<>(Arrays.asList(7,2,76,9,4,1,25,5,6,18,0,68,32));
        ArrayList<Integer> a5 = new ArrayList<>(Arrays.asList(4,1,8,9,96,16,5,0,6,54,50,46,158));
        ArrayList<Integer> a6 = new ArrayList<>(Arrays.asList(5,7,57,9,1,3,0,14,6,660,11,98,69,93,78,72,2,15,92,380,38
                ,30,55,54,25,310,76,56,75,4,53,50,42));
        ArrayList<Integer> a7 = new ArrayList<>(Arrays.asList(5,7,57,9,1,3,0,14,6,660,11,98,69,93,78,72,2,15,92,380,38
                ,30,55,54,25,310,76,56,75,4,53,50,42,6, 3, 4, 5,13, 10, 21, 20,8, 5, 11, 4, 6,7,2,76,9,4,1,25,5,6,18,0
                ,68,32,4,1,8,9,96,16,5,0,6,54,50,46,158,5,7,57,9,1,3,0,14,6,660,11,98,69,93,78,72,2,15,92,380,38,30,55
                ,54,25,310,76,56,75,4,53,50,42));
        ArrayList<ArrayList<Integer>> aList = new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7));

        for (ArrayList<Integer> list : aList) {
            System.out.println(list);
            System.out.println(moves1(list) + " number of swaps moves1(arrInt)");
            System.out.println(moves(list) + " number of swaps moves(arrInt)\n");
//            System.out.println(moves2(list) + " number of swaps moves2(arrInt)");
//            System.out.println(moves3(list) + " number of swaps moves3(arrInt)");
//            System.out.println(moves4(list) + " number of swaps moves4(arrInt)\n");
        }

    }

    public static int moves(List<Integer> intArr) {

        //ArrayList<Integer> intArr = new ArrayList<>(arr);
        int left = 0;
        int right = intArr.size() - 1;
        int cnt = 0;

        startTime = System.nanoTime();
        while(left < right) {

            while(intArr.get(left) % 2 == 0 && left < right) {
                left++;
            }

            while(intArr.get(right) % 2 == 1 && left < right) {
                right--;
            }

            if (left < right) {
                Collections.swap(intArr, left, right);
                left++;
                right--;
                cnt++;
            }
        }
        endTime = System.nanoTime();
        System.out.println("moves() execution time: " + (endTime - startTime) + "[ns]");

        return cnt;
    }

    public static int moves1(List<Integer> arr) {

        Integer[] intArr = new Integer[arr.size()];
        intArr = arr.toArray(intArr);
        int left = 0;
        int right = intArr.length - 1;
        int cnt = 0;

        startTime = System.nanoTime();
        while(left < right) {

            while(intArr[left] % 2 == 0 && left < right) {
                left++;
            }

            while(intArr[right] % 2 == 1 && left < right) {
                right--;
            }

            if (left < right) {
                int temp = intArr[left];
                intArr[left] = intArr[right];
                intArr[right] = temp;
                left++;
                right--;
                cnt++;
            }
        }
        endTime = System.nanoTime();
        System.out.println("moves1() execution time: " + (endTime - startTime) + "[ns]");

        return cnt;
    }

    public static int moves2(List<Integer> arr) {
        Integer[] intArr = new Integer[arr.size()];
        intArr = arr.toArray(intArr);
        int left = 0;
        int right = intArr.length - 1;
        int cnt = 0;

        int j = -1;
        startTime = System.nanoTime();
        for (int i = 0; i < intArr.length; i++) {

            if (intArr[i] % 2 == 1) {
                j++;
                int temp = intArr[i];
                intArr[i] = intArr[j];
                intArr[j] = temp;
                cnt++;
            }
        }
        endTime = System.nanoTime();
        System.out.println("moves2() execution time: " + (endTime - startTime) + "[ns]");
        return cnt;
    }

    public static int moves3(List<Integer> arr) {
        Integer[] intArr = new Integer[arr.size()];
        intArr = arr.toArray(intArr);
        int left = 0;
        int right = intArr.length - 1;
        int cnt = 0;

        startTime = System.nanoTime();
        if (intArr.length % 2 == 1) {
            int temp = intArr[intArr.length / 2];
            intArr[intArr.length / 2] = intArr[intArr.length - 1];
            intArr[intArr.length - 1] = temp;
            cnt++;
        }
        endTime = System.nanoTime();
        System.out.println("moves3() execution time: " + (endTime - startTime) + "[ns]");
        return cnt;
    }

    public static int moves4(List<Integer> arr) {
        Integer[] intArr = new Integer[arr.size()];
        intArr = arr.toArray(intArr);
        int left = 0;
        int right = intArr.length - 1;
        int cnt = 0;

        startTime = System.nanoTime();
        for (int i = 0; i < intArr.length / 2; i++) {

            if (intArr[i] % 2 == 1 && intArr[intArr.length - (i + 1)] % 2 == 0) {
                int temp = intArr[i];
                intArr[i] = intArr[intArr.length - (i + 1)];
                intArr[intArr.length - (i + 1)] = temp;
                cnt++;
            }
            if (intArr[intArr.length / 2 + 1] % 2 == 1) {

            }
        }
        endTime = System.nanoTime();
        System.out.println("moves4() execution time: " + (endTime - startTime) + "[ns]");
        return cnt;
    }

}
