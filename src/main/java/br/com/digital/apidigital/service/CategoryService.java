package br.com.digital.apidigital.service;

import br.com.digital.apidigital.persistence.dto.CategoryDto;
import br.com.digital.apidigital.persistence.entity.Category;
import br.com.digital.apidigital.persistence.mapper.CategoryMapper;
import br.com.digital.apidigital.repository.CategoryRepository;
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
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDto save(CategoryDto categoryDto) {
        Category entity = categoryMapper.toEntity(categoryDto);
        return categoryMapper.toDto(repository.save(entity));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public CategoryDto findById(long id) {
        return categoryMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<CategoryDto> findByCondition(CategoryDto categoryDto, Pageable pageable) {
        Page<Category> entityPage = repository.findAll(pageable);
        List<Category> entities = entityPage.getContent();
        return new PageImpl<>(categoryMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public CategoryDto update(CategoryDto categoryDto, Long id) {
        CategoryDto data = findById(id);
        Category entity = categoryMapper.toEntity(categoryDto);
        BeanUtil.copyProperties(data, entity);
        return save(categoryMapper.toDto(entity));
    }
}