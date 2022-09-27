package br.com.digital.apidigital.service;

import br.com.digital.apidigital.persistence.dto.ClientDto;
import br.com.digital.apidigital.persistence.entity.Client;
import br.com.digital.apidigital.persistence.mapper.ClientMapper;
import br.com.digital.apidigital.repository.ClientRepository;
import br.com.digital.apidigital.service.exception.ResourceNotFoundException;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ClientService {
    private final ClientRepository repository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository repository, ClientMapper clientMapper) {
        this.repository = repository;
        this.clientMapper = clientMapper;
    }

    public ClientDto save(ClientDto clientDto) {
        Client entity = clientMapper.toEntity(clientDto);
        return clientMapper.toDto(repository.save(entity));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public ClientDto findById(long id) {
        return clientMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<ClientDto> findByCondition(ClientDto clientDto, Pageable pageable) {
        Page<Client> entityPage = repository.findAll(pageable);
        List<Client> entities = entityPage.getContent();
        return new PageImpl<>(clientMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ClientDto update(ClientDto clientDto, Long id) {
        ClientDto data = findById(id);
        Client entity = clientMapper.toEntity(clientDto);
        BeanUtil.copyProperties(data, entity);
        return save(clientMapper.toDto(entity));
    }
}