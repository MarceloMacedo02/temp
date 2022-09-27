package br.com.digital.apidigital.persistence.dto;

import br.com.digital.apidigital.persistence.entity.Category;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@ApiModel()
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends AbstractDto<Long> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    @Size(max = 255)
    private String description;
    private Double price;
    private String imgUrl;
    private Instant date;
    private List<Category> categories;


}