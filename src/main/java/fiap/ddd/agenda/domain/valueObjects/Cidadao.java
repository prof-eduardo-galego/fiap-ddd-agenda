package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class Cidadao {
    @NonNull NomeCompleto nomeCompleto;
    @NonNull CPF cpf;

    public Cidadao(NomeCompleto nomeCompleto, CPF cpf) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }
}
