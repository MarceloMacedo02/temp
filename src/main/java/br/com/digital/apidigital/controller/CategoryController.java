package br.com.digital.apidigital.controller;

import br.com.digital.apidigital.persistence.dto.CategoryDto;
import br.com.digital.apidigital.service.CategoryService;
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

@RequestMapping("/category")
@RestController
@Slf4j
@Api("category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("id") Long id) {
        CategoryDto category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(categoryService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<CategoryDto>> pageQuery(CategoryDto categoryDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CategoryDto> categoryPage = categoryService.findByCondition(categoryDto, pageable);
        return ResponseEntity.ok(categoryPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated CategoryDto categoryDto, @PathVariable("id") Long id) {
        categoryService.update(categoryDto, id);
        return ResponseEntity.ok().build();
    }
}