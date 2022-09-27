package br.com.digital.apidigital.persistence.dto;

import br.com.digital.apidigital.persistence.annotation.CheckMobile;
import br.com.digital.apidigital.persistence.entity.User;
import br.com.digital.apidigital.persistence.entity.peaple.complements.address.Address;
import br.com.digital.apidigital.persistence.entity.peaple.complements.contact.Contact;
import br.com.digital.apidigital.persistence.entity.peaple.complements.contact.Phone;
import br.com.digital.apidigital.persistence.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@ApiModel()
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto extends AbstractDto<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    @JsonProperty("fotografia")
    private String photo;

    @JsonProperty("endereco_principal")
    private Address address;

    @JsonProperty("enderecos")
    private List<Address> addresss;

    @JsonProperty("tipo_pessoa")
    private TipoPessoa typePerson;

    @JsonProperty("contato")
    private Contact contact;

    @JsonProperty("contatos")
    private List<Contact> contacts;

    @JsonProperty("telefones")
    @CheckMobile
    private Phone phones;

    @JsonProperty("usuario")
    private User user;

}