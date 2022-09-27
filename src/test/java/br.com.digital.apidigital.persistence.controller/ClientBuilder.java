package br.com.digital.apidigital.persistence.controller;

import br.com.digital.apidigital.persistence.dto.ClientDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class ClientBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ClientDto getDto() {
        ClientDto dto = new ClientDto();
        dto.setId(1L);
        return dto;
    }
}