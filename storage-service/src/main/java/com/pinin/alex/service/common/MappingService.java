package com.pinin.alex.service.common;

import java.util.List;
import java.util.function.Function;

public interface MappingService
{
    <T> T map(Object o, Class<T> c);

    <T> List<T> mapAll(List<?> objects, Class<T> c);

    <TTo, TFrom> List<TTo> mapAll(List<? extends TFrom> objects, Class<TTo> c, Function<TFrom, TTo> mappingFunction);
}
