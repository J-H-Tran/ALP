package com.udemy.concurrency;

public class Deadlock2Main {

    public static void main(String[] args) {
        PolitePerson jane = new PolitePerson("Jane");
        PolitePerson felix = new PolitePerson("Felix");

        // both may sayHello() but possible that neither of them will sayHelloBack()
        new Thread(new Runnable() {
            @Override
            public void run() {
                jane.sayHello(felix);

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                felix.sayHello(jane);
            }
        }).start();
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
 *
 * So, we synchronize the 2 methods. When running on a single thread deadlock won't occur.
 * but what happens when we have them running on separate threads?
 * A deadlock occurs, but why?
 * Since the methods are synchronized won't one thread block until the other thread has finished executing the method?
 * Yes, if the threads are calling the method using teh same obj!
 * when we synchronize on a method a thread has to acquire the intrinsic lock for the obj it's using to call the
 * synchronized method.
 *
 * Underlying cause of a deadlock occurs because of the oder in which threads are trying to acquire locks
 * so to avoid this, have the threads acquire the locks in the same order
 * We could also see if it's possible to pass information to other obj using parameters, rather than having them
 * call methods to get information.
 * */