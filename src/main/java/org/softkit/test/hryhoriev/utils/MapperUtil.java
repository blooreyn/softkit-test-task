package org.softkit.test.hryhoriev.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class MapperUtil {
    private MapperUtil() {
    }

    public static <T> void mapEntityToDto(Object entity, T dto) {
        Map<String, Field> entityFieldNamesFieldsMap = Arrays.stream(entity.getClass().getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, field -> field));

        Arrays.stream(dto.getClass().getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .forEach(dtoField -> {
                    Field entityField = entityFieldNamesFieldsMap.get(dtoField.getName());
                    entityField.setAccessible(true);
                    try {
                        dtoField.set(dto, entityField.get(entity));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
