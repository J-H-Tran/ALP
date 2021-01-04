package com.udemy.concurrency;

import static com.udemy.concurrency.ThreadColor.*;

public class IntroConcurrencyAndThreadsMain {

    public static void main(String[] args) {
        /*2 ways to create a thread:
        *
        * 1) create an instance of the Thread class
        *   What we want to do is kick off a thread that's going to run some code
        *   so we need a way to tell the thread what code we want to run and we do that by
        *   creating a subclass of the Thread class and Overriding the run()
        *   Rather than creating a thread instance we create an instance of a subclass
        *
        * - Need to create the instance of Thread class
        * - Then call the .start() to run the thread
        *
        * 2) Using the Runnable Interface
        *   An advantage of this is that it only requires us to implement a single method, run()
        *   Instead of implementing the run() of a class that subclasses Thread
        *   we can have any class implement the Runnable Interface and then
        *   all we have to do is add the run() to that class and write executable code inside it
        *
        * - create class that implements Runnable
        * - create an instance of Thread class
        * - pass an instance of class implementing Runnable to Thread constructor
        * - call Thread instance's start()
        *
        * Just as we can create threads by subclassing the Thread class we can also have an anonymous class implement Runnable
        * and pass an instance of it to the Thread constructor.
        *
        * A thread will terminate when it returns from its run() implicitly because it reaches the methods end
        * or explicitly when it executes a return state.
        * A common mistake when creating and running threads is to call the Thread instance's run() instead of start()
        * we have to implement run() but we don't call it instead, we call start(), JVM calls run() for us.
        *
        * When we use run() instead of start() the application wont crash instead of executing the code on a new thread
        * the code will execute in the thread where it's been called. We can set the thread's name with setName() to distinguish between threads
        * */

        System.out.println(ANSI_PURPLE + "Hello from main thread");

        Thread anotherThread = new AnotherThread(); // creates the instance of Thread
        anotherThread.setName("== Another Thread ==");
        anotherThread.start(); // JVM calls the @Override run() of the subclass that extends Thread class, the Thread instance

        // using anonymous class
        new Thread() {
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from Anon");
            }
        }.start();

        // using Runnable Interface
//        Thread myRunnableThread = new Thread(new MyRunnable());
//        myRunnableThread.start();

        // using anonymous class with Runnable Interface
        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from Anon implements Runnable");
            }
        });
        myRunnableThread.start();

        System.out.println(ANSI_PURPLE + "Hello again from main thread"); // even though this line is after the Thread instance it's possible that we see it's output before the thread's output

        /*
        * Rather than creating a named class of AnotherThread we could've created an anonymous class
        * this is useful if we only every want to run the code once. In that case, an anonymous class would work
        * and is probably easier to configure.
        * But, if we intend to run on the same code several times then creating a class, such as above, for the thread would make more sense and have a better result
        *
        * We are NOT allowed to start same instance of a thread more than once
        * If we want to execute the code in run() more than once, cannot start a Thread instance that is already running
        * we'd have to create a new instance of the subclass each time we want to run it
        *
        * When using an anonymous class we have to start the thread immediately
        * that's another consideration when deciding to use a named or anonymous class
        *
        * Note: we CANNOT guarantee the order in which the threads will be executed since it depends on the way your machine handles it's scheduling of the threads
        * */
//        anotherThread.start(); <- throws IllegalThreadStateException when trying to start a thread that's already running

        /*
        * Q. Why use subclass instance of Thread vs class implementing Runnable Interface?
        *
        * - Most of the time, developers use runnable way of creating threads because it's more convenient and
        *   there's also many methods in the Java API that want a Runnable instance passed to them
        * - Since lambda expressions were introduced it's become even more convenient to use anonymous Runnable instances
        *
        * When given the choice there isn't a right or wrong
        * Most developers use Runnable because it's more flexible and it is recommended
        * */

        /* Interrupts
        * We interrupt a thread when we want it to stop what it was doing and to do something else
        * More often than not, when we do that we interrupt a thread because we want it to terminate.
        *
        * Suppose we have a thread that is monitoring a buffer for data that another thread is fetching
        * So, when the fetching thread determines that there's no more data to fetch, for whatever reason,
        * we can interrupt the monitoring thread and terminate it because it's not needed anymore.
        *
        * There are 2 ways for threads to notice that they've been interrupted:
        *
        * 1) catch the InterruptedException
        * 2) when the run() doesn't call any methods that throws the InterruptedException then,
        *   it should call the interrupted() periodically to check whether it's been interrupted
        *   and the method returns true when another thread has interrupted it
        *
        * Q. How does one thread interrupt another thread?
        *
        * It calls the interrupt() on the Thread instance that it wants to interrupt
        * Meaning, that it will need a reference to the Thread instance to be able to call the interrupt method on
        * the other thread.
        * */
    }
}
/* Introduction
 *
 * process - a unit of execution that has its own memory space
 *   Each instance of a JVM runs as a process (not true of ALL JVM implementations but true for most)
 *   When we run a Java console application we're KICKING off a process
 *
 * heap - a process' own memory space
 * Many people use the terms 'process' and 'application' interchangeably, they mean the same thing
 * If we run 2 Java applications the each will have their own memory space called 'heap'. Heap is not shared between applications
 *
 * thread - A unit of execution within a process
 *   Each process can have multiple threads.
 *   In Java, every process has at least 1 thread (main thread aka main method)
 *   Just about every Java process also has multiple system threads that handle tasks like memory management and I/O
 *   We don't explicitly create and code those threads. Our code runs on the main thread or in threads that we create explicitly
 *
 * Creating a thread doesn't have a large resource cost compared to creating a process
 * But, every thread created in a process shares that process' memory and files
 * This can become problematic.
 *
 * In addition to the process' memory or heap each thread has it's own 'thread stack', memory that only a particular thread can access
 * Heap vs Thread Stack
 *
 * Summary:
 * - Every Java app runs as a single process
 * - Each process can have multiple threads
 * - Every process has a Heap
 * - Every thread has a Thread Stack
 *
 * Q. Why would we want to use multiple threads in our application and not just stick with the main thread?
 * 2 main reasons:
 *   1) We sometimes want to perform a task that might take a long time.
 *   ie. When querying a DB or fetch data from somewhere on the internet
 *   We could do this on the main thread, but the code within each mean thread executes linearly.
 *   The main thread won't be able to do anything else while it waits for data
 *   ^This is a big problem that we need to avoid as developers
 *   Instead of tying up the main thread we can create another thread and execute the long-running task on that thread
 *   This frees up the main thread so that it can continue executing: report progress or accept user input
 *   while the long-running task continues to execute in the background.
 *
 *   2) An API could require us to provide one
 *   Sometimes we have to provide code that will run when a method we've called reaches a certain point in it's execution
 *   In this case, we don't create the thread but pass in the code that we want to run on the thread. -> Concurrency
 *
 * --- Concurrency ---
 * Refers to an application doing more than one thing at a time
 * That doesn't necessarily mean that the application is doing more than one thing at the same time, all the time
 * It means, progress can be made on more than one task and not just a single task.
 * Basically, one task doesn't have to complete before another task can start.
 *
 * Note: When working with threads we are at the mercy of the JVM and the OS when it comes to when the threads are
 * scheduled to run. Output varies from run to run and we might not see what someone else sees when they run the application
 * on their machine. Reason why working with threads can be tricky
 * */
