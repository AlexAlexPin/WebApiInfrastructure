package com.pinin.alex.service.common;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
class MappingServiceImpl implements MappingService
{
    private final ModelMapper modelMapper;

    public MappingServiceImpl(@Autowired ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    @Override
    public <T> T map(Object o, Class<T> c)
    {
        return modelMapper.map(o, c);
    }

    @Override
    public <T> List<T> mapAll(List<?> objects, Class<T> c)
    {
        return objects.stream()
                .map(o -> map(o, c))
                .collect(Collectors.toList());
    }

    @Override
    public <TTo, TFrom> List<TTo> mapAll(List<? extends TFrom> objects, Class<TTo> c, Function<TFrom, TTo> mappingFunction)
    {
        return objects.stream()
                .map(mappingFunction)
                .collect(Collectors.toList());
    }
}
