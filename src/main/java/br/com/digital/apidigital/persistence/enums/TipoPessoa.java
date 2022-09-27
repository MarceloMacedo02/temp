package br.com.digital.apidigital.persistence.enums;

public enum TipoPessoa {
    JURIDICA(0, "Jurídica"),
    FISICA(1, "Física");
    private int id;
    private String name;

    TipoPessoa(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
