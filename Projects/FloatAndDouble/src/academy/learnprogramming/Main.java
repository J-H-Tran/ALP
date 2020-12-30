package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

        float myMinFloatValue = Float.MIN_VALUE;
        float myMaxFloatValue = Float.MAX_VALUE;
        double myMinDoubleValue = Double.MIN_VALUE;
        double myMaxDoubleValue = Double.MAX_VALUE;

        System.out.println("Min Float Value: " + myMinFloatValue);
        System.out.println("Max Float Value: " + myMaxFloatValue);
        System.out.println("Min Double Value: " + myMinDoubleValue);
        System.out.println("Max Double Value: " + myMaxDoubleValue);

        int myIntValue = 5 / 3;
        //float myFloatValue = 5.25; //double is default decimal number
        float myFloatValue = 5f / 3f; //preferred
        double myDoubleValue = 5d / 3d;
        System.out.println("myIntValue: " + myIntValue);
        System.out.println("myFloatValue: " + myFloatValue);
        System.out.println("myDoubleValue: " + myDoubleValue);

        double pi = 3.14;
    }
}
