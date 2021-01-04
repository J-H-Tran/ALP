package com.udemy.concurrency;

public class IntroConcurrencyAndThreadsMain {

    public static void main(String[] args) {

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
 * on their machine
 * */
