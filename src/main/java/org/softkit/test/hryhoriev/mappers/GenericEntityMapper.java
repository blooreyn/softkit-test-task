package org.softkit.test.hryhoriev.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public abstract class GenericEntityMapper<E, D> implements IEntityMapper<E, D> {
    private Map<String, Field> entityFieldNamesFieldsMap;
    private List<Field> daoFields;

    protected GenericEntityMapper(Class<E> entityType, Class<D> daoType, List<String> ignoredFieldsList) {
        initFields(entityType, daoType, ignoredFieldsList);
    }

    @Override
    public D mapEntityToDto(E entity, D dto) {
        daoFields.forEach(dtoField -> {
            try {
                dtoField.setAccessible(true);
                Field entityField = entityFieldNamesFieldsMap.get(dtoField.getName());
                entityField.setAccessible(true);

                dtoField.set(dto, entityField.get(entity));
            } catch (IllegalAccessException e) {
                log.error("Fail to map entity - ({}) to dto - ({})", entity.getClass(), dto.getClass());
            }
        });
        return dto;
    }

    private void initFields(Class<E> entityType, Class<D> daoType, List<String> ignoredFieldsList) {
        entityFieldNamesFieldsMap = Arrays.stream(entityType.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toMap(Field::getName, field -> field));

        daoFields = Arrays.stream(daoType.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(field -> !ignoredFieldsList.contains(field.getName()))
                .collect(Collectors.toList());
    }

}
