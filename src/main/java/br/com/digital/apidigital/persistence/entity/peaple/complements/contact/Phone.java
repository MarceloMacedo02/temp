package br.com.digital.apidigital.persistence.entity.peaple.complements.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class Phone {

    @JsonProperty("telefone_comercial")
    private String commercialPhone;

    @JsonProperty("ramal")
    private String extensionPhone;

    @JsonProperty("telefone_residencial")
    private String homePhone;

    @JsonProperty("tefone_celular")
    private String mobilePhone;

    @JsonProperty("is_WathsApp")
    private Boolean isWathsApp=true;

    @JsonProperty("telefone_celular_2")
    private String mobilePhone2;




}
