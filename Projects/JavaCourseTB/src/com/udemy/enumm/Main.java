package com.udemy.enumm;

public class Main {

    enum Denomination {
        ONE(1), FIVE(5), TEN(10),
        TWENTY(20), FIFTY(50),
        ONE_HUNDRED(100),
        TWO_HUNDRED(200),
        FIVE_HUNDRED(500);

        private int value;

        Denomination(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        System.out.println("Denomination.FIFTY -> " + Denomination.FIFTY);
        System.out.println("Denomination.FIFTY.value -> " + Denomination.FIFTY.value);

        System.out.println("Denomination.valueOf(\"FIVE_HUNDRED\") -> " + Denomination.valueOf("FIVE_HUNDRED"));
        System.out.println("Denomination.valueOf(\"FIVE_HUNDRED\").value -> " + Denomination.valueOf("FIVE_HUNDRED").value);
    }
}
