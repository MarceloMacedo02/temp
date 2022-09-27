package br.com.digital.apidigital.persistence.controller;

import br.com.digital.apidigital.persistence.dto.CategoryDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class CategoryBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static CategoryDto getDto() {
        CategoryDto dto = new CategoryDto();
        dto.setId(1L);
        return dto;
    }
}