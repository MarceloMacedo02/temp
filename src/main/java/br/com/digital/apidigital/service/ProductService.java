package br.com.digital.apidigital.service;

import br.com.digital.apidigital.persistence.dto.ProductDto;
import br.com.digital.apidigital.persistence.entity.Product;
import br.com.digital.apidigital.persistence.mapper.ProductMapper;
import br.com.digital.apidigital.repository.ProductRepository;
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
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;
    }

    public ProductDto save(ProductDto productDto) {
        Product entity = productMapper.toEntity(productDto);
        return productMapper.toDto(repository.save(entity));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public ProductDto findById(long id) {
        return productMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<ProductDto> findByCondition(ProductDto productDto, Pageable pageable) {
        Page<Product> entityPage = repository.findAll(pageable);
        List<Product> entities = entityPage.getContent();
        return new PageImpl<>(productMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ProductDto update(ProductDto productDto, Long id) {
        ProductDto data = findById(id);
        Product entity = productMapper.toEntity(productDto);
        BeanUtil.copyProperties(data, entity);
        return save(productMapper.toDto(entity));
    }
}