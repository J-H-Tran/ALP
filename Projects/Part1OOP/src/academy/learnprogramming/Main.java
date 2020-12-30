package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

//        Car porsche = new Car();
//        Car toyota = new Car();
//
//        porsche.setColor("red");
//        System.out.println(porsche.toString());

//        BankAccount checking = new BankAccount();
//        BankAccount savings = new BankAccount();
//
//        checking.deposit(10000.00);
//        checking.withdraw(3000.00);
//        checking.withdraw(8000.00);
//        checking.setAccountNumber(791825528);
//
//        System.out.println(checking.toString());

        VipCustomer michaelJordan = new VipCustomer();
        VipCustomer wayneBrady = new VipCustomer("Wayne Brady", 20000);

        System.out.println(michaelJordan.getEmailAddress());
        System.out.println(String.format("%.2f", wayneBrady.getCreditLimit()));


    }
}
