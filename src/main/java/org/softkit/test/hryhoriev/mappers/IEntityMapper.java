package org.softkit.test.hryhoriev.mappers;

public interface IEntityMapper<E, D> {
    D mapEntityToDto(E entity, D dto);
}
