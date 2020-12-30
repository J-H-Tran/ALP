package academy.learnprogramming;

public class CellPhone implements ITelephone {

    int cellPhoneNumber;

    public CellPhone(int number) {
        this.cellPhoneNumber = number;
    }

    @Override
    public void powerOn() {
        System.out.println("Cell phones is powered on.");
    }

    @Override
    public void callPhone(int phoneNumber) {

        if (phoneNumber == this.cellPhoneNumber) {
            System.out.println("Mobile ringtone is playing");
        } else {
            System.out.println("Wrong number.");
        }
    }

    @Override
    public void answerPhone() {
        System.out.println("Call answered.");
    }
}
