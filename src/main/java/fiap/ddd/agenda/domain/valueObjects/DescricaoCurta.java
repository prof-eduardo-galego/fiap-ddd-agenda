package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class DescricaoCurta {
    @NonNull String descricao;

    public DescricaoCurta(String descricao) {
        if (descricao.isEmpty()) {
            throw new IllegalArgumentException("Descricao nao pode ser vazio");
        }
        if (descricao.length() > 50) {
            throw new IllegalArgumentException("Descricao muito longa");
        }

        this.descricao = descricao;
    }
}
