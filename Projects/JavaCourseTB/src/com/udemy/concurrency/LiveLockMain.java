package com.udemy.concurrency;

public class LiveLockMain {

    public static void main(String[] args) {
        final Worker worker1 = new Worker("Worker 1", true);
        final Worker worker2 = new Worker("Worker 2", true);

        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker1.work(sharedResource, worker2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker2.work(sharedResource, worker1);
            }
        }).start();

    }
}
/*
 * Live lock issue:
 * similar to dead lock but instead of threads being blocked they're constantly active and usually waiting for all
 * the other threads to complete their tasks.When all the threads are waiting for others to complete, none of them
 * can actually progress.
 *
 * if t1 loops as it waits for t2 to complete, and t2 loops waiting for t1 to complete.
 * it could so happen that they both begin looping and are waiting for the other to complete and they never will.
 * t1 and t2 are not blocked though.
 *
 * Not really a one size fits all solution. Keep in mind that any time threads have to wait for other threads to
 * complete something and they don't block while they wait, there is a potential for a live lock.
 *
 * Another potential problem when using threads Slip Condition (a specific kind of race condition aka thread interference)
 * occurs when a thread can be suspended between reading a condition and acting on that condition
 * If we haven't synchronized the code properly the following can happen:
 * 1. T1 checks the status and gets OK, suspends
 * 2. T2 checks the status and gets OK, reads EOF from buffer, sets status to EOF, terminates
 * 3. T2 runs again, attempts to read from buffer, throws Exception/crashes
 *
 * Because the threads can interfere with each other when checking and setting the condition, T1 tried to do something
 * based on obsolete information, when it checked the stats it was OK.
 * By the time it acted on the condition it checked previously, the status had been updated by T2. T1 is unaware
 * and an error occurred.
 *
 * similar solution to this as other kinds of thread interference:
 * - Use synchronized blocks or locks to synchronize the critical sections of code.
 *
 * Overview:
 * - if code is already synchronized then the placement of the synchronization may be the problem
 * - when using multiple locks, the order in which the locks can be acquired can also result in slipped condition
 * */

/*Other info in threads and it's issues
*
* atomic action - cannot be suspended in the middle of being executed. Either it completes, or it doesn't happen at all
* println() is not an atomic action and a thread may be suspended at any point in the execution of a print() statement
* Once a thread starts to run an atomic action we can be confident that it won't be suspended until it has completed
* the action.
*
* Atomic actions:
* 1. Reading writing reference variables. ie. myObj1 = myObj2 is atomic, a thread cannot be suspended when executing this
* 2. Reading and Writing primitive variables, except long and double. ie. myInt = 10 atomic, myDouble = 1.2354 not atomic
* 3. Reading and Writing volatile variables, is atomic
*
* You may think that since we don't have to worry about thread interference with atomic actions, we don't need to
* synchronize them, but that isn't true.
* Because of the way Java manages memory it is possible to get memory consistency errors when multiple threads can read
* and write the same variable. Each thread has a CPU cache which can contain copies of values that are in main memory.
*
* Since it's faster to read from a cache this can improve performance of an app. There wouldn't be a problem if there
* was only one CPU but most computer have more than one CPU. When running an app, each thread may be running
* on a different CPU and each CPU has it's own cache. It's possible for values in the different caches to become out of
* sync with each other and with the values in the main memory - a memory consistency error.
*
* Even if writing a variable is atomic it's possible for a scenario where T1 gets value from memory and writes to it's
* own cache and when T2 reads the value that is outdated.
* Here, we can use volatile variables. non volatile variables aren't guaranteed to write an updated value back to memory.
* but volatile variables are written back to memory immediately. It guarantees that every time a variable reads from
* a volatile variable it will get the latest value.
*
* We can still get a race condition when more than one thread updates the value of a volatile variable when they
* execute non-atomic operations.
*
* volatile: commonly used with long and double to make these variables atomic
* Java 1.5 atomic subpackage in java.util.concurrent.atomic provides us with classes that we can use to ensure
* that reading and writing variables is atomic. There are times when using the volatile keyword will eliminate
* the need for synchronization but it would be nice if we could R+W variables without having to worry about
* thread interference or memory consistency errors, atomic subpackage does that in specific cases.
*
* Atomic classes are really meant to be used in situations when a values is being incremented or decremented.
* They're intended to be used when the code is using a loop counter, generating a sequence of numbers for some reason.
* */