package com.retronic.remoting.utils;

import com.retronic.remoting.converter.IDtoConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ConverterUtils {

    private ConverterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <E, T> List<E> convertToDTOs(IDtoConverter<E, T> dtoConverter, T[] t) {
        if (t != null) {
            return convertToDTOs(dtoConverter, Arrays.asList(t));
        }

        return Collections.emptyList();
    }

    public static <E, T> List<E> convertToDTOs(IDtoConverter<E, T> dtoConverter, Iterable<T> t) {
        if (t != null) {
            List<E> dtos = new ArrayList<>();

            for (T t1 : t) {
                dtos.add(dtoConverter.convertToDto(t1));
            }

            return dtos;
        }

        return Collections.emptyList();
    }

    public static <E, T> List<T> convertToEntities(IDtoConverter<E, T> dtoConverter, Iterable<E> t) {
        if (t != null) {
            return StreamSupport.stream(t.spliterator(), true).map(dtoConverter::convertToEntity).collect(Collectors.<T>toList());
        }

        return Collections.emptyList();
    }
}
