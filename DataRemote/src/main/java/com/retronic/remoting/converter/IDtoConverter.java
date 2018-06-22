package com.retronic.remoting.converter;

public interface IDtoConverter<E, T> {

    E convertToDto(T t);

    T convertToEntity(E e);
}
