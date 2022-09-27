package br.com.digital.apidigital.controller;

import br.com.digital.apidigital.persistence.dto.ClientDto;
import br.com.digital.apidigital.service.ClientService;
import br.com.digital.apidigital.service.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/clients")
@RestController
@Slf4j
@Api("clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated ClientDto clientDto) {
        clientService.save(clientDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable("id") Long id) {
        ClientDto client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(clientService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<ClientDto>> pageQuery(ClientDto clientDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ClientDto> clientPage = clientService.findByCondition(clientDto, pageable);
        return ResponseEntity.ok(clientPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ClientDto clientDto, @PathVariable("id") Long id) {
        clientService.update(clientDto, id);
        return ResponseEntity.ok().build();
    }
}