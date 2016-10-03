package com.ssa.ironyard.fitness.model;

import java.util.Objects;

public interface DomainObject extends Cloneable {

        Integer getId();
        default boolean isLoaded()
        {
            return false;
        }

        default boolean deeplyEquals(DomainObject other)
        {
            if (! Objects.deepEquals(this, other))
                return false;
            return true;
        }
      
        @Override
        String toString();

        @Override
        boolean equals(Object other);

        @Override
        int hashCode();


        DomainObject clone();
    }
