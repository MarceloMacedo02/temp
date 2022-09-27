package br.com.digital.apidigital.persistence.controller;

import br.com.digital.apidigital.persistence.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class ProductBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ProductDto getDto() {
        ProductDto dto = new ProductDto();
        dto.setId(1L);
        return dto;
    }
}