package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

        int myValue = 10000;

        int myMinIntValue = Integer.MIN_VALUE;
        int myMaxIntValue = Integer.MAX_VALUE;
        System.out.println("Integer Min value = " + myMinIntValue);
        System.out.println("Integer Max value = " + myMaxIntValue);
        System.out.println("Min - 1 = " + (myMinIntValue - 1));
        System.out.println("Max + 1 = " + (myMaxIntValue + 1));

        int myMaxIntTest = 2147483647;

        byte myMinByteValue = Byte.MIN_VALUE;
        byte myMaxByteValue = Byte.MAX_VALUE;
        System.out.println("Byte min val: " + myMinByteValue);
        System.out.println("Byte max val: " + myMaxByteValue);

        short myMinShortValue = Short.MIN_VALUE;
        short myMaxShortValue = Short.MAX_VALUE;
        System.out.println("Short min val: " + myMinShortValue);
        System.out.println("Short max val: " + myMaxShortValue);

        //long myLongValue = 100; //this is actually incorrect and requires an "L" at the end of the value. Java will convert the int to a long
        long myLongValue = 100L;
        long myMinLongValue = Long.MIN_VALUE;
        long myMaxLongValue = Long.MAX_VALUE;
        System.out.println("Long min val: " + myMinLongValue);
        System.out.println("Long max val: " + myMaxLongValue);
        long bigLongLiteralValue =  2147483647234L;
        System.out.println(bigLongLiteralValue);

        short bigShortLiteralValue = 32767;

        int myTotal = (myMinIntValue / 2);
        //byte myNewByteValue = (myMinByteValue / 2); // we get an error here due to DEFAULT java value being int
        byte myNewByteValue = (byte) (myMinByteValue / 2);

        short myNewShortValue = (short) (myMinShortValue / 2); //In general, always use an int unless there's a specific reason not to

        byte byteVal = (byte) 100;
        short shortVal = (short) 30000;
        int intVal = 27;
        long longVal = (50000 + (10 * (byteVal + shortVal + intVal)));
        System.out.println("longVal: " + longVal);

        System.out.println("**************************************\n" +
                "**************************************\n" +
                "**************************************\n");
        Integer propertyValue = (Integer) null;

        
    }
}
