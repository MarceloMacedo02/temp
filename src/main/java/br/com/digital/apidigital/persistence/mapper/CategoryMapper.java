package br.com.digital.apidigital.persistence.mapper;

import br.com.digital.apidigital.persistence.dto.CategoryDto;
import br.com.digital.apidigital.persistence.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {
}