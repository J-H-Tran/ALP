package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
        Dog dog = new Dog("Pug");
        dog.breathe();
        dog.eat();

        Parrot parrot = new Parrot("Parrot");
        parrot.breathe();
        parrot.eat();
        parrot.fly();

        Penguin penguin = new Penguin("Emperor");
        penguin.fly();
    }
}
