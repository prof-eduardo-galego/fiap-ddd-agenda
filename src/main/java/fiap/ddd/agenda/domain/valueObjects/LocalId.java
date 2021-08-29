package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class LocalId {
    @NonNull Integer id;

    public LocalId(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id do local nao pode ser menor que zero");
        }

        this.id = id;
    }
}
