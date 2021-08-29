package fiap.ddd.agenda.domain.valueObjects;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class ReservaId {
    @NonNull UUID id;

    public ReservaId() {
        this.id = UUID.randomUUID();
    }
}
