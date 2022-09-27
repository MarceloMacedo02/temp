package br.com.digital.apidigital.persistence.mapper;

import br.com.digital.apidigital.persistence.dto.ProductDto;
import br.com.digital.apidigital.persistence.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {
}