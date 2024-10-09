package com.eventfy.Cadastro_Eventfy.mapper;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.InvocationTargetException;
@AllArgsConstructor
public class ModelMapperCustom {

    private final ModelMapper mapper;

    public <S, D> TypeMap<S, D> createTypeMap(Class<S> sourceType, Class<D> destinationType) {
        var result = this.mapper.getTypeMap(sourceType, destinationType);
        if (result == null) {
            result = this.mapper.createTypeMap(sourceType, destinationType);
        }
        return result;
    }

    public <S, D>  void addConverter(Class<? extends Converter<S, D>> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.mapper.addConverter(instantiateFrom(clazz));
    }

    public <T> T map(Object source, Class<T> destinationType) {
        if (source == null) {
            return null;
        }
        return this.mapper.map(source, destinationType);
    }

    public <T> List<T> map(List<?> list, Class<T> destinationType) {
        return list.stream().map(e -> map(e, destinationType)).collect(Collectors.toList());
    }

    public <T> T map(Object source, T destination) {
        this.mapper.map(source, destination);
        return destination;
    }

    @SneakyThrows
    private <S,D> Converter<S, D> instantiateFrom(Class<? extends Converter<S, D>> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return clazz == null ? null : clazz.getDeclaredConstructor().newInstance();
    }

}