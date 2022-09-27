package br.com.digital.apidigital.persistence.dto;

import br.com.digital.apidigital.persistence.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel()
public class CategoryDto extends AbstractDto<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
    @JsonIgnore
    private List<Product> products;



}