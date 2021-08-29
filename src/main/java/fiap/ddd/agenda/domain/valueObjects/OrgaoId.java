package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class OrgaoId {
    @NonNull Integer id;

    public OrgaoId(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id do orgao nao pode ser menor que zero");
        }

        this.id = id;
    }
}
