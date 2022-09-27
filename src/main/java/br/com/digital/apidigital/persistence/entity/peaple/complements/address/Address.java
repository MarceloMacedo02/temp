package br.com.digital.apidigital.persistence.entity.peaple.complements.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    @Column(length = 255)
    @JsonProperty("rua")
    private String street;

    @Column(length = 80)
    @JsonProperty("nro")
    private String nro;

    @Column(length = 120)
    @JsonProperty("complementos")
    private String complement;

    @Column(length = 100)
    @JsonProperty("bairro")
    private String neighborhood;

    @Column(length = 120)
    @JsonProperty("cidade")
    private String city;

    @Column(length = 80)
    @JsonProperty("cep")
    private String zip;

    @Column(length = 4)
    @JsonProperty("estado")
    private String state;
}
