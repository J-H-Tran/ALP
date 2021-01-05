package com.udemy.concurrency;

public class Deadlock2Main {

    public static void main(String[] args) {
        PolitePerson jane = new PolitePerson("Jane");
        PolitePerson felix = new PolitePerson("Felix");

        jane.sayHello(felix);
        felix.sayHello(jane);
    }

    static class PolitePerson {
        private final String name;

        public PolitePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void sayHello(PolitePerson person) {
            System.out.format("%s: \"%s" + " has said hello to me.\"%n", this.name, person.getName());
            person.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson person) {
            System.out.format("%s: \"%s" + " has said hello back to me\".%n", this.name, person.getName());
        }
    }
}
 /*
 * For this example, when an obj sayHello() we want the other obj to sayHelloBack() before another thread
 * can sayHello() to the same obj. Also, we want an obj to finish sayHelloBack() to one obj before it
 * sayHello() to another obj.
 * */