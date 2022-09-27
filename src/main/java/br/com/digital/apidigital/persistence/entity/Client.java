package br.com.digital.apidigital.persistence.entity;



import br.com.digital.apidigital.persistence.entity.interfaces.BaseEntity;
import br.com.digital.apidigital.persistence.entity.peaple.complements.address.Address;
import br.com.digital.apidigital.persistence.entity.peaple.complements.contact.Contact;
import br.com.digital.apidigital.persistence.entity.peaple.complements.contact.Phone;
import br.com.digital.apidigital.persistence.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "tb_client")
@NoArgsConstructor
//@MappedSuperclass
public class Client implements Serializable, BaseEntity {
    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("fotografia")
    private String photo;


    @JsonProperty("endereco_principal")
    @Embedded
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Column(name = "id", nullable = true, insertable = true, updatable = true)
    private Address address = new Address();


    @JsonProperty("enderecos")
    @Embedded
    @ElementCollection
    @CollectionTable(
            name = "tb_client_addresss",
            joinColumns = @JoinColumn(name = "id")
    )
    private List<Address> addresss = new ArrayList<>();

    @JsonProperty("tipo_pessoa")
    private TipoPessoa typePerson = TipoPessoa.FISICA;

    @JsonProperty("contato")
    @Embedded
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Column(name = "id", nullable = true, insertable = true, updatable = true)
    private Contact contact = new Contact();

    @JsonProperty("contatos")
    @Embedded
    @ElementCollection
    @CollectionTable(
            name = "tb_client_contact",
            joinColumns = @JoinColumn(name = "id")
    )
    private List<Contact> contacts = new ArrayList<>();

    @JsonProperty("telefones")
    @Embedded
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Column(name = "id", nullable = true, insertable = true, updatable = true)
    private Phone phones = new Phone();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty("usuarios")
    @NotNull(message = "Usuário não pode ser nulo")

    private User user;

}
