package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

        Dog rex = new Dog("rex");
        rex.printName();
        Dog fluffy = new Dog("fluffy");
        rex.printName();    //prints fluffy
        fluffy.printName(); //prints fluffy
    }
}
