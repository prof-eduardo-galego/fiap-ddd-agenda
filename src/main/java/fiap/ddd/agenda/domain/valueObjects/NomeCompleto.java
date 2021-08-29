package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class NomeCompleto {
    @NonNull String nomeCompleto;

    public NomeCompleto(String nomeCompleto) {
        if (nomeCompleto.isEmpty()) {
            throw new IllegalArgumentException("Nome nao pode ser vazio");
        }
        if (nomeCompleto.length() > 150) {
            throw new IllegalArgumentException("Nome muito longa");
        }

        this.nomeCompleto = nomeCompleto;
    }
}