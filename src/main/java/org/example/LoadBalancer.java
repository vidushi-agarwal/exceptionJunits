package org.example;

import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Set;

public class LoadBalancer {
    private static final int MAXIMUM_INSTANCES = 10;

    Set<String> registeredInstances;

    public LoadBalancer() {
        this.registeredInstances = new HashSet<>();
    }

    public boolean registerInstance(String instance) throws Exception {
            if(instance.equals(null) || instance.equals(""))
                throw new InvalidPropertiesFormatException("instance is invalid");
            if(registeredInstances.size() == MAXIMUM_INSTANCES){
                throw new OutOfCapacityException("registeredInstances is out of capacity ");

            } else if( registeredInstances. contains(instance)){
                throw new DuplicateException("The instance "+instance+" is already registered");

            } else {
                registeredInstances.add(instance);
                return true;
            }
        }



}
