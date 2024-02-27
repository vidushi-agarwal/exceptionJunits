package org.example;

public class OutOfCapacityException extends Exception {
    public OutOfCapacityException(String registeredInstancesIsOutOfCapacity) {
        super(registeredInstancesIsOutOfCapacity);
    }
}
