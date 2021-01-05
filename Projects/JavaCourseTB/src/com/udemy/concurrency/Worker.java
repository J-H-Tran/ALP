package com.udemy.concurrency;

public class Worker {
    private String name;
    private boolean isActive;

    public Worker(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public synchronized void work(SharedResource sharedResource, Worker otherWorker) {
        while (isActive) {
            // checks to see if it owns the shared resource, if it doesn't wait() and check again
            if (sharedResource.getOwner() != this) {
                try {
                    wait(1000);
                } catch (InterruptedException e) {

                }
                continue;
            }
            // check to see whether the other thread is active, if it is give shared resource to it
            if (otherWorker.isActive()) {
                System.out.println(getName()+" : give the resource to the worker "+otherWorker.getName());
                sharedResource.setOwner(otherWorker);
                continue;
            }
            // use shared resource if it owns it & other thread is inactive
            System.out.println(getName()+": working on the common resource");
            isActive = false;
            sharedResource.setOwner(otherWorker);
        }
    }
}
