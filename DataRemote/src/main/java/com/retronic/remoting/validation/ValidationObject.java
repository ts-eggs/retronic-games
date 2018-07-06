package com.retronic.remoting.validation;

public interface ValidationObject<T> {

    T getDto();

    boolean isCreate();

}
