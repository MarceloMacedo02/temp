package br.com.digital.apidigital.persistence.entity.peaple.complements.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Contact {

    @JsonProperty("primeiro_nome")
    private String firstName;


    @JsonProperty("ultimo_nome")
    private String lastName;

    @JsonProperty("nick_name")
    private String nickName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("site")
    private String site;

    @JsonProperty("instagram")
    private String instagram;

    @JsonProperty("telegram")
    private String telegram;

    @JsonProperty("email_alternative")
    private String emailAlternative;




}
