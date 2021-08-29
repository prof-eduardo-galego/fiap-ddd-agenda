package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class ServicoId {
    @NonNull Integer id;

    public ServicoId(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id do servico nao pode ser menor que zero");
        }

        this.id = id;
    }
}
