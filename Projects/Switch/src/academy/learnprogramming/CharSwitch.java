package academy.learnprogramming;

public class CharSwitch {

    public static void main(String[] args) {

        char charVariable = 'C';
        switch(charVariable) {

            case 'A':
                System.out.println("char " + charVariable + " was found.");
                break;
            case 'B':case 'C': case 'D':case 'E':
                System.out.println("char " + charVariable + " was found.");
                break;
            default:
                System.out.println("no character was found :(");
                break;
        }
    }
}
