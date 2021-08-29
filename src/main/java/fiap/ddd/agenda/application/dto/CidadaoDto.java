package fiap.ddd.agenda.application.dto;

import lombok.Value;

@Value
public class CidadaoDto {
    private String nomeCompleto;
    private String cpf;

    public CidadaoDto(String nomeCompleto, String cpf) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

}
