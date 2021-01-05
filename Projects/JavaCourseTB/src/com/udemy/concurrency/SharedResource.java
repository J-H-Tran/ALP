package com.udemy.concurrency;

public class SharedResource {
    private Worker owner;

    public SharedResource(Worker owner) {
        this.owner = owner;
    }

    public Worker getOwner() {
        return owner;
    }
    // won't synchronize getter because we're changing data in setter and we want it to be synchronized
    // we don't want any thread interference when we're setting which thread is currently using the resource
    public synchronized void setOwner(Worker owner) {
        this.owner = owner;
    }

}
