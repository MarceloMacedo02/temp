package br.com.digital.apidigital.persistence.mapper;

import br.com.digital.apidigital.persistence.dto.ClientDto;
import br.com.digital.apidigital.persistence.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDto, Client> {
}