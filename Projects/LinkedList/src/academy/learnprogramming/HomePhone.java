package academy.learnprogramming;

public class HomePhone implements ITelephone {

    int homePhoneNumber;

    public HomePhone(int number) {
        this.homePhoneNumber = number;
    }

    @Override
    public void powerOn() {
        System.out.println("Home phone is on unless unplugged.");
    }

    @Override
    public void callPhone(int phoneNumber) {

        if (phoneNumber == this.homePhoneNumber) {
            System.out.println("House phone is ringing");
        } else {
            System.out.println("Wrong number.");
        }
    }

    @Override
    public void answerPhone() {
        System.out.println("Call answered.");
    }
}
